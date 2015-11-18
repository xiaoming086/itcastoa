package cn.itcast.oa0909.struts2.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.struts2.json.JSONResult;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa0909.domain.Kynamic;
import cn.itcast.oa0909.domain.Version;
import cn.itcast.oa0909.service.KynamicService;
import cn.itcast.oa0909.struts2.action.base.BaseAction;

@Controller("kynamicAction")
@Scope("prototype")
public class KynamicAction extends BaseAction<Kynamic>{
	@Resource(name="kynamicService")
	private KynamicService kynamicService;
	
	private Collection<Version> versionList;
	
	public Collection<Version> getVersionList() {
		return versionList;
	}

	private Kynamic kynamic;
	
	public Kynamic getKynamic() {
		return kynamic;
	}

	private String message;
	
	private Long kid;
	
	public void setKid(Long kid) {
		this.kid = kid;
	}

	public Long getKid() {
		return kid;
	}

	public String getMessage() {
		return message;
	}

	public Collection<Kynamic> getKynamicList() {
		return kynamicList;
	}

	private Collection<Kynamic> kynamicList;
	
	public String showKynamicTree(){
		this.kynamicList = this.kynamicService.getAllKynamic();
		return SUCCESS;
	}
	
	public String saveKynamic(){
		Kynamic kynamic = new Kynamic();
		BeanUtils.copyProperties(this.getModel(), kynamic);
		this.kynamicService.saveKynamic(kynamic);
		this.kid = kynamic.getKid();
		this.message = "操作成功";
		return SUCCESS;
	}
	
	public String isExsitName(){
		boolean flag = this.kynamicService.exsitName(this.getModel().getName());
		if(flag){//重名了
			this.message = "1";
		}else{//可以使用
			this.message = "2";
		}
		return SUCCESS;
	}
	
	public String deleteNode(){
		this.kynamicService.deleteKynamicByID(this.getModel().getKid());
		this.message = "操作成功";
		return SUCCESS;
	}
	
	public String showSiblingNodes(){
		this.kynamicList = this.kynamicService.getSiblingsNodes(this.getModel().getKid());
		return SUCCESS;
	}
	
	public String showParentNode(){
		this.kynamic = this.kynamicService.getParentNode(this.getModel().getKid());
		return SUCCESS;
	}
	
	public String updateKynamic(){
		Kynamic kynamic = this.kynamicService.getKynamicById(this.getModel().getKid());
		kynamic.setName(this.getModel().getName());
		this.kynamicService.updateKynamic(kynamic);
		return SUCCESS;
	}
	
	public String showVersionsByKid(){
		this.versionList = this.kynamicService.getVersionsByKid(this.getModel().getKid());
		return SUCCESS;
	}
}
