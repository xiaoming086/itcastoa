package cn.itcast.oa0909.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.oa0909.dao.FormTemplateDao;
import cn.itcast.oa0909.dao.base.impl.BaseDaoImpl;
import cn.itcast.oa0909.domain.FormTemplate;

@Repository("formTemplateDao")
public class FormTemplateDaoImpl extends BaseDaoImpl<FormTemplate> implements FormTemplateDao<FormTemplate>{

}
