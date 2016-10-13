package sy.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import sy.dao.UserDaoI;
import sy.model.Tuser;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDaoI {
 
}
