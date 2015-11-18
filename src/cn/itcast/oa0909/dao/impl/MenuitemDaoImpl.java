package cn.itcast.oa0909.dao.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.itcast.oa0909.dao.MenuitemDao;
import cn.itcast.oa0909.dao.base.impl.BaseDaoImpl;
import cn.itcast.oa0909.domain.Menuitem;
import cn.itcast.oa0909.domain.User;
import cn.itcast.oa0909.utils.OAUtils;

@Repository("menuitemDao")
public class MenuitemDaoImpl extends BaseDaoImpl<Menuitem> implements MenuitemDao<Menuitem>{

	public Collection<Menuitem> getMenuitemsByPid(Long pid) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find("from Menuitem where pid=?",pid);
	}

	public Set<Menuitem> getMenuitemsByIDS(Long[] ids) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from Menuitem");
		stringBuffer.append(" where mid in(");
		for(int i=0;i<ids.length;i++){
			if(i<ids.length-1){
				stringBuffer.append(ids[i]+",");
			}else{
				stringBuffer.append(ids[i]);
			}
		}
		stringBuffer.append(")");
		List<Menuitem> menuitemList = this.hibernateTemplate.find(stringBuffer.toString());
		return new HashSet<Menuitem>(menuitemList);
	}

	public Collection<Menuitem> getMenuitemsByUser() {
		// TODO Auto-generated method stub
		User user = OAUtils.fromSession();
		if("admin".equals(user.getUsername())){
			return this.getAllEntry();
		}else{
			return this.hibernateTemplate.find("from Menuitem m inner join fetch m.users u where u.uid=?",user.getUid());
		}
	}

}
