package pw.mario.journal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="DICT_LOCALES", schema="MARIO")
public class Locale {
	@Id
	@SequenceGenerator(name="localeIdSeq", sequenceName="LOCALE_ID_SEQ", schema="MARIO", initialValue=1, allocationSize=10)
	@GeneratedValue(generator="localeIdSeq", strategy=GenerationType.SEQUENCE)
	@Column(name="LOCALE_ID")
	private long localeId;
	
	@Column(name="LOCALE", nullable=false)
	private String locale;
		
	@Column(name="VALUE")
	private String valuel;
}
