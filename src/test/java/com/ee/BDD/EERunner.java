package com.ee.BDD;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ee.Config.XcelReportCollector;
import com.ee.StepDefs.EESteps;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features={"classpath:"},
		plugin={"pretty","html:target/Destination/cucumber-pretty",
				"rerun:target/rerun.txt",
				"json:target/cucumber.json"},
		tags={"@EETc"},
		glue={"com.ee.StepDefs"},monochrome=true)

public class EERunner extends AbstractTestNGCucumberTests
{
		@BeforeSuite
		public void setup() throws IOException
		{
			try
			{
				DateFormat dateFormat=new SimpleDateFormat("MM-dd-yy__HHmmss_");
				Date date=new Date();
				Properties fileName=new Properties();
				fileName.setProperty("dateTime", dateFormat.format(date));
				FileOutputStream fos= new FileOutputStream(new File("Report.properties"));
				fileName.store(fos, "Setting the file name for test execution report");
				fos.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		@AfterSuite
		public void collectReport() throws IOException
		{
			XcelReportCollector reportCollector=new XcelReportCollector();
			reportCollector.createSummarySheet();
			//EESteps.extent.endTest(EESteps.logger);
			EESteps.extent.flush();
			EESteps.extent.close();
		}
}