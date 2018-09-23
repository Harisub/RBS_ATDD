package plymp;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.gson.stream.JsonReader;

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
	
	public static void readApp(JsonReader jsonReader) throws IOException{
	    jsonReader.beginObject();
	     while (jsonReader.hasNext()) {
	         String name = jsonReader.nextName();
	         System.out.println(name);
	         if (name.contains("source")){
	             jsonReader.beginObject();
	             while (jsonReader.hasNext()) {
	                 String n = jsonReader.nextName();
	                 if (n.equals("name")){
	                     System.out.println(jsonReader.nextString());
	                 }
	                 if (n.equals("age")){
	                     System.out.println(jsonReader.nextInt());
	                 }
	                 if (n.equals("messages")){
	                     jsonReader.beginArray();
	                     while  (jsonReader.hasNext()) {
	                          System.out.println(jsonReader.nextString());
	                     }
	                     jsonReader.endArray();
	                 }
	             }
	             jsonReader.endObject();
	         }

	     }
	     jsonReader.endObject();
	}
	}
	

