package pw.mario.journal.service;

import java.io.Serializable;
import java.util.List;

import pw.mario.journal.model.Tag;

public interface TagService extends Serializable {
	List<Tag> getTags();
	
	Tag updateTag(Tag t);
	
	Tag getTag(String name);
	
	void removeTag(Tag t);
	
	Tag addTag(Tag t);
}
