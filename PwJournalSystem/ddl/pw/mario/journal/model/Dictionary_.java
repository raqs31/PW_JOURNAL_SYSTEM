package pw.mario.journal.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pw.mario.journal.model.dictionaries.DictionaryId;
import pw.mario.locale.LocaleEnum;

@Generated(value="Dali", date="2015-11-25T19:41:46.076+0100")
@StaticMetamodel(Dictionary.class)
public class Dictionary_ {
	public static volatile SingularAttribute<Dictionary, DictionaryId> id;
	public static volatile SingularAttribute<Dictionary, String> desciption;
	public static volatile SingularAttribute<Dictionary, String> parentCode;
	public static volatile SingularAttribute<Dictionary, String> attr1;
	public static volatile SingularAttribute<Dictionary, String> attr2;
	public static volatile SingularAttribute<Dictionary, String> attr3;
	public static volatile SingularAttribute<Dictionary, String> attr4;
	public static volatile SingularAttribute<Dictionary, Long> nAttr1;
	public static volatile SingularAttribute<Dictionary, Long> nAttr2;
	public static volatile SingularAttribute<Dictionary, LocaleEnum> locale;
}
