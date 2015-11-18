package cn.itcast.oa0909.service;

import java.io.Serializable;
import java.util.Collection;

import cn.itcast.oa0909.domain.User;

public interface UserService {
	public Collection<User> getAllUser();
	
	public User getUserById(Serializable id);
	
	public void saveUser(User user);
	
	public void updateUser(User user);
	
	public User getUserByName(String username);
}
