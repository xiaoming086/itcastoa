package cn.itcast.oa0909.service;

import java.io.Serializable;

import cn.itcast.oa0909.domain.Person;

public interface PersonService {
	public void savePerson(Person person);
	
	public Person getPersonById(Serializable id);
}
