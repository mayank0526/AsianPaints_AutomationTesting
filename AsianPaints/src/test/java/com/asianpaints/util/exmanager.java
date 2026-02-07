package com.asianpaints.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class exmanager {
	static ExtentReports extent;
	static String projectpath=System.getProperty("user.dir");
	public static ExtentReports getreport()
	{
		if(extent==null)
		{
			extent=new ExtentReports();
	  		ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"\\asianpaints_Report.html");
	  		extent.attachReporter(spark);
		}
		return extent;
		
	}
}
