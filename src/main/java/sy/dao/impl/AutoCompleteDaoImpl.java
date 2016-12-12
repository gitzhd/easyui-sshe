package sy.dao.impl;

import org.springframework.stereotype.Repository;

import sy.dao.AutoCompleteDao;
@Repository("autoCompleteDao")
public class AutoCompleteDaoImpl<T> extends BaseDaoImpl implements AutoCompleteDao<T>{


}
