package cn.itcast.oa0909.test;

import java.util.Collection;

import org.junit.Test;

import cn.itcast.oa0909.domain.Department;
import cn.itcast.oa0909.service.DepartmentService;

public class DepartmentTest extends BaseSpring{
	@Test
	public void testQuery(){
		DepartmentService departmentService = (DepartmentService)context.getBean("departmentService");
		Collection<Department> departmentList = departmentService.getAllDepartment();
	}
}
