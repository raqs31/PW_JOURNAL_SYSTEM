package pw.mario.journal.service.article.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import pw.mario.journal.dao.ArticleDAO;
import pw.mario.journal.dao.DictionaryDAO;
import pw.mario.journal.dao.TagDAO;
import pw.mario.journal.dao.UserDAO;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.Department;
import pw.mario.journal.model.Dictionary;
import pw.mario.journal.model.Tag;
import pw.mario.journal.model.User;
import pw.mario.journal.qualifiers.DictionaryType;
import pw.mario.journal.qualifiers.enums.DictType;
import pw.mario.journal.service.article.NewArticleService;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class NewArticleServiceImpl implements NewArticleService {
	@Inject UserDAO userDao;
	@Inject ArticleDAO articleDao;
	@Inject @DictionaryType(DictType.ARTICLE_STATUS) DictionaryDAO dictionary;
	@Inject TagDAO tagDao;
	
	@Override
	public List<User> getUsersFromDepartment(Department d) {
		return userDao.getUsersWithDepartment(d);
	}

	@Override
	public void createArticle(Article a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Dictionary> getArticlesStatuses() {
		return dictionary.getDictionaries();
	}

	@Override
	public List<Tag> getTags() {
		return tagDao.getTagList();
	}

	@Override
	public Article initArticle() {
		Article art = new Article();
		art.setAuthors(new TreeSet<>());
		art.setTagList(new TreeSet<>());
		art.setVersions(new LinkedList<>());
		return art;
	}
	
	

}
