package com.ee.POMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ee.Config.GenericFunctions;
import com.ee.Config.SharedDriver;

public class EESignInPage
{
	public static WebDriver driver;
	public static GenericFunctions objGenericFunctions;
	
	@FindBy(xpath="//a[@class='logout']")
	public static WebElement eleLogOut;
	
	
	public EESignInPage(SharedDriver commonDriver, GenericFunctions commonGenericFunctions)
	{
		driver=commonDriver;
		objGenericFunctions=commonGenericFunctions;
		PageFactory.initElements(driver, this);
	}
}