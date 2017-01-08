package com.tz.bms;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:54:21
 */
public class TestDDL {
	
	@Test
	public void testDDL(){
		Configuration cfg=new Configuration().configure();
		SchemaExport export=new SchemaExport(cfg);
		export.create(true,true);
	}
}
