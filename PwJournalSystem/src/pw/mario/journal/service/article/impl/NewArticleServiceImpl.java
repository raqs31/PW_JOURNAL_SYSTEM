package pw.mario.journal.service.article.impl;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import lombok.extern.log4j.Log4j;
import pw.mario.common.exception.PerformActionException;
import pw.mario.common.util.file.FileHandler;
import pw.mario.journal.dao.article.ArticleDAO;
import pw.mario.journal.dao.article.ArticleVersionDao;
import pw.mario.journal.dao.common.TagDAO;
import pw.mario.journal.dao.common.UserDAO;
import pw.mario.journal.dao.dictionary.ArticleStatusDao;
import pw.mario.journal.model.article.Article;
import pw.mario.journal.model.article.ArticleVersion;
import pw.mario.journal.model.common.Department;
import pw.mario.journal.model.common.SystemRole;
import pw.mario.journal.model.common.Tag;
import pw.mario.journal.model.common.User;
import pw.mario.journal.model.dictionary.ArticleStatus;
import pw.mario.journal.model.dictionary.Dictionary;
import pw.mario.journal.service.article.NewArticleService;
import pw.mario.journal.service.common.FileManagerService;

@Log4j
@Stateless
public class NewArticleServiceImpl implements NewArticleService {
	@Inject UserDAO userDao;
	@Inject ArticleDAO articleDao;
	@Inject ArticleVersionDao versionDao;
	@Inject FileManagerService fileManager;
	@Inject ArticleStatusDao<ArticleStatus> dictionary;
	@Inject TagDAO tagDao;
	
	@Override
	public List<User> getUsers(Department d) {
		return userDao.getUsers(d, SystemRole.Roles.AUTHOR);
	}

	@Override
	@Transactional(value=TxType.REQUIRED, rollbackOn=Exception.class)
	public Article createArticle(Article a, FileHandler tmp) throws PerformActionException {
		log.debug("Create article " + a + " START");
		
		if (tmp != null) {
			ArticleVersion newVersion = versionDao.createNewVersion(a);
			a.getVersions().add(newVersion);
			tmp.setFileName(versionDao.createArticleName(newVersion));
			newVersion.setAttachement(fileManager.saveFile(tmp));
		}

		a.setStatus(dictionary.getInitialProcess());
		a = articleDao.addArticle(a);
		
		log.debug("Finish create article ID: " + a.getArticleId());
		
		return a;
	}
	
	@Override
	public List<Dictionary> getArticlesStatuses() {
		return null;
	}

	@Override
	public List<Tag> getTags() {
		return tagDao.getTagList();
	}

	@Override
	public Article initArticle() {
		Calendar currentDate = Calendar.getInstance();
		Article art = new Article();
		art.setVersions(new LinkedList<>());
		art.setYear(currentDate.get(Calendar.YEAR));
		art.setMonth(currentDate.get(Calendar.MONTH));
		art.setDay(currentDate.get(Calendar.DAY_OF_MONTH));
		
		return art;
	}
}
