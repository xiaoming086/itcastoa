package cn.itcast.oa0909.service;

import java.io.File;

import cn.itcast.oa0909.domain.Form;

public interface WorkFlowService {
	public void submit(Long ftid,File resource);
}
