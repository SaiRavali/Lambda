package lambda;
import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class Lambdatest {

	public RemoteWebDriver driver = null;
 String username = "sairavali.g";
	String accessKey = "m7Mx3NLqkmU57aXtM8FfTJl1P2CwR65Ip3tFcrm5M21radI58J";
	
	@BeforeTest
  public void setUp() throws Exception {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("platform", "Windows 10");
	     capabilities.setCapability("browserName", "Chrome");
	     capabilities.setCapability("version", "92.0"); // If this cap isn't specified, it will just get the any available one
      capabilities.setCapability("resolution","1024x768");
      capabilities.setCapability("build", "First Test");
      capabilities.setCapability("name", "Sample Test");
      capabilities.setCapability("network", true); // To enable network logs
      capabilities.setCapability("visual", true); // To enable step by step screenshot
      capabilities.setCapability("video", true); // To enable video recording
      capabilities.setCapability("console", true); // To capture console logs
  
      try {       
			driver= new RemoteWebDriver(new URL("https://"+username+":"+accessKey+"@hub.lambdatest.com/wd/hub"), capabilities);            
      } catch (MalformedURLException e) {
          System.out.println("Invalid grid URL");
      }
  }

	public void testScript() throws Exception {
				try {
					driver.get("https://www.lambdatest.com/automation-demos");
				    driver.findElement(By.id("username")).sendKeys("lambda");
				    driver.findElement(By.id("password")).sendKeys("lambda123");
				    driver.findElement(By.xpath("//button[@type='submit']")).click();
				    driver.findElement(By.name("email")).sendKeys("sairavali.g@gmail.com");
				    driver.findElement(By.id("populate")).click();
				    
				    
				    Alert alert=driver.switchTo().alert();
					String textalert=alert.getText();
					System.out.println("textalert:" +textalert);
					alert.accept();
					
				    driver.findElement(By.id("month")).click();
				    driver.findElement(By.id("customer-service")).click();
				    driver.findElement(By.id("discounts")).click();
				    
				    WebElement dropDown1 = driver.findElement(By.id("preferred-payment"));
					Select dd1 = new Select(dropDown1);
					dd1.selectByIndex(1);
				  
				    driver.findElement(By.id("tried-ecom")).click();	   
				    
				    WebElement source = driver.findElementByXPath("//div[@id='__next']/div/section[2]/div/div/div[4]/div[2]/div/div/div/div/div[6]");  
					  WebElement target = driver.findElementByXPath("//div[@id='__next']/div/section[2]/div/div/div[4]/div[2]/div/div/div/div/div[10]");
					   Actions builder = new Actions(driver);
					  builder.dragAndDrop(source, target).perform();
					  
					  System.out.println("Drag & Drop test case successful\n");
				        Thread.sleep(3000);
				        Assert.assertNotEquals(source, target);
				        driver.findElement(By.id("comments")).sendKeys("Feedback");
					  
				   // driver.findElement(By.xpath("//div[@id='__next']/div/section[2]/div/div/div[4]/div[2]/div/div/div/div")).click();
				   
					
					 driver.get("https://www.lambdatest.com/selenium-automation");
				     driver.switchTo().newWindow(WindowType.TAB);

				    	driver.findElementByCssSelector("Body").sendKeys(Keys.CONTROL + "t");
				    	 driver.get("https://www.lambdatest.com/selenium-automation");
				   
				    WebElement ele = driver.findElement(By.xpath("//img[@title='Jenkins']"));
					WebDriverWait wait = new WebDriverWait(driver, 60, 15000);
					wait.until(ExpectedConditions.visibilityOf(ele));
			        
			        String src = ele.getAttribute("src");
			        BufferedImage bufferedImage = ImageIO.read(new URL(src));
			        File outputfile = new File("/Users/jenkins.png");
			        ImageIO.write(bufferedImage, "png", outputfile);
			        
				    driver.findElement(By.id("submit-button")).click();
				  driver.findElement(By.xpath("//div[@id='__next']/div/section[2]/section/div/div/p")).click();
				
	} 
	catch (Exception e) {
        System.out.println(e.getMessage());
    }
	}

	@AfterClass
    public void tearDown() throws Exception {
       if (driver != null) {
            String status = null;
			((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
