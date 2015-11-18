package cn.itcast.oa0909.service.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.oa0909.dao.DepartmentDao;
import cn.itcast.oa0909.domain.Department;
import cn.itcast.oa0909.service.DepartmentService;
import cn.itcast.oa0909.utils.DeleteMode;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;

	public void saveDepartment(Department department) {
		// TODO Auto-generated method stub
		this.departmentDao.saveEntry(department);
	}

	public void updateDeparment(Department department) {
		// TODO Auto-generated method stub
		this.departmentDao.updateEntry(department);
	}

	public void deleteDepartmentByID(Serializable id,String deleteMode) {
		// TODO Auto-generated method stub
		this.departmentDao.deleteEntry(id);
	}

	public Collection<Department> getAllDepartment() {
		// TODO Auto-generated method stub
		return this.departmentDao.getAllEntry();
	}

	public Department getDepartmentById(Serializable id) {
		// TODO Auto-generated method stub
		return (Department)this.departmentDao.getEntryById(id);
	}
	
}
