package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PO {
	WebDriver driver;
	@FindBy(xpath="//cufon//cufontext")
	 public WebElement homePageText;
		
	@FindBy(xpath="//tr//td//a[@id='viewlistGenericList']")
	 public WebElement homePageTGenlistr;
	
	@FindBy(xpath="//span/a[@id='cred_forgot_password_link']")
	 public WebElement homePageTPWD;
	
	public static String homevarmen="//a/span[@class='additional-background']//span[@class='menu-item-text']";
	public PO(WebDriver driver){

        this.driver = driver;

        PageFactory.initElements(driver, this);

    }
}
