package cn.itcast.oa0909.dao;

import java.util.Collection;

import cn.itcast.oa0909.dao.base.BaseDao;
import cn.itcast.oa0909.domain.Kynamic;
import cn.itcast.oa0909.domain.Version;

public interface KynamicDao<T> extends BaseDao<T>{
	public Kynamic getKynamicByName(String name);
	
	public Collection<Kynamic> getSiblingNodes(Long kid);
	
	public Kynamic getParentNode(Long kid);
	
	public Collection<Version> getVersionsByKid(Long kid);
}
