package com.asianpaints.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class config {

	static String projectpath = System.getProperty("user.dir");
	
	public static String getproperty (String key) throws IOException{
		
		Properties prop = new Properties();
		File f2=new File(projectpath+"\\asianpaints.properties");
		  FileInputStream fs=new FileInputStream(f2);
		  prop.load(fs);
		  return prop.getProperty(key);
		
		
	}
}
