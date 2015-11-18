package cn.itcast.oa0909.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.itcast.oa0909.dao.DepartmentDao;
import cn.itcast.oa0909.dao.base.impl.BaseDaoImpl;
import cn.itcast.oa0909.domain.Department;
import cn.itcast.oa0909.domain.User;
import cn.itcast.oa0909.utils.DeleteMode;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao<Department>{

}
