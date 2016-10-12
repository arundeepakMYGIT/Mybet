package com.util.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import org.springframework.stereotype.Service;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;


public class ApplicationUtilTest {
	
	private static Logger log = Logger.getLogger(ApplicationUtilTest.class);
	
	@Test
	public void errorMessage()
	{
		try
		{
			Thread.sleep(5000);
		}
	
		catch (AssertionError | InterruptedException e)
		{
			Assert.assertEquals(e.getLocalizedMessage(), !e.toString().isEmpty());
			log.info("Unspecified Error message: " +e.getLocalizedMessage() + ", CAUSE: " + (e.getCause() != null ? e.getCause().getLocalizedMessage() : "unspecified cause"));
		}
	}
	
	public void loginit(String folder, String filename)
	{
		String logs = "LOGS/"+folder+ "/";
		//String logFileName = d + ".log";
		//log.info("\n**************Log file for this run: " + logFileName + "\n**************\n");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		//get current date time with Date()
		Date date = new Date();
		String d = dateFormat.format(date);

		Logger rootLogger = Logger.getRootLogger();
		@SuppressWarnings("rawtypes")
		Enumeration appenders = rootLogger.getAllAppenders();
		FileAppender fa = null;
		while(appenders.hasMoreElements())
		{
			Appender currAppender = (Appender) appenders.nextElement();
			if(currAppender instanceof FileAppender)
			{
				fa = (FileAppender) currAppender;
			}
		}
		if(fa != null)
		{
			fa.setName(logs + filename + "/" + d + ".log");
			fa.activateOptions();
		}
		else
		{
			log.info("No File Appender found");
		}
	}

}
