package pw.mario.journal.service.impl;

import java.util.List;

	import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pw.mario.journal.dao.TagDAO;

import lombok.Data;
import pw.mario.journal.model.Tag;
import pw.mario.journal.service.TagService;

@Data
@Stateless
public class TagServiceImpl implements TagService {
	@Inject
	private TagDAO tagDao;
	
	@Override
	public List<Tag> getTags() {
		return tagDao.getTagList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Tag updateTag(Tag t) {
		return tagDao.updateTag(t);
	}

	@Override
	public Tag getTag(String name) {
		return tagDao.getTag(name);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removeTag(Tag t) {
		tagDao.removeTag(t);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Tag addTag(Tag t) {
		return tagDao.addTag(t);
	}

}
