

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
	
	private DriverFactory() {
		
	}
	private static DriverFactory instance=new DriverFactory();
	
	public static DriverFactory getinstance() {
		
		return instance;
	}
	
	

	private  ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(); 
			
	
	public void setchromeDriver() // Method to get the driver object and launch the browser
	   {
		System.out.println("Thread -factiry"+ Thread.currentThread().getId());
		
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		
	      driver.set(new ChromeDriver());
	      
	      }
		  public ThreadLocal<WebDriver> getDriver() // call this method to get the driver object and launch the browser
			   {
				  return driver;
			   }

			   public void removeDriver() // Quits the driver and closes the browser
			   {
				 
			      driver.remove();
			   }
	
			   
		
			   
}
