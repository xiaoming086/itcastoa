package cn.itcast.oa0909.service;

import cn.itcast.oa0909.domain.User;

public interface LoginService {
	public User checkUAndP(String username,String password);
}
