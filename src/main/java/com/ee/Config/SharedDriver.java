package com.ee.Config;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;

public class SharedDriver extends EventFiringWebDriver
{
	public static final WebDriver REAL_DRIVER;
	public static final Thread CLOSE_THREAD=new Thread()
	{
		@Override
		public void run()
		{
			REAL_DRIVER.quit();
		}
	};
	static
	{
		Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
		try
		{
			ChromeOptions objOptions=new ChromeOptions();
			HashMap<String, Object>prefs=new HashMap<String, Object>();
			prefs.put("profile.default_content_setting.popups", 0);
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("safebrowsing.enabled", true);
			prefs.put("download.default_directory", System.getProperty("user.dir")+File.separator+"DownloadFiles");
			LoggingPreferences logPrefs=new LoggingPreferences();
			logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
			objOptions.setExperimentalOption("prefs", prefs);
			objOptions.addArguments("--start-maximized");
			objOptions.addArguments("--dns-prefetch-disable");
			objOptions.addArguments("safebrowsing-disable-extension-blacklist");
			objOptions.addArguments("disable-infobars");
			objOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			REAL_DRIVER=new ChromeDriver(objOptions);
		}
		catch(Throwable throwable)
		{
			throwable.printStackTrace();
			throw new Error(throwable);
		}
	}
	public SharedDriver()
	{
		super(REAL_DRIVER);
		REAL_DRIVER.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@Override
	public void close()
	{
		System.out.println("driver close");
		super.close();
	}
	@Override
	public TargetLocator switchTo()
	{
		return super.switchTo();
	}
	@After
	public void embedScreenshot(Scenario scenario)
	{
		try
		{
			byte[] screenshot=getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		}
		catch(WebDriverException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
}