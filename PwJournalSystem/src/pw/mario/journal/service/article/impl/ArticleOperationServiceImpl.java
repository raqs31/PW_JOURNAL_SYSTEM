package pw.mario.journal.service.article.impl;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.primefaces.model.UploadedFile;

import lombok.extern.log4j.Log4j;
import pw.mario.common.action.AbstractActionFactory;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.dao.article.ArticleDAO;
import pw.mario.journal.dao.article.ArticleVersionDao;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.ArticleVersion;
import pw.mario.journal.model.Rule;
import pw.mario.journal.model.User;
import pw.mario.journal.qualifiers.Button;
import pw.mario.journal.service.FileManagerService;
import pw.mario.journal.service.article.ArticleOperationService;
import pw.mario.journal.util.files.FileHandler;

@Log4j
@Stateless
public class ArticleOperationServiceImpl implements ArticleOperationService {

	@Inject private ArticleDAO articleDao;
	@Inject private ArticleVersionDao versionDao;
	@Inject private FileManagerService fileManager;
	@Inject @Button AbstractActionFactory<ButtonAction, Article> actionFactory;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Article getArticle(Long id, User u) {
		Article article = articleDao.getArticle(id);
		articleDao.loadDetails(article);
		return article;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addNewVersion(Article a, UploadedFile newFile) throws PerformActionException {
		
		ArticleVersion newVersion = versionDao.createNewVersion(a);
		String fileName = versionDao.createArticleName(newVersion);
		
		
		try {
			newVersion.setAttachement(fileManager.saveFile(newFile, fileName));
		} catch (PerformActionException e) {
			throw e;
		}
		versionDao.save(newVersion);
		a.getVersions().add(newVersion);
		log.debug("Finish create article ID: " + a.getArticleId());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addNewVersion(Article a, FileHandler handler) throws PerformActionException {
		a.setVersions(versionDao.getVersions(a));
		ArticleVersion newVersion = versionDao.createNewVersion(a);
		
		handler.setFileName(versionDao.createArticleName(newVersion));
		newVersion.setAttachement(fileManager.saveFile(handler));
		handler.getFile().delete();

		versionDao.save(newVersion);
		
		a.getVersions().add(newVersion);
		a.getVersions().sort(( v1, v2)-> v2.getVersionNum().compareTo(v1.getVersionNum()));
		
		log.debug("Finish create article ID: " + a.getArticleId());
	}

	@Override
	public List<Rule> getAvailableSteps(Article a, User u) {
		return articleDao.getAvailableRules(a, u);
	}

	@Override
	public Collection<ButtonAction> getActions(Article a, User u) {
		return actionFactory.getActions(a, u);
	}

}
