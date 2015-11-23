package pw.mario.journal.dao;

import java.util.List;

import pw.mario.journal.model.Tag;

public interface TagDAO {
	public void addTag(Tag t);
	
	public List<Tag> getTagList();
	
	public void removeTag(Tag t);
	
	public Tag getTag(String tagName);
}
