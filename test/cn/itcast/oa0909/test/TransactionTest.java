package cn.itcast.oa0909.test;

import org.junit.Test;

import cn.itcast.oa0909.dao.PersonDao;
import cn.itcast.oa0909.domain.Person;
import cn.itcast.oa0909.service.PersonService;

public class TransactionTest extends BaseSpring{
	/**
	 * 没有事务环境
	 * 当应用程序调用完Person person = (Person)this.getHibernateTemplate().load(Person.class, 1L);
	 *           session直接关闭了,那么所在的dao方法session不存在了
	 */
	@Test
	public void testHibernateTemplate(){
		PersonDao personDao = (PersonDao)context.getBean("personDao");
		personDao.getPersonById(1L);
	}
	/**
	 * 当整个方法有事务环境，当方法调用完以后，session关闭
	 */
	@Test
	public void testService(){
		PersonService personService = (PersonService)context.getBean("personService");
		Person person = personService.getPersonById(1L);
		System.out.println(person.getPname());
	}
}
