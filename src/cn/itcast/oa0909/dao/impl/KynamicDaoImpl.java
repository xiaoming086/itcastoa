package cn.itcast.oa0909.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.oa0909.dao.KynamicDao;
import cn.itcast.oa0909.dao.base.impl.BaseDaoImpl;
import cn.itcast.oa0909.domain.Kynamic;
import cn.itcast.oa0909.domain.Version;

@Repository("kynamicDao")
public class KynamicDaoImpl extends BaseDaoImpl<Kynamic> implements KynamicDao<Kynamic>{

	public Kynamic getKynamicByName(String name) {
		// TODO Auto-generated method stub
		List<Kynamic> kynamicList =  this.hibernateTemplate.find("from Kynamic where name=?",name);
		if(kynamicList.size()==0){
			return null;
		}else{
			return kynamicList.get(0);
		}
	}

	public Collection<Kynamic> getSiblingNodes(Long kid) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from Kynamic");
		stringBuffer.append(" where pid=(");
		stringBuffer.append("select pid from Kynamic where kid=?)");
		return this.hibernateTemplate.find(stringBuffer.toString(),kid);
	}

	public Kynamic getParentNode(Long kid) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from Kynamic");
		stringBuffer.append(" where kid=(");
		stringBuffer.append("select pid from Kynamic where kid=?)");
		List<Kynamic> kynamicList = this.hibernateTemplate.find(stringBuffer.toString(),kid);
		return kynamicList.get(0);
	}

	public Collection<Version> getVersionsByKid(Long kid) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find("from Version v where v.kynamic.kid=?",kid);
	}
	
}
