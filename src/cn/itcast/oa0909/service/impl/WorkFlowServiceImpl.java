package cn.itcast.oa0909.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa0909.dao.FormTemplateDao;
import cn.itcast.oa0909.dao.WorkFlowDao;
import cn.itcast.oa0909.domain.Form;
import cn.itcast.oa0909.domain.FormTemplate;
import cn.itcast.oa0909.domain.User;
import cn.itcast.oa0909.service.WorkFlowService;
import cn.itcast.oa0909.utils.OAUtils;
import cn.itcast.oa0909.utils.UploadUtils;

@Service("workFlowService")
public class WorkFlowServiceImpl implements WorkFlowService{
	@Resource(name="workFlowDao")
	private WorkFlowDao workFlowDao;
	
	@Resource(name="formTemplateDao")
	private FormTemplateDao formTemplateDao;
	
	@Resource(name="processEngine")
	private ProcessEngine processEngine;

	@Transactional(readOnly=false)
	public void submit(Long ftid,File resource) {
		/**
		 *  *  上传表单
         *     往form表中插入一行数据
                      applicatetime  当前时间
                      applicator   就是登入系统的人
                      state        审批中
                      url          上传表单以后可以生成该值
                      ftid         表单模板ID(在页面上应该用隐藏域)
         *     jbpm
             *  启动流程实例
                	根据pdkey启动流程实例，因为页面上传递到后台的参数只有pdkey
                	把form作为流程变量，保存到流程实例中
             *  完成请假申请的任务
                completeTask(String taskId);
		 */
		
		//文件的上传
		String url = UploadUtils.saveUploadFile(resource);
		/********************************************************************************************/
				/**
				 * 保存form
				 */
			//从session中提取user
			User user = OAUtils.fromSession();
			//往form表中插入一行数据
			Form form = new Form();
			form.setApplicatetime(new Date());
			form.setApplicator(user.getUsername());
			form.setState("申请中");
			
			//把formTempalte提取出来
			FormTemplate formTemplate = (FormTemplate)this.formTemplateDao.getEntryById(ftid);
			//建立form与formTemplate之间的关系
			form.setFormTemplate(formTemplate);
			String title = formTemplate.getName()+"_"+user.getUsername()+"_"+new Date();
			form.setTitle(title);
			form.setUrl(url);
			this.workFlowDao.saveEntry(form);
		/********************************************************************************************/
			/**
			 * jbpm的事情
			 */
			//启动流程实例,设置流程变量
			Map<String, Form> variables = new HashMap<String, Form>();
			variables.put("form", form);
			ProcessInstance pi = this.processEngine.getExecutionService()
			.startProcessInstanceByKey(formTemplate.getProcessKey(), variables);
			/**
			 * 根据当前正在执行的实例获取正在执行的任务
			 */
			Task task = this.processEngine.getTaskService()
			.createTaskQuery()
			.executionId(pi.getId())
			.uniqueResult();
			//完成请假申请的任务
			this.processEngine.getTaskService()
			.completeTask(task.getId());
		/********************************************************************************************/
	}
}
