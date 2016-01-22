package pw.mario.faces.articles.co;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
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
	@Inject @ArticleTab @Getter @Setter private List<ArticlesTab> articlesTabs;
	@Getter @Setter private Article articleDetail;
	
	@PostConstruct
	private void init() {
		for (ArticlesTab t: articlesTabs) {
			log.debug("Instance of articleTab: " + t.getTittle());
			t.refreshActions();
		}
		Collections.sort(articlesTabs, new ArticlesTab.ArticleTabComparator());
	}
}
