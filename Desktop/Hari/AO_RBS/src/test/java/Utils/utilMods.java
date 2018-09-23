package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import com.thoughtworks.selenium.Wait;

import pageObjects.*;
import cucumber.api.Scenario;
public class utilMods {


	InputStream input = null;
	InputStream inputEv = null;
	InputStream inputEv_Genre=null;
	InputStream inputEv_VOD=null;
	OutputStream output = null;
	String oprTor=null;
	String heirar=null;
	String offAirTBA=null;
	String qualityFilter=null;
	JSONArray Flds=null;
	String sec[]=null;
	String Fld=null;
	String filedI=null;
	String filedS=null;
	String qLty=null;
	String srcTyp=null;
	String EvTyp=null;
	String srcID=null;	
	JSONObject delivery=null;
	JSONArray lanG=null;
	JSONObject ProgID=null;
	String Progid_p=null;

	JSONObject srID=null;
	int yearOfProduction=0;
	String srID_p=null;
	String srID_q=null;

	JSONArray otherAttribute=null;
	JSONObject otherAttribute_value=null;
	JSONObject otherAttribute_value_valID=null;
	String otherAttribute_value_valID_VAL=null;

	JSONArray relatedMaterial=null;
	

	JSONArray tagAR=null;
	JSONObject hierarchyLevel=null;
	JSONObject category=null;
	String categoryId=null;
	String hRti=null;
	FileHandler fh; 
	JSONObject valAR=null;
	JSONObject valueNames=null;
	String en=null;	

	JSONArray searchableTitles=null;
	JSONObject hierarchyLevel_Serach=null;
	String hierarchyLevel_Value=null;
	String hierarchyLevel_Type=null;
	JSONObject da=null;

	utilsPack uPE= new utilsPack();
	JSONArray contributions=null;
	JSONObject contributionType=null;
	String contributionTypeId=null;


	//2016/11/16 12:08:43

	JSONArray Images=null;
	String orientation=null;
	String shape=null;
	private Scenario scenario;

	static final Logger logger = Logger.getLogger(utilMods.class.getName());
	WebDriver driver= new ChromeDriver();
	public void UI() throws InterruptedException, IOException{

	    // Write code here that turns the phrase above into concrete actions
			
		System.setProperty("webdriver.chrome.driver", "C:/Users/EZVSXHA/Desktop/Hari/chromedriver.exe");
			
		driver.get("http://automationpractice.com/index.php");  
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
	}

	public void plcOrder() throws InterruptedException, IOException{
		
		PO po= new PO(driver);
		po.placeOrder();
				
		Thread.sleep(10000);
	}
	
	public void Verifyorder() throws InterruptedException, IOException{
		// TODO Auto-generated method stub
		
	}
}
	
	