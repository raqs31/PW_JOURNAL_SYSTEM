package pw.mario.journal.dao.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.TagDAO;
import pw.mario.journal.model.Tag;

@Default
@Dependent
public class TagDAOImpl extends AbstractDAOImpl<Tag> implements TagDAO {

	@Override
	public Tag addTag(Tag t) {
		persist(t);
		return find(t.getId());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Tag> getTagList() {
		return em.createQuery("select t from Tag t").getResultList();
	}

	@Override
	public void removeTag(Tag t) {
		delete(t);
	}
	
	@Override
	public Tag getTag(String tagName) {
		return (Tag) em.createQuery("select t from Tag t where t.name = ?").setParameter(1, tagName).getSingleResult();
	}

	@Override
	public Tag updateTag(Tag t) {
		return merge(t);
	}

}
