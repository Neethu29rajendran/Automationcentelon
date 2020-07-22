package com.automation.test.automation_test;

import org.testng.annotations.Test;

import junit.framework.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNgTest {
	
	public String baseurl="http://automationpractice.com//index.php";
	public static WebDriver driver;
	
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
  }
  
  @Test(priority=1)
  public void clicksignin()
  {
	WebElement sig=  driver.findElement(By.linkText("Sign in"));
	sig.click();
    WebElement e=driver.findElement(By.name("SubmitCreate"));
    Assert.assertEquals(true, e.isDisplayed());

  }
  
  @Test(priority=2)
  @Parameters({"email_id"})
  public void createaccount(String email_id)
  {
	  WebElement e=driver.findElement(By.name("email_create"));
	  e.sendKeys(email_id);
	  WebElement submit= driver.findElement(By.name("SubmitCreate"));
	  submit.click();
	  WebElement e1=driver.findElement(By.name("submitAccount"));
	    Assert.assertEquals(true, e1.isDisplayed());
	  
	 
  }
  
  @Test(priority=3)
  @Parameters({"firstname","lastname","password","company","address","addresstwo","city","postcode","additional","mobilenumber","alias"})
  public void register(String firstname,String lastname,String password,String company,String address,String addresstwo,String city,String postcode,String additional,String mobilenumber,String alias)
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
	  
	   // WebDriverWait wait = new WebDriverWait(driver, 15);
	    //WebElement month1=wait.until(ExpectedConditions.elementToBeClickable(By.id("months")));


	  
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
     
	  
	 String titl=driver.getTitle();
	 Assert.assertEquals("My account - My Store", titl);

  }
  
  @Test(priority=4)
  @Parameters({"firstname","lastname"})  
  public void checknames(String firstname,String lastname)
  {
	  String fullname =firstname+" "+lastname;
	  
	  WebElement name=driver.findElement(By.xpath("//*[@title=\"View my customer account\"]/span"));
	  String loginname=name.getText();
	  
	  
	  Assert.assertEquals(fullname, loginname);
	  
  }
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver","F:\\Neethu Selenium\\chromedriver.exe");
	   driver=new ChromeDriver();
        driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get(baseurl); 
	  
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
