package cn.itcast.oa0909.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.oa0909.dao.LoginDao;
import cn.itcast.oa0909.domain.User;
import cn.itcast.oa0909.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	@Resource(name="loginDao")
	private LoginDao loginDao;

	public User checkUAndP(String username, String password) {
		// TODO Auto-generated method stub
		return this.loginDao.getUserByUAndP(username, password);
	}
}
