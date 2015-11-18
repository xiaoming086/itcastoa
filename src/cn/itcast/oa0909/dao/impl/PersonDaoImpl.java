package cn.itcast.oa0909.dao.impl;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.oa0909.dao.PersonDao;
import cn.itcast.oa0909.domain.Person;

public class PersonDaoImpl extends HibernateDaoSupport implements PersonDao{

	public void savePerson(Person person) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(person);
	}

	public Person getPersonById(Serializable id) {
		// TODO Auto-generated method stub
		Person person = (Person)this.getHibernateTemplate().load(Person.class, 1L);
		return person;
	}
}
