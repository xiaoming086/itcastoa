package cn.itcast.oa0909.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import cn.itcast.oa0909.domain.Person;

public class ParameterInterceptor {
	@Test
	public void test()throws Exception{
		Class personClass = Person.class;
		Field[] fields = personClass.getDeclaredFields();
		for(Field field:fields){
			String methodName = field.getName();
			if(methodName.equals("pname")){
				String firstNum = methodName.substring(0,1);
				String otherNum = methodName.substring(1);
				methodName = "set"+firstNum.toUpperCase()+otherNum;
				String methodName2 = "get"+firstNum.toUpperCase()+otherNum;
				Method method = personClass.getMethod(methodName, String.class);
				Method method2 = personClass.getMethod(methodName2);
				Object obj = personClass.newInstance();
				method.invoke(obj, "aaa");
				System.out.println(method2.invoke(obj));
			}
		}
	}
}
