package cn.itcast.oa0909.dao;

import java.util.Collection;

import cn.itcast.oa0909.dao.base.BaseDao;
import cn.itcast.oa0909.domain.User;

public interface UserDao<T> extends BaseDao<T>{
	public Collection<User> getUsers();
	
	public User getUserByUsername(String username);
}
