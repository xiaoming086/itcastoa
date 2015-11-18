package cn.itcast.oa0909.struts2.action;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa0909.domain.Menuitem;
import cn.itcast.oa0909.domain.User;
import cn.itcast.oa0909.service.MenuitemService;
import cn.itcast.oa0909.service.PrivilegeService;
import cn.itcast.oa0909.service.UserService;
import cn.itcast.oa0909.struts2.action.base.BaseAction;
import cn.itcast.oa0909.utils.OAUtils;

@Controller("privilegeAction")
@Scope("prototype")
public class PrivilegeAction extends BaseAction<Menuitem>{
	@Resource(name="privilegeService")
	private PrivilegeService privilegeService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="menuitemService")
	private MenuitemService menuitemService;
	
	private Collection<Menuitem> privilegeList;
	
	private Collection<Menuitem> checkedNodes;
	
	public Collection<Menuitem> getCheckedNodes() {
		return checkedNodes;
	}

	public void setCheckedNodes(Collection<Menuitem> checkedNodes) {
		this.checkedNodes = checkedNodes;
	}

	private Long uid;
	
	private Long[] aa;
	
	public Long[] getAa() {
		return aa;
	}

	public void setAa(Long[] aa) {
		this.aa = aa;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getMids() {
		return mids;
	}

	public void setMids(String mids) {
		this.mids = mids;
	}

	private String mids;

	public Collection<Menuitem> getPrivilegeList() {
		return privilegeList;
	}
	
	public String showPrivilege(){
		this.privilegeList = this.privilegeService.getPrivileges(this.uid);
		return SUCCESS;
	}
	
	/**
	 * 保存某一个用户的权限
	 * @return
	 */
	public String savePrivilege(){
		User user = this.userService.getUserById(this.getUid());
		Set<Menuitem> menuitems = this.menuitemService.getMenuitemsByIDS(OAUtils.string2Longs(this.mids));
		user.setMenuitems(menuitems);
		this.userService.updateUser(user);
		return SUCCESS;
	}
	
	public String aaa(){
		System.out.println(this.checkedNodes);
		return SUCCESS;
	}
}
