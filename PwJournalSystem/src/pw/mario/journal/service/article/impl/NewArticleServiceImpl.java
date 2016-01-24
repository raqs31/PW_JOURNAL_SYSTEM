package pw.mario.journal.service.article.impl;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import lombok.extern.log4j.Log4j;
import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.dao.TagDAO;
import pw.mario.journal.dao.UserDAO;
import pw.mario.journal.dao.article.ArticleDAO;
import pw.mario.journal.dao.article.ArticleVersionDao;
import pw.mario.journal.dao.dictionary.DictionaryDAO;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.ArticleVersion;
import pw.mario.journal.model.Department;
import pw.mario.journal.model.Dictionary;
import pw.mario.journal.model.SystemRole;
import pw.mario.journal.model.Tag;
import pw.mario.journal.model.User;
import pw.mario.journal.model.dictionaries.ArticleStatus;
import pw.mario.journal.qualifiers.DictionaryType;
import pw.mario.journal.qualifiers.enums.DictType;
import pw.mario.journal.service.FileManagerService;
import pw.mario.journal.service.article.NewArticleService;
import pw.mario.journal.util.files.FileHandler;

@Log4j
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class NewArticleServiceImpl implements NewArticleService {
	@Inject UserDAO userDao;
	@Inject ArticleDAO articleDao;
	@Inject ArticleVersionDao versionDao;
	@Inject FileManagerService fileManager;
	@Inject @DictionaryType(DictType.ARTICLE_STATUS) DictionaryDAO<ArticleStatus> dictionary;
	@Inject TagDAO tagDao;
	
	@Override
	public List<User> getUsers(Department d) {
		return userDao.getUsers(d, SystemRole.Roles.AUTHOR);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Article createArticle(Article a, FileHandler tmp) throws PerformActionException {
		log.debug("Create article " + a + " START");
		
		ArticleVersion newVersion = versionDao.createNewVersion(a);
		a.getVersions().add(newVersion);
		tmp.setFileName(versionDao.createArticleName(newVersion));
		if (tmp != null)
			newVersion.setAttachement(fileManager.saveFile(tmp));
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
