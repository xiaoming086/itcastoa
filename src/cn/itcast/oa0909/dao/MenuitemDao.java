package cn.itcast.oa0909.dao;

import java.util.Collection;
import java.util.Set;

import cn.itcast.oa0909.dao.base.BaseDao;
import cn.itcast.oa0909.domain.Menuitem;

public interface MenuitemDao<T> extends BaseDao<T>{
	public Collection<Menuitem> getMenuitemsByPid(Long pid);
	
	public Set<Menuitem> getMenuitemsByIDS(Long[] ids);
	
	public Collection<Menuitem> getMenuitemsByUser();
}
