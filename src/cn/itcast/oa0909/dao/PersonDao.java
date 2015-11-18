package cn.itcast.oa0909.dao;

import java.io.Serializable;

import cn.itcast.oa0909.domain.Person;

public interface PersonDao {
	public void savePerson(Person person);
	
	public Person getPersonById(Serializable id);
}
