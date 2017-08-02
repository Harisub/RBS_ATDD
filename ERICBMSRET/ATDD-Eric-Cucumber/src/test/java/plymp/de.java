package plymp;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.firefox.FirefoxDriver;  

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import org.openqa.selenium.support.*;
import org.openqa.selenium.support.*;

import pageObjects.PO;
public class de {


	@Given("^i launch the packone - pre execution query$")
public void i_want_to_write_a_step_with_precondition() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	




	System.setProperty("webdriver.chrome.driver", "C:/Users/EZVSXHA/Desktop/Hari/chromedriver.exe");
		
	WebDriver driver= new ChromeDriver();
	Actions ace= new Actions(driver);
PO op= new PO(driver);
	
	

	driver.get("https://ericoll.internal.ericsson.com/");  
if(!op.homePageText.getText().equalsIgnoreCase("ERICOLL")){
	
}

	Thread.sleep(1000);
	
	driver.manage().window().maximize();
	String met="false";
	
	List<WebElement> arrStor=new ArrayList<WebElement>();
	
	arrStor=driver.findElements(By.xpath(op.homevarmen));
	
	for(WebElement we:arrStor){
		if(we.getText().equalsIgnoreCase("EriCOLL Migration")){
			
			ace.moveToElement(we).clickAndHold().build().perform();
			 met="true";
		}
		if(met.equalsIgnoreCase("true")){
			if(we.getText().equalsIgnoreCase("EriCOLL Migration On Yammer")){
				ace.click(we).build().perform();
				break;
			}
		}
	}
	
	Thread.sleep(1000);
	op.homePageTPWD.click();
	
	driver.get("https://ericoll.internal.ericsson.com/"); 
	driver.findElement(By.xpath("//a//img[@src='/SpotlightImages/New%20spotlightpicts/Blog2.png']")).click();
	Thread.sleep(2000);
	arrStor=driver.findElements(By.xpath(op.homevarmen));
	for(WebElement we:arrStor){
		
		
			if(we.getText().equalsIgnoreCase("Lists")){
				ace.click(we).build().perform();
				op.homePageTGenlistr.click();
				break;
			}
		
	}
	
}


@When("^I complete Pre execution query \"(.*)\"$")
public void i_complete_action(int abc) throws FileNotFoundException, IOException {
	
	FileInputStream file = new FileInputStream(new File("C:\\Users\\EZVSXHA\\workspace\\ATDD-Eric-Cucumber\\src\\test\\resources\\wbTble.xlsx"));
	 XSSFWorkbook Workbookwb=null;
	System.out.println(abc);
	

    	 Workbookwb = new XSSFWorkbook(file);
    
 
    Sheet sheet = Workbookwb.getSheet("Functions");
    int cc=1;
    
    for(int rw=0;rw<sheet.getLastRowNum();rw++){
    	XSSFRow row = (XSSFRow) sheet.getRow(rw);
    	XSSFCell cell = row.getCell(cc);
    	  String value = cell.toString();
    	if(value.equalsIgnoreCase("CSICB2")){
    		ATG h= new ATG();
    		h.CSICB2();
    		
    	}
    }
}

@When("^UI module completes loading$")
public void ui_module_completes_loading() throws Throwable {

    // Write code here that turns the phrase above into concrete actions
	




	System.setProperty("webdriver.chrome.driver", "C:/Users/EZVSXHA/Desktop/Hari/chromedriver.exe");

	WebDriver driver= new ChromeDriver();
	Actions ace= new Actions(driver);

	
	//PageFactory.initElements(driver, this);

	driver.get("https://ericoll.internal.ericsson.com/");  
if(!driver.findElement(By.xpath("//cufon//cufontext")).getText().equalsIgnoreCase("ERICOLL")){
	
}

	Thread.sleep(1000);
	
	driver.manage().window().maximize();
	String met="false";
	//a/span[@class='additional-background']//span[@class='menu-item-text']
	List<WebElement> arrStor=new ArrayList<WebElement>();
	
	arrStor=driver.findElements(By.xpath("//a/span[@class='additional-background']//span[@class='menu-item-text']"));
	
	for(WebElement we:arrStor){
		if(we.getText().equalsIgnoreCase("EriCOLL Migration")){
			
			ace.moveToElement(we).clickAndHold().build().perform();
			 met="true";
		}
		if(met.equalsIgnoreCase("true")){
			if(we.getText().equalsIgnoreCase("EriCOLL Migration On Yammer")){
				ace.click(we).build().perform();
				break;
			}
		}
	}
	
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span/a[@id='cred_forgot_password_link']")).click();
	
	driver.get("https://ericoll.internal.ericsson.com/"); 
	
	Thread.sleep(2000);
	arrStor=driver.findElements(By.xpath("//a/span[@class='additional-background']//span[@class='menu-item-text']"));
	for(WebElement we:arrStor){
		
		
			if(we.getText().equalsIgnoreCase("Lists")){
				ace.click(we).build().perform();
				driver.findElement(By.xpath("//tr//td//a[@id='viewlistGenericList']")).click();
				break;
			}
		
	}
	
	


    

    
}

@When("^Post query$")
public void some_other_action() throws Throwable {

    // Write code here that turns the phrase above into concrete actions
	




	System.setProperty("webdriver.chrome.driver", "C:/Users/EZVSXHA/Desktop/Hari/chromedriver.exe");

	WebDriver driver= new ChromeDriver();
	Actions ace= new Actions(driver);

	
	//PageFactory.initElements(driver, this);

	driver.get("https://ericoll.internal.ericsson.com/");  
if(!driver.findElement(By.xpath("//cufon//cufontext")).getText().equalsIgnoreCase("ERICOLL")){
	
}

	Thread.sleep(1000);
	
	driver.manage().window().maximize();
	String met="false";
	//a/span[@class='additional-background']//span[@class='menu-item-text']
	List<WebElement> arrStor=new ArrayList<WebElement>();
	
	arrStor=driver.findElements(By.xpath("//a/span[@class='additional-background']//span[@class='menu-item-text']"));
	
	for(WebElement we:arrStor){
		if(we.getText().equalsIgnoreCase("EriCOLL Migration")){
			
			ace.moveToElement(we).clickAndHold().build().perform();
			 met="true";
		}
		if(met.equalsIgnoreCase("true")){
			if(we.getText().equalsIgnoreCase("EriCOLL Migration On Yammer")){
				ace.click(we).build().perform();
				break;
			}
		}
	}
	
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span/a[@id='cred_forgot_password_link']")).click();
	
	driver.get("https://ericoll.internal.ericsson.com/"); 
	
	Thread.sleep(2000);
	arrStor=driver.findElements(By.xpath("//a/span[@class='additional-background']//span[@class='menu-item-text']"));
	for(WebElement we:arrStor){
		
		
			if(we.getText().equalsIgnoreCase("Lists")){
				ace.click(we).build().perform();
				driver.findElement(By.xpath("//tr//td//a[@id='viewlistGenericList']")).click();
				break;
			}
		
	}
	
	


    

   
}


@Then("^I validate the outcomes$")
public void i_validate_the_outcomes() throws Throwable {

    // Write code here that turns the phrase above into concrete actions
	




	System.setProperty("webdriver.chrome.driver", "C:/Users/EZVSXHA/Desktop/Hari/chromedriver.exe");

	WebDriver driver= new ChromeDriver();
	Actions ace= new Actions(driver);

	
	//PageFactory.initElements(driver, this);

	driver.get("https://ericoll.internal.ericsson.com/");  
if(!driver.findElement(By.xpath("//cufon//cufontext")).getText().equalsIgnoreCase("ERICOLL")){
	
}

	Thread.sleep(1000);
	
	driver.manage().window().maximize();
	String met="false";
	//a/span[@class='additional-background']//span[@class='menu-item-text']
	List<WebElement> arrStor=new ArrayList<WebElement>();
	
	arrStor=driver.findElements(By.xpath("//a/span[@class='additional-background']//span[@class='menu-item-text']"));
	
	for(WebElement we:arrStor){
		if(we.getText().equalsIgnoreCase("EriCOLL Migration")){
			
			ace.moveToElement(we).clickAndHold().build().perform();
			 met="true";
		}
		if(met.equalsIgnoreCase("true")){
			if(we.getText().equalsIgnoreCase("EriCOLL Migration On Yammer")){
				ace.click(we).build().perform();
				break;
			}
		}
	}
	
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span/a[@id='cred_forgot_password_link']")).click();
	
	driver.get("https://ericoll.internal.ericsson.com/"); 
	
	Thread.sleep(2000);
	arrStor=driver.findElements(By.xpath("//a/span[@class='additional-background']//span[@class='menu-item-text']"));
	for(WebElement we:arrStor){
		
		
			if(we.getText().equalsIgnoreCase("Lists")){
				ace.click(we).build().perform();
				driver.findElement(By.xpath("//tr//td//a[@id='viewlistGenericList']")).click();
				break;
			}
		
	}
	
	


    

    
}




}
