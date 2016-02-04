package pw.mario.journal.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import lombok.NoArgsConstructor;
import pw.mario.journal.dao.TagDAO;
import pw.mario.journal.model.Tag;
import pw.mario.journal.service.TagService;

@NoArgsConstructor
@Stateless
public class TagServiceImpl implements TagService {
	private static final long serialVersionUID = 1L;
	@Inject
	private TagDAO tagDao;
	
	@Override
	public List<Tag> getTags() {
		return tagDao.getTagList();
	}

	@Override
	@Transactional(value=TxType.REQUIRED)
	public Tag updateTag(Tag t) {
		return tagDao.updateTag(t);
	}

	@Override
	public Tag getTag(String name) {
		return tagDao.getTag(name);
	}

	@Override
	@Transactional(value=TxType.REQUIRED)
	public void removeTag(Tag t) {
		tagDao.removeTag(t);
	}
	
	@Override
	@Transactional(value=TxType.REQUIRED)
	public Tag addTag(Tag t) {
		return tagDao.addTag(t);
	}

}
