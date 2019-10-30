package com.ee.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig
{
	private static TestConfig testConfig;
	private static String requiredEnvironmentName;
	private static Properties properties;
	
	public static String valueFor(final String keyName) throws Throwable
	{
		return getInstance().getProperty(keyName);
	}
	private static TestConfig getInstance() throws IOException
	{
		if(testConfig==null)
		{
			properties=new Properties();
			requiredEnvironmentName=System.getProperty("env","data");
			populateCommonProperties();
			populateEnvProperties(requiredEnvironmentName);
			testConfig=new TestConfig();
		}
		return testConfig;
	}
	private static void populateCommonProperties() throws IOException
	{
		readPropertiesFile("data");
	}
	private static void populateEnvProperties(final String requiredEnvironment) throws IOException
	{
		readPropertiesFile(requiredEnvironment);
	}
	private static void readPropertiesFile(String filePath) throws IOException
	{
		String propertiesFilePath=String.format(".\\Data.properties", filePath);
		File propertiesFile=new File(propertiesFilePath);
		if(!propertiesFile.exists())
		{
			throw new FileNotFoundException(String.format("File not found", filePath));
		}
		InputStream input= new FileInputStream(propertiesFilePath);
		properties.load(input);
		input.close();
	}
	private String getProperty(final String keyName)
	{
		String value=properties.getProperty(keyName);
		if(value==null)
			throw new Error(String.format("Key not found", keyName,requiredEnvironmentName));
		return value;
}
}