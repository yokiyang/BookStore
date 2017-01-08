package com.tz.bms.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;



public class SchemaExportUtil {
	public static void main(String[] args) {
		//加载配置文件,并解析
		Configuration cfg=new Configuration();
		
		SchemaExport export=new SchemaExport(cfg);
			
		export.create(true,true);
	}
}
