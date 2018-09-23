package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import cucumber.api.Scenario;

public class utilsPack {

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
	String origin=null;
	
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

	
	JSONArray contributions=null;
	JSONObject contributionType=null;
	String contributionTypeId=null;
	
	
	 //2016/11/16 12:08:43
	
	JSONArray Images=null;
	
	public String dataDate(){ 
SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dt = new Date();
	
		return(dateFormatter.format(dt));
		
		
	}
	
	
	public String dataDateENd(){ 
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
				
				Date dt = new Date();
				Calendar c = Calendar.getInstance(); 
				c.setTime(dt); 
				c.add(Calendar.DATE, 1);
				Date dtNext = c.getTime();
				
				return(dateFormatter.format(dtNext));
				
			}
	
	public String urlEV(String srcID,String stDate, String enDate){
		
		final String urlE="http://telia.uat.ecdapi.net//stores-active/contentInstance/event/filter?filter={%22term%22:%22internalIds.sourceId%22,%22value%22:%22"+srcID+"%22}&windowOfAvailabilityStart="+stDate+"T06:00:00Z&windowOfAvailabilityEnd="+enDate+"T06:00:00Z&windowOfAvailabilityType=strictStart&count=false";
		
		return urlE;
	}
	
public String urlEV_VOD(String srcID,String stDate, String enDate){
		
		final String urlE="http://telia.uat.ecdapi.net//stores-active/contentInstance/event/filter?filter={%22term%22:%22internalIds.sourceId%22,%22value%22:%22"+srcID+"%22}&windowOfAvailabilityStart="+stDate+"T06:00:00Z&windowOfAvailabilityEnd="+enDate+"T06:00:00Z&count=false";
		
		return urlE;
	}


public String urlEV_VOD_40(String srcID,String stDate, String enDate, String subGenre){
	
		
	final String urlE="filter%3D%7B%22operator%22%3A%22and%22%2C%2520%22criteria%22%3A%2520%5B%7B%22term%22%3A%22internalIds.sourceId%22%2C%22value%22%3A%22"+srcID+"%22%7D%2C%7B%22term%22%3A%22streams.availabilityStart%22%2C%22operator%22%3A%22atMost%22%2C%22value%22%3A%22"+stDate+"T06%3A00%3A00Z%22%7D%2C%7B%22term%22%3A%22streams.availabilityEnd%22%2C%22operator%22%3A%22atLeast%22%2C%22value%22%3A%22"+enDate+"T06%3A00%3A00Z%22%7D%2C%7B%22operator%22%3A%22nestedAnd%22%2C%22criteria%22%3A%5B%7B%22term%22%3A%22tags.category.categoryId%22%2C%22value%22%3A%22subGenre.ericsson%22%7D%2C%7B%22term%22%3A%22tags.value.valueNames.en%22%2C%22value%22%3A%22"+subGenre+"%22%7D%5D%7D%5D%7D%26annotation%3D%7B%22unconditionalAttributes%22%3A%2520%5B%22internalIds%22%2C%22tags%22%2C%22otherAttributes%22%5D%7D";
	//final String urlE="filter={%22operator%22:%22and%22,%20%22criteria%22:%20[{%22term%22:%22internalIds.sourceId%22,%22value%22:%22"+srcID+"%22},{%22term%22:%22streams.availabilityStart%22,%22operator%22:%22atMost%22,%22value%22:%22"+stDate+"T06:00:00Z%22},{%22term%22:%22streams.availabilityEnd%22,%22operator%22:%22atLeast%22,%22value%22:%22"+enDate+"T06:00:00Z%22},{%22operator%22:%22nestedAnd%22,%22criteria%22:[{%22term%22:%22tags.category.categoryId%22,%22value%22:%22subGenre.ericsson%22},{%22term%22:%22tags.value.valueNames.en%22,%22value%22:%22"+subGenre+"%22}]}]}&annotation={%22unconditionalAttributes%22:%20[%22streams%22,%22source%22,%22internalIds%22,%22tags%22,%22images%22,%22qualityRatings%22,%22searchableTextItems%22,%22programAwards%22,%22ageRatings%22,%22yearOfProduction%22,%22relatedMaterial%22,%22seasonNumber%22,%22numberOfEpisodes%22,%22searchableTitles%22,%22otherAttributes%22,%22contributions%22,%22rights%22,%22episodeNumber%22,%22videoAttributes%22,%22seriesNumber%22,%22countriesOfOrigin%22,%22broadcaster%22]}";
	//final String urlE="filter={%22operator%22:%22and%22, %22criteria%22: [{%22term%22:%22internalIds.sourceId%22,%22value%22:%22"+srcID+"%22},{%22term%22:%22streams.availabilityStart%22,%22operator%22:%22atMost%22,%22value%22:%22"+stDate+"T06:00:00Z%22},{%22term%22:%22streams.availabilityEnd%22,%22operator%22:%22atLeast%22,%22value%22:%22"+enDate+"T06:00:00Z%22}]}]}&annotation={%22unconditionalAttributes%22: [%22streams%22,%22source%22,%22internalIds%22,%22tags%22,%22images%22,%22qualityRatings%22,%22searchableTextItems%22,%22programAwards%22,%22ageRatings%22,%22yearOfProduction%22,%22relatedMaterial%22,%22seasonNumber%22,%22numberOfEpisodes%22,%22searchableTitles%22,%22otherAttributes%22,%22contributions%22,%22rights%22,%22episodeNumber%22,%22videoAttributes%22,%22seriesNumber%22,%22countriesOfOrigin%22,%22broadcaster%22]}&count=false";
	String url = "http://telia.uat.ecdapi.net/stores-active/contentInstance/event/filter?numberOfResults=1000&";
	url = url + urlE;//URLEncoder.encode(
	return url;
}

public String urlEV_Genre(String srcID,String stDate, String enDate){
	
	final String urlE="http://telia.uat.ecdapi.net/stores-active/contentInstance/event/attribute/tags.value.valueNames.en?filter={%22operator%22:%22and%22,%20%22criteria%22:%20[{%22term%22:%22internalIds.sourceId%22,%22value%22:%22"+srcID+"%22},{%22term%22:%22streams.availabilityStart%22,%22operator%22:%22atMost%22,%22value%22:%22"+stDate+"T06:00:00Z%22},{%22term%22:%22streams.availabilityEnd%22,%22operator%22:%22atLeast%22,%22value%22:%22"+enDate+"T06:00:00Z%22}]}&condition={%22term%22:%22tags.category.categoryId%22,%22value%22:%22subGenre.ericsson%22}";
	return urlE;
}
	
	

	public String urlSC_Linear(){
		
		final String urlS="http://telia.uat.ecdapi.net//stores-active/source/filter?numberOfResults=1000&annotation={%22unconditionalAttributes%22:%20[%22source%22,%22sourceId%22,%22sourceType%22,%22broadcaster%22,%22delivery%22]}&{%22term%22:%22internalIds.sourceType%22,%22value%22:%22linear%22}";
		
		return urlS;
	}
	
	
	public String urlSC_VOD(){
		
		final String urlS="http://telia.uat.ecdapi.net//stores-active/source/filter?numberOfResults=1000&annotation={%22unconditionalAttributes%22:%20[%22source%22,%22sourceId%22,%22sourceType%22,%22broadcaster%22,%22delivery%22]}&{%22term%22:%22internalIds.sourceType%22,%22value%22:%22vod%22}";
		
		return urlS;
	}
	
	
public String urlSC_Both(){
		
		final String urlS="http://telia.uat.ecdapi.net//stores-active/source/filter?numberOfResults=1000&annotation={%22unconditionalAttributes%22:%20[%22source%22,%22sourceId%22,%22sourceType%22,%22broadcaster%22,%22delivery%22]}";
		
		return urlS;
	}
	
	

	
	
public void ExcelOP(int ip,int errorSrcsize, ArrayList errorSrc) throws IOException{
	
	FileInputStream filein = new FileInputStream("C://Users//EZVSXHA//workspace//AO-KPIl//src//test//resources//report.xlsx");
	  Workbook wb = new XSSFWorkbook(filein);
	  CreationHelper createHelper = wb.getCreationHelper();
	Sheet sheet = wb.getSheetAt(0);
	  
	Row row = sheet.getRow(ip);
	
	  
    row.createCell(1).setCellValue(errorSrcsize);
    row.createCell(2).setCellValue(createHelper.createRichTextString(errorSrc.toString()));
    FileOutputStream fileOut = new FileOutputStream("C://Users//EZVSXHA//workspace//AO-KPIl//src//test//resources//report.xlsx");
    wb.write(fileOut);
    fileOut.close();
		
	}




public void ExceEvent(int ip,double applicableEvents,double non_compl, double compl_events, double perc,String srcID, String KPI) throws IOException{
	
	FileInputStream filein = new FileInputStream("C://Users//EZVSXHA//workspace//AO-KPIl//src//test//resources//eventReport"+KPI+".xlsx");
	  Workbook wb = new XSSFWorkbook(filein);
	  CreationHelper createHelper = wb.getCreationHelper();
	Sheet sheet = wb.getSheetAt(0);
	
	Row row = sheet.createRow(ip);
	if(row==null){
		System.out.println("NULL ROWS FOR"+KPI+" "+srcID);
	}
	 row.createCell(0).setCellValue(createHelper.createRichTextString(srcID));
	
	 int percValue=(int) Math.round(perc);
	 
	  
  row.createCell(1).setCellValue(applicableEvents);
  row.createCell(2).setCellValue(compl_events);
  row.createCell(3).setCellValue(non_compl);
  row.createCell(4).setCellValue(percValue);
  
  
  FileOutputStream fileOut = new FileOutputStream("C://Users//EZVSXHA//workspace//AO-KPIl//src//test//resources//eventReport"+KPI+".xlsx");
  wb.write(fileOut);
  fileOut.close();
		
	}

public void MissedKPis(ArrayList<String> fileSheets) throws IOException{
	
	
}



public ArrayList<String> KPis(ArrayList<String> fileSheets) throws IOException{
	return fileSheets;
	
}




public int LangChk(JSONObject obj,JSONObject objEv, int total_EV_event,String langChk, int compl_ev){

	
	
	
	 ArrayList<String> arStore = new ArrayList<String>(); 
	
	delivery=(JSONObject) obj.get("delivery");
	lanG=delivery.getJSONArray("languages");

	for(int ar=0;ar<lanG.length();ar++){
		arStore.add(lanG.getString(ar)+":");

	}

	searchableTitles=(JSONArray) objEv.get("searchableTitles");
	
	for(int o=0;o<searchableTitles.length();o++){
		hierarchyLevel_Serach = (JSONObject) searchableTitles.get(o);

		hierarchyLevel_Value= hierarchyLevel_Serach.get("hierarchyLevel").toString();
		hierarchyLevel_Type= hierarchyLevel_Serach.get("type").toString();

		JSONObject lamgStore=null;


		if(hierarchyLevel_Value.equalsIgnoreCase("program")){

			lamgStore=(JSONObject) hierarchyLevel_Serach.get("value");
			String lStore=lamgStore.toString().replaceAll("\"", "");;
			for(String lan:arStore){
				if(lStore.contains(lan) &&  hierarchyLevel_Type.equalsIgnoreCase("episode")){
					//total_Source++;

					langChk="PASS";
				

				}
				else{
				/*	total_Ev++;
					total_EV_event++;*/

			
				}

			}


		}
		/*else{
		total_EV_event++;
	}*/


	}

	if(!langChk.equalsIgnoreCase("PASS")){
	
		total_EV_event++;
		
	}
	else{
		compl_ev++;
	}
	

	 return total_EV_event;

}




public boolean offAir(JSONObject objEv, boolean offAirTBA){

	otherAttribute= (JSONArray) objEv.get("otherAttributes");
	
	for(int j=0;j<otherAttribute.length();j++){
		
		
		
		otherAttribute_value = (JSONObject) otherAttribute.get(j);
	
		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
		
	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
		offAirTBA=true;
	}
							    	
	}
	
	return offAirTBA;
}

public void treshLd(double compl_ev,double applicableEvents, int ip, String srcID, String data) throws IOException{
	double compl_events=compl_ev;//applicableEvents-non_compl;
	double non_compl=applicableEvents-compl_events;

	double perc =(compl_events/applicableEvents)*100;
	
	ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,data);
	
}
}