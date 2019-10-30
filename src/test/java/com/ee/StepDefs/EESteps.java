package com.ee.StepDefs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import com.ee.Config.GenericFunctions;
import com.ee.Config.SharedDriver;
import com.ee.Config.XcelReportCollector;
import com.ee.POMPages.EECreateAccountPage;
import com.ee.POMPages.EELandingPage;
import com.ee.POMPages.EESignInPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	EESteps.class
                    })
public class EESteps
{
	public static WebDriver driver;
	public XcelReportCollector reportCollector;
	ArrayList<String> testStepResults;
	EELandingPage objEELandingPage;
	EECreateAccountPage objEECreateAccountPage;
	EESignInPage objEESignInPage;
	GenericFunctions objGenericFunctions;
	Logger log;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	public EESteps(SharedDriver commonDriver, GenericFunctions commonGenericFunctions, EELandingPage commonEELandingPage, EECreateAccountPage commonEECreateAccountPage, EESignInPage commonEESignInPage, ArrayList<String>Results, XcelReportCollector commonXcelReportCollector)
	{
		driver=commonDriver;
		DOMConfigurator.configure("log4j.xml");
		objGenericFunctions=commonGenericFunctions;
		objEELandingPage=commonEELandingPage;
		objEECreateAccountPage=commonEECreateAccountPage;
		objEESignInPage=commonEESignInPage;
		testStepResults=new ArrayList<String>();
		testStepResults=Results;
		reportCollector=commonXcelReportCollector;
		//log = Logger.getLogger("devpinoyLogger");
		log = Logger.getLogger(EESteps.class.getName());
		extent = new ExtentReports (System.getProperty("user.dir") +"/TestReports/EEExtentReport.html", true);
		extent
        .addSystemInfo("Host Name", "Estern Enterprise")
        .addSystemInfo("Environment", "EE QA")
        .addSystemInfo("User Name", "grbh niti");
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	}
	@Given("^I will be launching EE on \"([^\"]*)\" environment$")
	public void launchEE(String strEnv) throws Throwable 
	{
		String strTitle=null;
	   try
	   {
		   System.out.println(strEnv);
		   objEELandingPage.load(strEnv);
		   //driver.manage().wait(5000);
		   Thread.sleep(5000);
		   strTitle=driver.getTitle();
		   if(strTitle.contains("My Store"))
		   {
			   log.info("Website launched");
			   logger = extent.startTest("launchEE");
			   logger.log(LogStatus.PASS, "EE launched successfully");
			   extent.endTest(logger);
			   testStepResults.add("Launch EE, PASS, EE launched successfully");
			   
		   }
		   else
		   {
			   logger = extent.startTest("launchEE");
			   logger.log(LogStatus.PASS, "EE NOT launched");
			   extent.endTest(logger);
			   testStepResults.add("Launch EE, FAIL, EE NOT launched");
		   }
		  /* if(objGenericFunctions.eleExist(FBLoginPage.eleHome)
				   testStepResults.add("")*/
	   }
	   catch(Exception e)
	   {
		   
	   }
	    
	}
	@Then("^create new user$")
	public void create_new_user() throws Throwable 
	{
		try
		{
			Random rand = new Random();
			int randno = rand.nextInt(1000);
			String strUserName=objGenericFunctions.getProperty("UserName");
	   	    String strPassword=objGenericFunctions.getProperty("Password");
	   	    strUserName=strUserName+randno+"@yahoo.co.in";
	   	    System.out.println(strUserName);
	   	    objGenericFunctions.eleClick(EELandingPage.eleLandingSign);
	   	    objEECreateAccountPage.ExplicitWaitTillVisible(EECreateAccountPage.eleCreateUserTitle);
	   	    if(objGenericFunctions.eleExist(EECreateAccountPage.eleCreateUserTitle))
	   	    {
	   	    	objEELandingPage.moveToElement(EELandingPage.signupEmail);
	   	    	objGenericFunctions.enterValueInputBox(EELandingPage.signupEmail,strUserName);
	   	    	objGenericFunctions.eleClick(EELandingPage.btnCreateAccount);
	   	    	objGenericFunctions.eleClick(EECreateAccountPage.eleGender.get(1));
	   	    	objGenericFunctions.enterValueInputBox(EECreateAccountPage.eleCustFirstName, "ABC");
	   	    	objGenericFunctions.enterValueInputBox(EECreateAccountPage.eleCustLastName, "XYZ");
	   	    	objGenericFunctions.enterValueInputBox(EECreateAccountPage.elePassword, strPassword);
	   	    	/*objGenericFunctions.selectDDValue(EECreateAccountPage.eleDays, "24");
	   	    	objGenericFunctions.selectDDValue(EECreateAccountPage.eleMonths, "2");
	   	    	objGenericFunctions.selectDDValue(EECreateAccountPage.eleYear, "1990");*/
	   	    	objGenericFunctions.enterValueInputBox(EECreateAccountPage.eleAddress, "testAddress12345");
	   	    	objGenericFunctions.enterValueInputBox(EECreateAccountPage.eleCity, "testCity12345");
	   	    	objGenericFunctions.selectDDValue(EECreateAccountPage.eleState, "Georgia");
	   	    	objGenericFunctions.enterValueInputBox(EECreateAccountPage.eleZip, "30002");
	   	    	objGenericFunctions.enterValueInputBox(EECreateAccountPage.eleMobileNumber, "9699851282");
	   	    	objGenericFunctions.eleClick(EECreateAccountPage.eleSubmittButton);
	   	    	if(objGenericFunctions.eleExist(EECreateAccountPage.eleMyAccountHeader))
	   	    	{
	   	    		log.info("User created");
	   	    		logger = extent.startTest("create_new_user");
	   	    		extent.endTest(logger);
	   	    		logger.log(LogStatus.PASS, "EE user created successfully");
	   	    		testStepResults.add("Create user, PASS, User created successfully");
	   	    	}
	   	    }
	   	    else
	   	    {
	   	    	logger = extent.startTest("create_new_user");
   	    		logger.log(LogStatus.FAIL, "Create user page NOT opened");
   	    		extent.endTest(logger);
   	    		testStepResults.add("Create user, FAIL, Create user page NOT opened");
	   	    }
	   	    	
		}
		catch(Exception e)
		{
			logger = extent.startTest("create_new_user");
	    	logger.log(LogStatus.FAIL, "Create user-Exception occured"+e.getMessage());
	    	extent.endTest(logger);
			testStepResults.add("Create user, FAIL,Exception occured"+e.getMessage());
		}
   	    	
	}
	@Then("^I logout from application$")
	public void logout() throws Throwable 
	{
		try
		{
		    objGenericFunctions.eleClick(EESignInPage.eleLogOut);
		    String strTitle="Login - My Store";
		    String strExpected=driver.getTitle();
		    if(strTitle.equals(strExpected))
		    {
		    	log.info("User logged out from application");
		    	logger = extent.startTest("logout");
   	    		logger.log(LogStatus.PASS, "EE user loggedout successfully");
   	    		extent.endTest(logger);
		    	testStepResults.add("Logout from application, PASS, User loggedout successfully");
		    }
		    else
		    {
		    	logger = extent.startTest("logout");
   	    		logger.log(LogStatus.FAIL, "EE user NOT loggedout");
   	    		extent.endTest(logger);
		    	testStepResults.add("Logout from application, FAIL, User NOT loggedout from application");
		    }
		}
		catch(Exception e)
		{
			logger = extent.startTest("logout");
	    	logger.log(LogStatus.FAIL, "EE user NOT loggedout-exception occured"+e.getMessage());
	    	extent.endTest(logger);
			testStepResults.add("Logout from application, FAIL,Exception occured"+e.getMessage());
		}
	}
	@Then("^I login into application$")
	public void login() 
	{
		try
		{
			objGenericFunctions.eleClick(EELandingPage.eleLandingSign);
	   	    objEECreateAccountPage.ExplicitWaitTillVisible(EECreateAccountPage.eleCreateUserTitle);
	   	    if(objGenericFunctions.eleExist(EECreateAccountPage.eleCreateUserTitle))
	   	    {
	   	    	objGenericFunctions.enterValueInputBox(EELandingPage.eleEmail, "grbhniti586@yahoo.co.in");
	   	    	objGenericFunctions.enterValueInputBox(EELandingPage.elePassword, "test12345");
	   	    	objGenericFunctions.eleClick(EELandingPage.eleSubmitLogin);
	   	    	if(objGenericFunctions.eleExist(EESignInPage.eleLogOut))
	   	    	{
	   	    		log.info("User logged in successfully");
	   	    		logger = extent.startTest("login");
	   	    		logger.log(LogStatus.PASS, "EE user loggedin successfully");
	   	    		extent.endTest(logger);
	   	    		testStepResults.add("Login into application, PASS, User loggedIN successfully");
	   	    	}
			    else
			    {
			    	logger = extent.startTest("login");
			    	logger.log(LogStatus.FAIL, "EE user NOT loggedin");
			    	extent.endTest(logger);
			    	testStepResults.add("Login into application, FAIL, User NOT loggedIN");
			    }
	   	    }
		}
		catch(Exception e)
		{
			logger = extent.startTest("login");
	    	logger.log(LogStatus.FAIL, "EE user NOT loggedin-exception occured"+e.getMessage());
	    	extent.endTest(logger);
			testStepResults.add("Login into application, FAIL, Exception occured"+e.getMessage());
		}
	}
	@Then("^I create test report for \"([^\"]*)\"$")
	public void createTestReport(String strSheetName) throws IOException 
	{
		
	    long millis=System.currentTimeMillis()%1000;
	    reportCollector.createFeatureReport(strSheetName+"_"+millis, testStepResults);
	}
}
