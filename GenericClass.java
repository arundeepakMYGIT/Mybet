package genericClass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


//########################################################################################################
//# Class Type : Generic Class
//# Class Name : GenericClass
//#------------------------------------------------------------------------------------------------------
//# Description: Generic class is collection of all kind of function which is globally accessible for
//#	 all kind of application . This is application independent framework.
//#------------------------------------------------------------------------------------------------------
//# BrowserName - Google Chrome, IE, Mozilla Firefox, Opera
//########################################################################################################

public class GenericClass {
	// ########################################################################################################
	// ### Static and Dictionary key that are used in globally (Function level,

	public String ErrDescription;
	public Hashtable<String, String> dicConfig = new Hashtable<String, String>();
	public Hashtable<String, String> dicOutput = new Hashtable<String, String>();
	public WebDriver driver = null;
	// public static WebElement element = null;
	public List<WebElement> elements;
	public String sReportFileName = ""; // HTML
	public int iStepCount = 0;
	public int iPassStepCount = 0;
	public int iFailStepCount = 0;
	public int TotalFailStepCount = 0;
	public String sSscreenshotFile = "";
	public int IterationCount = 1;
	public String StartDateExecution = "";
	public String EndDateExecution = "";
	public Logger Log;

	private HSSFSheet ExcelWSheetTR;
	private HSSFWorkbook ExcelWBookTR;
	private HSSFCell CellTR;
	private HSSFRow RowTR;
	private String Path_TestDataTR;

	private HSSFSheet ExcelWSheetTD;
	private HSSFWorkbook ExcelWBookTD;
	private HSSFCell CellTD;
	@SuppressWarnings("unused")
	private HSSFRow RowTD;
	@SuppressWarnings("unused")
	private String Path_TestDataTD;

	String result;

	JSONObject res;
	JSONObject json_properties;
	JSONObject firstObj;
	JSONArray firArray;
	String getProfilURL = "https://mcom-qa.kohlsecommerce.com/skavastream/xact/v5/kohls/profile/get?campaignId=4";

	Connection connection;
	ResultSet resultSet;

	// #######################################################################################################
	// Constructor: GenericClass
	// Description: Log4J declaration with this class name and call the browser
	// setup function
	// #######################################################################################################
	public GenericClass() {

		try {
			Loginit(this.getClass().getName());
			// IESetup();
			ChromeSetup();
			// FirefoxSetup();

		} catch (Exception e) {
			throw new IllegalStateException("Can't start IE", e);
		}
	}


	// #######################################################################################################
	// Description: Log4j initialization function, with its classname
	// testclass as the parameter
	// #######################################################################################################
	@SuppressWarnings("rawtypes")
	public void Loginit(String classname) {

		Log = Logger.getLogger(GenericClass.class);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		// get current date time with Date()
		Date date = new Date();
		String d = dateFormat.format(date);

		String logs = "LOGS/";

		Logger rootLogger = Logger.getRootLogger();
		Enumeration appenders = rootLogger.getAllAppenders();
		FileAppender fa = null;
		while (appenders.hasMoreElements()) {
			Appender currAppender = (Appender) appenders.nextElement();
			if (currAppender instanceof FileAppender) {
				fa = (FileAppender) currAppender;
			}
		}
		if (fa != null) {
			fa.setFile(logs + classname + "/" + d + ".log");
			fa.activateOptions();
		} else {
			Log.info("--- No File Appender found");
		}
	}


	// #######################################################################################################
	// Description: Navigate to the provided URL
	// #######################################################################################################
	public void getURL(String url) {
		driver.navigate().to(url);
	}

	// #######################################################################################################
	// Description: Titel von Login Page kreigen
	// #######################################################################################################
	public String GetPageTitel()
	{
		Log.info("- - - Page Titel ist: " +driver.getTitle());
		return driver.getTitle();
	}


	// #######################################################################################################
	// Description: IE browser Setup
	// #######################################################################################################
	public void IESetup() {
		System.setProperty("webdriver.ie.driver",
				"Resourcen/IEDriverServer.exe");
		// driver = new InternetExplorerDriver();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		// capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		// capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		// capabilities.setCapability("ignoreProtectedModeSettings", true);
		// capabilities.setCapability("disable-popup-blocking", true);
		// capabilities.setCapability("enablePersistentHover", true);
		driver = new InternetExplorerDriver(capabilities);
		Log.info("--- IE Driver is invoked");
	}

	// #######################################################################################################
        // Description: IE browser Setup
        // #######################################################################################################
        public void EdgeSetup() {
                System.setProperty("webdriver.edge.driver", "Resourcen/msedgedriver.exe");
                DesiredCapabilities capabilities = DesiredCapabilities.edge();
                driver = new EdgeDriver(capabilities);
                driver = new EdgeDriver();

                driver.manage().window().maximize();
                Log.info("--- Edge Driver is invoked");
        }


	// #######################################################################################################
	// Description: Chrome browser Setup
	// #######################################################################################################
	public void ChromeSetup() {
//		System.setProperty("webdriver.chrome.driver", "Resourcen/chromedriver.exe");
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--start-maximized");
//		//driver = new ChromeDriver(options);
//		Log.info("--- Chrome Driver is invoked");


		System.setProperty("webdriver.chrome.driver", "Resourcen/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Log.info("--- Chrome Driver is invoked");
	}


	// #######################################################################################################
	// Description: Navigate to the provided URL
	// #######################################################################################################
	public void FirefoxSetup() {
		System.setProperty("webdriver.gecko.driver", "Resourcen/geckodriver.exe");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("firefox_binary","Resourcen/geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();







		//FirefoxProfile profile = new FirefoxProfile();

		//profile.setAcceptUntrustedCertificates(true);
		//profile.setAssumeUntrustedCertificateIssuer(false);
		//driver = new FirefoxDriver(profile);

		// ProfilesIni profile = new ProfilesIni();
		// FirefoxProfile myprofile = profile.getProfile("default");
		// myprofile.setAcceptUntrustedCertificates(true);
		// myprofile.setAssumeUntrustedCertificateIssuer(true);
		// driver = new FirefoxDriver(myprofile);

		// DesiredCapabilities dc = DesiredCapabilities.firefox();
		// dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		//
		// FirefoxProfile profile = new FirefoxProfile();
		// profile.setAcceptUntrustedCertificates(true);
		//
		// dc.setCapability(FirefoxDriver.PROFILE, profile);
		//
		// // this is the important line - i.e. don't use Marionette
		// dc.setCapability(FirefoxDriver.MARIONETTE, false);
		//
		// driver = new FirefoxDriver(dc);

		//DesiredCapabilities dc = DesiredCapabilities.firefox();
		//dc.setCapability(FirefoxDriver.MARIONETTE, false);

		// ffProfilePath =
		// "C:\Users\arundeepak\AppData\Roaming\Mozilla\Firefox\Profiles\sm7mmj99.Standard-Benutzer";
		// profile = webdriver.FirefoxProfile(profile_directory=ffProfilePath)
		// geckoPath = 'D:\Work\PyTestFramework\geckodriver.exe'
		// browser = webdriver.Firefox(firefox_profile=profile,
		// capabilities=firefox_capabilities, executable_path=geckoPath)
		// browser.get('http://stackoverflow.com')

		Log.info("--- Firefox Driver is invoked");
	}


	// #######################################################################################################
	// Description: Execute actions on the browser level
	// #######################################################################################################
	@SuppressWarnings("unused")
	public boolean ExecuteActionOnBrowser(String strBrowserAction, String strValue) {

		boolean Flag = false;

		try {
			switch (strBrowserAction.toLowerCase().trim().replace(" ", "")) {
			case "windowid":
				dicOutput.put("strOutPut", driver.getWindowHandle().toString());
				return true;
			case "back":
				driver.navigate().back();
				return true;
			case "forward":
				driver.navigate().forward();
				return true;
			case "refresh":
				driver.navigate().refresh();
				return true;
			case "maximize":
				driver.manage().window().maximize();
				return true;
			case "geturl":
				dicOutput.put("strOutPut", driver.getCurrentUrl().toString());
				return true;
			case "navigate":
				driver.navigate().to(strValue);
				driver.manage().window().maximize();
				return true;
			case "gettitle":
				dicOutput.put("strOutPut", driver.getTitle().toString());
				return true;
			case "focus":
				switch (strValue.toLowerCase().trim().replace(" ", "")) {
				case "0":
				case "":
					driver.switchTo().window(dicOutput.get("ParentWinID"));
					driver.manage().window().maximize();
					Implicitwait(1000);
					return true;
				case "1":
					String parentId = driver.getWindowHandles().iterator().next();
					String ChildId = driver.getWindowHandles().iterator()
							.next();
					driver.switchTo().window(ChildId);
					driver.manage().window().maximize();
					Implicitwait(1000);
					return true;
				case "2":
					String parentId1 = driver.getWindowHandles().iterator().next();
					String ChildId1 = driver.getWindowHandles().iterator().next();
					String ChildId2 = driver.getWindowHandles().iterator().next();
					driver.switchTo().window(ChildId2);
					driver.manage().window().maximize();
					Implicitwait(1000);
					return true;
				}
				return true;
			case "basewindow":
				driver.switchTo().window(dicOutput.get("ParentWinID"));
				return true;
			case "close":
				driver.close();
				return true;
			case "getpagesource":
				dicOutput.put("strOutPut", driver.getPageSource().toString());
				return true;
			case "count":
				dicOutput.put("strOutPut",
						Integer.toString(driver.getWindowHandles().size()));
				return true;

			}

		} catch (Exception ee) {
			ErrDescription = "Unbale to Perform Action : " + strBrowserAction
					+ " On Browser";
		}
		return Flag;
	}


	// #######################################################################################################
	// Description: Explicit Wait Function. Waits for the specific amount of time, until it finds the specified webelement on the DOM
	// #######################################################################################################
	public void ExplicitWait(WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
	}


	// #######################################################################################################
	// Description: End of the test case
	// #######################################################################################################
	public void EndTestCase(String testCaseName) {

		Log.info("--- ENDE DES TESTES: " + testCaseName);

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		EndDateExecution = df.format(calobj.getTime()).toString();

		// @ get all Open Browser instance.
		int TotalBrower = 0;
		ExecuteActionOnBrowser("count", "");
		TotalBrower = Integer.parseInt(dicOutput.get("strOutPut"));

		// @ focus on Browser and close the browser
		for (int i = 0; i <= TotalBrower; i++) {
			ExecuteActionOnBrowser("focus", String.valueOf(i));
		}
	}


	// #######################################################################################################
	// Description: Execute actions on the page level
	// #######################################################################################################
	public void ExecuteActionOnPage(WebElement xpath, String strAction, String strValue) throws InterruptedException {

		// @ Perform Action.
		Actions action = new Actions(driver);
		String parentHandle = driver.getWindowHandle(); // get the current window handle
		WebDriverWait wait = new WebDriverWait(driver, 5);
		xpath = wait.until(ExpectedConditions.elementToBeClickable(xpath));

		switch (strAction) {
		case "click":
			Log.info("--- " + xpath.getText() + " element is clicked");
			xpath.click();
			// driver.findElement(By.id(username)).click();
			Thread.sleep(1500);
			break;

		case "rightclick":
			Log.info("--- " + xpath.getText() + " element is right clicked");
			action.contextClick(xpath).perform();
			Thread.sleep(1500);
			break;

		case "sendKeys":
			//xpath.clear();
			Log.info("--- " + strValue + " is entered in the element" + xpath.getText());
			xpath.sendKeys(strValue);
			Thread.sleep(1500);
			break;

		case "defineWH":
			xpath.click(); // click some link that opens a new window
			Log.info("--- " + xpath.getText() + " element is clicked");
			for (String winHandle : driver.getWindowHandles()) {
			    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			    Log.info("--- Windows Handling function is called and is navigated to the Child window");
			}
			break;

			//Actions need to be performed in between these two cases
		case "closeWH":
			driver.close(); // close newly opened window when done with it
			driver.switchTo().window(parentHandle); // switch back to the original window
			Log.info("--- Child window is closed");
			break;

		case "uncheck":
			if ((xpath).isSelected()) {
				Log.info("--- " + xpath.getText() + " element is unchecked");
				xpath.click();
				Thread.sleep(1500);
				break;
			}

		case "selectbytext":
			Select sel = new Select(xpath);
			Log.info("--- " + xpath.getText() + " element is selected by visible text" + strValue);
			sel.selectByVisibleText(strValue);
			break;

		case "selectbyvalue":
			Select sel1 = new Select(xpath);
			Log.info("--- " + xpath.getText() + " element is selected by value" + strValue);
			sel1.selectByValue(strValue);
			break;

		case "selectbyindex":
			Select sel2 = new Select(xpath);
			Log.info("--- " + xpath.getText() + "element is selected by index" + strValue);
			sel2.selectByIndex(Integer.parseInt(strValue));
			break;

		case "deselectAll":
			Select sel3 = new Select(xpath);
			Log.info("--- " + xpath.getText() + " element is deselected");
			sel3.deselectAll();
			break;
		}
	}


	// #######################################################################################################
	// Description: To verify if the webelement is displayed or not
	// #######################################################################################################
	public boolean IfElementDisplayed(WebElement element, boolean status) {
		return status = element.isDisplayed();
	}


	// #######################################################################################################
	// Description: To fulfill the Log, Excel and Sceenshot criterias for PASS scenario
	// #######################################################################################################
	public void TestCasePassDisplay(int tcNummer, String projekt, String TC, String methodName, int row, int column) throws Exception {
		System.out.println(methodName + "_" + tcNummer + ": " + "method is PASSED");
		Log.info("--- " + methodName + "_" + tcNummer + ": " + "method is PASSED");
		SetCellDataTRPASS("PASS", row, column);
		getscreenshotPASS(projekt, TC, tcNummer, methodName);
	}


	// #######################################################################################################
	// Description: To fulfill the Log, Excel and Sceenshot criterias for FAIL scenario
	// #######################################################################################################
	public void TestCaseFailDisplay(int tcNummer, String projekt, String TC, String methodName, int row, int column) throws Exception {
		System.out.println(methodName + "_" + tcNummer + ": " + "method is FAILED");
		Log.error("--- " + methodName + "_" + tcNummer + ": " + "method is FAILED");
		SetCellDataTRFAIL("FAIL", row, column);
		getscreenshotPASS(projekt, TC, tcNummer, methodName);
	}


	// #######################################################################################################
	// Description: To match two strings if they are equal (equalsIgnoreCase)
	// #######################################################################################################
	public boolean MatchExactString(String set, String subset) {
		if (set.toLowerCase().equalsIgnoreCase(subset.toLowerCase())) {
			Log.info("Two Strings matched");
			return true;
		} else {
			Log.error("Two strings are NOT matched");
			return false;
		}
	}


	// #######################################################################################################
	// Description: To match two strings if they are contained (contains) with eliminating the case sensitive
	// #######################################################################################################
	public boolean MatchContainsString(String set, String subset) {
		if (set.toLowerCase().trim().contains((subset.toLowerCase().trim()))) {
			Log.info("Two Strings matched");
			return true;
		} else {
			Log.error("Two strings are NOT matched");
			return false;
		}
	}


	// #######################################################################################################
	// Description: To match two strings if they are contained (contains) with eliminating the case sensitive
	// #######################################################################################################
	public boolean ElementExistenceCheckBoolean(String Xpath, WebElement element) {
		try {
			element = driver.findElement(By.xpath(Xpath));

			return true;
		} catch (Exception e) {
			ErrDescription = "element does not exist on page level";
			Log.error("--- " + element.getText() + "is NOT displayed");
		}
		return false;
	}


	// #######################################################################################################
	// Description: To mouse hover and hold on the specified web element
	// #######################################################################################################
	public boolean MouseOverandHold(WebElement element) {
		try {
			Mouse mouse = ((HasInputDevices) driver).getMouse();
			Locatable hoverItem = (Locatable) element;
			mouse.mouseMove(hoverItem.getCoordinates());
			Log.info("--- MouseOver for " + element.getText()
					+ " element is successfully displayed");
			return true;
		} catch (Exception e) {
			Log.error("--- MouseOver for " + element.getText()
					+ " element is NOT displayed");
		}
		return false;
	}


	// #######################################################################################################
	// Description: To highlight on the specified web element
	// #######################################################################################################
	public boolean elementHighlight(WebElement element) {
		try {
			for (int i = 0; i < 5; i++) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(
						"arguments[0].setAttribute('style', arguments[1]);",
						element, "color: yellow; border: 5px solid yellow;");
				js.executeScript(
						"arguments[0].setAttribute('style', arguments[1]);",
						element, "");
				Log.info("--- Element " + element.getText() + " is highlighted");
				Thread.sleep(300);
			}
			return true;
		} catch (Exception e) {
			Log.error("--- Element " + element.getText()
					+ " is NOT highlighted");
		}
		return false;
	}


	// #######################################################################################################
	// Description: Wait implicit wait for the specified seconds
	// #######################################################################################################
	public void Implicitwait(int intTimeOut) {
		driver.manage().timeouts().implicitlyWait(intTimeOut, TimeUnit.SECONDS);
	}


	// #######################################################################################################
	// Description: Function to take the screenshot at a particular point for the passed Testcase
	// #######################################################################################################
	public void getscreenshotPASS(String projekt, String TC, int TCnumber, String className) throws Exception {
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");// dd/MM/yyyy
			Date now = new Date();
			String strDate = sdfDate.format(now);

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String fName = "Screenshots\\" + projekt + "\\" + TC + "\\" + TCnumber + "_" + className + "\\" + "PASSED Screenshots" + "\\" + strDate + ".png";
			FileUtils.copyFile(scrFile, new File(fName));
			Log.info("Screenshot is successfully captured for the PASSED testcase:" + TCnumber + ": " +TC);
		}
		catch (IOException e) {
			e.getMessage();
			System.out.println("Failed to capture screenshot: " + e.getMessage());
			Log.error("Screenshot is NOT captured for the PASSED testcase:" + TCnumber + ": " +TC);
		}
	}

	// #######################################################################################################
        // Description: Function to take the screenshot at a particular point for the failed Testcase
        // #######################################################################################################
        public void getscreenshotFAIL(String projekt, String TC, int TCnumber, String className) throws Exception {
                try {
                        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");// dd/MM/yyyy
                        Date now = new Date();
                        String strDate = sdfDate.format(now);

                        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                        String fName = "Screenshots\\" + projekt + "\\" + TC + "\\" + TCnumber + "_" + className + "\\" + "FAILED Screenshots" + "\\" + strDate + ".png";
                        FileUtils.copyFile(scrFile, new File(fName));
                        Log.info("Screenshot is successfully captured for the FAILED testcase:" + TCnumber + ": " +TC);
                }
                catch (IOException e) {
                        e.getMessage();
                        System.out.println("Failed to capture screenshot: " + e.getMessage());
                        Log.error("Screenshot is NOT captured for the FAILED testcase:" + TCnumber + ": " +TC);
                }
        }


	// #######################################################################################################
	// Description: Execute action on the popup
	// #######################################################################################################
	public boolean ExecuteActionOnPopup(String strAction) {
		// @ verify Pop up is present or not.
		try {
			switch (strAction.toLowerCase().trim()) {
			case "accept":
			case "ok":
			case "yes":
				driver.switchTo().alert().accept();
				break;
			case "dismiss":
			case "no":
			case "cancel":
				driver.switchTo().alert().dismiss();
				break;
			case "gettext":
				dicOutput.put("strOutPut", driver.switchTo().alert().getText());
				break;
			}
		} catch (Exception e) {
			Log.error("Exception message: " + e);
			return false;
		}

		return true;
	}


	// #######################################################################################################
	// Description: Return 1 String value from SQL query with values connection URL, DB username, DB password and SQL query
	// #######################################################################################################
	public String executeSQLQuery(String connectionURL, String DBUsername, String DBPassword, String sqlQuery) {
        String connectionUrl="";
        String resultValue = "";
        ResultSet rs;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e) {
        	Log.error(e.getMessage());
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(connectionUrl, DBUsername, DBPassword);
            if(connection!=null) {
            	Log.info("--- Connected to the database...");
                System.out.println("Connected to the database...");
            }else {
            	Log.error("--- Database connection failed");
                System.out.println("Database connection failed");
            }
            Statement stmt = connection.createStatement();
            rs=stmt.executeQuery(sqlQuery);

            try {
                while(rs.next()){
                    resultValue = rs.getString(1).toString();
                }
            } catch (SQLException e) {
            	Log.error(e.getMessage());
                e.printStackTrace();
            }
            catch (NullPointerException err) {
            	Log.fatal("--- No Records obtained for this specific query");
                System.out.println("No Records obtained for this specific query");
                err.printStackTrace();
            }
            connection.close();

        }catch(SQLException sqlEx) {
        	Log.error("--- SQL Exception: " +sqlEx.getStackTrace());
            System.out.println( "SQL Exception:" +sqlEx.getStackTrace());
        }
        return resultValue;
    }


	// #######################################################################################################
	// Description: Return ArrayList of String values from SQL query with values connection URL, DB username, DB password and SQL query
	// #######################################################################################################
	public ArrayList<String> executeSQLQuery_List(String connectionURL, String DBUsername, String DBPassword, String sqlQuery) {
		String connectionUrl = "";
		ArrayList<String> resultValue = new ArrayList<String>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(connectionUrl, DBUsername, DBPassword);
			if (connection != null) {
				Log.info("--- Connected to the database");
				System.out.println("Connected to the database");
			} else {
				Log.error("--- Failed to connect to ");
				System.out.println("Failed to connect to ");
			}

			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);

			try {
				while (resultSet.next()) {
					int columnCount = resultSet.getMetaData().getColumnCount();
					StringBuilder stringBuilder = new StringBuilder();
					for (int iCounter = 1; iCounter <= columnCount; iCounter++) {
						stringBuilder.append(resultSet.getString(iCounter).trim() + " ");
					}
					String reqValue = stringBuilder.substring(0, stringBuilder.length() - 1);
					resultValue.add(reqValue);
				}
			} catch (SQLException e) {
				Log.error(e.getMessage());
				e.printStackTrace();
			} catch (NullPointerException ex) {
				Log.error("--- No Records found for this specific query" + ex.getStackTrace());
				System.out.println("No Records found for this specific query" + ex.getStackTrace());
			} finally {
				if (connection != null) {
					try {
						connection.close();
						Log.info("--- Connection is closed");
					} catch (SQLException ex) {
						Log.error("--- SQL Exception:" + ex.getStackTrace());
						System.out.println("SQL Exception:" + ex.getStackTrace());
					}
				}
			}

		} catch (SQLException sqlEx) {
			Log.error("--- SQL Exception:" + sqlEx.getStackTrace());
			System.out.println("SQL Exception:" + sqlEx.getStackTrace());
		}
		return resultValue;
	}


	// #######################################################################################################
	// Description: To get the temp path and return using String
	// #######################################################################################################
	public String GetTempPath() {
		return System.getProperty("java.io.tmpdir");
	}


	// #######################################################################################################
	// Description: To save the HTML reports path
	// #######################################################################################################
	public void SaveHTMLReportsPath() {
		boolean blnFlag = false;
		String HTMLReportsLink = GetTempPath() + "AUT_Results\\Reports\\";
		blnFlag = CreateDirectory(HTMLReportsLink);
		if (blnFlag) {
			try {
				Writer output;
				output = new BufferedWriter(new FileWriter(HTMLReportsLink
						+ "HTMLRepotsLink.txt", true));
				String newLine = System.getProperty("line.separator");
				output.append(newLine + sReportFileName);
				output.close();
			} catch (Exception e) {
			}
		}
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
		final String password = "12345678";

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
	// Description: API - GET Call - Response von API mit Java Json
	// #######################################################################################################
	// die GET API Call Seite aufrufen
	public String ReadFromUrl()
	{
		String ResponseData="";
		try
		{
			driver.get(getProfilURL);
			Thread.sleep(4000);
			ResponseData=driver.findElement(By.xpath("/html/body/pre")).getText();
			driver.navigate().back();
			Thread.sleep(2000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ResponseData="Error";
		}
		return ResponseData;
	}

	// noch bearbeiten benötigt
	public String compareAll(String firObjKey, String firArrKey, int location, String getStringText) throws Exception //info array
	{
		Thread.sleep(3000);
		result = ReadFromUrl();
		res=new JSONObject(result); //initialize the Stream URL into JSON object
		firstObj = res.getJSONObject(firObjKey); //initialize the Stream URL into JSON object
		firArray = firstObj.getJSONArray(firArrKey); //initialize the Stream URL into JSON array
		String getStringValue = firArray.getJSONObject(location).getString(getStringText);
		return getStringValue;
	}

	// noch bearbeiten benötigt
	public boolean returnCompareAll(String firObjKey, String firArrKey, int location, String getStringText) throws Exception
	{
		result = ReadFromUrl();
		res=new JSONObject(result); //initialize the Stream URL into JSON object
		firstObj = res.getJSONObject(firObjKey); //initialize the Stream URL into JSON object
		firArray = firstObj.getJSONArray(firArrKey); //initialize the Stream URL into JSON array
		boolean getStringValue = firArray.getJSONObject(location).getString(getStringText).isEmpty();
		return getStringValue;
	}

	// noch bearbeiten benötigt
	public String firstObject(String objValue, String arrayValue, int val, String value) throws Exception //info object
	{
		result = ReadFromUrl();
		res=new JSONObject(result); //initialize the Stream URL into JSON object
		JSONObject infop = res.getJSONObject("properties"); //gets the user info of the Json result
		JSONObject info = infop.getJSONObject(objValue); //gets the user info of the Json result
		JSONArray fir = info.getJSONArray(arrayValue);
		String saleAlrts = fir.getJSONObject(val).getString(value);
		return saleAlrts;
	}


	// #######################################################################################################
	// Description: TEST RESULTS# To configure and enter the test results in Excel using POI
	// #######################################################################################################
	// Set the File path and Sheetname as Arguments to set the excel file
	public void SetExcelFileTR(String Path, String SheetName) throws Exception {


		try {
			Path_TestDataTR = Path;

			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet
			ExcelWBookTR = new HSSFWorkbook(ExcelFile);
			ExcelWSheetTR = ExcelWBookTR.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	// oiriginal To write in the Excel cell, Row num and Col num are the parameters
	public void SetCellDataTRFAIL(String Result, int RowNum, int ColNum) throws Exception
	{
		try {
			if (ExcelWSheetTR.getRow(RowNum) == null) {
				RowTR = ExcelWSheetTR.createRow(RowNum);
			}
			else
			{
			RowTR = ExcelWSheetTR.getRow(RowNum);
			CellTR = RowTR.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			}

			if (CellTR == null)
			{
				CellTR = RowTR.createCell(ColNum);
				CellTR.setCellValue("");
				CellTR.setCellValue(Result);
			}
			else
			{
				CellTR.setCellValue(Result);
			}

			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(Path_TestDataTR);
			ExcelWBookTR.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw (e);
		}
	}

	// duplicate To write in the Excel cell, Row num and Col num are the parameters
        public void SetCellDataTRPASS(String Result, int RowNum, int ColNum) throws Exception
        {
            try {
                if (ExcelWSheetTR.getRow(RowNum) == null) {
                        RowTR = ExcelWSheetTR.createRow(RowNum);
                }
                else
                {
                RowTR = ExcelWSheetTR.getRow(RowNum);
                CellTR = RowTR.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
                }

                if (CellTR == null)
                {
                        CellTR = RowTR.createCell(ColNum);
                        CellTR.setCellValue("");
                        CellTR.setCellValue(Result);
                }
                else
                {
                        CellTR.setCellValue(Result);
                }

                // Constant variables Test Data path and Test Data file name
                FileOutputStream fileOut = new FileOutputStream(Path_TestDataTR);
                ExcelWBookTR.write(fileOut);
                fileOut.flush();
                fileOut.close();
        }
        catch (Exception e)
        {
                e.printStackTrace();
                throw (e);
        }
        }

	// This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	public String GetCellData(int RowNum, int ColNum) throws Exception {

		try {

			CellTR = ExcelWSheetTR.getRow(RowNum).getCell(ColNum);
			String CellData;
			switch (CellTR.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				System.out.println("its an numeric value");
				CellData = "" + (int) CellTR.getNumericCellValue();
				break;
			case HSSFCell.CELL_TYPE_STRING:
				CellData = CellTR.getStringCellValue();
				break;
			default:
				CellData = "";
			}
			return CellData.trim();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	// To get the last row number
	public int GetLastRowNumber() {
		return ExcelWSheetTR.getLastRowNum();
	}

	// To get the row number in String
	public String GetRow(int rowNum) {
		return ExcelWSheetTR.getRow(rowNum).toString();
	}

	// To check of the specific row is empty
	public boolean IsRowEmpty(int row, int exceed) {

		RowTR = ExcelWSheetTR.getRow(row);
		if (RowTR == null)
			return (true);

		for (int c = RowTR.getFirstCellNum(); c < exceed; c++) {
			if (RowTR.getCell(c) == null
					|| RowTR.getCell(c).toString().trim().equals("")
					|| RowTR.getCell(c).getCellType() == HSSFCell.CELL_TYPE_BLANK)
				return true;
		}
		return false;
	}

	// #######################################################################################################
	// Description: TEST DATA# To configure and get the test data in Excel using POI
	// #######################################################################################################
	// Set the File path and Sheetname as Arguments to set the excel file
		public void SetExcelFileTD(String Path, String SheetName) throws Exception {

			try {
				Path_TestDataTD = Path;

				// Open the Excel file
				FileInputStream ExcelFile = new FileInputStream(Path);

				// Access the required test data sheet
				ExcelWBookTD = new HSSFWorkbook(ExcelFile);
				ExcelWSheetTD = ExcelWBookTD.getSheet(SheetName);
			} catch (Exception e) {
				throw (e);
			}
		}

		// This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
		public String GetCellDataTD(int RowNum, int ColNum) throws Exception {

			try {

				CellTD = ExcelWSheetTD.getRow(RowNum).getCell(ColNum);
				String CellData;
				switch (CellTD.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC:
					System.out.println("its an numeric value");
					CellData = "" + (int) CellTD.getNumericCellValue();
					break;
				case HSSFCell.CELL_TYPE_STRING:
					CellData = CellTD.getStringCellValue();
					break;
				default:
					CellData = "";
				}
				return CellData.trim();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return "";
			}
		}
}