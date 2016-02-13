package pw.mario.journal.dao;

import java.util.List;

import pw.mario.journal.model.common.Tag;

public interface TagDAO {
	Tag addTag(Tag t);

	List<Tag> getTagList();

	void removeTag(Tag t);

	Tag getTag(String tagName);

	Tag updateTag(Tag t);
}
