package pw.mario.journal.service.article.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import lombok.extern.log4j.Log4j;
import pw.mario.common.action.AbstractActionFactory;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.api.Refreshable;
import pw.mario.common.exception.LockException;
import pw.mario.common.exception.PerformActionException;
import pw.mario.common.exception.RouteActionException;
import pw.mario.common.util.file.FileHandler;
import pw.mario.journal.dao.RulesDao;
import pw.mario.journal.dao.UserDAO;
import pw.mario.journal.dao.article.ArticleDAO;
import pw.mario.journal.dao.article.ArticleVersionDao;
import pw.mario.journal.data.ExecutionContext;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.ArticleAcceptor;
import pw.mario.journal.model.ArticleVersion;
import pw.mario.journal.model.Rule;
import pw.mario.journal.model.User;
import pw.mario.journal.qualifiers.Button;
import pw.mario.journal.qualifiers.Rules;
import pw.mario.journal.service.FileManagerService;
import pw.mario.journal.service.article.ArticleOperationService;
import pw.mario.journal.validator.ValidationFactory;

@Log4j
@Stateless
@Transactional(value=TxType.REQUIRED, rollbackOn=Exception.class)
public class ArticleOperationServiceImpl implements ArticleOperationService {

	@Inject private ArticleDAO articleDao;
	@Inject private ArticleVersionDao versionDao;
	@Inject private FileManagerService fileManager;
	@Inject private RulesDao ruleDao;
	@Inject private UserDAO userDao;
	@Inject @Button private AbstractActionFactory<ButtonAction, Article> actionFactory;
	@Inject @Rules private AbstractActionFactory<ButtonAction, Article> ruleActionFactory;
	@Inject private ValidationFactory validationFactory;
	
	@Override
	public Article getArticle(Long id, User u) {
		Article article = articleDao.getArticle(id);
		articleDao.refresh(article);
		articleDao.loadDetails(article);
		return article;
	}

	@Override
	public void addNewVersion(Article a, FileHandler handler) throws PerformActionException {
		try {
			log.debug(a.getObjectVersionNumber());
			a = articleDao.getLockedArticle(a, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			ArticleVersion newVersion = versionDao.createNewVersion(a);
			
			handler.setFileName(versionDao.createArticleName(newVersion));
			newVersion.setAttachement(fileManager.saveFile(handler));
			handler.getFile().delete();
		
			a.getVersions().add(newVersion);
			a.getHistory().add(articleDao.addArticleHistory(a, newVersion));
			
			articleDao.save(a);
			
			a.getVersions().sort(( v1, v2)-> v2.getVersionNum().compareTo(v1.getVersionNum()));
			log.debug("Finish create article ID: " + a.getArticleId());
		} catch (LockException ex) { 
			log.warn(ex.getMessage(), ex);
			throw ex;
		} catch (OptimisticLockException e) {
			log.warn(e.getMessage(), e);
			throw new PerformActionException("Zmienił się stan artykułu, nie można wykonać aktualizacji");
		}
	}
		
	@Override
	public void addAcceptorVersion(Article a, User acceptor, FileHandler handler) throws PerformActionException {
		try {
			log.debug(a.getObjectVersionNumber());
			a = articleDao.getLockedArticle(a, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			ArticleVersion newVersion = versionDao.createNewVersion(a);
			
			handler.setFileName(versionDao.createArticleName(newVersion));
			newVersion.setAttachement(fileManager.saveFile(handler));
			handler.getFile().delete();
		
			a.getVersions().add(newVersion);
			a.getHistory().add(articleDao.addArticleHistory(a, newVersion));

			versionDao.save(newVersion);
			
			a.getArticleAcceptor(acceptor).setVersion(newVersion);
			
			articleDao.save(a);
			
			a.getVersions().sort(( v1, v2)-> v2.getVersionNum().compareTo(v1.getVersionNum()));
			log.debug("Finish create article ID: " + a.getArticleId());
		} catch (LockException ex) { 
			log.warn(ex.getMessage(), ex);
			throw ex;
		} catch (OptimisticLockException e) {
			log.warn(e.getMessage(), e);
			throw new PerformActionException("Zmienił się stan artykułu, nie można wykonać aktualizacji");
		}
	}
	@Override
	public List<Rule> getAvailableSteps(Article a, User u) {
		return articleDao.getAvailableRules(a, u);
	}

	@Override
	public Collection<ButtonAction> getActions(Article a, User u, Refreshable toRefresh) {
		Collection<ButtonAction> actions = actionFactory.getActions(a, u, toRefresh);
		actions.addAll(ruleActionFactory.getActions(a, u, toRefresh));
		
		List<ButtonAction> toReturn = new LinkedList<>(actions);
		toReturn.sort((b1, b2) -> b1.getValue().compareTo(b2.getValue()));
		
		return actions;
	}

	@Override
	public void execute(ExecutionContext ctx) throws RouteActionException {
		checkExecutionContext(ctx);
		try {
			Article toProcess = articleDao.getLockedArticle(ctx.getArticle(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			ctx.setArticle(toProcess);
			
			validate(ctx);
			if (!ctx.isValid()) {
				if (ctx.getErrors() == null)
					throw new RouteActionException("Kontekst jest nieprawidłowy a nie znalazło żadnego błędu", "Spróbuj ponownie lub zgłoś błąd");
				ctx.getErrors().forEach(err -> FacesContext.getCurrentInstance().addMessage(null, err.convert()));
				throw new RouteActionException("Z powodu błędów walidacji nie przeprocesowano artykułu");
			}
			
			setExecutionParameter(toProcess, ctx);
			toProcess.setStatus(ctx.getRule().getToStatus());
			toProcess.getHistory().add(articleDao.addArticleHistory(toProcess, ctx));
			
			articleDao.save(toProcess);
		} catch (OptimisticLockException e) {
			throw new RouteActionException("W międzyczasie artykuł został zmodyfikowany");
		} catch (LockException e) {
			throw new RouteActionException("W międzyczasie artykuł został zmodyfikowany");
		} catch (PerformActionException e) {
			throw new RouteActionException(e.getMessage());
		} catch (Exception e) {
			log.fatal("Unexpected error", e);
			throw new RouteActionException("Wystąpił nieoczekiwany błąd podczas procesowania");
		}
	}
	@Transactional(value=TxType.SUPPORTS)
	private void checkExecutionContext(ExecutionContext ctx) throws RouteActionException {
		if (ctx == null || ctx.getArticle() == null || ctx.getRule() == null) {
			
			log.fatal("RouteActionException: ctx: " + ctx);
			log.fatal("RouteActionException: article: " + ctx != null ? ctx.getArticle() : null);
			log.fatal("RouteActionException: rule: " + ctx != null ? ctx.getUser() : null);
			throw new RouteActionException("Nie udało się wykonać reguły", "Kontekst jest nieporawnie zainicjowany");
		}
	}

	@Override
	public void deleteArticle(Article a) {
		articleDao.deleteArticle(a);
	}

	@Override
	public List<User> getAvailableManagements(Long articleId) {
		return userDao.getAvailableManagers(articleId);
	}

	@Override
	public List<User> getAvailableAcceptors(Long articleId) {
		return userDao.getAvailableAcceptors(articleId);
	}

	@Override
	public Rule getRule(Long ruleId) {
		return ruleDao.getRule(ruleId);
	}
	
	private void validate(ExecutionContext ctx) throws RouteActionException {
		validationFactory
			.getValidations(ctx.getRule())
			.forEach(av -> av.validate(ctx));
	}
	
	private void setExecutionParameter(Article article, ExecutionContext ctx) throws RouteActionException {
		Rule rule = ctx.getRule();
		
		if (rule.withUserAction()) {
			if (rule.getPickAcceptors()) {
				if (article.getAcceptors() == null)
					article.setAcceptors(new LinkedList<>());
				ctx.getAcceptors().forEach(
						u-> {
							ArticleAcceptor acc = new ArticleAcceptor();
							acc.setAcceptor(u);
							acc.setApply(false);
							acc.setArticle(article);
							article.getAcceptors().add(acc);
						}
				);
			}
			if (rule.getPickManager())
				article.setManagement(ctx.getManager());
		}
		
		if (rule.getClearManager())
			article.setManagement(null);
		if (rule.getClearAcceptors())
			article.getAcceptors().clear();
		if (rule.getSetAcceptorStatus()) {
			ArticleAcceptor acceptor = article.getArticleAcceptor(ctx.getUser());
			if (acceptor == null)
				throw new RouteActionException("Użytkownik wykonujący akcję nie jest aktualnym recenzentem");
			acceptor.setState(rule.getAcceptorStatus());
			acceptor.setApply(true);
		}
		
		if (rule.getApplyAcceptorsState()) {
			article.getAcceptors()
				.stream()
				.filter(acc -> acc.getApply() == false)
				.forEach(acc-> acc.setApply(true));
		}
	}
}
