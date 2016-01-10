package pw.mario.journal.service.article.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pw.mario.journal.dao.TagDAO;
import pw.mario.journal.dao.UserDAO;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.ArticleVersion;
import pw.mario.journal.model.Tag;
import pw.mario.journal.model.User;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.ArticleManager;
import pw.mario.journal.service.article.ArticleService;

@Stateless
@ArticleManagement(ArticleManager.STUB)
public class StubArticleManagerService implements ArticleService {
	@Inject private UserDAO userDao;
	@Inject private TagDAO tagDao;

	private static final long serialVersionUID = 1L;
	private List<Article> articles;
	
	@PostConstruct
	private void init() {
		User u = userDao.getUsersList().get(0);
		Set<Tag> tags = new HashSet<>(tagDao.getTagList());
		
		
		for (int i = 0; i < 25; i++) {
			Article a = new Article();
			a.setArticleId(i);
			a.setAuthors(new HashSet<>());
			a.getAuthors().add(u);
			a.setManagement(u);
			a.setTagList(tags);
			a.setName("ArticleStub" + i);
			a.setDescription("DescriptionStub" + i);
			addArticle(a);
		}
	}
	
	@Override
	public Article addArticle(Article a) {
		getArticles(null).add(a);
		return a;
	}

	@Override
	public Article updateArticle(Article a) {
		return a;
	}

	@Override
	public void removeArticle(Article a) {
		getArticles(null).remove(a);
	}

	@Override
	public List<Article> getArticles(User u) {
		if (articles == null)
			articles = new LinkedList<>();
		return articles;
	}

	@Override
	public String[] rolesAllowed() {
		return new String[]{};
	}

}
