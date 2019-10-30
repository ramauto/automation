package com.ee.POMPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.ee.Config.GenericFunctions;
import com.ee.Config.SharedDriver;

public class EECreateAccountPage
{
	public static WebDriver driver;
	public static GenericFunctions objGenericFunctions;
	//input[@type='email']
	@FindBy(xpath="//div[@class='header_user_info']/a")
	public static WebElement eleLandingSign;

	@FindBy(xpath="//h1[@class='page-heading']")
	public static WebElement eleCreateUserTitle;
	
	@FindBy(xpath="//input[@name='id_gender']")
	public static List<WebElement> eleGender;
	
	@FindBy(xpath="//input[@id='customer_firstname']")
	public static WebElement eleCustFirstName;
	
	@FindBy(xpath="//input[@id='customer_lastname']")
	public static WebElement eleCustLastName;
	
	@FindBy(xpath="//input[@name='passwd']")
	public static WebElement elePassword;
	
	@FindBy(xpath="//select[@name='days']")
	public static WebElement eleDays;
	
	@FindBy(xpath="//select[@name='months']")
	public static WebElement eleMonths;
	
	@FindBy(xpath="//select[@name='years']")
	public static WebElement eleYear;
	
	@FindBy(xpath="//input[@name='address1']")
	public static WebElement eleAddress;
	
	@FindBy(xpath="//input[@name='city']")
	public static WebElement eleCity;
	
	@FindBy(xpath="//select[@name='id_state']")
	public static WebElement eleState;
	
	@FindBy(xpath="//input[@name='postcode']")
	public static WebElement eleZip;
	
	@FindBy(xpath="//input[@name='phone_mobile']")
	public static WebElement eleMobileNumber;
	
	@FindBy(xpath="//h1[text()='My account']")
	public static WebElement eleMyAccountHeader;
	
	@FindBy(id="submitAccount")
	public static WebElement eleSubmittButton;
	
	public EECreateAccountPage(SharedDriver commonDriver, GenericFunctions commonGenericFunctions)
	{
		driver=commonDriver;
		objGenericFunctions=commonGenericFunctions;
		PageFactory.initElements(driver, this);
	}
	public void ExplicitWaitTillVisible(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver, 2000);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
}