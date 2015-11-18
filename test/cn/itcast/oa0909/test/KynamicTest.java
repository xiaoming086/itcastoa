package cn.itcast.oa0909.test;

import org.junit.Test;

import cn.itcast.oa0909.domain.Kynamic;
import cn.itcast.oa0909.service.KynamicService;

public class KynamicTest extends BaseSpring{
	@Test
	public void testSaveKynamic(){
		Kynamic kynamic = new Kynamic();
		kynamic.setIsParent(true);
		kynamic.setName("知识管理");
		kynamic.setPid(0L);
		KynamicService kynamicService = (KynamicService) context.getBean("kynamicService");
		kynamicService.saveKynamic(kynamic);
	}
}
