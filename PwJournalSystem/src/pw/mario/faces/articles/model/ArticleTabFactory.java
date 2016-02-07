package pw.mario.faces.articles.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJBAccessException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import lombok.extern.log4j.Log4j;
import pw.mario.journal.qualifiers.ArticleTab;

@Log4j
@RequestScoped
public class ArticleTabFactory {
	@Produces
	@ArticleTab
	public List<ArticlesTab> getAllowedArticleTabs(@ArticleTab Instance<ArticlesTab> tabs, InjectionPoint ip) {
		List<ArticlesTab> allowedTab = new LinkedList<>();
		Iterator<ArticlesTab> tabIterator = tabs.iterator();
		
		while (tabIterator.hasNext()) {
			try {
				ArticlesTab t = tabIterator.next();
				
				if (t.tabAllowed()) {
					allowedTab.add(t);
					log.debug("DODAJĘ " + t);
				}
			} catch (EJBAccessException e){
				log.debug("AccesException", e);
			} 
		}
		return allowedTab;
	}
}
