package com.genericClass;

import static org.testng.Assert.assertTrue;

import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.openxml4j.util.ZipSecureFile;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.FillPatternType;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
//import org.apache.poi.util.Removal;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFColor;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.commons.io.FileUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fla.PageObjects.PageObjects_FLAVollTabelle4;
import com.fla.PageObjects.PageObjects_FLAEntwTabelle2;
import com.fla.PageObjects.PageObjects_FLALogin1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import org.json.JSONObject;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.interactions.Mouse;


public class GenericClass {
	
	// ########################################################################################################
		// ### Static and Dictionary key that are used in globally (Function level,

		public static WebDriver driverBrowser;
		public static WebDriver driverWinium;
		public static Properties properties = null;
		public Hashtable<String, String> dicConfig = new Hashtable<String, String>();
		public Hashtable<String, String> dicOutput = new Hashtable<String, String>();
		public String ErrDescription;

//		public XSSFSheet ExcelWSheetTR;
//		public XSSFWorkbook ExcelWBookTR;
//		public XSSFCell CellTR;
//		public XSSFRow RowTR;
		public String Path_TestDataTR;
	
//		private static MissingCellPolicy xRow;
//		private XSSFSheet ExcelWSheetTD;
//		private XSSFWorkbook ExcelWBookTD;
//		private XSSFCell CellTD;
//		private XSSFRow RowTD;
		private String Path_TestDataTD;

		String result;
		
		public static ExtentReports extent;
		public static ExtentSparkReporter spark;
		public static ExtentTest test;
		public static Throwable throwable = null;
		
		public static String antwortVonResponseBodyErstellenTestZyklus;
//		public static String antwortVonResponseBodyErstellenTestZyklus = "IDMSTR-C88";
		
		public Assertion hardAssert = new Assertion();
		public SoftAssert softAssert = new SoftAssert();
		
		// #######################################################################################################
		// Constructor: GenericClass
		// Description: 
		// setup function
		// #######################################################################################################
//		public GenericClass() {
//
//			try {
//				//Loginit(this.getClass().getName());
//				//IESetup();
//				//ChromeSetup(URL);
//				// FirefoxSetup();
//				//browserPropertyUndLog();
//
//			} catch (Exception e) {
//				throw new IllegalStateException("Exception: Can't start the browser", e);
//			}
//		}
		
		
		public void aufrufURL(String url) throws IOException {
			driverBrowser.manage().window().maximize();
			driverBrowser.get(url);
			extentReport("", "Info", url+ "Link ist aufgerufen", throwable);
			driverBrowser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		
		
		// #######################################################################################################
		// Beschreibung: Propertydatei(Testdata) einsetzen
		// #######################################################################################################
		public static Properties loadPropertyFile() throws IOException{
			FileInputStream fileInputStream = new FileInputStream("Resources/Testdata.properties");
			properties = new Properties();
			properties.load(fileInputStream);
			return properties;
		}
		
//		public void setupPropertyUndLog() throws IOException {
//			loadPropertyFile();
//			Loginit(this.getClass().getName());
//		}
		
		// #######################################################################################################
		// Beschreibung: Extentreport (HTML Report) einsetzen (neu erstellen, Logs schreiben, Screenshots anh�ngen, usw)
		// #######################################################################################################
		
		public void extentReport(String extentTestfallname, String input, String text, Throwable throwable) throws IOException
		{
			switch(input.toUpperCase()) {
			case "REPORT":
				
				extent = new ExtentReports();
				spark = new ExtentSparkReporter("FLA Testautomatisierungsreport Technischetests.html");
				
			    spark.config().setTheme(Theme.DARK);
			    spark.config().setDocumentTitle("FLA Automatisierungsreport Technischetests");
			    spark.config().setReportName("Extent Report FLA");
			    extent.attachReporter(spark);
			    break;
			
			case "TESTFALL":
				test = extent.createTest(extentTestfallname); //
				break;
				
			case "PASS":
				test.pass("<b><font color = green>" + text + "<font></b>");
				break;
				
			case "FAIL":
				String path = screenshot(text);
	 	    	try {
	 	    		test.fail("<b><font color = red>" + text + "<font></b>", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	 	    		test.fail(throwable.fillInStackTrace());
	 	    	}
	 	    	catch (Exception e)
	 	    	{
	 	    		test.warning("Screenshot koennte nicht angehaengt werden");
	 	    	}
	 	    	break;
	 	    	
			case "FAILWIN":
				String pathWin = takeScreenshotWin(text);
	 	    	try {
	 	    		test.fail("<b><font color = red>" + text + "<font></b>", MediaEntityBuilder.createScreenCaptureFromPath(pathWin).build());
	 	    		test.fail(throwable.fillInStackTrace());
	 	    	}
	 	    	catch (Exception e)
	 	    	{
	 	    		test.warning("Screenshot k�nnte nicht angeh�ngt werden");
	 	    	}
	 	    	break;
	 	    	
			case "INFO":
				test.info(text);
				break;
				
			case "SKIP":
				test.skip("<b><font color = yellow>" + text + "<font></b>");
				break;
				
			case "WARN":
				test.warning("<b><font color = orange>" + text + "<font></b>");
				break;
				
			default:
				test.info("Fehler aufgetreten Report zu generieren");
				break;
			}
				extent.flush();
		}
		
		// #######################################################################################################
		// Beschreibung: Screenshot aufnehmen f�r Browser Extentreport (HTML Report)
		// #######################################################################################################
		public String takeScreenshot(String methodName) {
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");// dd/MM/yyyy
			Date now = new Date();
			String strDate = sdfDate.format(now);
			String fName = "Screenshots\\" + "Extent FLA technische Screenshots" + "\\" + strDate + ".png";
			try {
				File scrFile = ((TakesScreenshot) driverBrowser).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(fName));
			}
			catch (IOException e) {
				e.getMessage();
				System.out.println("Failed to capture screenshot: " + e.getMessage());
			}
			return fName;
		}
		
		public static String screenshot(String methodName) {
			String format = "png";
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");// dd/MM/yyyy
			Date now = new Date();
			String strDate = sdfDate.format(now);
            
            String fileName = "Screenshots\\" + "Extent FLA technische Screenshots" + "\\" + strDate + "."  + format;
			
			try {
	            Robot robot = new Robot();
	            
	            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
	            ImageIO.write(screenFullImage, format, new File(fileName));
	             
	            System.out.println("A full screenshot saved!");
	        } catch (AWTException | IOException ex) {
	            System.err.println(ex);
	            System.out.println("Failed to capture screenshot: " + ex.toString());
	        }
			return fileName;
		}
		
		// #######################################################################################################
		// Beschreibung: Screenshot aufnehmen f�r Winium Extentreport (HTML Report)
		// #######################################################################################################
		public static String takeScreenshotWin(String methodName) {
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");// dd/MM/yyyy
			Date now = new Date();
			String strDate = sdfDate.format(now);
			String fName = "Screenshots\\" + "Extent Tralita Screenshots" + "\\" + strDate + ".png";
			try {
				File scrFile = ((TakesScreenshot) driverWinium).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(fName));
			}
			catch (IOException e) {
				e.getMessage();
				System.out.println("Failed to capture screenshot: " + e.getMessage());
			}
			return fName;
		}
		
		public void softAssertStringPruefen(String softAssertElement, String text) throws IOException
		{
			try {
				softAssert.assertEquals(true, softAssertElement);
				extentReport("", "info", text + " ist erfolgreich angezeigt", throwable);
				System.out.println(text + " ist erfolgreich angezeigt");
			}
			catch (Exception e)
			{
				extentReport("", "info", text + " ist NICHT erfolgreich angezeigt", throwable);
				System.out.println(text + " ist NICHT erfolgreich angezeigt");
			}
		}
		
		public boolean softAssertIntPruefen(int softAssertElement, String text) throws IOException
		{
			try {
				softAssert.assertEquals(true, softAssertElement);
				extentReport("", "info", text + " ist erfolgreich angezeigt", throwable);
				System.out.println(text + " ist erfolgreich angezeigt");
				return true;
			}
			catch (Exception e)
			{
				extentReport("", "info", text + " ist NICHT erfolgreich angezeigt", throwable);
				System.out.println(text + " ist NICHT erfolgreich angezeigt");
				return false;
			}
		}
		
		public void hardAssertStringPruefen(String hardAssertElement, String hardAssertPrueftext, String text) throws IOException
		{
			try {
				
				hardAssert.assertEquals(hardAssertElement, hardAssertPrueftext);
				extentReport("", "info", text + " ist erfolgreich angezeigt", throwable);
				System.out.println(text + " ist erfolgreich angezeigt");
			}
			catch (Exception e)
			{
				extentReport("", "info", text + " ist NICHT erfolgreich angezeigt", throwable);
				System.out.println(text + " ist NICHT erfolgreich angezeigt");
			}
		}
		
		public void hardAssertIntPruefen(int hardAssertElement, int hardAssertPrueftext, String text) throws IOException
		{
			try {
				hardAssert.assertEquals(hardAssertElement, hardAssertPrueftext);
				extentReport("", "info", text + " ist erfolgreich angezeigt", throwable);
				System.out.println(text + " ist erfolgreich angezeigt");
				//return true;
			}
			catch (AssertionError e)
			{
				extentReport("", "info", text + " ist NICHT erfolgreich angezeigt", throwable);
				System.out.println(text + " ist NICHT erfolgreich angezeigt");
				//return false;
			}
		}
		
		public void hardAssertStringZeitUnterschiedTest(ResultSet rs, String zeitstempel, String prueftext, String text) throws IOException
		{
			try {
				//Assert.assertEquals("true", zeitstempelUnterschiedTest(rs.getString(zeitstempel), prueftext));
				
				//softAssert.assertEquals("true", zeitstempelUnterschiedTest(rs.getString(zeitstempel), prueftext));
				hardAssert.assertEquals("true", zeitstempelUnterschiedTest(rs.getString(zeitstempel), prueftext));
				//hardAssert.assertEquals("true", "true");
				extentReport("", "info", text + " ist erfolgreich angezeigt", throwable);
				System.out.println(text + " ist erfolgreich angezeigt");
			}
			catch (AssertionError | SQLException e)
			{
				System.out.println("Exception ist: "+e);
				extentReport("", "info", text + " ist NICHT erfolgreich angezeigt", throwable);
				System.out.println(text + " ist NICHT erfolgreich angezeigt");
			}
		}
		
		public String halbeStundeZurueck()
		{
			SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			cal.add(Calendar.MINUTE, -30);
			Date halbeStundeZurueck = cal.getTime();
			String halb = sdfDate.format(halbeStundeZurueck);
			System.out.println("halbe Stunde zurueck ist: " +halb);
			return halb;
		}
		
		public boolean textVerifyNotNull(String value) {
			boolean flag = false;
			
			if(value.contains("(null)")) {
				flag = true;
			}
			else {
				flag = false;
			}
			return flag;
		}
		
		public boolean zeitstempelGrosserTest(String zeitstempelEins, String zeitstempelZwei) {
			boolean flag = false;
			
			try {
				String zeitstempelSplitLeerEins [] = zeitstempelEins.split(" ");
				String zeitstempelSplitLeerzeichenEins = zeitstempelSplitLeerEins[1];
				String zeitstempelSplitKommaEins [] = zeitstempelSplitLeerzeichenEins.split("\\.");
				String zeitstempelSplittedKommaEins = zeitstempelSplitKommaEins[0];
				String zeitstempelStMinSekEins = zeitstempelSplittedKommaEins.replaceAll(":", "");
				String zeitStempelNurMitSekSplitEins [] = zeitstempelStMinSekEins.split(",");
				String zeitStempelNurMitSekEins = zeitStempelNurMitSekSplitEins[0]; 
				System.out.println("zeitstempelStMinSek Eins ist: " +zeitStempelNurMitSekEins);
				int zeitStempelEinsNurMitSek = Integer.parseInt(zeitStempelNurMitSekEins);
				
				String zeitstempelSplitLeerZwei [] = zeitstempelZwei.split(" ");
				String zeitstempelSplitLeerzeichenZwei = zeitstempelSplitLeerZwei[1];
				String zeitstempelSplitKommaZwei [] = zeitstempelSplitLeerzeichenZwei.split("\\.");
				String zeitstempelSplittedKommaZwei = zeitstempelSplitKommaZwei[0];
				String zeitstempelStMinSekZwei = zeitstempelSplittedKommaZwei.replaceAll(":", "");
				String zeitStempelNurMitSekSplitZwei [] = zeitstempelStMinSekZwei.split(",");
				String zeitStempelNurMitSekZwei = zeitStempelNurMitSekSplitZwei[0]; 
				System.out.println("zeitstempelStMinSek Zwei ist: " +zeitStempelNurMitSekZwei);
				int zeitStempelZweiNurMitSek = Integer.parseInt(zeitStempelNurMitSekZwei);
				
				hardAssert.assertEquals(true, zeitStempelEinsNurMitSek < zeitStempelZweiNurMitSek);
				System.out.println("Zeitstempel ist richtig");
				flag = true;
			}
			catch (Exception e) {
				System.out.println("Exception ist zeit unterscheid: " +e.getLocalizedMessage());
				System.out.println("uuid ist falsch");
				flag = false;
			}
			return flag;
		}
		
		public boolean zeitstempelUnterschiedTest(String zeitstempel, String nameZeitstempel) throws IOException {
			boolean flag = false;
			try {
				SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
				Date jetzt = new Date();
				String strDateJetzt = sdfDate.format(jetzt);
				String zeitJetztSplit [] = strDateJetzt.split(" ");
				String zeitJetztSplitZwei = zeitJetztSplit[1].replaceAll(":", "");
				int zeitJetztStundeMinuteSekunden = Integer.parseInt(zeitJetztSplitZwei);
				System.out.println("Zeit jetzt ist: " +zeitJetztStundeMinuteSekunden);
			
				
				Date halbeStundeFrueher = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(halbeStundeFrueher);
				cal.add(Calendar.MINUTE, -30);
				Date halbeStundeZurueck = cal.getTime();
				String halb = sdfDate.format(halbeStundeZurueck);
				String halbeStundeZurueckSplit [] = halb.split(" ");
				String halbeStundeZurueckZwei = halbeStundeZurueckSplit[1].replaceAll(":", "");
				int halbeStundeZurueckVonJetztStundeMinutenSekunden = Integer.parseInt(halbeStundeZurueckZwei);
				System.out.println("Zeit vor halber Stunde zurueck ist: " +halbeStundeZurueckVonJetztStundeMinutenSekunden);
				
				
				String zeitstempelSplitLeer [] = zeitstempel.split(" ");
				String zeitstempelSplitLeerzeichen = zeitstempelSplitLeer[1];
				String zeitstempelSplitKomma [] = zeitstempelSplitLeerzeichen.split("\\.");
				String zeitstempelSplittedKomma = zeitstempelSplitKomma[0];
				String zeitstempelStMinSek = zeitstempelSplittedKomma.replaceAll(":", "");
				System.out.println("zeitstempelStMinSek" +zeitstempelStMinSek);
				
				//String zeitstempelSplitPunkt [] = zeitstempelStMinSek.split(".");
				//String zeitstempelSplittedPunkt = zeitstempelSplitPunkt[0];
				
				int zeitstempelStundeMinutenSekunden = Integer.parseInt(zeitstempelStMinSek);
				
				System.out.println("uuid Zeit ist: " +zeitstempelStundeMinutenSekunden);
				
				
//				System.out.println("Datenbankwert ist: " +zeitstempel); // 2021-07-01 10:44:53.861
//				String uuidSplitLeer [] = zeitstempel.split(" ");
//				String uuidSplitLeerzeichen = uuidSplitLeer[1].replaceAll("[\\\r\\\n]+", "");;
//				System.out.println(uuidSplitLeerzeichen.replaceAll("[\\\r\\\n]+", ""));
////				String uuidSplitKomma [] = uuidSplitLeerzeichen.split(".");
////				String uuidSplittedKomma = uuidSplitKomma[1];
//				String uuidStMinSek = uuidSplitLeerzeichen.replaceAll(":", "");
//				System.out.println("uuidStMinSek ist: " +uuidStMinSek);
//				
//				String uuidSplitKomma [] = uuidStMinSek.split("\\.");
//				String uuidSplittedKomma = uuidSplitKomma[0];
//				System.out.println(uuidSplittedKomma); //String words[]=temp.split("\\.");
//				int uuidStundeMinutenSekunden = Integer.parseInt(uuidSplittedKomma);  
//				
//				String uuid = "01.07.21 10:44:53,861000000"; // 01.07.21 10:44:53,861000000
//				
//				
//				
//				
//				
//				String uuidSplitPunkt[] = uuidStMinSek.split(",");
//				String uuidSplittedPunkt = uuidSplitPunkt[0];
//				System.out.println("uuidSplittedPunkt ist: "+uuidSplittedPunkt);
				 
				
				//System.out.println("uuid Zeit ist: " +uuidStundeMinutenSekunden); 
				
				hardAssert.assertEquals(true, zeitstempelStundeMinutenSekunden < zeitJetztStundeMinuteSekunden);
				hardAssert.assertEquals(true, zeitstempelStundeMinutenSekunden > halbeStundeZurueckVonJetztStundeMinutenSekunden);
				
				System.out.println("Zeitstempel ist richtig"); 
				
//				if((uuidStundeMinutenSekunden < zeitJetztStundeMinuteSekunden) && (uuidStundeMinutenSekunden > halbeStundeZurueckVonJetztStundeMinutenSekunden))
//				{
//					System.out.println("uuid ist richtig");
//				}
//				else
//				{
//					System.out.println("uuid ist falsch");
//				}
				flag = true;
			}
			catch (Exception e)
			{
				System.out.println("Exception ist zeit unterscheid: " +e.getLocalizedMessage());
				System.out.println("uuid ist falsch");
				flag = false;
			}
			return flag;
		}
//		
//		public boolean zeitstempelUnterschiedTesttt(String zeitstempel, String nameZeitstempel) throws IOException 
//		{
//			try {
//				SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
//				Date jetzt = new Date();
//				String strDateJetzt = sdfDate.format(jetzt);
//				String zeitJetztSplit [] = strDateJetzt.split(" ");
//				String zeitJetztSplitZwei = zeitJetztSplit[1].replaceAll(":", "");
//				int zeitJetztStundeMinuteSekunden = Integer.parseInt(zeitJetztSplitZwei);
//				System.out.println("Zeit jetzt ist: " +zeitJetztStundeMinuteSekunden);
//			
//				Date halbeStundeFrueher = new Date();
//				Calendar cal = Calendar.getInstance();
//				cal.setTime(halbeStundeFrueher);
//				cal.add(Calendar.MINUTE, -30);
//				Date halbeStundeZurueck = cal.getTime();
//				String halb = sdfDate.format(halbeStundeZurueck);
//				String halbeStundeZurueckSplit [] = halb.split(" ");
//				String halbeStundeZurueckZwei = halbeStundeZurueckSplit[1].replaceAll(":", "");
//				int halbeStundeZurueckVonJetztStundeMinutenSekunden = Integer.parseInt(halbeStundeZurueckZwei);
//				System.out.println("Zeit vor halber Stunde zurueck ist: " +halbeStundeZurueckVonJetztStundeMinutenSekunden);
//				
//				//String zeitstempel = "08.08.21 14:25:25,053000000";
////				System.out.println("unbearbeitete zeitstempel ist: " +zeitstempel);
////				String zeitstempelSplitLeer [] = zeitstempel.split(" ");
////				String zeitstempelSplitLeerzeichen = zeitstempelSplitLeer[1];
////				System.out.println("dsdsdsds1 " +zeitstempelSplitLeerzeichen);
////				
////				String doppelpunktRaus = zeitstempelSplitLeerzeichen.replaceAll(":", "");
////				System.out.println("doppelpunkt raus: " +doppelpunktRaus);
////				
////				String zeitstempelSplitKomma [] = doppelpunktRaus.split(".");
////				String zeitstempelSplittedKomma = zeitstempelSplitKomma[0];
////				System.out.println("dsdsdsds2 " +zeitstempelSplittedKomma);
//////				String zeitstempelStMinSek = zeitstempelSplittedKomma.replaceAll(".", "");
//////				System.out.println("problem ist" +zeitstempelStMinSek);
////				
////				int zeitstempelStundeMinutenSekunden = Integer.parseInt(zeitstempelSplittedKomma);
////				
////				System.out.println("zeitstempel Zeit ist: " +zeitstempelStundeMinutenSekunden);
//				
//				
//				//String zeitstempel
//				
//				
//				
//				
//				//String uuid = "08.08.21 14:25:25,053000000";
//				String zeitstempelSplitLeer [] = zeitstempel.split(" ");
//				String zeitstempelSplitLeerzeichen = zeitstempelSplitLeer[1];
//				String zeitstempelSplitKomma [] = zeitstempelSplitLeerzeichen.split(",");
//				String zeitstempelSplittedKomma = zeitstempelSplitKomma[0];
//				String zeitstempelStMinSek = zeitstempelSplittedKomma.replaceAll(":", "");
//				System.out.println("zeitstempelStMinSek" +zeitstempelStMinSek);
//				
//				String zeitstempelSplitPunkt [] = zeitstempelStMinSek.split(".");
//				String zeitstempelSplittedPunkt = zeitstempelSplitPunkt[0];
//				
//				int zeitstempelStundeMinutenSekunden = Integer.parseInt(zeitstempelSplittedPunkt);
//				
//				System.out.println("uuid Zeit ist: " +zeitstempelStundeMinutenSekunden);
//				
//				
////				try {
////					if((zeitstempelStundeMinutenSekunden < zeitJetztStundeMinuteSekunden) && (zeitstempelStundeMinutenSekunden > halbeStundeZurueckVonJetztStundeMinutenSekunden))
////					{
////						System.out.println("zeitstempelStundeMinutenSekunden ist richtig");
////						extentReport("", "info", nameZeitstempel+ " Zeitstempel liegt zwischen jetzige Zeit und halbe Stunde frueher", throwable);
////						return true;
////					}
////				}
////				catch (Exception e)
////				{
////					System.out.println("zeitstempelStundeMinutenSekunden ist falsch");
////					extentReport("", "info", nameZeitstempel+ " Zeitstempel liegt NICHT zwischen jetzige Zeit und halbe Stunde frueher", throwable);
////					//TestCaseFailDisplay("TC_2 IDMSFLA-T134 Folgende Attribute sind gesetzt: ID, UUID, Aktenzeichen, Personennummer, Dienststellennummer, Auslese_Zeitstempel, Kiosk_Name, Abholung_Bestaetigt", 2, column, "", e);
////					return false;
////				}
////				return true;
//				
//				if((zeitstempelStundeMinutenSekunden < zeitJetztStundeMinuteSekunden) && (zeitstempelStundeMinutenSekunden > halbeStundeZurueckVonJetztStundeMinutenSekunden))
//				{
//					System.out.println("zeitstempelStundeMinutenSekunden ist richtig");
//					extentReport("", "info", nameZeitstempel+ " Zeitstempel liegt zwischen jetzige Zeit und halbe Stunde frueher", throwable);
//					
//				}
//				else
//				{
//					System.out.println("zeitstempelStundeMinutenSekunden ist falsch");
//					extentReport("", "info", nameZeitstempel+ " Zeitstempel liegt NICHT zwischen jetzige Zeit und halbe Stunde frueher", throwable);
//					//TestCaseFailDisplay("TC_2 IDMSFLA-T134 Folgende Attribute sind gesetzt: ID, UUID, Aktenzeichen, Personennummer, Dienststellennummer, Auslese_Zeitstempel, Kiosk_Name, Abholung_Bestaetigt", 2, column, "", e);
//					
//				}
//				return true;
//			}
//			catch (Exception e)
//			{
//				System.out.println(e.getMessage());
//				return false;
//			}
//			
//			
//		}
//		
//		public static String getScreenshotName(String methodName)
//		{
//			Date d = new Date();
//			String fileName = methodName + "_" + d.toString().replace(":", "_").replace(" ",  "_") + ".png";
//			return fileName;
//		}
		
		// #######################################################################################################
		// Beschreibung: Browser und Property Datei (Testdata)
		// #######################################################################################################
		@BeforeSuite
		public void browserPropertyUndLog() throws IOException, InterruptedException {
			
			loadPropertyFile();
			String browser = properties.getProperty("browser");
			String url = properties.getProperty("flaURLtest");
			
			if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
				driverBrowser = new ChromeDriver();
				extentReport("", "Info", "Chrome Browser ist aufgerufen", throwable);
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "Resources/geckodriver.exe");

				driverBrowser = new FirefoxDriver();
				driverBrowser.manage().deleteAllCookies();
				extentReport("", "Info", "Firefox Browser ist aufgerufen", throwable);
			}
			else if(browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						"Resourcen/IEDriverServer.exe");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				driverBrowser = new InternetExplorerDriver(capabilities);
				extentReport("", "Info", "IE Browser ist aufgerufen", throwable);
			}
			else if(browser.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver", "Resources/msedgedriver.exe");
                //WICHTIG DesiredCapabilities capabilities = DesiredCapabilities.edge();
                //WICHTIG driverBrowser = new EdgeDriver(capabilities);
                driverBrowser = new EdgeDriver();
                extentReport("", "Info", "Edge Browser ist aufgerufen", throwable);
			}
			else {
				extentReport("", "Info", "Browserart ist nicht unterst�tzt", throwable);
			}
			driverBrowser.manage().window().maximize();
			driverBrowser.get(url);
			Thread.sleep(3500);
			extentReport("", "Info", url+ "Link ist aufgerufen", throwable);
			driverBrowser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}


		// #######################################################################################################
		// Description: Log4j initialization function, with its classname
		// testclass as the parameter
		// #######################################################################################################
//		@SuppressWarnings("rawtypes")
//		public void Loginit(String classname) {
//
//			Log = Logger.getLogger(GenericClass.class);
//
//			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//			// get current date time with Date()
//			Date date = new Date();
//			String d = dateFormat.format(date);
//
//			String logs = "LOGS/";
//
//			Logger rootLogger = Logger.getRootLogger();
//			Enumeration appenders = rootLogger.getAllAppenders();
//			FileAppender fa = null;
//			while (appenders.hasMoreElements()) {
//				Appender currAppender = (Appender) appenders.nextElement();
//				if (currAppender instanceof FileAppender) {
//					fa = (FileAppender) currAppender;
//				}
//			}
//			if (fa != null) {
//				fa.setFile(logs + classname + "/" + d + ".log");
//				fa.activateOptions();
//			} else {
//				Log.info("--- No File Appender found");
//			}
//		}
		
		// #######################################################################################################
		// Beschreibung: Winiumdriver Setup und Aufruf von Maris 
		// #######################################################################################################		
//		public void shellScriptAusfuehren() throws IOException, InterruptedException
//		{
//			Process pr = Runtime.getRuntime().exec("cmd /c resources\\testshell.sh");
//			StringBuilder output = new StringBuilder();
//			BufferedReader reader = new BufferedReader(new InputStreamReader (pr.getInputStream()));
//			String line;
//			while((line = reader.readLine()) != null) {
//				output.append(line + "\n");
//			}
//			
//			int exitVal = pr.waitFor();
//			if(exitVal == 0) {
//				System.out.println("erfolgreich");
//				System.out.println(output);
//				System.exit(0);
//			}
//			else {
//				System.out.println("pech");
//			}
//		}
		
		public void shellSkriptTestdatenAusfuellen(String PKZ, String AKZ, String DSN, String dateiPfad) {
			//String dateiPfad = "ShellskriptAuslesungen/Test/bin/auslesungen-testdata.txt";
			
			//String fileName = "ShellskriptAuslesungen/TestNotepad/sample.txt";
			
//			String PKZ = "51905263";
//			String AKZ = "7024839";
//			String DSN = "99A";	
			String total = PKZ + " " +AKZ + " " + DSN;

	        List<String> content = Arrays.asList(total);

	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dateiPfad))) {

	            for (String t : content) {
	                bw.write(t);
	                bw.newLine();
	                // same
	                //bw.write(System.lineSeparator());
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		public void shellScriptAusfuehren(String pfad) throws IOException, InterruptedException
		{
			//String ttt = "C:/Arun/IDMS/FLA/SeleniumTestautomatisierung/bin/uploadauslesungensecretsKopie.sh";
			//String pfadString = "pfad";
			
			ProcessBuilder pbb = new ProcessBuilder("cmd", "/c", pfad);
			pbb.redirectErrorStream(true);
			Process pr = pbb.start();
		}
		
		
		// #######################################################################################################
		// Beschreibung: Winiumdriver Setup und Aufruf von Maris 
		// #######################################################################################################		
		public void winiumDriverSetupUndAufrufMARiS() throws IOException, InterruptedException
		{
			try  
			{  
			//constructor of file class having file as argument  
			File file = new File("Resources/Winium.Desktop.Driver.exe");   
			if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not  
			{  
			System.out.println("Winium Driver nicht unterst�tzt");  
			return;  
			}  
			Desktop desktop = Desktop.getDesktop();  
			if(file.exists())         //checks file exists or not  
			desktop.open(file);              //opens the specified file  
			}  
			catch(Exception e)  
			{  
			e.printStackTrace();  
			} 
			
			String MarisPfad = "C:\\Program Files\\MARiS\\Gui-Shell\\MARiS.Gui.Shell.exe";
			DesktopOptions dop = new DesktopOptions();
			dop.setApplicationPath(MarisPfad);
			
			//URL url = new URL("http://localhost:9999");
			
			driverWinium = new WiniumDriver(new URL("http://localhost:9999"), dop); //http://127.0.0.1:9999/
			//driverWinium = new WiniumDriver(new URL(WiniumURL), dop);
			Thread.sleep(5000);
			//driverBrowser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		
		
		public void zephyrTestSchrittErstellenUpdaten(String testFallJira, String testFallBeschreibung, String testData, String erwErgebnis) throws IOException, InterruptedException
		{
			try{
				RestAssured.baseURI ="http://jira.server.intern";
				RequestSpecification request = RestAssured.given();
				 
				JSONObject testScript = new JSONObject();
				testScript.put("type", "STEP_BY_STEP");
				   
				JSONObject steps = new JSONObject();
				   
				steps.put("description", testFallBeschreibung); //testBeschreibungAuto
				steps.put("testData", testData); //tesdataAuto
				steps.put("expectedResult", erwErgebnis); //expresultAuto
				   
				JSONArray stepsArray = new JSONArray();
				stepsArray.put(steps);
				   
				testScript.put("steps", stepsArray);
				   
				JSONObject stepsObject = new JSONObject();
				   
				stepsObject.put("", steps);
				   
				JSONObject testFall = new JSONObject();
				testFall.put("testScript", testScript);
				   
				System.out.println("print this: " +testFall.toString());
				   
				request.header("Authorization", properties.getProperty("Authorization"));
				request.header("Content-Type", properties.getProperty("zyklusContentType"));
					 
				request.body(testFall.toString());
				Response response = request.put("/rest/atm/1.0/testcase/"+testFallJira); //IDMSTR-T470
					 
				int statusCode = response.getStatusCode();
				System.out.println("Success oder ?? Status code nach dem Aufruf von Erstellen Zephyr Testschritt ist: " +statusCode); 
			}
			catch(Exception e) {
			    System.out.println("Exception e ist: " +e);
			}
		}
		
		
		
		
		
		// #######################################################################################################
		// Beschreibung: TM4J Plugin in Jira - Erstellt ein Testzyklus 
		// #######################################################################################################
		public void zephyrTestZyklusErstellen() throws IOException, InterruptedException
		{
			try{
				SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");// dd/MM/yyyy
				Date now = new Date();
				String strDate = sdfDate.format(now);
				
				RestAssured.baseURI =properties.getProperty("TM4JBaseURL");
				 RequestSpecification request = RestAssured.given();
				 
				 JSONObject requestParams = new JSONObject();
				 requestParams.put("projectKey", properties.getProperty("zyklusProjectKey"));
				 requestParams.put("name", properties.getProperty("zyklusName")+strDate); //"TestJavabeispielJava4"
				 requestParams.put("iteration", properties.getProperty("zyklusIteration"));
				 requestParams.put("folder", properties.getProperty("zyklusFolder"));
				 requestParams.put("status", properties.getProperty("zyklusStatus"));
				 requestParams.put("description", properties.getProperty("zyklusDescription"));
				 
				 request.header("Authorization", properties.getProperty("Authorization"));
				 request.header("Content-Type", properties.getProperty("zyklusContentType"));
				 
				 request.body(requestParams.toString());
				 Response response = request.post(properties.getProperty("zyklusRestURL"));
				 
				 int statusCode = response.getStatusCode();
				 System.out.println("Status code nach dem Aufruf von Erstellen Zephyr Testzyklus ist: " +statusCode);
				 //extentReport("", "info", "Status code nach dem Aufruf von Erstellen Zephyr Testzyklus ist: "+statusCode, throwable);
				 
				 String successCode = response.getBody().print().toString();//.get("id");//.asString();//.htmlPath().get("id");
				 String successCodeSplit [] = successCode.split(":");
				 antwortVonResponseBodyErstellenTestZyklus = successCodeSplit[1].replaceAll("}", "").replaceAll("\"", "");
				 System.out.println("Antwort von Response Body nach dem Aufruf von Erstellen Zephyr Testzyklus ist: " +antwortVonResponseBodyErstellenTestZyklus);
				 extentReport("", "info", "Antwort von Response Body nach dem Aufruf von Erstellen Zephyr Testzyklus ist: "+antwortVonResponseBodyErstellenTestZyklus, throwable);
			}
			catch(Exception e) {
			    System.out.println("Exception e ist: " +e);
			}
		}
		
		// #######################################################################################################
		// Beschreibung: TM4J Plugin in Jira - F�hrt einen entsprechenden Testfall aus
		// #######################################################################################################
	public void zephyrTestfallAusfuehren(String testfallErgebnis, String testcase) throws IOException {
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss-SSS");// dd/MM/yyyy..... ("yyyy-MM-dd_HH-mm-ss");
			
			SimpleDateFormat sdfDate1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfDate2 = new SimpleDateFormat("HH:mm:ss");
			Date now = new Date();
			String strDate1 = sdfDate1.format(now);
			String strDate2 = sdfDate2.format(now);

			RestAssured.baseURI = properties.getProperty("TM4JBaseURL");
			RequestSpecification request = RestAssured.given();
			JSONObject requestParams = new JSONObject();

			if (testfallErgebnis.equalsIgnoreCase("Pass")) {
				requestParams.put("status", "Pass");
			} else if(testfallErgebnis.equalsIgnoreCase("Fail")){
				requestParams.put("status", "Fail");
			}
			else {
				extentReport("", "info", "Testfallergebnis nicht erkannt", throwable);
			}
			requestParams.put("environment", properties.getProperty("TFenvironment"));
			requestParams.put("executedBy", properties.getProperty("TFexecutedBy"));
			requestParams.put("actualStartDate", strDate1+ "T" +strDate2+ "-0300");
			requestParams.put("actualEndDate", strDate1+ "T" +strDate2+ "-0300");
			
//			requestParams.put("actualStartDate", "2016-02-14T19:22:00-0300");
//			requestParams.put("actualEndDate", "2016-02-15T19:22:00-0300");

			request.header("Authorization", properties.getProperty("Authorization"));
			request.header("Content-Type", properties.getProperty("TFContentType"));

			request.body(requestParams.toString());
			
			//System.out.println("Antwort von Response Body nach dem Aufruf von Erstellen Zephyr Testzyklus 2 ist: " +antwortVonResponseBodyErstellenTestZyklus);
			//extentReport("", "info", "Antwort von Response Body nach dem Aufruf von Erstellen Zephyr Testzyklus 2 ist: "+antwortVonResponseBodyErstellenTestZyklus, throwable);
			
			Response response = request.post("/rest/atm/1.0/testrun/"+antwortVonResponseBodyErstellenTestZyklus+"/testcase/"+testcase+"/testresult");

			int statusCode = response.getStatusCode();
			//System.out.println("Status code ist: " + statusCode);
			
			if(statusCode < 400)
			{
				extentReport("", "info", "Testfallergebnis in Jira updatet erfolgreich. Status code: "+statusCode+ ", Testzyklus: "+antwortVonResponseBodyErstellenTestZyklus+", Testfall: "+testcase, throwable);
			}
			else
			{
				extentReport("", "info", "Testfallergebnis in Jira NICHT updatet. Status code: "+statusCode, throwable);
			}
			
			//extentReport("", "info", "Status code nach dem Aufruf vom ausgef�hrten Zephyr Testfall ist: "+statusCode, throwable);
			
//			String successCode = response.getBody().print().toString();// .get("id");//.asString();//.htmlPath().get("id");
//			String successCodeSplit[] = successCode.split(":");
//			String successCodeSplitArray2 = successCodeSplit[1].replace("}", "");
			//System.out.println("Antwort von Response Body ist: " + successCodeSplitArray2);
			//extentReport("", "info", "Antwort von Response Body nach dem Aufruf vom ausgef�hrten Zephyr Testfall  ist: "+successCodeSplitArray2, throwable);
		}
		catch(Exception e) {
		    System.out.println("Exception e ist: " +e);
		}
	}
		
		// #######################################################################################################
		// Beschreibung: TM4J Plugin in Jira - H�ngt eine Datei unter dem Testzyklus an
		// #######################################################################################################
		public void reportimTestZyklusAnhaengen(String reportPfad, String description)
		{
			try {

				RestAssured.baseURI =properties.getProperty("TM4JBaseURL");
			    RestAssured.given()
			    		.header("Authorization", properties.getProperty("Authorization"))
			            .header("content-type", properties.getProperty("dateiContentType"))
			            .multiPart("file",new File( reportPfad))
			            .formParam("description", description)
			            .when()
			            .post("/rest/atm/1.0/testrun/"+antwortVonResponseBodyErstellenTestZyklus+"/attachments")
			            .then()
			            .assertThat()
			            .statusCode(201);
			    
			    
			}
			catch(Exception e) {
			    System.out.println("Exception e ist: " +e);
			}
		}
		
//		// #######################################################################################################
//		// Beschreibung: TM4J Plugin in Jira - H�ngt eine Datei unter dem Testzyklus an
//		// #######################################################################################################
//		public void zephyrExcelReportimTestZyklusAnhaengen()
//		{
//			try {
//
//				RestAssured.baseURI =properties.getProperty("TM4JBaseURL");
//			    RestAssured.given()
//			    		.header("Authorization", properties.getProperty("Authorization"))
//			            .header("content-type", properties.getProperty("dateiContentType"))
//			            .multiPart("file",new File( properties.getProperty("fileExtentReportPfad")))
//			            .formParam("description", properties.getProperty("fileDescriptionParameter"))
//			            .when()
//			            .post("/rest/atm/1.0/testrun/"+antwortVonResponseBodyErstellenTestZyklus+"/attachments")
//			            .then()
//			            .assertThat()
//			            .statusCode(201);
//			}
//			catch(Exception e) {
//			    System.out.println("Exception e ist: " +e);
//			}
//		}
		
		
		
		// #######################################################################################################
		// Beschreibung: Inhalt von PDF vergleichen mit angegebenem Wert
		// #######################################################################################################
		public String pdfContentVerifizieren(String Pfad) throws IOException
		{
			FileInputStream obj = new FileInputStream(Pfad);
			PDDocument objDoc = PDDocument.load(obj);
			PDFTextStripper objPDFStrp = new PDFTextStripper();
			String pdfContent = objPDFStrp.getText(objDoc);
			System.out.println("pdfContent ist: " +pdfContent);
			return pdfContent;
		}
		
		// #######################################################################################################
		// Description: entsprechden Link aufrufen
		// #######################################################################################################
		public void getURL(String url) {
			driverBrowser.navigate().to(url);
		}

		// #######################################################################################################
		// Description: Titel von Login Page erhalten
		// #######################################################################################################
		public String GetPageTitel() throws IOException
		{
			extentReport("", "info", "- - - Page Titel ist: ", throwable);
			return driverBrowser.getTitle();
		}

		// #######################################################################################################
		// Beschreibung: Explicit Wait Function - Browser. Waits for the specific amount of time, until it finds the specified webelement on the DOM
		// #######################################################################################################
		public void ExplicitWait(WebElement element, int time) {
			WebDriverWait wait = new WebDriverWait(driverBrowser, time);
			element = wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		
		// #######################################################################################################
		// Beschreibung: Explicit Wait Function - Browser. Waits for the specific amount of time, until it finds the specified webelement on the DOM
		// #######################################################################################################
		public void ExplicitWaitVisible(WebElement element, int time) {
			WebDriverWait wait = new WebDriverWait(driverBrowser, time);
			element = wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		// #######################################################################################################
		// Beschreibung: Explicit Wait Function - Windows. Waits for the specific amount of time, until it finds the specified webelement on the DOM
		// #######################################################################################################
		public void ExplicitWaitWin(WebElement element, int time) {
			WebDriverWait wait = new WebDriverWait(driverWinium, time);
			//element = wait.until(ExpectedConditions.visibilityOf(element)); //
			element = wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		
		// #######################################################################################################
		// Beschreibung: Explicit Wait Function - Windows. Waits for the specific amount of time, until it finds the specified webelement on the DOM
		// #######################################################################################################
		public void ExplicitWaitWinVisible(WebElement element, int time) {
			WebDriverWait wait = new WebDriverWait(driverWinium, time);
			element = wait.until(ExpectedConditions.visibilityOf(element));
		}

		// #######################################################################################################
		// Beschreibung: entsprechender Text im Textfeld schreiben - Browser
		// #######################################################################################################
		public void sendKeys(WebElement xpath, String strValue, String feld) throws InterruptedException, IOException
		{
			Thread.sleep(100);
			ExplicitWait(xpath, 30);
			xpath.clear();
			xpath.sendKeys(strValue, Keys.TAB);
//			driverBrowser.findElement(By.xpath(xpath)).clear();
//			driverBrowser.findElement(By.xpath(xpath)).sendKeys(strValue);
			extentReport("", "info", strValue+ " wurde im Textfeld: " +feld+ " geschrieben", throwable);
		}
		
		// #######################################################################################################
		// Beschreibung: auf entsprechenden Element klicken - Browser
		// #######################################################################################################
		public void click(WebElement xpath, String button) throws InterruptedException, IOException
		{
			Thread.sleep(700);
			ExplicitWait(xpath, 30);
			xpath.click();
//			driverBrowser.findElement(By.xpath(xpath)).click();
			extentReport("", "info", "auf " +button+ " geklickt", throwable);
		}
		
		// #######################################################################################################
		// Beschreibung: auf entsprechenden Element klicken - Windows
		// #######################################################################################################
		public void clickWin(WebElement xpath, String button) throws InterruptedException, IOException
		{
			Thread.sleep(200);
			ExplicitWaitWin(xpath, 30);
			xpath.click();
//			driverBrowser.findElement(By.xpath(xpath)).click();
			extentReport("", "info", "auf " +button+ " geklickt", throwable);
		}
		
		// #######################################################################################################
		// Beschreibung: entsprechender Text im Textfeld schreiben - Browser
		// #######################################################################################################
		public void sendKeysWin(WebElement xpath, String strValue, String feld) throws InterruptedException, IOException
		{
			Thread.sleep(200);
			ExplicitWaitWin(xpath, 30);
			xpath.clear();
			xpath.sendKeys(strValue);
//			driverBrowser.findElement(By.xpath(xpath)).clear();
//			driverBrowser.findElement(By.xpath(xpath)).sendKeys(strValue);
			//Log.info("--- " + strValue + " is entered in the element");
			extentReport("", "info", strValue+ " wurde im Textfeld: " +feld+ " geschrieben", throwable);
		}
		
		// #######################################################################################################
		// Beschreibung: scrollen bis das Element sichtbar ist und drauf klicken
		// #######################################################################################################
		public void clickScroll(WebElement xpath, String button) throws InterruptedException, IOException
		{
			Thread.sleep(200);
			ExplicitWait(xpath, 30);
			
			JavascriptExecutor jse = (JavascriptExecutor)driverBrowser;
			jse.executeScript("arguments[0].scrollIntoView()", xpath); 
			xpath.click();
//			driverBrowser.findElement(By.xpath(xpath)).click();
			//Log.info("---element is clicked");
			extentReport("", "info", "auf " +button+ " geklickt", throwable);
		}
		
		// #######################################################################################################
		// Beschreibung: auf den entsprechenden Element rechtklicken - Browser
		// #######################################################################################################
		public void rightclick(WebElement xpath, String button) throws InterruptedException, IOException
		{
			Thread.sleep(200);
			ExplicitWait(xpath, 30);
			Actions action = new Actions(driverBrowser);
			action.contextClick(xpath).perform();
			//Log.info("---  element is right clicked");
			extentReport("", "info", "auf " +button+ " rechtgeklickt", throwable);
		}
		
		// #######################################################################################################
		// Beschreibung: auf den entsprechenden Element rechtklicken - Windows
		// #######################################################################################################
		public void rightclickWin(WebElement xpath, String button) throws InterruptedException, IOException
		{
			Thread.sleep(200);
			ExplicitWaitWin(xpath, 30);
			Actions action = new Actions(driverWinium);
			action.contextClick(xpath).perform();
			//Log.info("---  element is right clicked");
			extentReport("", "info", "auf " +button+ " rechtgeklickt", throwable);
		}
		
		// #######################################################################################################
		// Beschreibung: auf den entsprechenden Element doppelklicken - Windows
		// #######################################################################################################
		public void doubleclickWin(WebElement xpath, String button) throws InterruptedException, IOException
		{
			Thread.sleep(200);
			ExplicitWaitWin(xpath, 30);
			Actions action = new Actions(driverWinium);
			action.doubleClick(xpath).perform();
			//Log.info("---  element is double clicked");
			extentReport("", "info", "auf " +button+ " doppelgeklickt", throwable);
		}
		
		// #######################################################################################################
		// Beschreibung: entsprechendes PDF von dem angegebenen Pfad l�schen
		// #######################################################################################################
		public void deletePDF(String pdfPfad)
		{
			File deletePdfArabisch = new File(pdfPfad);
			deletePdfArabisch.deleteOnExit();
		}

		// #######################################################################################################
		// Beschreibung: im Extent Report (HTML) und Excel schreiben, dass der Testfall erfolgreich ist
		// #######################################################################################################
		public void TestCasePassDisplay(String methodName, String testfall) throws Exception {
			System.out.println(methodName+ " method is PASSED");
			extentReport("", "pass", "Testfallergebnis: "+methodName + " - erfolgreich", throwable);
			//SetCellDataTRPASS("PASS", row, column);
			
			if(!testfall.isEmpty())
			{
				zephyrTestfallAusfuehren("Pass", testfall);
			}
		}


		// #######################################################################################################
		// Beschreibung: im Extent Report (HTML) und Excel schreiben, dass der Testfall NICHT erfolgreich ist - Browser
		// #######################################################################################################
		public void TestCaseFailDisplay(String methodName, String testfall, Throwable throwable) throws Exception {
			System.out.println(methodName+ " method is FAILED");
			extentReport("", "fail", "Testfallergebnis: "+methodName + " - fehlgeschlagen", throwable);
			//SetCellDataTRFAIL("FAIL", row, column);
			
			if(!testfall.isEmpty())
			{
				zephyrTestfallAusfuehren("Fail", testfall);
			}
		}
		
		// #######################################################################################################
		// Beschreibung: im Extent Report (HTML) und Excel schreiben, dass der Testfall NICHT erfolgreich ist - Windows
		// #######################################################################################################
		public void TestCaseFailDisplayWin(String methodName, int row, int column, String testfall, Throwable throwable) throws Exception {
			System.out.println(methodName+ " method is FAILED");
			extentReport("", "failwin", "Testfallergebnis: "+methodName + " ist fehlgeschlagen", throwable);
			//SetCellDataTRFAIL("FAIL", row, column);
			
			if(!testfall.isEmpty())
			{
				zephyrTestfallAusfuehren("Fail", testfall);
			}
		}

		// #######################################################################################################
		// Beschreibung: Mouse hover und halten
		// #######################################################################################################
		public boolean MouseOverandHold(WebElement element) {
			try {
				Mouse mouse = ((HasInputDevices) driverBrowser).getMouse();
				Locatable hoverItem = (Locatable) element;
				mouse.mouseMove(hoverItem.getCoordinates());
				return true;
			} catch (Exception e) {
			}
			return false;
		}


		// #######################################################################################################
		// Beschreibung: Element highlighten
		// #######################################################################################################
		public boolean elementHighlight(WebElement element) {
			try {
				for (int i = 0; i < 5; i++) {
					JavascriptExecutor js = (JavascriptExecutor) driverBrowser;
					js.executeScript(
							"arguments[0].setAttribute('style', arguments[1]);",
							element, "color: yellow; border: 5px solid yellow;");
					js.executeScript(
							"arguments[0].setAttribute('style', arguments[1]);",
							element, "");
					Thread.sleep(300);
				}
				return true;
			} catch (Exception e) {
			}
			return false;
		}


		// #######################################################################################################
		// Beschreibung: implicit warten bis angegebenen Sekunden
		// #######################################################################################################
		public void Implicitwait(int intTimeOut) {
			driverBrowser.manage().timeouts().implicitlyWait(intTimeOut, TimeUnit.SECONDS);
		}


		// #######################################################################################################
		// Beschreibung: Execute action on the popup
		// #######################################################################################################
		public boolean ExecuteActionOnPopup(String strAction) {
			// @ verify Pop up is present or not.
			try {
				switch (strAction.toLowerCase().trim()) {
				case "accept":
				case "ok":
				case "yes":
					driverBrowser.switchTo().alert().accept();
					break;
				case "dismiss":
				case "no":
				case "cancel":
					driverBrowser.switchTo().alert().dismiss();
					break;
				case "gettext":
					dicOutput.put("strOutPut", driverBrowser.switchTo().alert().getText());
					break;
				}
			} catch (Exception e) {
				return false;
			}
			return true;
		}


		// #######################################################################################################
		// Description: To get the temp path and return using String
		// #######################################################################################################
		public String GetTempPath() {
			return System.getProperty("java.io.tmpdir");
		}

		// #######################################################################################################
		// Description: To create a directory on the specified path
		// #######################################################################################################
		public boolean CreateDirectory(String DirectoryName) {
			File file = new File(DirectoryName);
			if (!file.exists()) {
				if (file.mkdirs()) {
					return true;
				} else {
					ErrDescription = "Failed to create directory!";
					return false;
				}
			}
			return true;
		}
		
		
		


		// #######################################################################################################
		// Description: To delete the directory on the specified path
		// #######################################################################################################
		public void DeleteDirectory(String DirectoryName) {
			File directory = new File(DirectoryName);
			if (!directory.exists()) {
				return;
			} else {
				try {
					DeleteFolderContent(directory);
				} catch (Exception e) {
					System.out.println("Exception");
				}
			}
		}


		// #######################################################################################################
		// Description: To delete the folder content
		// #######################################################################################################
		public void DeleteFolderContent(File file) {
			if (file.isDirectory()) {

				// directory is empty, then delete it
				if (file.list().length == 0) {
					file.delete();

				} else {
					// list all the directory contents
					String files[] = file.list();

					for (String temp : files) {
						// construct the file structure
						File fileDelete = new File(file, temp);

						// recursive delete
						DeleteFolderContent(fileDelete);
					}

					// check the directory again, if empty then delete it
					if (file.list().length == 0) {
						file.delete();
					}
				}

			} else {
				// if file, then delete it
				file.delete();
			}
		}


		// #######################################################################################################
		// Description: To get the time difference
		// #######################################################################################################
		public String GetTimeDiffernce(String dateStart, String dateStop) {
			String Difference = "";
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			try {
				java.util.Date dt1 = format.parse(dateStart);
				java.util.Date dt2 = format.parse(dateStop);
				long diff = dt2.getTime() - dt1.getTime();
				long diffSeconds = TimeUnit.MILLISECONDS.toSeconds(diff) % 60;
				long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60;
				long diffHours = TimeUnit.MILLISECONDS.toHours(diff) % 24;
				String Hour = "";
				String Minutes = "";
				String Second = "";
				if (diffHours < 10) {
					Hour = "0" + diffHours;
				} else {
					Hour = String.valueOf(diffHours);
				}
				if (diffMinutes < 10) {
					Minutes = "0" + diffMinutes;
				} else {
					Minutes = String.valueOf(diffMinutes);
				}
				if (diffSeconds < 10) {
					Second = "0" + diffSeconds;
				} else {
					Second = String.valueOf(diffSeconds);
				}
				Difference = Hour + ":" + Minutes + ":" + Second;
			} catch (Exception e) {
			}
			return Difference;
		}


		// #######################################################################################################
		// Description: To read contents from a text file and return the content using String
		// #######################################################################################################
		public String ReadAllTextFromFile(String FilePath) {
			String Text = "";
			try {
				Text = new String(Files.readAllBytes(Paths.get(FilePath)));
			} catch (Exception e) {
			}
			return Text;
		}

		// #######################################################################################################
		// Description: To send an email on the specified credentials using PORT and HOST
		// #######################################################################################################
		public void SendMail() {
			final String username = "arun.deepak@gmx.de";
			final String password = "";

			Properties props = new Properties();
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.starttls.enable", true);
			props.put("mail.smtp.host", "mail.gmx.net");
			props.put("mail.smtp.port", "995");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					});
			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("from.arun.deepak@gmx.de"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse("to.arundeepak_02@ymail.com"));
				message.setSubject("Testing Subject");
				message.setText("PFA");

				MimeBodyPart messageBodyPart = new MimeBodyPart();

				Multipart multipart = new MimeMultipart();

				messageBodyPart = new MimeBodyPart();
				String file = "test-output/testng-reports.js";
				String fileName = "testng reports";
				DataSource source = new FileDataSource(file);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(fileName);
				multipart.addBodyPart(messageBodyPart);

				message.setContent(multipart);

				System.out.println("Sending");

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}

		// #######################################################################################################
		// Description: Pfad und Name von Excelsheet einsetzen
		// #######################################################################################################
		// Set the File path and Sheetname as Arguments to set the excel file
//		public void SetExcelFileTR(String Path, String SheetName) throws Exception {
//
//
//			try {
//				Path_TestDataTR = Path;
//
//				// Open the Excel file
//				FileInputStream ExcelFile = new FileInputStream(Path);
//
//				ZipSecureFile.setMinInflateRatio(0);
//				// Access the required test data sheet
//				ExcelWBookTR = new XSSFWorkbook(ExcelFile);
//				ExcelWSheetTR = ExcelWBookTR.getSheet(SheetName);
//			} catch (Exception e) {
//				throw (e);
//			}
//		}
		
		// #######################################################################################################
		// Beschreibung: Testergebnisse auf angegebene Zeile schreiben
		// #######################################################################################################
//		public void SetCellData(String Result, int RowNum, int ColNum) throws Exception
//        {
//            try {
//                if (ExcelWSheetTR.getRow(RowNum) == null) {
//                        RowTR = ExcelWSheetTR.createRow(RowNum);
//                }
//                else
//                {
//                RowTR = ExcelWSheetTR.getRow(RowNum);
//                CellTR = RowTR.getCell(ColNum, xRow.RETURN_BLANK_AS_NULL);
//                }
//                
//                CellStyle style = ExcelWBookTR.createCellStyle();  
//                // Setting Background color  
////                style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());  
////                style.setFillPattern(FillPatternType.BIG_SPOTS); 
//                
//                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());  
//                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
//
//                if (CellTR == null)
//                {
//                        CellTR = RowTR.createCell(ColNum);
//                        CellTR.setCellValue("");
//                        
//                        CellTR.setCellValue(Result);
//                        CellTR.setCellStyle(style);
//                }
//                else
//                {
//                        CellTR.setCellValue(Result);
//                        CellTR.setCellStyle(style);
//                }
//
//                // Constant variables Test Data path and Test Data file name
//                FileOutputStream fileOut = new FileOutputStream(Path_TestDataTR);
//                ExcelWBookTR.write(fileOut);
//                fileOut.flush();
//                fileOut.close();
//        }
//        catch (Exception e)
//        {
//                e.printStackTrace();
//                throw (e);
//        }
//        }

		// #######################################################################################################
		// Beschreibung: Testergebnisse auf angegebene Zeile schreiben
		// #######################################################################################################
//		public void SetCellDataTRFAIL(String Result, int RowNum, int ColNum) throws Exception
//		{
//			try {
//				if (ExcelWSheetTR.getRow(RowNum) == null) {
//					RowTR = ExcelWSheetTR.createRow(RowNum);
//				}
//				else
//				{
//				RowTR = ExcelWSheetTR.getRow(RowNum);
//				CellTR = RowTR.getCell(ColNum, xRow.RETURN_BLANK_AS_NULL);
//				}
//				
//				CellStyle style = ExcelWBookTR.createCellStyle();  
//                // Setting Background color  
////                style.setFillBackgroundColor(IndexedColors.RED.getIndex());  
////                style.setFillPattern(FillPatternType.BIG_SPOTS);  
//                
//                style.setFillForegroundColor(IndexedColors.RED.getIndex());  
//                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
//
//				if (CellTR == null)
//				{
//					CellTR = RowTR.createCell(ColNum);
//					CellTR.setCellValue("");
//					//CellTR.setCellValue(Result);
//					CellTR.setCellValue(Result);
//					CellTR.setCellStyle(style);
//				}
//				else
//				{
//					//CellTR.setCellValue(Result);
//					CellTR.setCellValue(Result);
//					CellTR.setCellStyle(style);
//				}
//
//				// Constant variables Test Data path and Test Data file name
//				FileOutputStream fileOut = new FileOutputStream(Path_TestDataTR);
//				ExcelWBookTR.write(fileOut);
//				fileOut.flush();
//				fileOut.close();
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//				throw (e);
//			}
//		}

		// #######################################################################################################
		// Beschreibung: Testergebnisse auf angegebene Zeile schreiben
		// #######################################################################################################
//	        public void SetCellDataTRPASS(String Result, int RowNum, int ColNum) throws Exception
//	        {
//	            try {
//	                if (ExcelWSheetTR.getRow(RowNum) == null) {
//	                        RowTR = ExcelWSheetTR.createRow(RowNum);
//	                }
//	                else
//	                {
//	                RowTR = ExcelWSheetTR.getRow(RowNum);
//	                CellTR = RowTR.getCell(ColNum, xRow.RETURN_BLANK_AS_NULL);
//	                }
//	                
//	                CellStyle style = ExcelWBookTR.createCellStyle();  
//	                // Setting Background color  
////	                style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());  
////	                style.setFillPattern(FillPatternType.BIG_SPOTS);  
//	                
//	                style.setFillForegroundColor(IndexedColors.GREEN.getIndex());  
//	                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
//
//	                if (CellTR == null)
//	                {
//	                        CellTR = RowTR.createCell(ColNum);
//	                        CellTR.setCellValue("");
//	                        
//	                        CellTR.setCellValue(Result);
//	                        CellTR.setCellStyle(style);
//	                        
//	                        //CellTR.setCellValue(Result);
//	                        //CellTR.setCellValue(Result);
//	                }
//	                else
//	                {
//	                        CellTR.setCellValue(Result);
//	                        //CellTR.setCellValue(Result);
//	                        CellTR.setCellStyle(style);
//	                }
//
//	                // Constant variables Test Data path and Test Data file name
//	                FileOutputStream fileOut = new FileOutputStream(Path_TestDataTR);
//	                ExcelWBookTR.write(fileOut);
//	                fileOut.flush();
//	                fileOut.close();
//	        }
//	        catch (Exception e)
//	        {
//	                e.printStackTrace();
//	                throw (e);
//	        }
//	        }

			// #######################################################################################################
			// Beschreibung: Wert von angegebene Zeile nehmen und in String speichern
			// #######################################################################################################
//		public String GetCellData(int RowNum, int ColNum) throws Exception {
//
//			try {
//
//				CellTR = ExcelWSheetTR.getRow(RowNum).getCell(ColNum);
//				String CellData;
//				switch (CellTR.getCellType()) {
//				case NUMERIC:
//					System.out.println("its an numeric value");
//					CellData = "" + (int) CellTR.getNumericCellValue();
//					break;
//				case STRING:
//					CellData = CellTR.getStringCellValue();
//					break;
//				default:
//					CellData = "";
//				}
//				return CellData.trim();
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//				return "";
//			}
//		}

		// #######################################################################################################
		// Beschreibung: letzte Reihenummer von Excel erhalten
		// #######################################################################################################
//		public int GetLastRowNumber() {
//			return ExcelWSheetTR.getLastRowNum();
//		}

		// #######################################################################################################
		// Beschreibung: Wert von angegebener Reihe erhalten
		// #######################################################################################################
//		public String GetRow(int rowNum) {
//			return ExcelWSheetTR.getRow(rowNum).toString();
//		}

		// #######################################################################################################
		// Beschreibung: pr�fen ob angegebene Reihe null ist
		// #######################################################################################################
//		public boolean IsRowEmpty(int row, int exceed) {
//
//			RowTR = ExcelWSheetTR.getRow(row);
//			if (RowTR == null)
//				return (true);
//
//			for (int c = RowTR.getFirstCellNum(); c < exceed; c++) {
//				if (RowTR.getCell(c) == null
//						|| RowTR.getCell(c).toString().trim().equals(""))
//					return true;
//			}
//			return false;
//		}

		// #######################################################################################################
		// Beschreibung: Testergebnisse auf angegebene Zeile schreiben
		// #######################################################################################################
		// Set the File path and Sheetname as Arguments to set the excel file
//			public void SetExcelFileTD(String Path, String SheetName) throws Exception {
//
//				try {
//					Path_TestDataTD = Path;
//
//					// Open the Excel file
//					FileInputStream ExcelFile = new FileInputStream(Path);
//
//					// Access the required test data sheet
//					ExcelWBookTD = new XSSFWorkbook(ExcelFile);
//					ExcelWSheetTD = ExcelWBookTD.getSheet(SheetName);
//				} catch (Exception e) {
//					throw (e);
//				}
//			}

			// #######################################################################################################
			// Beschreibung: Wert von angegebene Zeile nehmen und in String speichern
			// #######################################################################################################
//			public String GetCellDataTD(int RowNum, int ColNum) throws Exception {
//
//				try {
//
//					CellTD = ExcelWSheetTD.getRow(RowNum).getCell(ColNum);
//					String CellData;
//					switch (CellTD.getCellType()) {
//					case NUMERIC:
//						System.out.println("its an numeric value");
//						CellData = "" + (int) CellTD.getNumericCellValue();
//						break;
//					case STRING:
//						CellData = CellTD.getStringCellValue();
//						break;
//					default:
//						CellData = "";
//					}
//					return CellData.trim();
//				}
//				catch (Exception e)
//				{
//					e.printStackTrace();
//					return "";
//				}
//			}

}
