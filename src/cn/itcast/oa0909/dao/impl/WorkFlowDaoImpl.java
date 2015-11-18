package cn.itcast.oa0909.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.oa0909.dao.WorkFlowDao;
import cn.itcast.oa0909.dao.base.impl.BaseDaoImpl;
import cn.itcast.oa0909.domain.Form;

@Repository("workFlowDao")
public class WorkFlowDaoImpl extends BaseDaoImpl<Form> implements WorkFlowDao<Form>{

}
