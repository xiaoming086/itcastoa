package cn.itcast.oa0909.test;

import org.junit.Test;

import cn.itcast.oa0909.service.PDManager;

public class PDManagerTest extends BaseSpring{
	@Test
	public void testPDManager(){
		PDManager pdManager = (PDManager)context.getBean("pdManager");
		pdManager.getLasterVersions();
	}
}
