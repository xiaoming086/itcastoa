package cn.itcast.oa0909.service;

import java.util.Collection;

import cn.itcast.oa0909.domain.Kynamic;
import cn.itcast.oa0909.domain.Version;

public interface KynamicService {
	public Collection<Kynamic> getAllKynamic();
	
	public Kynamic getKynamicById(Long kid);
	
	public void saveKynamic(Kynamic kynamic);
	
	public boolean exsitName(String name);
	
	public void deleteKynamicByID(Long kid);
	
	public Collection<Kynamic> getSiblingsNodes(Long kid);
	
	public Kynamic getParentNode(Long kid);
	
	public void updateKynamic(Kynamic kynamic);
	
	public Collection<Version> getVersionsByKid(Long kid);
}
