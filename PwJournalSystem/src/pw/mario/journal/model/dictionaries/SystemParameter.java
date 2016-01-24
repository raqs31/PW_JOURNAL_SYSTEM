package pw.mario.journal.model.dictionaries;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.NoArgsConstructor;
import pw.mario.journal.model.Dictionary;

@Entity
@NoArgsConstructor
@DiscriminatorValue(SystemParameter.DICTIONARY_NAME)
@NamedQueries({
	@NamedQuery(name=SystemParameter.QUERY,
		query="select d from SystemParameter d where d.code =?1"
			)
})
public class SystemParameter extends Dictionary {
	public static final String DICTIONARY_NAME = "SYSTEM_PARAM"; 
	public static final String QUERY = "systemParameter";
	public interface Parameters {
		String ARTICLE_DIR = "ARTICLE_DIR";
	}
	
	
	public String getPropertyName() {
		return getAttr1();
	}
	
	public String getPropertyValue() {
		return getAttr2();
	}
}
