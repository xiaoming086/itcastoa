package cn.itcast.oa0909.service.impl;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.oa0909.dao.MenuitemDao;
import cn.itcast.oa0909.domain.Menuitem;
import cn.itcast.oa0909.service.MenuitemService;

@Service("menuitemService")
public class MenuitemServiceImpl implements MenuitemService{
	@Resource(name="menuitemDao")
	private MenuitemDao menuitemDao;

	public Collection<Menuitem> getAllMenuitem() {
		// TODO Auto-generated method stub
		return this.menuitemDao.getAllEntry();
	}

	public Collection<Menuitem> getMenuitemsByPid(Long pid) {
		// TODO Auto-generated method stub
		return this.menuitemDao.getMenuitemsByPid(pid);
	}

	public Set<Menuitem> getMenuitemsByIDS(Long[] ids) {
		// TODO Auto-generated method stub
		return this.menuitemDao.getMenuitemsByIDS(ids);
	}

	public Collection<Menuitem> getMenuitemsByUser() {
		// TODO Auto-generated method stub
		return this.menuitemDao.getMenuitemsByUser();
	}
}
