package cn.itcast.oa0909.struts2.action;

import java.io.File;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa0909.domain.Form;
import cn.itcast.oa0909.domain.FormTemplate;
import cn.itcast.oa0909.service.FormTemplateService;
import cn.itcast.oa0909.service.WorkFlowService;
import cn.itcast.oa0909.struts2.action.base.BaseAction;

@Controller("workFlowAction")
@Scope("prototype")
public class WorkFlowAction extends BaseAction<Form>{
	@Resource(name="formTemplateService")
	private FormTemplateService formTemplateService;
	
	@Resource(name="workFlowService")
	private WorkFlowService workFlowService;
	
	private Long ftid;
	
	public Long getFtid() {
		return ftid;
	}

	public void setFtid(Long ftid) {
		this.ftid = ftid;
	}

	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}

	private File resource;
	
	public String getAllFormTemplate(){
		Collection<FormTemplate> ftList = this.formTemplateService.getAllFormTemplate();
		ActionContext.getContext().put("ftList", ftList);
		return "workFlow_formTemplateList";
	}
	
	public String submitUI(){
		return "submitUI";
	}
	
	public String submit(){
		this.workFlowService.submit(ftid, resource);
		return null;
	}
}
