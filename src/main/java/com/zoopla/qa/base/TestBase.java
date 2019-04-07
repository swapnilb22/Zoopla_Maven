/**
 * 
 */
package com.zoopla.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;
import com.zoopla.qa.util.WebEventListener;
import com.zoopla.qa.util.TestUtil;

/**
 * @author swapnil
 *
 */
public class TestBase 
{

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;  
	public static JavascriptExecutor js;
	static	Logger log = Logger.getLogger(TestBase.class);
	public	Actions action;

	public static WebEventListener eventListener;
	public static EventFiringWebDriver e_driver;
	

	public TestBase()
	{


		try 
		{
			prop = new Properties();

			FileInputStream ip;

				// setting the config.properties file path
				ip = new FileInputStream("C:\\Users\\swapnil\\eclipse-workspace\\Zoopla_Maven\\src\\main\\java\\com\\zoopla\\qa\\config\\config.properties");
				prop.load(ip);
					
			
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();

		}catch(IOException e)
		{
			e.printStackTrace();
		}

		
	}

	public static void initialization()
	{
		String broweserName = prop.getProperty("browser");


		if (broweserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\Users\\swapnil\\eclipse-workspace\\WebDriver\\test\\scripts\\resources\\chromedriver.exe");
		
		
			driver=new ChromeDriver();
			log.info("Chrome Browser Started");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		}

		else if (broweserName.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\Users\\swapnil\\eclipse-workspace\\WebDriver\\test\\scripts\\resources\\geckodriver.exe");
			driver=new FirefoxDriver();
			log.info("Firefox Browser Started");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}

		else
		{
			System.out.println("Browser did not identified");
		}
		

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_Load_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));

		

		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

}
	/*
	 * Below method clicks on the element
	 * 
	 * @Param: Elemenet Name
	 * 
	 * 
	 */
	public static void clickOnElement(WebElement elementName)
	{
		js = (JavascriptExecutor)driver;	
		js.executeScript("arguments[0].click();",elementName);
	}
	
	
	
	/*
	 * Below method wait for element to visible for action.
	 * 
	 * @Param: Element Name
	 * 
	 */
	public static void waitForElement(WebElement elementName)
	{
		wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.visibilityOf(elementName));
	}
}