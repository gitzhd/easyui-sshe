package sy.service;

import java.io.Serializable;

import sy.model.Tuser;
import sy.pageModel.User;

public interface UserServiceI {
	

	public void save(User user);
	public User login(User user);
}
