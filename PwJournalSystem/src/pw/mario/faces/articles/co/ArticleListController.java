package pw.mario.faces.articles.co;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import pw.mario.faces.articles.model.ArticlesTab;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.ArticleTab;

@Log4j
@Named
@NoArgsConstructor
@ViewScoped
public class ArticleListController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject @ArticleTab private Instance<ArticlesTab> articleTabsModel;
	@Getter @Setter List<ArticlesTab> articlesTabs;
	@Getter @Setter Article articleDetail;
	
	@PostConstruct
	private void init() {
		articlesTabs = new LinkedList<>();
		for (ArticlesTab t: articleTabsModel) {
			log.debug("Instance of articleTab: " + t.getTittle());
			articlesTabs.add(t);
		}
	}
}
