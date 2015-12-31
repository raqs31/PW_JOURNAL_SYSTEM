package pw.mario.journal.service;

import java.util.List;

import pw.mario.journal.model.Tag;

public interface TagService {
	List<Tag> getTags();
	
	Tag updateTag(Tag t);
	
	Tag getTag(String name);
	
	void removeTag(Tag t);
	
	Tag addTag(Tag t);
}
