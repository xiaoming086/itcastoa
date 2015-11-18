package cn.itcast.oa0909.service;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;

import cn.itcast.oa0909.domain.FormTemplate;

public interface FormTemplateService {
	public Collection<FormTemplate> getAllFormTemplate();
	
	public void saveFormTemplate(FormTemplate formTemplate,File resource);
	
	public InputStream download(Long ftid) throws Exception;
}
