package cn.itcast.oa0909.struts2.action;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;

import javax.annotation.Resource;

import org.apache.struts2.dispatcher.StreamResult;
import org.jbpm.api.ProcessDefinition;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa0909.domain.FormTemplate;
import cn.itcast.oa0909.service.FormTemplateService;
import cn.itcast.oa0909.service.PDManager;
import cn.itcast.oa0909.struts2.action.base.BaseAction;
import cn.itcast.oa0909.utils.UploadUtils;

@Controller("formTemplateAction")
@Scope("prototype")
public class FormTemplateAction extends BaseAction<FormTemplate>{
	@Resource(name="formTemplateService")
	private FormTemplateService formTemplateService;
	
	@Resource(name="pdManager")
	private PDManager pdManager;
	
	private File resource;
	
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}

	public String getAllFormTemplate(){
		Collection<FormTemplate> ftList = this.formTemplateService.getAllFormTemplate();
		ActionContext.getContext().put("ftList", ftList);
		return listAction;
	}
	
	public String addUI(){
		Collection<ProcessDefinition> pdList = this.pdManager.getLasterVersions();
		ActionContext.getContext().put("pdList", pdList);
		return addUI;
	}
	
	public String add(){
		FormTemplate formTemplate = new FormTemplate();
		BeanUtils.copyProperties(this.getModel(), formTemplate);
		this.formTemplateService.saveFormTemplate(formTemplate, resource);
		return action2action;
	}
	
	public String download() throws Exception{
		this.inputStream = this.formTemplateService.download(this.getModel().getFtid());
		return SUCCESS;
	}
}
