package plymp;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.impl.Log4JLogger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;


import org.junit.Assert;













import Utils.Utilsobject;
import Utils.utilMods;
//import Utils.utilModsBck;
//import pageObjects.PO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class de {

	InputStream input = null;
	InputStream inputEv = null;
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
	JSONObject ProgID=null;
	String Progid_p=null;
	
	JSONObject srID=null;
	String srID_p=null;
	
	JSONArray otherAttribute=null;
	JSONObject otherAttribute_value=null;
	JSONObject otherAttribute_value_valID=null;
	String otherAttribute_value_valID_VAL=null;
	
	JSONArray tagAR=null;
	JSONObject hierarchyLevel=null;
	JSONObject category=null;
	String categoryId=null;
    String hRti=null;
	
    JSONObject valAR=null;
	JSONObject valueNames=null;
	String en=null;	
	
	 static final Logger logger = Logger.getLogger(de.class.getName());
	//Logger logger = Logger.getLogger("MyLog"); 

	 utilMods Mod=new utilMods();
	 

	
	@Given("^I am in the shopping page$")
	public void ui_module_completes_loadin1g() throws Throwable {
		Mod.UI();
	}
	
	@When("^I place order for a tshirt$")
	public void ui_place_order() throws Throwable {
		Mod.plcOrder();
	}
	
	@Then("^i should be able to verify order in order history$")
	public void ui_place_order_verify() throws Throwable {
		Mod.Verifyorder();
	}
}

