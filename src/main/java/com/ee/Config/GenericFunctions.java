package com.ee.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class GenericFunctions
{
	public String getProperty(String key) throws IOException
	{
			String strFilePathName=".\\Data.properties",strProperty=null;
			File file=new File(strFilePathName);
			FileInputStream fileInput=null;
			try
			{
				fileInput=new FileInputStream(file);
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			Properties prop=new Properties();
			try
			{
				prop.load(fileInput);
				strProperty=prop.getProperty(key);
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			finally 
			{
				fileInput.close();
			}
			return strProperty;
	}
	public boolean eleExist(WebElement ele)
	{
		boolean blnFlag=false;
		try
		{
			if(ele.isDisplayed())
				blnFlag=true;
		}
		catch(Exception e)
		{
			
		}
		return blnFlag;
	}
	public void enterValueInputBox(WebElement ele, String strValue)
	{
		ele.sendKeys(strValue);
	}
	public void eleClick(WebElement ele)
	{
		ele.click();
	}
	public void selectDDValue(WebElement ele, String strValue)
	{
		Select fruits = new Select(ele);
		fruits.selectByVisibleText(strValue);
	}
}