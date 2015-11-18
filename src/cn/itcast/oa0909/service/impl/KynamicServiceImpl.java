package cn.itcast.oa0909.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa0909.dao.KynamicDao;
import cn.itcast.oa0909.domain.Kynamic;
import cn.itcast.oa0909.domain.Version;
import cn.itcast.oa0909.service.KynamicService;

@Service("kynamicService")
public class KynamicServiceImpl implements KynamicService{
	@Resource(name="kynamicDao")
	private KynamicDao kynamicDao;

	public Collection<Kynamic> getAllKynamic() {
		// TODO Auto-generated method stub
		return this.kynamicDao.getAllEntry();
	}

	@Transactional(readOnly=false)
	public void saveKynamic(Kynamic kynamic) {
		// TODO Auto-generated method stub
		this.kynamicDao.saveEntry(kynamic);
	}

	public boolean exsitName(String name) {
		// TODO Auto-generated method stub
		Kynamic kynamic =  this.kynamicDao.getKynamicByName(name);
		return kynamic!=null?true:false;
	}

	@Transactional(readOnly=false)
	public void deleteKynamicByID(Long kid) {
		// TODO Auto-generated method stub
		this.kynamicDao.deleteEntry(kid);
	}

	public Collection<Kynamic> getSiblingsNodes(Long kid) {
		// TODO Auto-generated method stub
		return this.kynamicDao.getSiblingNodes(kid);
	}

	public Kynamic getParentNode(Long kid) {
		// TODO Auto-generated method stub
		return this.kynamicDao.getParentNode(kid);
	}

	@Transactional(readOnly=false)
	public void updateKynamic(Kynamic kynamic) {
		// TODO Auto-generated method stub
		this.kynamicDao.updateEntry(kynamic);
	}

	public Kynamic getKynamicById(Long kid) {
		// TODO Auto-generated method stub
		return (Kynamic)this.kynamicDao.getEntryById(kid);
	}

	public Collection<Version> getVersionsByKid(Long kid) {
		// TODO Auto-generated method stub
		return this.kynamicDao.getVersionsByKid(kid);
	}
}
