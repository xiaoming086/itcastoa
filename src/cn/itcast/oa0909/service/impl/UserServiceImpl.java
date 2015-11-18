package cn.itcast.oa0909.service.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa0909.dao.UserDao;
import cn.itcast.oa0909.domain.User;
import cn.itcast.oa0909.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource(name="userDao")
	private UserDao userDao;

	public Collection<User> getAllUser() {
		// TODO Auto-generated method stub
		return this.userDao.getUsers();
	}

	@Transactional(readOnly=false)
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		this.userDao.saveEntry(user);
	}

	public User getUserById(Serializable id) {
		// TODO Auto-generated method stub
		return (User)this.userDao.getEntryById(id);
	}

	@Transactional(readOnly=false)
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		this.userDao.updateEntry(user);
	}

	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return this.userDao.getUserByUsername(username);
	}
}
