package cn.itcast.oa0909.struts2.action;

import java.lang.reflect.Type;
import java.util.Collection;

import cn.itcast.oa0909.domain.Person;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AAction extends ActionSupport implements ModelDriven<Person>{
	private Person model = new Person();
	public String aa(){
		return "aa";
	}
	
	public String bb(){
		return "bb";
	}

	public Person getModel() {
		// TODO Auto-generated method stub
		return this.model;
	}
}
