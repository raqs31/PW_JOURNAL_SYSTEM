package pw.mario.journal.dao.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import pw.mario.journal.dao.TagDAO;
import pw.mario.journal.model.Tag;

@Default
@Dependent
public class TagDAOImpl extends AbstractDAOImpl<Tag> implements TagDAO {

	@Override
	public void addTag(Tag t) {
		persist(t);
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
		return (Tag) em.createQuery("selet t from Tag t where t.name = ?").setParameter(1, tagName).getSingleResult();
	}

}
