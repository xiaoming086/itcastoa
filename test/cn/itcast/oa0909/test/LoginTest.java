package cn.itcast.oa0909.test;

import org.junit.Test;

import cn.itcast.oa0909.dao.LoginDao;

public class LoginTest extends BaseSpring{
	@Test
	public void testLogin(){
		LoginDao loginDao = (LoginDao)context.getBean("loginDao");
		loginDao.getUserByUAndP("admin", "11");
	}
}
