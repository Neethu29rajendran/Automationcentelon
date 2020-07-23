package com.automation.test.automation_test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import junit.framework.Assert; 



public class TestNgTest {
	
	public String baseurl="http://automationpractice.com//index.php";
	public  WebDriver driver;
	
	
		
		@BeforeTest
		@Parameters("browser")
		public void selectBrowser(String browser)
		{
			if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			    driver=new ChromeDriver();
		   }

				else if(browser.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
							
				driver = new FirefoxDriver();

							
			}
			  driver.manage().window().maximize();
			  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			  driver.get(baseurl); 
		}
		
			
		public  void takeScreenshot() throws Exception {
						
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
			
			File destination = new File("./target/surefire-reports/Screenshots/"+timeStamp+".png");
			String imgSrc = "./Screenshots/"+timeStamp+".png";

			
			
			FileUtils.copyFile(scrFile, destination);
			 
			
			String path = "<img src='"+imgSrc+"' height='200' width='200' />";
			Reporter.log(path);
		
		}
		
  @Test(priority=1)
  public void click_signin_button() throws Exception
  {
	WebElement sig=  driver.findElement(By.linkText("Sign in"));
	sig.click();
	takeScreenshot();

    WebElement e=driver.findElement(By.name("SubmitCreate"));
    Assert.assertEquals(true, e.isDisplayed());
    Thread.sleep(3000);
  }
  
  @Test(priority=2)
  @Parameters({"email_id"})
  public void create_account(String email_id) throws Exception
  {

	  WebElement e=driver.findElement(By.name("email_create"));
	  e.sendKeys(email_id);
	  WebElement submit= driver.findElement(By.name("SubmitCreate"));
	  submit.click();
	  takeScreenshot();

	  
	  WebElement e1=driver.findElement(By.name("submitAccount"));
	  Assert.assertEquals(true, e1.isDisplayed());
	    Thread.sleep(3000);

	  
	 
  }
  
  @Test(priority=3)
  @Parameters({"firstname","lastname","password","company","address","addresstwo","city","postcode","additional","mobilenumber","alias"})
  public void registration(String firstname,String lastname,String password,String company,String address,String addresstwo,String city,String postcode,String additional,String mobilenumber,String alias) throws Exception
  {

	  WebElement radio1=driver.findElement(By.id("id_gender2"));
	  radio1.click();
	
	  WebElement firstname1=driver.findElement(By.name("customer_firstname"));
	  firstname1.sendKeys(firstname);
	  
	  WebElement lastname1=driver.findElement(By.name("customer_lastname"));
	  lastname1.sendKeys(lastname);
	  
	  WebElement pwd1=driver.findElement(By.name("passwd"));
	  pwd1.sendKeys(password);
	  
	  WebElement days1=driver.findElement(By.name("days"));
	  Select obj=new Select(days1);
	  obj.selectByValue("29");
	  
	   
	  
	 WebElement month1=driver.findElement(By.name("months"));
	  Select obj1=new Select(month1);
	  obj1.selectByValue("7");
	  
	  WebElement year1=driver.findElement(By.name("years"));
	  Select obj2=new Select(year1);
	  obj2.selectByValue("1990");
	  
	  WebElement comp=driver.findElement(By.id("company"));
	  comp.sendKeys(company);
	  
	  WebElement add=driver.findElement(By.id("address1"));
	  add.sendKeys(address);
	  
	  WebElement add2=driver.findElement(By.id("address2"));
	  add2.sendKeys(addresstwo);
	  
	  
	  WebElement city1=driver.findElement(By.id("city"));
	  city1.sendKeys(city);
	  
	  WebElement state1=driver.findElement(By.name("id_state"));
	  Select obj3=new Select(state1);
	  obj3.selectByVisibleText("Wyoming");
	  
	  WebElement postcode1=driver.findElement(By.id("postcode"));
	  postcode1.sendKeys(postcode);
	  
	  WebElement additional1=driver.findElement(By.id("other"));
	  additional1.sendKeys(additional);
	  
	  
	  WebElement mob1=driver.findElement(By.id("phone_mobile"));
	  mob1.sendKeys(mobilenumber);
	  
	  WebElement alias1=driver.findElement(By.id("alias"));
	  alias1.sendKeys(alias);
	  
	  WebElement reg=driver.findElement(By.id("submitAccount"));
	  reg.click();
     
	    Thread.sleep(3000);
		  takeScreenshot();

	  
	String titl=driver.getTitle();
	
	 Assert.assertEquals("My account - My Store", titl);

  }
  
  @Test(priority=4)
  @Parameters({"firstname","lastname"})  
  public void check_correct_names(String firstname,String lastname) throws Exception
  {

	  String fullname =firstname+" "+lastname;
	  
	  WebElement name=driver.findElement(By.xpath("//*[@title=\"View my customer account\"]/span"));
	  String loginname=name.getText();
	  takeScreenshot();

	  
	  Assert.assertEquals(fullname, loginname);
	    Thread.sleep(3000);

	  
  }
 
    
  @AfterTest
  public void afterTest() {
	  driver.close();
  }

 

 

}
