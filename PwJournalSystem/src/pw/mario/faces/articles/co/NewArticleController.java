package pw.mario.faces.articles.co;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import pw.mario.journal.model.Dictionary;
import pw.mario.journal.qualifiers.DictionaryType;
import pw.mario.journal.qualifiers.enums.DictType;
import pw.mario.journal.service.DictionaryService;

@Named
@ViewScoped
public class NewArticleController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject @DictionaryType(DictType.ARTICLE_STATUS) DictionaryService dictService;
	@Getter @Setter List<Dictionary> statuses;

	@PostConstruct
	private void init() {
		statuses = dictService.getDictionaries();
	}
}
