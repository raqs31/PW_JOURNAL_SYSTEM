package pw.mario.journal.service.article.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.primefaces.model.UploadedFile;

import com.google.common.io.Files;

import lombok.Cleanup;
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
import pw.mario.journal.service.article.NewArticleService;
import pw.mario.journal.util.files.FileHandler;
import pw.mario.journal.util.files.FileUtils;

@Log4j
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class NewArticleServiceImpl implements NewArticleService {
	@Inject UserDAO userDao;
	@Inject ArticleDAO articleDao;
	@Inject ArticleVersionDao versionDao;
	@Inject @DictionaryType(DictType.ARTICLE_STATUS) DictionaryDAO<ArticleStatus> dictionary;
	@Inject TagDAO tagDao;
	
	@Override
	public List<User> getUsers(Department d) {
		return userDao.getUsers(d, SystemRole.Roles.AUTHOR);
	}

	@Override
	public void createArticle(Article a, FileHandler tmp) throws PerformActionException {
		saveFile(tmp, "TestHUHE");
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
		art.setAuthors(new TreeSet<>());
		art.setTagList(new TreeSet<>());
		art.setVersions(new LinkedList<>());
		art.setYear(currentDate.get(Calendar.YEAR));
		art.setMonth(currentDate.get(Calendar.MONTH));
		art.setDay(currentDate.get(Calendar.DAY_OF_MONTH));
		
		return art;
	}
	
	private void saveFile(FileHandler file, String fileName) throws PerformActionException {
		File toSave = new File("C:/Programy/" + file.getFullName()); 

		try {
			if (!toSave.exists())
				toSave.createNewFile();
			@Cleanup InputStream input = new FileInputStream(file.getFile());
			@Cleanup OutputStream output = new FileOutputStream(toSave);
			FileUtils.copy(input, output);
			
		} catch (IOException e) {
			log.error("Error in saveFile", e);
			throw new PerformActionException(e.getMessage());
		} finally {
			file.getFile().delete();
		}
	}
}
