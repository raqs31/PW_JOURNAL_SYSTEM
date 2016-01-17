package pw.mario.journal.qualifiers;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import pw.mario.journal.qualifiers.enums.DictType;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE,FIELD,PARAMETER})
public @interface DictionaryType {
	DictType value();
}
