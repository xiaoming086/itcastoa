package cn.itcast.oa0909.service;

import java.io.Serializable;
import java.util.Collection;

import cn.itcast.oa0909.domain.Department;
import cn.itcast.oa0909.utils.DeleteMode;

public interface DepartmentService {
	public void saveDepartment(Department department);

	public void updateDeparment(Department department);

	public void deleteDepartmentByID(Serializable id,String deleteMode);

	public Collection<Department> getAllDepartment();

	public Department getDepartmentById(Serializable id);
}
