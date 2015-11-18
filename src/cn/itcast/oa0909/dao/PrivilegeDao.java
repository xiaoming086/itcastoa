package cn.itcast.oa0909.dao;

import java.util.Collection;

import cn.itcast.oa0909.dao.base.BaseDao;
import cn.itcast.oa0909.domain.Menuitem;

public interface PrivilegeDao<T> extends BaseDao<T>{
	public Collection<Menuitem> getMenuitemsByUID(Long uid);
}
