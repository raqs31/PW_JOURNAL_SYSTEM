package pw.mario.journal.dao.dictionary;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.model.dictionaries.SystemParameter;

@Default
@Dependent
public class SystemParameterDao extends AbstractDAOImpl<SystemParameter>{
	public SystemParameter getParameter(String code) {
		return createNamedTypedQuery(SystemParameter.QUERY).setParameter(1, code).getSingleResult();
	}
}
