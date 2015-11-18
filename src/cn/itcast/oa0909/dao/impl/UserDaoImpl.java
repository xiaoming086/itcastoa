package cn.itcast.oa0909.dao.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.oa0909.dao.UserDao;
import cn.itcast.oa0909.dao.base.impl.BaseDaoImpl;
import cn.itcast.oa0909.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User>{

	public Collection<User> getUsers() {
		// TODO Auto-generated method stub
		List<User> useList =  this.hibernateTemplate.find("from User u left join fetch u.department d left join fetch u.posts p");
		return new HashSet<User>(useList);
	}

	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		List<User> userList = this.hibernateTemplate.find("from User where username=?",username);
		if(userList.size()==0){
			return null;
		}else{
			return userList.get(0);
		}
	}

}
