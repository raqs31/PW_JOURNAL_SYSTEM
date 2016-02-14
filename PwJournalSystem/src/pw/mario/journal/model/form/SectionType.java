package pw.mario.journal.model.form;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;

public enum SectionType {
	CHECKBOX("Checkboxy"),
	SINGLEBOX("Single selections"),
	TEXT("Text");
	
	public static final List<SectionType> list;
	
	static {
		List<SectionType> types = new LinkedList<>();
		Collections.addAll(types, SectionType.values());
		list = Collections.unmodifiableList(types);
	}
	
	@Getter private String name;
	
	
	private SectionType(String name) {
		this.name = name;
	}
}
