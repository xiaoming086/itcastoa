package cn.itcast.oa0909.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.oa0909.dao.PrivilegeDao;
import cn.itcast.oa0909.domain.Menuitem;
import cn.itcast.oa0909.service.PrivilegeService;

@Service("privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService{
	@Resource(name="privilegeDao")
	private PrivilegeDao privilegeDao;

	public Collection<Menuitem> getPrivileges(Long uid) {
		// TODO Auto-generated method stub
		return this.privilegeDao.getMenuitemsByUID(uid);
	}
}
