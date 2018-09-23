package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PO {
	
	WebDriver driver;
	@FindBy(xpath="//a[@class='product_img_link']/img[@class='replace-2x img-responsive']")
	 public WebElement homePageText;
	
	@FindBy(xpath="//a[@title='T-shirts']")
	 public WebElement tshirt;
	
	@FindBy(id="email")
	 public WebElement email;
	
	@FindBy(id="passwd")
	 public WebElement pwd;
		
	@FindBy(xpath="//a[@class='button ajax_add_to_cart_button btn btn-default']")
	 public WebElement cartButton;
	
	@FindBy(xpath="//a[@class='btn btn-default button button-medium']/span")
	 public WebElement confrim_addtoCart;
	
	public static String homevarmen="//a/span[@class='additional-background']//span[@class='menu-item-text']";
	
	public PO(WebDriver driver){

        this.driver = driver;

        PageFactory.initElements(driver, this);

    }
	
		 
		 
	 public void placeOrder(){
		 Actions actions = new Actions(driver);

		 WebDriverWait wait=new WebDriverWait(driver, 50);
		driver.findElement(By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[3]")).click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='product_img_link']/img[@class='replace-2x img-responsive']")));
	
	actions.moveToElement(homePageText).moveToElement(homePageText).click().build().perform();
	actions.moveToElement(homePageText).moveToElement(homePageText).click().build().perform();
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	
	actions.moveToElement(cartButton).moveToElement(cartButton).click().build().perform();
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	confrim_addtoCart.click();
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	driver.findElement(By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']")).click();
	
	email.sendKeys("someone@example.com");
	pwd.sendKeys("Password123");
	//button[@class='button btn btn-default button-medium']
	 }
}
