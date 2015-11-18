package cn.itcast.oa0909.struts2.action;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.oa0909.service.PDManager;
import cn.itcast.oa0909.struts2.action.base.BaseAction;

@Controller("pdManagerAction")
@Scope("prototype")
public class PDManagerAction extends ActionSupport{
	
	private File resource;
	
	private InputStream inputStream;
	
	private String key;
	
	private String deploymentId;
	
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public File getResource() {
		return resource;
	}
	public void setResource(File resource) {
		this.resource = resource;
	}

	@Resource(name="pdManager")
	private PDManager pdManager;
	
	public String getLasterVersions(){
		Collection<ProcessDefinition> pdList = this.pdManager.getLasterVersions();
		ActionContext.getContext().put("pdList", pdList);
		return "listAction";
	}
	/**
	 * 转向到部署的页面
	 * @return
	 */
	public String deployUI(){
		return "deployUI";
	}
	
	public String deploy() throws Exception{
		this.pdManager.deploy(this.resource);
		return "action2action";
	}
	
	public String delete(){
		this.pdManager.deletePDKEY(this.getKey());
		return  "action2action";
	}
	
	public String showImage(){
		this.inputStream = this.pdManager.showImage(this.deploymentId);
		return SUCCESS;
	}
}
