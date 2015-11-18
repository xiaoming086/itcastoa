package cn.itcast.oa0909.service.impl;

import java.io.Serializable;

import cn.itcast.oa0909.dao.PersonDao;
import cn.itcast.oa0909.domain.Person;
import cn.itcast.oa0909.service.PersonService;

public class PersonServiceImpl implements PersonService{

	public void savePerson(Person person) {
		// TODO Auto-generated method stub
		this.personDao.savePerson(person);
	}
	private PersonDao personDao;
	public PersonDao getPersonDao() {
		return personDao;
	}
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	public Person getPersonById(Serializable id) {
		// TODO Auto-generated method stub
		Person person = this.personDao.getPersonById(id);
		return person;
	}
}
