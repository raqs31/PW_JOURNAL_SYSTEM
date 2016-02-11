package pw.mario.journal.validator;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import lombok.extern.log4j.Log4j;
import pw.mario.journal.qualifiers.Validator;

@Log4j
@ApplicationScoped
public class ArticleValidatorsProducer {
	private Map<String, ArticleValidatorBuilder> validationMap;
	@Inject @Validator Instance<ArticleValidatorBuilder> validators;
	
	@PostConstruct 
	private void init() {
		validationMap = new HashMap<>();
		
		for (ArticleValidatorBuilder validator: validators) {
			if (validationMap.containsValue(validator.name())) {
				log.fatal("Duplicate validaiton: " + validator.name());
				throw new RuntimeException("Walidacja " + validator.name() + " jest zdublowana");
			}
			validationMap.put(validator.name(), validator);
		}
	}
	
	@Produces
	@Validator
	public Map<String, ArticleValidatorBuilder> getValidationMap(InjectionPoint ip) {
		return validationMap;
	}
}
