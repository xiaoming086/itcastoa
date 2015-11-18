package cn.itcast.oa0909.service;

import java.util.Collection;

import cn.itcast.oa0909.domain.Menuitem;

public interface PrivilegeService {
	public Collection<Menuitem> getPrivileges(Long uid);
}
