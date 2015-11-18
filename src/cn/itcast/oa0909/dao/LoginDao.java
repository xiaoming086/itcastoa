package cn.itcast.oa0909.dao;

import cn.itcast.oa0909.domain.User;

public interface LoginDao {
	public User getUserByUAndP(String username,String password);
}
