package cn.itcast.oa0909.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa0909.dao.FormTemplateDao;
import cn.itcast.oa0909.domain.FormTemplate;
import cn.itcast.oa0909.service.FormTemplateService;
import cn.itcast.oa0909.utils.UploadUtils;

@Service("formTemplateService")
public class FormTemplateServiceImpl implements FormTemplateService{
	@Resource(name="formTemplateDao")
	private FormTemplateDao formTemplateDao;

	public Collection<FormTemplate> getAllFormTemplate() {
		// TODO Auto-generated method stub
		return this.formTemplateDao.getAllEntry();
	}

	@Transactional(readOnly=false)
	public void saveFormTemplate(FormTemplate formTemplate, File resource) {
		// TODO Auto-generated method stub
		/**
		 * 1、文件上传
		 * 2、保存数据到formtemplate表中
		 */
		String url = UploadUtils.saveUploadFile(resource);
		formTemplate.setUrl(url);
		this.formTemplateDao.saveEntry(formTemplate);
	}

	public InputStream download(Long ftid) throws Exception{
		// TODO Auto-generated method stub
		FormTemplate formTemplate = (FormTemplate)this.formTemplateDao.getEntryById(ftid);
		String fileName = URLEncoder.encode(formTemplate.getName(), "utf-8");
		ActionContext.getContext().put("fileName",fileName);
		return new FileInputStream(new File(formTemplate.getUrl()));
	}
}
