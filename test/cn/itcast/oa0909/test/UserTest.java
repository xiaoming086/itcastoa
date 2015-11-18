package cn.itcast.oa0909.test;

import org.junit.Test;

import cn.itcast.oa0909.dao.UserDao;

public class UserTest extends BaseSpring{
	@Test
	public void test(){
		UserDao userDao = (UserDao)context.getBean("userDao");
		userDao.getUserByUsername("aasdfa");
	}
}
