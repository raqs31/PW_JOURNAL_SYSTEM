package pw.mario.journal.dao.impl;

import java.util.List;

import javax.ejb.Singleton;

import pw.mario.journal.dao.TagDAO;
import pw.mario.journal.model.Tag;

@Singleton
public class TagDAOImpl extends AbstractDAOImpl implements TagDAO {

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
