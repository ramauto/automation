package com.ee.POMPages;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.ee.Config.GenericFunctions;
import com.ee.Config.SharedDriver;

public class EELandingPage
{
	public static WebDriver driver;
	
	@FindBy(xpath="//div[@class='header_user_info']/a")
	public static WebElement eleLandingSign;
	
	@FindBy(xpath="//input[@name='email_create']")
	public static WebElement signupEmail;
	
	@FindBy(xpath="//button[@id='SubmitCreate']")
	public static WebElement btnCreateAccount;
			
	@FindBy(name="email")
	public static WebElement eleEmail;
	
	@FindBy(name="passwd")
	public static WebElement elePassword;
	
	@FindBy(xpath="//button[@id='SubmitLogin']")
	public static WebElement eleSubmitLogin;
	
	public static GenericFunctions objGenericFunctions;
	String strURL;
	public EELandingPage(SharedDriver commonDriver, GenericFunctions commonGenericFunctions)
	{
		driver=commonDriver;
		objGenericFunctions=commonGenericFunctions;
		PageFactory.initElements(driver, this);
	}
	public void load(String strEnv) throws IOException
	{
		if(strEnv.equalsIgnoreCase("QA"))
			strURL=objGenericFunctions.getProperty("QAURL");
		else
			strURL=objGenericFunctions.getProperty("UATURL");
		driver.get(strURL);
	}
	public void ExplicitWaitTillVisible(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver, 2000);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void mouseHoverAndClick(WebElement ele)
	{
		Actions action = new Actions(driver);
		action.moveToElement(ele).click().build().perform();
	}
	public void moveToElement(WebElement ele)
	{
		Actions builder = new Actions(driver);
	    builder.moveToElement(ele).build().perform();
	}
}