/*package Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import cucumber.api.Scenario;
public class utilModsBck {
	

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

	utilsPack uPE= new utilsPack();
	JSONArray contributions=null;
	JSONObject contributionType=null;
	String contributionTypeId=null;
	
	
	 //2016/11/16 12:08:43
	
	JSONArray Images=null;
	String orientation=null;
	String shape=null;
	private Scenario scenario;
	
	 static final Logger logger = Logger.getLogger(utilModsBck.class.getName());
	 
	public void Data01() throws IOException{
		utilsPack pk = new utilsPack();
		
		
		 
		 
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		String theString =null;
		 URL url = new URL(uPE.urlSC_Linear());
		 Logger logger = Logger.getLogger("MyLog");  
		    FileHandler fh;  
		 
		try {
			
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
 int total_SRC=array.length();
 
 ArrayList errorSrc= new ArrayList();
 
 
 ArrayList scSrc= new ArrayList();
 
 String finalcombStatus="";
 
 logger.log(Level.INFO,"total records in source "+total_SRC);
 fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFileData01.log"); 
 logger.addHandler(fh);
 SimpleFormatter formatter = new SimpleFormatter();  
 fh.setFormatter(formatter);  
 

 String evtype="";
 String Srtype="";
 String intID="";
 String sourcrcID="";
 
 int ip=1;
 
		for(int i=0;i<array.length();i++){
			
			ArrayList errorEvent= new ArrayList();
			int total_Ev=0;
	        

	        // the following statement is used to log any messages  
	       
	        
	       
			  
			JSONObject obj = (JSONObject) array.get(i);
			
			
			
			JSONObject dlVry =(JSONObject) obj.get("delivery");
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				 if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
						
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate)); 
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	
					    	int total_EV_event=0;
					    	int rv_cntr=0;
					    	
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								if(!EvTyp.equalsIgnoreCase(srcTyp)){
							     total_Ev++;
							    
							     logger.log(Level.SEVERE,"SRC TYPE Not MATCH found for "+srcID);
							     
								 Srtype="source type";
								
								 
							 
								}
								
							}
							else{
								 total_Ev++;
								 total_EV_event++;
								  logger.log(Level.SEVERE,"eventType NOT found for "+srcID);
								  
								   evtype="Event type";
									
							}
	                          if(objEv.has("internalIds")){
								
								srID=(JSONObject) objEv.get("internalIds");
								
								if(srID.has("sourceId")){
									srID_p=srID.get("sourceId").toString();
									if(srID_p.equalsIgnoreCase("")){
										  total_Ev++;
										  total_EV_event++;
										  logger.log(Level.SEVERE,"SRC ID not found for "+srcID);
										   
										  sourcrcID="source id";
									}
								
								}
								
								else{
									 total_Ev++;
									 total_EV_event++;
									  logger.log(Level.SEVERE,"sourceId NOT found for "+srcID);
									  sourcrcID="source id";
									
								}
								
								
							}
							
							else{
								 total_Ev++;
								 total_EV_event++;
								  logger.log(Level.SEVERE,"internalIds NOT found for "+srcID);
								  intID="internal ID";
								
							}
							
	                          if(total_EV_event>0){

									rv_cntr++;
                              	
                              	errorEvent.add(rv_cntr);
                              }
	                         
					    }
					    }
					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-01");
						    ip++;
						    

					    if(total_Ev>0){
                      	  errorSrc.add(srcID);
                        }
					   
				 }
								
						
					 
				}
				
			else{
				
				 total_Ev++;
				  logger.log(Level.SEVERE,"Source Type NOT found for "+srcID);
				  Srtype="source type";
	
			
			}
			
		}
		
		
		
		if(errorSrc.size()>0){
			uPE.ExcelOP(1, errorSrc.size(), errorSrc);

		} 
		
		else{
			logger.log(Level.INFO,"success records for DATA-01 COUNT -SUCCEEDED FOR"+scSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+scSrc);
		}
	}
	

	public void Data02(){
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		String evtype="";
		String Srtype="";
		String intID="";
		String sourcrcID="";
		String finalcombStatus="";
		String Quality="";
		String Tags="";
		String seasonI="";
		String ProgIDD="";
		String OTA="";
try{	
	String theString =null;
	 FileHandler fh; 
	 
	 URL url = new URL(uPE.urlSC_Both());
	 
	

	 
	try {


		//String result = java.net.URLDecoder.decode(url, "UTF-8");
		
		input = url.openConnection().getInputStream();
		StringWriter writer = new StringWriter();
		IOUtils.copy(input, writer, "UTF-8");
		theString = writer.toString();
		
	}

	finally {

	}

	final String JSON_PATH = theString;
    JSONArray array = new JSONArray(JSON_PATH);
    boolean offAirTBA = false;
    ArrayList errorSrc= new ArrayList();
    
    
    int total_SRC=array.length();
   
    //logger.log(Level.INFO,"total records in source "+total_SRC);
    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile02.log"); 
    logger.addHandler(fh);
    
    logger.info("Total  count in source IDs "+total_SRC);
    SimpleFormatter formatter = new SimpleFormatter();  
    fh.setFormatter(formatter);  
    int ip=1;
	for(int i=0;i<array.length();i++){
		
		ArrayList errorEvent= new ArrayList();
		 int total_Ev=0;
		  
		JSONObject obj = (JSONObject) array.get(i);
		 
		
		if(obj.has("sourceType")){
			 srcTyp=obj.getString("sourceType");
			
			if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear) || srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
				 
				 srcID=obj.get("sourceId").toString();
					
				 
					System.out.println("sourceId "+srcID);
					
					URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate)); 
					
					inputEv = urlEv.openConnection().getInputStream();
					StringWriter writer = new StringWriter();
					IOUtils.copy(inputEv, writer, "UTF-8");
					theString = writer.toString();
					
					final String JSON_PATH_ev = theString;
				    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
				    if(arrayEv.length()>0){
				    for(int k=0;k<arrayEv.length();k++){
				    	int total_EV_event=0;
				    	int rv_cntr=0;
				    	JSONObject objEv = (JSONObject) arrayEv.get(k);
						 
						
		if(objEv.has("otherAttributes")){
				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
						
				    	for(int j=0;j<otherAttribute.length();j++){
				    		
				    		
				    		
				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
				    	
				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
				    		
				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
				    		offAirTBA=true;
				    	}
				    							    	
				    	}
						}
						
		else{
			 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
			 total_Ev++;
			 OTA="Other Attributes";
			 total_EV_event++;
		      
		     
		}
				    	if(!offAirTBA){
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								if(!EvTyp.equalsIgnoreCase(srcTyp)){
									 total_Ev++;
									 logger.log(Level.SEVERE,"eventType not matches with source for "+srcID);
									   Assert.assertFalse(false);
									   evtype="Event Type";
									
								}
								
							}
							
							else{
								total_Ev++;
								total_EV_event++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
								  
								   evtype="Event Type";
							}
							
							if(objEv.has("internalIds")){
								
								ProgID=(JSONObject) objEv.get("internalIds");
								
								if(ProgID.has("programId")){
									Progid_p=ProgID.get("programId").toString();
									if(Progid_p.equalsIgnoreCase("")){
										total_Ev++;
										total_EV_event++;
										logger.log(Level.SEVERE,"PROG ID not found for "+srcID);
										 ProgIDD="PROG ID";
									}
								
								}
								
								else{
									logger.log(Level.SEVERE,"PROG ID not found for "+srcID);
									total_Ev++;
									   Assert.assertFalse(false);
									   ProgIDD="PROG ID";
									   total_EV_event++;
									   
								}
								
							}
							
							else{
								total_EV_event++;
								logger.log(Level.SEVERE,"internalIds not found for "+srcID);
								total_Ev++;
								intID="Internal ID";
								   Assert.assertFalse(false);
							}
							
				    	}
							
				    	 if(total_EV_event>0){
				    		 rv_cntr++;
                         	
                         	errorEvent.add(rv_cntr);
                         }		
						
				    }
				    
				    double applicableEvents= arrayEv.length();
					   double non_compl=errorEvent.size();
					    double compl_events=applicableEvents-non_compl;
					    
					    double perc =(compl_events/applicableEvents)*100;
					
					    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-02");
					    ip++;
				    }

				    if(total_Ev>0){
				                         	  errorSrc.add(srcID);
				                           }


					
			 }
							
					
				 
			}
			
		else{
			 total_Ev++;
			 
			 logger.log(Level.SEVERE,"SourceType not found for "+srcID);

Srtype="source type";
		}
	
	}
	




if(errorSrc.size()>0){
			logger.log(Level.SEVERE,"error records for DATA-02 COUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc);
			
			uPE.ExcelOP(2, errorSrc.size(), errorSrc);
		} 
}

catch(Exception e){
	
}

	}

	
	public void Data_03() throws IOException{
		
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		String evtype="";
		String Srtype="";
		String intID="";
		String sourcrcID="";
		String finalcombStatus="";
		String Quality="";
		String Tags="";
		String seasonI="";
		String ProgIDD="";
		String serID="";

		String OTA="";
		

		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		
		try {

			
			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
			
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);

	    boolean enChk=false;
	    
	    ArrayList errorSrc= new ArrayList();
	  
	   
	    //logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile03.log"); 
	    logger.addHandler(fh);
	    int total_SRC=array.length();
	    logger.info("Total  count in source IDs "+total_SRC);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;

		for(int i=0;i<array.length();i++){
			
			 int total_Ev=0;
			 ArrayList errorEvent= new ArrayList();
			  
			JSONObject obj = (JSONObject) array.get(i);
			 
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				 JSONObject dlVry =(JSONObject) obj.get("delivery");
				 
				 if(dlVry.has("quality")){
				 String QL=dlVry.get("quality").toString();
				 
				if(QL.equalsIgnoreCase("premium")){
					
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear) || srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
					 
					 srcID=obj.get("sourceId").toString();
						
				
						System.out.println("sourceId "+srcID);
						
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	int total_EV_event=0;
					    	int rv_cntr=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
								if(objEv.has("eventType")){
									EvTyp=objEv.get("eventType").toString();
									
									if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
										 total_Ev++;
										 logger.log(Level.SEVERE,"Event type not VOD or LINEAR found for "+srcID);
										 total_EV_event++;
									 Assert.assertFalse(false);
										
									}
									
								}
							
							else{
								total_Ev++;
								total_EV_event++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
								 evtype="Event type";
																
								 Assert.assertFalse(false);
							}
							
							if(objEv.has("internalIds")){
								
								srID=(JSONObject) objEv.get("internalIds");
								
								if(srID.has("seriesId")){
									srID_p=srID.get("seriesId").toString();
									if(srID_p.equalsIgnoreCase("")){
										total_EV_event++;
										total_Ev++;
										logger.log(Level.SEVERE,"series ID not found for "+srcID);
										 Assert.assertFalse(false);
										 serID="Series ID";
									}
								
								}
								
								else{
									total_EV_event++;
									total_Ev++;
									logger.log(Level.SEVERE,"Series ID not found for "+srcID);
									
									serID="Series ID";
								}
								
								
							}
							
							else{
								total_EV_event++;
								total_Ev++;
								logger.log(Level.SEVERE,"internalIds not found for "+srcID);
								intID="Internal ID";
								
							}
							
							
                                if(objEv.has("tags")){
                                	tagAR=(JSONArray) objEv.get("tags");
                                	
                                	
                                	for(int z=0;z<tagAR.length();z++){
                                		hierarchyLevel=(JSONObject) tagAR.get(z);
                                		
                                		category=hierarchyLevel.getJSONObject("category");
                                		
                                		categoryId=category.get("categoryId").toString();
                                		
                                		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                	
                                		
                                		valAR=hierarchyLevel.getJSONObject("value");
                                		 valueNames=(JSONObject) valAR.get("valueNames");
                                			String en=valueNames.get("en").toString();	
                                			
                                			
                                		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Film")){
                                			enChk=true;
                                			
                                			
                                		}
                                		
                                		
                                		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Drama series")){
                                			enChk=true;
                                			
                                		}
                                	}
								if(!enChk){
									
								
								if(srID.has("seriesId")){
									srID_p=srID.get("seriesId").toString();
									System.out.println("SERIES ID "+srID_p);
									if(srID_p.equalsIgnoreCase("")){
										total_Ev++;
										total_EV_event++;
										 Assert.assertFalse(false);
										 logger.log(Level.SEVERE,"series ID not found for "+srcID);
										 serID="Series ID";
									}
								
								}
								
								
								else{
									total_Ev++;
									 logger.log(Level.SEVERE,"series ID not found for "+srcID);
									 serID="Series ID";
									 total_EV_event++;
								}
								
								
								}
							}
							
													
                                if(total_EV_event>0){
                                   rv_cntr++;
                                	
                                	errorEvent.add(rv_cntr);
                                }
                               
					    }
					    
					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-03");
						    ip++;
						    
					    
					    if(total_Ev>0){
					    	
	                      	  errorSrc.add(srcID);
	                        }

					    }
				 }
								
						
					 
				}
				
				 }
				 
				 else{
						total_Ev++;
						 logger.log(Level.SEVERE,"QUALITY not found for "+srcID);
						 Quality="Quality";
						
					}
				
		}
			else{
				total_Ev++;
				Srtype="Source Type";
				 logger.log(Level.SEVERE,"Source type not found for "+srcID);
			}
		}




if(errorSrc.size()>0){
			logger.log(Level.SEVERE,"error records for COUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc);
			
			uPE.ExcelOP(3, errorSrc.size(), errorSrc);
		} 
	
	}

	public void Data_04() {
		
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
	
		String seasonID="";
		String evtype="";
		String Srtype="";
		String intID="";
		String sourcrcID="";
		String finalcombStatus="";
		String Quality="";
		String seasonI="";
		String InernalID="";
		String EventTY="";
		String Tags="";

		String OTA="";
		
try{
	
	
	    
		 boolean offAirTBA = false;


		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
	    ArrayList errorSrc= new ArrayList();
	    
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data04.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  

	    int ip=1;
	    boolean enChk=false;
		for(int i=0;i<array.length();i++){
			
			ArrayList errorEvent= new ArrayList();
			 int total_Ev=0;
			  
			JSONObject obj = (JSONObject) array.get(i);
			 
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				 JSONObject dlVry =(JSONObject) obj.get("delivery");
				 
				 
				 if(dlVry.has("quality")){
					 
					 String QL=dlVry.get("quality").toString();
				if(QL.equalsIgnoreCase("premium")){
					
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear) || srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
						
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	
					    	int total_EV_event=0;
					    	int rv_cntr=0;
							
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){
						    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
								
						    	for(int j=0;j<otherAttribute.length();j++){
						    		
						    		
						    		
						    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
						    	
						    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
						    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
						    		
						    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
						    		offAirTBA=true;
						    	}
						    							    	
						    	}
								}
								
				else{
				
					 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
					 total_Ev++;
					 OTA="Other Attributes";
					 total_EV_event++;
				   
				     
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
								  
									 logger.log(Level.SEVERE,"Event type not VOD or LINEAR found for "+srcID);
								}
								
							}
							
							else{
																
								 total_Ev++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
								  EventTY="Event type";
								  total_EV_event++;
							   
							}
							
							if(objEv.has("internalIds")){
								
								srID=(JSONObject) objEv.get("internalIds");
								
						
								
							}
							
							else{
							    
								total_Ev++;
								logger.log(Level.SEVERE,"internalIds not found for "+srcID);
								 InernalID="Internal ID";
								 total_EV_event++;
							}
							
							
                                if(objEv.has("tags")){
                                	tagAR=(JSONArray) objEv.get("tags");
                                	
                                	
                                	for(int z=0;z<tagAR.length();z++){
                                		hierarchyLevel=(JSONObject) tagAR.get(z);
                                		
                                		category=hierarchyLevel.getJSONObject("category");
                                		
                                		categoryId=category.get("categoryId").toString();
                                		
                                		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                	
                                		
                                		valAR=hierarchyLevel.getJSONObject("value");
                                		 valueNames=(JSONObject) valAR.get("valueNames");
                                			String en=valueNames.get("en").toString();	
                                			
                                			
                                		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Film")){
                                			enChk=true;
                                			
                                			
                                		}
                                		
                                		
                                		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Drama series")){
                                			enChk=true;
                                			
                                		}
                                	}
								
								
							}
                                
                                else{
                                	logger.log(Level.SEVERE,"tags not found for "+srcID);
									total_Ev++;
									 Tags="Tags";
									 total_EV_event++;
									
								}
                                
							
                                if(!enChk && !offAirTBA){
									
    								
    								if(srID.has("seasonId")){
    									srID_p=srID.get("seasonId").toString();
    									System.out.println("SEASON ID "+srID_p);
    									if(srID_p.equalsIgnoreCase("")){
    										total_Ev++;
    										 total_EV_event++;
    											logger.log(Level.SEVERE,"Season ID not found for "+srcID);
    											seasonI="Season ID";
    									}
    								
    								}
    								else{
    									total_Ev++;
										
										logger.log(Level.SEVERE,"Season ID not found for "+srcID);
										seasonI="Season ID";
										 total_EV_event++;
    								}
    								
    								
    								
    								
    								}	
                                
                                if(total_EV_event>0){

									rv_cntr++;
                                	
                                	errorEvent.add(rv_cntr);
                                }
							
					    }
					    
					    
					    
					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-04");
						    ip++;
						    
					    
					    }
					    if(total_Ev>0){
					    	
	                      	  errorSrc.add(srcID);
	                        }

						
				 }
								
						
					 
				}
				
				 }
				 else{
						total_Ev++;
						logger.log(Level.SEVERE,"Quality not found for "+srcID);
						Quality="Quality";
					}
		}
			else{
				total_Ev++;
				Srtype="source type";
				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
			
			}
		}

		
	
		if(errorSrc.size()>0){
			logger.log(Level.SEVERE,"error records for COUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc);
			
			uPE.ExcelOP(4, errorSrc.size(), errorSrc);
		} 
		 
	}	

catch(Exception e){
	logger.log(Level.SEVERE,"error records for DATA-04 ");
	
	e.printStackTrace();
}
	}
	
	
	

	public void Data_05() throws IOException {
		
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		String evtype="";
		String Srtype="";
		String intID="";
		String sourcrcID="";
		String finalcombStatus="";
		String Quality="";
		String Tags="";
		String seasonI="";
		String ProgIDD="";
		String serID="";
		String OTA="";
		String evID="";
		
		

		 boolean offAirTBA = false;


		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 


		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
			
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
	    ArrayList errorSrc= new ArrayList();
	     
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data05.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  

	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			
			 int total_Ev=0;
			 ArrayList errorEvent= new ArrayList();
			  
			JSONObject obj = (JSONObject) array.get(i);
			 
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				 JSONObject dlVry =(JSONObject) obj.get("delivery");
				 
				 
			
					
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear) || srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
						
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	int total_EV_event=0;
					    	int rv_cntr=0;
							
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){
						    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
								
						    	for(int j=0;j<otherAttribute.length();j++){
						    		
						    		
						    		
						    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
						    	
						    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
						    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
						    		
						    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
						    		offAirTBA=true;
						    	}
						    							    	
						    	}
								}
								
				else{
					 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
					 total_Ev++;
					 OTA="Other Attributes";
					 total_EV_event++;
				  
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){

logger.log(Level.SEVERE,"Event type not VOD or LINEAR found for "+srcID);
									 total_Ev++;
									 total_EV_event++;
								     
									
								}
								
							}
							
							else{
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);					
								 total_Ev++;
								 evtype="Event Type";
								 total_EV_event++;
							   
							}
							
							if(objEv.has("internalIds")){
								
								srID=(JSONObject) objEv.get("internalIds");
								
						
								
							}
							
							else{
							    
								total_Ev++;
								logger.log(Level.SEVERE,"internalIds not found for "+srcID);
								intID="Internal ID";
								total_EV_event++;
							}
							
                               
                               if(!offAirTBA){
									
   								
   								if(srID.has("eventId")){
   									srID_p=srID.get("eventId").toString();
   									System.out.println("eventId ID "+srID_p);
   									if(srID_p.equalsIgnoreCase("")){
   										total_Ev++;
   										total_EV_event++;
   										logger.log(Level.SEVERE,"eventId ID not found for "+srcID);
   										evID="Event ID";
   									  
   									}
   								
   								}
   								
   								
   								else{
  									total_Ev++;
  									total_EV_event++;
  									logger.log(Level.SEVERE,"eventId ID not found for "+srcID);
  									evID="Event ID";
  									
  								}
                               							
   								
   								}
                               if(total_EV_event>0){
                            	   rv_cntr++;
                               	
                               	errorEvent.add(rv_cntr);
                               }
					    }
					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-05");
						    ip++;
						    
					    
					    }
					    if(total_Ev>0){
	                      	  errorSrc.add(srcID);
	                        }
						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				 Srtype="Source Type";
				
			}
		}
		
		
	
		if(errorSrc.size()>0){
			logger.log(Level.SEVERE,"error records for COUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc);
			uPE.ExcelOP(5, errorSrc.size(), errorSrc);
		} 

		 
		
	
	}

	public void Data_06() throws IOException {

		
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		String evtype="";
		String Srtype="";
		String intID="";
		String sourcrcID="";
		String finalcombStatus="";
		String Quality="";
		String Tags="";
		String seasonI="";
		String ProgIDD="";
		String serID="";
		String OTA="";
		String evID="";
		

		 boolean offAirTBA = false;


		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Linear());
		 
		
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
			
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
	    ArrayList errorSrc= new ArrayList();
	     
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data06.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			
			int total_Ev=0;
			ArrayList errorEvent= new ArrayList();
			  
			JSONObject obj = (JSONObject) array.get(i);
			 
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				 JSONObject dlVry =(JSONObject) obj.get("delivery");
				 
				 
			
					
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
						
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	int rv_cntr=0;
					    	int total_EV_event=0;
							
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){
						    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
								
						    	for(int j=0;j<otherAttribute.length();j++){
						    		
						    		
						    		
						    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
						    	
						    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
						    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
						    		
						    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
						    		offAirTBA=true;
						    	}
						    							    	
						    	}
								}
								
				else{
				
					 total_Ev++;
					 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
					 OTA="Other Attributes";
					 total_EV_event++;

				}
                              if(!offAirTBA){
									

      							if(objEv.has("eventType")){
      								EvTyp=objEv.get("eventType").toString();
      								
      								if(!EvTyp.equalsIgnoreCase(srcTyp)){
      								     total_Ev++;
      								   logger.log(Level.SEVERE,"eventType not found for "+srcID);
         								evtype="Event Type";
         								total_EV_event++;
         							    
      								   
      								}
      								
      								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
      									 total_Ev++;
      									logger.log(Level.SEVERE,"eventType not found for "+srcID);
          								evtype="Event Type";
          								total_EV_event++;
          							    
      									
      								}
      								
      							}
      							
      							else{
      																
      								 total_Ev++;
      								total_EV_event++;
      								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
      								evtype="Event Type";
      							    
      							}
      							
      							
                            	  if(objEv.has("source")){
      								
      								String srIDstr=objEv.get("source").toString();
      								
      								if(srIDstr.equalsIgnoreCase("")){
      									
      									total_Ev++;
      									total_EV_event++;
      									logger.log(Level.SEVERE,"Source not found for "+srcID);
      									sourcrcID="Source ID";
         								
      								}
      						
      								
      							}
      							
      							else{
      								total_EV_event++;
      								total_Ev++;
      								 logger.log(Level.SEVERE,"Source not found for "+srcID);
      								sourcrcID="Source ID";
      								
      							}		
  								
  								}
                              
                              if(total_EV_event>0){

									rv_cntr++;
                              	
                              	errorEvent.add(rv_cntr);
                              }
					    }
					    
					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-06");
						    ip++;
						    
					    }
					    if(total_Ev>0){
	                      	  errorSrc.add(srcID);
	                        }


						
				 }
								
						
					 
				
				
		}
			else{
				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				total_Ev++;
				
			}
		}
		//finalcombStatus=evtype+" ,"+Srtype+" ,"+intID+" ,"+sourcrcID+" ,"+Quality+", "+Tags+", "+seasonI+", "+", "+", "+OTA+","+ProgIDD+", "+serID+", "+evID;


if(errorSrc.size()>0){
			logger.log(Level.SEVERE,"error records for COUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc);
			
			uPE.ExcelOP(6, errorSrc.size(), errorSrc);
		} 
		
	
	
		
	}

	public void Data_07() throws IOException {
		
		String evtype="";
		String Srtype="";
		String intID="";
		String sourcrcID="";
		String finalcombStatus="";
		String Quality="";
		String Tags="";
		String seasonI="";
		String ProgIDD="";
		String serID="";
		String OTA="";
		String evID="";
		
		
		 boolean offAirTBA = false;

		 utilsPack pk = new utilsPack();
			
			String stDate=pk.dataDate();
			String enDate=pk.dataDateENd();
		  
			
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_VOD());
		
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
	    ArrayList errorSrc= new ArrayList();
	    
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data07.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    

	    int ip=1;
		for(int i=0;i<array.length();i++){
			ArrayList errorEvent= new ArrayList();
			
			int total_Ev=0;
			  
			JSONObject obj = (JSONObject) array.get(i);
			 
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				 JSONObject dlVry =(JSONObject) obj.get("delivery");
				 
				 
			
					
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
						
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	
					    	int total_EV_event=0;
					    	int rv_cntr=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){
						    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
								
						    	for(int j=0;j<otherAttribute.length();j++){
						    		
						    		
						    		
						    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
						    	
						    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
						    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
						    		
						    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
						    		offAirTBA=true;
						    	}
						    							    	
						    	}
								}
								
				else{
				
					 total_Ev++;
					 total_EV_event++;
					 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
				
				     
				}
                             
                             if(!offAirTBA){
									
                            	 if(objEv.has("eventType")){
     								EvTyp=objEv.get("eventType").toString();
     								
     								if(!EvTyp.equalsIgnoreCase(srcTyp)){
     								     total_Ev++;
     								    total_EV_event++;
     								   
     								}
     								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
     									 total_Ev++;
     									total_EV_event++;
     								   
     									
     								}
     								
     							}
     							
     							else{
     								total_EV_event++;				
     								 total_Ev++;
     								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
     							   
     							}
     							
     							
 								
                           	  if(objEv.has("source")){
     								
                           		String srIDstr=objEv.get("source").toString();
                           		
     								if(srIDstr.equalsIgnoreCase("")){
     									
     									total_Ev++;
     									total_EV_event++;
     									logger.log(Level.SEVERE,"Source not found for "+srcID);
     									
        								
     								}
     						
     								
     							}
     							
     							else{
     							    
     								total_Ev++;
     								 logger.log(Level.SEVERE,"Source not found for "+srcID);
     								total_EV_event++;
     								
     							}		
 								
 								}
                             
                             if(total_EV_event>0){
                            	 rv_cntr++;
                             	
                             	errorEvent.add(rv_cntr);
                             }

					    }
					    
					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-07");
						    ip++;
					    
					    }

					    if(total_Ev>0){
					    	// add map, src ID and event pass and event fail - total events , relevant events, event met conditions, event did not met conditions
					                         	  errorSrc.add(srcID);
					                           }

						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
				
			}
		}

//finalcombStatus=evtype+" ,"+Srtype+" ,"+intID+" ,"+sourcrcID+" ,"+Quality+", "+Tags+", "+seasonI+", "+", "+", "+OTA+","+ProgIDD+", "+serID+", "+evID;

if(errorSrc.size()>0){
	uPE.ExcelOP(7, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc);
			
			
		} 
		
	}

	public void Data_08() throws IOException {
		 boolean offAirTBA = false;
		 utilsPack pk = new utilsPack();
			
			String stDate=pk.dataDate();
			String enDate=pk.dataDateENd();
			
			
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
	    ArrayList errorSrc= new ArrayList();
	    
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data08.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    
	    int ip=1;
		for(int i=0;i<array.length();i++){
			ArrayList errorEvent= new ArrayList();
			 int total_Ev=0;
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			 
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				 JSONObject dlVry =(JSONObject) obj.get("delivery");
				 
				 
			
					
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear) || srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
						
						
						 
						
						URL urlEv =new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	int rv_cntr=0;
					    	int total_EV_event=0;
							
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){
						    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
								
						    	for(int j=0;j<otherAttribute.length();j++){
						    		
						    		
						    		
						    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
						    	
						    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
						    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
						    		
						    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
						    		offAirTBA=true;
						    	}
						    							    	
						    	}
								}
								
				else{
				
					 total_Ev++;
					 total_EV_event++;
					 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
				    
				     
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
								     
									 logger.log(Level.SEVERE,"eventType not found for "+srcID);
								}
								
							}
							
							else{
																
								 total_Ev++;
								 total_EV_event++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
							    
							}
                              
                              if(!offAirTBA){
                            		if(objEv.has("searchableTitles")){
        								
                            			delivery=(JSONObject) obj.get("delivery");
                						lanG=delivery.getJSONArray("languages");
                						
                						for(int ar=0;ar<lanG.length();ar++){
                							arStore.add(lanG.getString(ar)+":");
                							
                							}
                            			
                            			searchableTitles=(JSONArray) objEv.get("searchableTitles");
        									for(int o=0;o<searchableTitles.length();o++){
        										hierarchyLevel_Serach = (JSONObject) searchableTitles.get(o);
        										
        										hierarchyLevel_Value= hierarchyLevel_Serach.get("hierarchyLevel").toString();
        										        										
        										JSONObject lamgStore=null;
        										
        										
        										if(hierarchyLevel_Value.equalsIgnoreCase("program")){
        											
        											lamgStore=(JSONObject) hierarchyLevel_Serach.get("value");
        			    							String lStore=lamgStore.toString().replaceAll("\"", "");;
        											for(String lan:arStore){
        												if(lStore.contains(lan)){
        												}
        												else{
        													total_Ev++;
        													total_EV_event++;
        													 logger.log(Level.SEVERE,"Languages not found for "+srcID);
       			        								
        												}
        												
        											}
        											
        										}
        									
        										
        									}
        								
        								
        						
        								
        							}
        							
        							else{
        							    
        								total_Ev++;
        								total_EV_event++;
        								 logger.log(Level.SEVERE,"SEARCHABLE TITLES not found for "+srcID);
        								 
        							}
  								
  								                             							
  								
  								}
                              
                              if(total_EV_event>0){
                            	  rv_cntr++;
                              	          	errorEvent.add(rv_cntr);
                              }
					    }
					    
					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-08");
						    ip++;
						    


					    
					    }
					    if(total_Ev>0){
					                         	  errorSrc.add(srcID);
					                           }

						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
		}


if(errorSrc.size()>0){
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc);
			uPE.ExcelOP(8, errorSrc.size(), errorSrc);
		} 
		
	}

	public void Data_09() throws Exception {
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_VOD());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
	    ArrayList errorSrc= new ArrayList();
	    
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data09.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			ArrayList errorEvent= new ArrayList();
			 int total_Ev=0;
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				 JSONObject dlVry =(JSONObject) obj.get("delivery");
				 
				 
			
					
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
						
						
						 
						
						URL urlEv =new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	
					    	int total_EV_event=0;
					    	int rv_cntr=0;
							
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){
						    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
								
						    	for(int j=0;j<otherAttribute.length();j++){
						    		
						    		
						    		
						    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
						    	
						    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
						    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
						    		
						    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
						    		offAirTBA=true;
						    	}
						    							    	
						    	}
								}
								
				else{
				
					 total_Ev++;

					 logger.log(Level.SEVERE,"Other attribute not matches with source for "+srcID);
					  total_EV_event++;
				     
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								if(!EvTyp.equalsIgnoreCase(srcTyp)){
 								     total_Ev++;
 								    total_EV_event++;
 								    
 								}
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
								   
									
								}
								
							}
							
							
							
						
							
							
                             if(objEv.has("tags")){

                             	tagAR=(JSONArray) objEv.get("tags");
                             	
                             	
                             	for(int z=0;z<tagAR.length();z++){
                             		hierarchyLevel=(JSONObject) tagAR.get(z);
                             		
                             		category=hierarchyLevel.getJSONObject("category");
                             		
                             		categoryId=category.get("categoryId").toString();
                             		
                             		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                             	
                             		
                             		valAR=hierarchyLevel.getJSONObject("value");
                             		 valueNames=(JSONObject) valAR.get("valueNames");
                             			String en=valueNames.get("en").toString();	
                             			
                             			
                             		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Film")){
                             			enChk=true;
                             			total_Source++;
                             			break;
                             			
                             		}
                             		
                             		
                             		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Drama series")){
                             			enChk=true;
                             			total_Source++;
                             			break;
                             		}
                             	}
								
								
							
                             }                             
                             
                             else{
									total_Ev++;
									total_EV_event++;
									 logger.log(Level.SEVERE,"Tags not matches with source for "+srcID);
									
								}
                             
                             
                             
                             if(!offAirTBA && enChk){
                           		if(objEv.has("searchableTitles")){
       								
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
       												if(lStore.contains(lan) &&  hierarchyLevel_Type.equalsIgnoreCase("generic")){
       													total_Source++;
       													 logger.log(Level.INFO,"Success records for DATA-09 Languages "+total_Source);
       													
       												}
       												else{
       													total_Ev++;
       													total_EV_event++;

       												 logger.log(Level.SEVERE,"Language not matches with source for "+srcID);
       												}
       												
       											}
       											
       										}
       										else{
       											total_EV_event++;
       										 logger.log(Level.SEVERE,"PROGRAM not matches with source for "+srcID);
       										}
       										
       									}
       								
       								
       						
       								
       							}
       							
       							else{
       							    
       								total_Ev++;
       								total_EV_event++;
       							 logger.log(Level.SEVERE,"Searchable Titles not present for "+srcID);
       								
       							}
 								
 								                             							
 								
 								}
                             
                             if(total_EV_event>0){
	                                 rv_cntr++;
                                	
                                	errorEvent.add(rv_cntr);
                             }
                             
                             
					    }
					    
					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-09");
						    ip++;
					    
					    }

					    if(total_Ev>0){
	                      	  errorSrc.add(srcID);
	                        }
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
		}


if(errorSrc.size()>0){
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc);
			uPE.ExcelOP(9, errorSrc.size(), errorSrc);
		} 

	}

	public void Data_10() throws Exception{

utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
		
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data10.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    
	    int ip=1;
		for(int i=0;i<array.length();i++){
			ArrayList errorEvent= new ArrayList();
			int total_Ev=0;
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				 
				 
			
					
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
						
						
						 
						
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	
					    	int total_EV_event=0;
					    	int rv_cntr=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){
						    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
								
						    	for(int j=0;j<otherAttribute.length();j++){
						    		
						    		
						    		
						    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
						    	
						    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
						    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
						    		
						    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
						    		offAirTBA=true;
						    	}
						    							    	
						    	}
								}
								
				else{
				
					 total_Ev++;
					 total_EV_event++;

					 logger.log(Level.SEVERE,"OT Attribute not matches with source for "+srcID);
				    
				     
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
								     
									 total_EV_event++;
								}
								
							}
							
							else{
								total_EV_event++;						
								 total_Ev++;

								 logger.log(Level.SEVERE,"eventType not matches with source for "+srcID);
							     
							}
							
						
							
					
                            
                            
                            if(!offAirTBA){
                          		if(objEv.has("searchableTitles")){
      								
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
      													total_Source++;
      													 logger.log(Level.INFO,"Success records for DATA-05 Languages "+total_Source);
      													
      												}
      												else{
      													total_Ev++;
      													total_EV_event++;

      													 logger.log(Level.SEVERE,"Language not matches with source for "+srcID);
     			        								
      												}
      												
      											}
      											
      										}
      										else{
      											total_EV_event++;
      										}
      									
      										
      									}
      								
      								
      						
      								
      							}
      							
      							else{

      								 logger.log(Level.SEVERE,"Searchable Titles not matches with source for "+srcID);
      								total_Ev++;
      								total_EV_event++;
      							}
								
								                             							
								
								}
                            
                            if(total_EV_event>0){
                            	rv_cntr++;
                            	
                            	errorEvent.add(rv_cntr);
                            }

					    }
					    


					    double applicableEvents= arrayEv.length();
					 					   double non_compl=errorEvent.size();
					 					    double compl_events=applicableEvents-non_compl;
					 					    
					 					    double perc =(compl_events/applicableEvents)*100;
					 					
					 					    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-10");
					 					    ip++;
					 					    
					    
					    }

					    if(total_Ev>0){
					                         	  errorSrc.add(srcID);
					                           }
				 }
								
						
					 
				
				
		}
			else{

				 logger.log(Level.SEVERE,"SoruceTYPE not matches with source for "+srcID);
				total_Ev++;
				 
			}
		}

	
		if(errorSrc.size()>0){
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc);
			uPE.ExcelOP(10, errorSrc.size(), errorSrc);
		} 

		
		 
	
	}

	public void Data_11() {
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
		
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data11.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    
	    int ip=1;
		for(int i=0;i<array.length();i++){
			ArrayList errorEvent= new ArrayList();
			
			 ArrayList<String> arStore = new ArrayList<String>(); 
			 int total_Ev=0;
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			 if(dlVry.has("quality")){
			 String QL=dlVry.get("quality").toString();
			 
			if(QL.equalsIgnoreCase("premium")){
				
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
						
						
						 
						
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	
					    	int total_EV_event=0;
					    	int rv_cntr=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){
								
							}
								
				else{
				
					 total_Ev++;

					 logger.log(Level.SEVERE,"Ot Attribute not matches with source for "+srcID);
				  
				     
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
								    
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 total_EV_event++;
								 logger.log(Level.SEVERE,"eventType not matches with source for "+srcID);
							}
							
						
							
							
                           if(objEv.has("tags")){


                            	tagAR=(JSONArray) objEv.get("tags");
                            	
                            	
                            	for(int z=0;z<tagAR.length();z++){
                            		hierarchyLevel=(JSONObject) tagAR.get(z);
                            		
                            		category=hierarchyLevel.getJSONObject("category");
                            		
                            		categoryId=category.get("categoryId").toString();
                            		
                            		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                            	
                            		
                            		valAR=hierarchyLevel.getJSONObject("value");
                            		 valueNames=(JSONObject) valAR.get("valueNames");
                            			String en=valueNames.get("en").toString();	
                            			
                            			
                            		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Film")){
                            			enChk=true;
                            			total_Source++;
                            			break;
                            			
                            		}
                            		
                            		
                            		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Drama series")){
                            			enChk=true;
                            			total_Source++;
                            			break;
                            		}
                            	}
								
								
							
                            
                           }                             
                           
                           else{
									total_Ev++;
									total_EV_event++;
									 logger.log(Level.SEVERE,"Tags not matches with source for "+srcID);
									
								}
                           
                           
                           
                           if(enChk){
                         		if(objEv.has("searchableTextItems")){
     								
                         			delivery=(JSONObject) obj.get("delivery");
             						lanG=delivery.getJSONArray("languages");
             						
             						for(int ar=0;ar<lanG.length();ar++){
             							arStore.add(lanG.getString(ar)+":");
             							
             							}
                         			
                         			searchableTitles=(JSONArray) objEv.get("searchableTextItems");
     									for(int o=0;o<searchableTitles.length();o++){
     										hierarchyLevel_Serach = (JSONObject) searchableTitles.get(o);
     										
     										
     										hierarchyLevel_Type= hierarchyLevel_Serach.get("type").toString();
     										        										
     										JSONObject lamgStore=null;
     										
     									
     											lamgStore=(JSONObject) hierarchyLevel_Serach.get("value");
     			    							String lStore=lamgStore.toString().replaceAll("\"", "");
     											for(String lan:arStore){
     												if(lStore.contains(lan) &&  hierarchyLevel_Type.equalsIgnoreCase("longSynopsis")){
     													total_Source++;
     													total_EV_event++;
     													 logger.log(Level.INFO,"Success records for DATA-011 Languages "+total_Source);
     													
     												}
     												else{
     													total_Ev++;
     													total_EV_event++;
     													 logger.log(Level.SEVERE,"Languages not matches with source for "+srcID);
    			        								
     												}
     												
     											}
     											
     										
     									
     										
     									}
     								
     								
     						
     								
     							}
     							
     							else{
     							    
     								total_Ev++;
     								total_EV_event++;
     								 logger.log(Level.SEVERE,"searchableTextItems not matches with source for "+srcID);
     								 
     							}
								
								                             							
								
								}
                           
                           if(total_EV_event>0){

								rv_cntr++;
                           	
                           	errorEvent.add(rv_cntr);
                           }
					    }
					    

					    double applicableEvents= arrayEv.length();
					 					   double non_compl=errorEvent.size();
					 					    double compl_events=applicableEvents-non_compl;
					 					    
					 					    double perc =(compl_events/applicableEvents)*100;
					 					
					 					    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-11");
					 					    ip++;
					 					    
					    
					    }
					    if(total_Ev>0){
	                      	  errorSrc.add(srcID);
	                        }
						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				 logger.log(Level.SEVERE,"SRC Type not matches with source for "+srcID);
				
			}
			
		}
			 }
		}
		if(errorSrc.size()>0){
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc);
			uPE.ExcelOP(11, errorSrc.size(), errorSrc);
		} 	
	
		
	
	}	
	
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void Data_12(){
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		

		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
		
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data12.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			ArrayList errorEvent= new ArrayList();
			  int total_Ev=0;
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			
			 
			
				
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
						
						
						 
						
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    	
					    	
					    for(int k=0;k<arrayEv.length();k++){
					    	int total_EV_event=0;
					    	int rv_cntr=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){

						    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
								
						    	for(int j=0;j<otherAttribute.length();j++){
						    		
						    		
						    		
						    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
						    	
						    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
						    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
						    		
						    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
						    		offAirTBA=true;
						    	}
						    							    	
						    	}
								
							}
								
				else{
				
					 total_Ev++;

total_EV_event++;
					 logger.log(Level.SEVERE,"OtherAttribute not found for "+srcID);
				    
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;

total_EV_event++;
								     
									
								}
								
							}
							
							else{
																
								 total_Ev++;

total_EV_event++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
							    
							}
							
						
							
							
                           if(objEv.has("tags")){


                            	tagAR=(JSONArray) objEv.get("tags");
                            	
                            	
                            	for(int z=0;z<tagAR.length();z++){
                            		hierarchyLevel=(JSONObject) tagAR.get(z);
                            		
                            		category=hierarchyLevel.getJSONObject("category");
                            		
                            		categoryId=category.get("categoryId").toString();
                            		
                            		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                            	
                            		
                            		valAR=hierarchyLevel.getJSONObject("value");
                            		 valueNames=(JSONObject) valAR.get("valueNames");
                            			String en=valueNames.get("en").toString();	
                            			
                            			
                            		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Adult")){
                            			enChk=true;
                            			total_Source++;
                            			break;
                            			
                            		}
                            	}
								
								
							
                            
                           }                             
                           
                           else{
                        	   total_EV_event++;
									total_Ev++;
									 logger.log(Level.SEVERE,"Tags not found for "+srcID);
									 
								}
                           
                           
                           
                           if(!offAirTBA && !enChk){
                         		if(objEv.has("searchableTextItems")){
     								
                         			delivery=(JSONObject) obj.get("delivery");
             						lanG=delivery.getJSONArray("languages");
             						
             						for(int ar=0;ar<lanG.length();ar++){
             							arStore.add(lanG.getString(ar)+":");
             							
             							}
                         			
                         			searchableTitles=(JSONArray) objEv.get("searchableTextItems");
     									for(int o=0;o<searchableTitles.length();o++){
     										hierarchyLevel_Serach = (JSONObject) searchableTitles.get(o);
     										
     										
     										hierarchyLevel_Type= hierarchyLevel_Serach.get("type").toString();
     										        										
     										JSONObject lamgStore=null;
     										
     									
     											lamgStore=(JSONObject) hierarchyLevel_Serach.get("value");
     			    							String lStore=lamgStore.toString().replaceAll("\"", "");
     											for(String lan:arStore){
     												if(lStore.contains(lan) &&  hierarchyLevel_Type.equalsIgnoreCase("shortSynopsis")){
     													total_Source++;
     													 logger.log(Level.INFO,"Success records for DATA-012 Languages "+total_Source);
     													
     												}
     												else{
     													total_Ev++;

total_EV_event++;
     													 logger.log(Level.SEVERE,"Language not found for "+srcID);
    			        								
     												}
     												
     											}
     											
     										
     									
     										
     									}
     								
     								
     						
     								
     							}
     							
     							else{

total_EV_event++;
     								total_Ev++;
     								 logger.log(Level.SEVERE,"searchableTextItems not found for "+srcID);
     								
     							}
								
								                             							
								
								}
                           
                           if(total_EV_event>0){
   					    	rv_cntr++;
                           	
                           	errorEvent.add(rv_cntr);
                           }
					    }
					    
					   
					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-12");
						    ip++;

					    }
					    
					    
						    

					    

					    if(total_Ev>0){
					                         	  errorSrc.add(srcID);
					                           }
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
			
		
		}


if(errorSrc.size()>0){
	uPE.ExcelOP(12, errorSrc.size(), errorSrc);		
	logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
	
		
	
	}	
	
		catch(Exception e){
			e.printStackTrace();
		}
	
		
	}

	public void Data_13(){
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		


		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
		
		
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data13.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  

	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			 int total_Ev=0;
			 ArrayList errorEvent= new ArrayList();
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			
			 
			
				
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
						
						
						 
						
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	int rv_cntr=0;
					    	int total_EV_event=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){

						    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
								
						    	for(int j=0;j<otherAttribute.length();j++){
						    		
						    		
						    		
						    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
						    	
						    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
						    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
						    		
						    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
						    		offAirTBA=true;
						    	}
						    							    	
						    	}
								
							}
								
				else{
				
					 total_Ev++;
					 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
				     
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
								  
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 total_EV_event++;

								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
							}
							
				
                           
                           
                           if(!offAirTBA){
                         	
                         			
                         			
        							
        							
                                    if(objEv.has("tags")){


                                     	tagAR=(JSONArray) objEv.get("tags");
                                     	
                                     	
                                     	for(int z=0;z<tagAR.length();z++){
                                     		hierarchyLevel=(JSONObject) tagAR.get(z);
                                     		
                                     		category=hierarchyLevel.getJSONObject("category");
                                     		
                                     		if(category.has("categoryId")){
                                     		
                                     		total_Source++;
                                     		logger.log(Level.INFO,"Success records for DATA-13 "+total_Source);
                                     		}
                                     		 else{
              									total_Ev++;
              									total_EV_event++;

              									logger.log(Level.SEVERE,"categoryId not found for "+srcID);
              									
              								}
                                     	}
         								
         								
                                 
                                    
                         		}
     							
     							else{
     							    
     								total_Ev++;
     								logger.log(Level.SEVERE,"Tags not found for "+srcID);
     								total_EV_event++;

     								
     							}
								
								                             							
								
								}
                           
                           if(total_EV_event>0){
                        	   rv_cntr++;
                           	
                           	errorEvent.add(rv_cntr);
                           }
					    }
					    



					    double applicableEvents= arrayEv.length();
					 					   double non_compl=errorEvent.size();
					 					    double compl_events=applicableEvents-non_compl;
					 					    
					 					    double perc =(compl_events/applicableEvents)*100;
					 					
					 					    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-13");
					 					    ip++;
					 					    
					    
					    }
					    if(total_Ev>0){
	                      	  errorSrc.add(srcID);
	                        }
						
				 }
								
				
		}
			else{
				total_Ev++;
				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				 
			}
			
		
		}
		if(errorSrc.size()>0){
			uPE.ExcelOP(13, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 

	
	}	
	
		catch(Exception e){
			e.printStackTrace();
		}
	
		
	
		
	}

	public void Data_14() {
		

utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		


		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
	    ArrayList errorSrc= new ArrayList();
	    
	    ArrayList errorEvent =new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data14.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  

	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			
			int total_Ev=0;
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			 
			 if(dlVry.has("quality")){
			 String QL=dlVry.get("quality").toString();
									 
			if(QL.equalsIgnoreCase("premium")){
				
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
						
						
						 
						
						URL urlEv =new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							int total_EV_event=0;
							int rv_cntr=0;
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
								    
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 total_EV_event++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
							}
							
				
                                    if(objEv.has("tags")){




                                    	tagAR=(JSONArray) objEv.get("tags");
                                    	
                                    	
                                    	for(int z=0;z<tagAR.length();z++){
                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
                                    		
                                    		category=hierarchyLevel.getJSONObject("category");
                                    		
                                    		categoryId=category.get("categoryId").toString();
                                    		
                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                    	
                                    		
                                    		valAR=hierarchyLevel.getJSONObject("value");
                                    		 valueNames=(JSONObject) valAR.get("valueNames");
                                    			String en=valueNames.get("en").toString();	
                                    			
                                    			
                                    		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&en.contains("Drama")){
                                    			enChk=true;
                                    			total_Source++;
                                    			break;
                                    			
                                    		}
                                    		
                                    		
                                     		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&en.contains("Film")){
                                     			enChk=true;
                                     			total_Source++;
                                     			break;
                                     			
                                     		}
                                     		
                                     		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&en.contains("Movie/Drama")){
                                     			enChk=true;
                                     			total_Source++;
                                     			break;
                                     			
                                     		}
                                    	}
        							
                                  
                                    
                         		}
     							
     							else{
     							    
     								total_Ev++;
     								 logger.log(Level.SEVERE,"tags not found for "+srcID);
     								total_EV_event++;
     								
     							}
								
								if(enChk){                             							
									 total_Source++;
   									 logger.log(Level.INFO,"Success records for DATA-14 "+total_Source);
								}
								

								   if(total_EV_event>0){
									   rv_cntr++;
	                                	
	                                	errorEvent.add(rv_cntr);
								                                }
					    }
					    
					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-14");
						    ip++;
						    
					    
					    }
					    if(total_Ev>0){
					                         	  errorSrc.add(srcID);
					                           }
						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				
				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
			
		
		}
			 }
		}
	

if(errorSrc.size()>0){
	uPE.ExcelOP(14, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
		
	}	
	
		catch(Exception e){
			e.printStackTrace();
		}
	
		
	
		
	
	}

	public void Data_15() {
		
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
	    ArrayList errorSrc= new ArrayList();
	    
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data15.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			ArrayList errorEvent= new ArrayList();
			int total_Ev=0;
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			 if(dlVry.has("quality")){
			 String QL=dlVry.get("quality").toString();
									 
			if(QL.equalsIgnoreCase("premium")){
				
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	
					    	int total_EV_event=0;
					    	int rv_cntr=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(!objEv.has("otherAttributes")){				
								logger.log(Level.SEVERE,"Other Attributes not VOD or LINEAR found for "+srcID);
								total_EV_event++;
								total_Ev++;
				     
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
									 logger.log(Level.SEVERE,"Event type not VOD or LINEAR found for "+srcID);
								   
								}
								
							}
							
							else{
																
								 total_Ev++;
								 total_EV_event++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
							    
							}
							
				
                                    if(objEv.has("tags")){




                                    	tagAR=(JSONArray) objEv.get("tags");
                                    	
                                    	
                                    	for(int z=0;z<tagAR.length();z++){
                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
                                    		
                                    		category=hierarchyLevel.getJSONObject("category");
                                    		
                                    		categoryId=category.get("categoryId").toString();
                                    		
                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                    	
                                    		
                                    		valAR=hierarchyLevel.getJSONObject("value");
                                    		 valueNames=(JSONObject) valAR.get("valueNames");
                                    		 
                                    		 if(valueNames.has("en")){
                                    			String en=valueNames.get("en").toString();
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&en.contains("News"))
                                        		{
                                        			enChk=true;
                                        			total_Source++;
                                        			break;
                                        			
                                        		}
                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&en.contains("shop"))
                                        		{
                                        			enChk=true;
                                        			total_Source++;
                                        			break;
                                        			
                                        		}
                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&en.contains("religion"))
                                        		{
                                        			enChk=true;
                                        			total_Source++;
                                        			break;
                                        			
                                        		}
                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&en.contains("adult"))
                                        		{
                                        			enChk=true;
                                        			total_Source++;
                                        			break;
                                        			
                                        		}
                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&en.contains("sport"))
                                        		{
                                        			enChk=true;
                                        			total_Source++;
                                        			break;
                                        			
                                        		}
                                    			
                                    		 }
                                    			if(valueNames.has("in")){
                                    			String in=valueNames.get("in").toString();
                                    			
                                    			 if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&in.contains("News")){
                                    				 enChk=true;
                                         			total_Source++;
                                         			break;
                                    			 }
                                    			 
                                    			 if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&in.contains("shop")){
                                    				 enChk=true;
                                         			total_Source++;
                                         			break;
                                    			 }
                                    			 
                                    			 if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&in.contains("religion")){
                                    				 enChk=true;
                                         			total_Source++;
                                         			break;
                                    			 }
                                    			 
                                    			 if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&in.contains("adult")){
                                    				 enChk=true;
                                         			total_Source++;
                                         			break;
                                    			 }
                                    			 
                                    			 if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&in.contains("sport")){
                                    				 enChk=true;
                                         			total_Source++;
                                         			break;
                                    			 }
                                    			 
                                    			 
                                    			
                                    			}
                                    		
                                    	}
        							
                          
                                    
                         		}
     							
     							else{
     								total_Ev++;
     								total_EV_event++;
     								 logger.log(Level.SEVERE,"TAGS not found for "+srcID);
     								
     							}
								
								if(!enChk){       
									if(categoryId.equalsIgnoreCase("contentTag.ericsson")){
										total_Source++;
	   									 logger.log(Level.INFO,"Success records for DATA-15 "+total_Source);
									}
									 else{
		     							    
		     								total_Ev++;
		     								total_EV_event++;
		     								 logger.log(Level.SEVERE,"contentTag.ericsson not found for "+srcID);
		     								
		     							}
									 
								}
                           
								
								if(total_EV_event>0){
                                        rv_cntr++;
                                	
                                	errorEvent.add(rv_cntr);
                                }



					    }
					    

					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-15");
						    ip++;
						    
					    
					    }
					    if(total_Ev>0){
	                      	  errorSrc.add(srcID);
	                        }
						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
			
		
		}
			 }

		}

		if(errorSrc.size()>0){
			uPE.ExcelOP(15, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
		
	
		
	
	}	
	
		catch(Exception e){
			 e.printStackTrace();
		}
	
		
	
		
	
	
		
	}

	public void Data_16() {
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		

		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
		

	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data16.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    
	    int ip=1;
		for(int i=0;i<array.length();i++){
			
			
			int total_Ev=0;
			ArrayList errorEvent= new ArrayList();
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			
									 
			
				
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv =new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    	
					    for(int k=0;k<arrayEv.length();k++){
					    
					    	int total_EV_event=0;
					    	int rv_cntr=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							srID=(JSONObject) objEv.get("internalIds");
							if(objEv.has("otherAttributes")){	
								


						    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
								
						    	for(int j=0;j<otherAttribute.length();j++){
						    		
						    		
						    		
						    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
						    	
						    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
						    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
						    		
						    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
						    		offAirTBA=true;
						    	}
						    							    	
						    	}
								
							
							}
				
				
			else{
					 total_Ev++;
					 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
					 total_EV_event++;
				    
				     
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
								 total_EV_event++;
							    
							}
							
				
                                    if(objEv.has("tags")){
                                    	tagAR=(JSONArray) objEv.get("tags");
                                    	
                                    	
                                    	for(int z=0;z<tagAR.length();z++){
                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
                                    		
                                    		category=hierarchyLevel.getJSONObject("category");
                                    		
                                    		categoryId=category.get("categoryId").toString();
                                    		
                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                    	
                                    		
                                    		valAR=hierarchyLevel.getJSONObject("value");
                                    		 valueNames=(JSONObject) valAR.get("valueNames");
                                    		 String en=valueNames.get("en").toString();	
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Drama Series")){
                                        			enChk=true;
                                        			total_Source++;
                                        			break;
                                        			
                                        		}
                                        		
                                        		
                                         		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Film")){
                                         			enChk=true;
                                         			total_Source++;
                                         			break;
                                         			
                                         		}
                                         		
                                         		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.contains("Movie/Drama")){
                                         			enChk=true;
                                         			total_Source++;
                                         			break;
                                         			
                                         		}
                                    		
                                    	}
        							
                                
                         		}
     							
     							else{
     							    
     								total_Ev++;
     								logger.log(Level.SEVERE,"TAGS not found for "+srcID);
     								total_EV_event++;
     								
     							}
								
								if(!offAirTBA && enChk){

									//programAwards needs to be done

									if(hRti.equalsIgnoreCase("programAwards")){
										total_Source++;
	   									 logger.log(Level.INFO,"Success records for DATA-16 "+total_Source);
									}
									 else{
		     							    
		     								total_Ev++;
		     								total_EV_event++;
		     								logger.log(Level.SEVERE,"ProgramAwards not found for "+srcID);
		     								
		     							}
									 
								}
                         
								if(total_EV_event>0){
									rv_cntr++;
                                	//String eventId=srID.get("eventId").toString();
                                	errorEvent.add(rv_cntr);
                                }

	
					    }
					    double applicableEvents= arrayEv.length();
					   double non_compl=errorEvent.size();
					    double compl_events=applicableEvents-non_compl;
					    
					    double perc =(compl_events/applicableEvents)*100;
					
					    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-16");
					    ip++;
					    
					    
					    }
					    if(total_Ev>0){
					                         	  errorSrc.add(srcID);
					                           }

						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
			
		
		

		}
		if(errorSrc.size()>0){
			uPE.ExcelOP(16, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
	
	}	
	
		catch(Exception e){
			
			 e.printStackTrace();
		}
	
		
	
		
	
	
		
	
	}

	public void Data_17() {
		

utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		

		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data17.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			
			int total_Ev=0;
			ArrayList errorEvent= new ArrayList();
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			
									 
			
				
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	
					    	int total_EV_event=0;
					    	
					    	int rv_cntr=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){	
								


						    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
								
						    	for(int j=0;j<otherAttribute.length();j++){
						    		
						    		
						    		
						    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
						    	
						    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
						    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
						    		
						    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
						    		offAirTBA=true;
						    	}
						    							    	
						    	}
								
							
							}
				
				
			else{
					 total_Ev++;
					 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
				   
					 total_EV_event++;
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
								    
									 total_EV_event++;
								}
								
							}
							
							else{
																
								 total_Ev++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
								 total_EV_event++;
							}
							
				
                                    
								
								if(!offAirTBA){
									 if(objEv.has("publishedDuration")){
										total_Source++;
	   									 logger.log(Level.INFO,"Success records for DATA-17 "+total_Source);
									}
									 else{
		     							    
		     								total_Ev++;
		     								logger.log(Level.SEVERE,"publishedDuration not found for "+srcID);
		     								 total_EV_event++;
		     							}
									 
								}
								
								if(total_EV_event>0){
									rv_cntr++;
                                	
                                	errorEvent.add(rv_cntr);
                                }






					    }
					    
					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-17");
						    ip++;
					    }
					    if(total_Ev>0){
	                      	  errorSrc.add(srcID);
	                        }

						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
			
		
		

		}


if(errorSrc.size()>0){
	uPE.ExcelOP(17, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
	
	}	
	
		catch(Exception e){
			
			 e.printStackTrace();
		}
	
		
	
		
	
	
		
	
	
	}

	public void Data_18() {

		

utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		

		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Linear());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data18.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			
			int total_Ev=0;
			ArrayList errorEvent= new ArrayList();

			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			
									 
			
				
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	
					    	int total_EV_event=0;
					    	int rv_cntr=0;

							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){	
								


						    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
								
						    	for(int j=0;j<otherAttribute.length();j++){
						    		
						    		
						    		
						    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
						    	
						    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
						    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
						    		
						    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
						    		offAirTBA=true;
						    	}
						    							    	
						    	}
								
							
							}
				
				
			else{
					 total_Ev++;
					 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
					 total_EV_event++;
				     
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
									
								}
								
							}
							
							else{
								logger.log(Level.SEVERE,"eventType not found for "+srcID);						
								 total_Ev++;
								 total_EV_event++;
							}
							
				
                                  
								
								if(!offAirTBA){
									 if(objEv.has("publishedStartDateTime")){
										total_Source++;
	   									 logger.log(Level.INFO,"Success records for DATA-018 "+total_Source);
									}
									 else{
		     							    
		     								total_Ev++;
		     								logger.log(Level.SEVERE,"publishedStartDateTime not found for "+srcID);
		     								 total_EV_event++;
									 }
									 
									 if(objEv.has("publishedEndDateTime")){
											total_Source++;
		   									 logger.log(Level.INFO,"Success records for DATA-020 "+total_Source);
										}
										 else{
			     							    
			     								total_Ev++;
			     								logger.log(Level.SEVERE,"publishedEndDateTime not found for "+srcID);
			     								 total_EV_event++;
			     							}
									 
								}

								if(total_EV_event>0){
									rv_cntr++;
                                	
                                	errorEvent.add(rv_cntr);
                                }
								
					    }
					    
					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-18");
						    ip++;
						    
					    }
					    if(total_Ev>0){
	                      	  errorSrc.add(srcID);
	                        }
						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
			
		
		

		}
	

if(errorSrc.size()>0){
	uPE.ExcelOP(18, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
	}	
	
		catch(Exception e){
			e.printStackTrace();
			
		}
	
		
	
		
	
	
		
	
	
	
		
	}

	public void Data_24() {
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		try{

		 boolean imgFlag = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data24.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    
	    int ip=1;
		for(int i=0;i<array.length();i++){
			
			 int total_Ev=0;
			 ArrayList errorEvent= new ArrayList();
			 
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			
									 
			
				
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear) || srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	
					    	int total_EV_event=0;
					    	int rv_cntr=0;

							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){
								
							}
				
				
			else{
					 total_Ev++;
					 total_EV_event++;
					 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
				  
				     
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 total_EV_event++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
							     
							}
							
				
                                    if(objEv.has("tags")){
                                    	tagAR=(JSONArray) objEv.get("tags");
                                    	
                                    	
                                    	for(int z=0;z<tagAR.length();z++){
                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
                                    		
                                    		category=hierarchyLevel.getJSONObject("category");
                                    		
                                    		categoryId=category.get("categoryId").toString();
                                    		
                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                    	
                                    		
                                    		valAR=hierarchyLevel.getJSONObject("value");
                                    		 valueNames=(JSONObject) valAR.get("valueNames");
                                    			String en=valueNames.get("en").toString();	
                                    			
                                    			
                                    		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Film")){
                                    			enChk=true;
                                    			
                                    			
                                    		}
                                    		
                                    		
                                    		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Drama series")){
                                    			enChk=true;
                                    			
                                    		}
                                    	}
                                    }
     							
     							else{
     								total_Ev++;
     								 total_EV_event++;
     								logger.log(Level.SEVERE,"|TAGS not found for "+srcID);
     							}
								
                                    if(objEv.has("images")){
                                    	Images=(JSONArray) objEv.get("images");
                                    	for(int im=0;im<Images.length();im++){
                                    		
                                    		orientation= Images.getJSONObject(im).get("orientation").toString();
                                    		 shape=Images.getJSONObject(im).get("shape").toString();
                                    		 if(orientation.equalsIgnoreCase(Utilsobject.landscape) || orientation.equalsIgnoreCase(Utilsobject.portrait)){
                                    		
                                    	if(shape.toString().equalsIgnoreCase(Utilsobject.L4) || shape.toString().equalsIgnoreCase(Utilsobject.P4))	{ 
                                    			imgFlag=true;
                                    			break;
                                    		}
                                    		 }
                                    		 
                                    		
                                    	}
                                    	
                                    }
                                    else{
	     							    
	     								total_Ev++;
	     								
	     								logger.log(Level.SEVERE,"No Records found with images not found for "+srcID);
	     								 total_EV_event++;
	     								  	     								
	     							}
 
								if(enChk && imgFlag){
									
  						
								}
								  else{
	     							    
	     								total_Ev++;
	     								 total_EV_event++;
	     								logger.log(Level.SEVERE,"No Records found with IMG flag and Film/Drama not found for "+srcID);
								  }
								if(total_EV_event>0){
									rv_cntr++;
                                	
                                	errorEvent.add(rv_cntr);
                                }

					    }
					    
					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-24");
						    ip++;
				
					    }

					    if(total_Ev>0){
					                         	  errorSrc.add(srcID);
					                           }
						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				
				logger.log(Level.SEVERE,"SourceType not found for "+srcID);
			}
			
		
		

		}

if(errorSrc.size()>0){
	uPE.ExcelOP(19, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
	}	
	
		catch(Exception e){
		
			e.printStackTrace();
		}
	
	
	}

	public void Imgreq1(){

utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		try{

		 boolean imgFlag = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data24_IM1.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			
			int total_Ev=0;
			ArrayList errorEvent= new ArrayList();
			 
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			
									 
			
				
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
					    for(int k=0;k<arrayEv.length();k++){
					    	
					    	int total_EV_event=0;
					    	int rv_cntr=0;


							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){
								
							}
				
				
			else{
					 total_Ev++;
					 total_EV_event++;
					 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
				   
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
								 total_EV_event++;
							    
							}
							
				
								
                                    if(objEv.has("images")){
                                    	Images=(JSONArray) objEv.get("images");
                                    	for(int im=0;im<Images.length();im++){
                                    		
                                    		orientation=Images.getJSONObject(im).get("orientation").toString();
                                    		 shape= Images.getJSONObject(im).get("shape").toString();
                                    		 if(orientation.equalsIgnoreCase(Utilsobject.landscape) || orientation.equalsIgnoreCase(Utilsobject.portrait)){
                                    		
                                    	if(shape.toString().equalsIgnoreCase(Utilsobject.L4) || shape.toString().equalsIgnoreCase(Utilsobject.P4))	{ 
                                    			imgFlag=true;
                                    			break;
                                    		}
                                    		 }
                                    		 
                                    		
                                    	}
                                    	
                                    }
                                    else{
	     							    
	     								total_Ev++;
	     								logger.log(Level.SEVERE,"IMAGES not found for "+srcID);
	     								 total_EV_event++;
	     							}
 
                                   
								if(imgFlag){
									
									
									 if(objEv.has("internalIds")){
	        								
	        								srID=(JSONObject) objEv.get("internalIds");
	        								
	        								if(srID.has("seriesId")){
	        									srID_q=srID.get("seriesId").toString();
	        														
	        								}
	        								
	        								else{
	        									
	        									total_Ev++;
	        									logger.log(Level.SEVERE,"seriesId not found for "+srcID);
	        									 total_EV_event++;
	        								}
	        								
	        								if(srID.has("seasonId")){
	        									srID_p=srID.get("seasonId").toString();
	        									System.out.println("SEASON ID "+srID_p);
	        									if(srID_p.equalsIgnoreCase("") && srID_q.equalsIgnoreCase("")){
	        										total_Ev++;
	        										logger.log(Level.SEVERE,"seasonId not found for "+srcID);
	        										 total_EV_event++;
	        									}
	        								
	        								}
	        								
	        								
	        							}
	        							
	        							else{
	        							    
	        								total_Ev++;
	        								logger.log(Level.SEVERE,"internalIds not found for "+srcID);
	        								 total_EV_event++;
											
	        							}
									 
								
									
								}
								if(total_EV_event>0){
									rv_cntr++;
                                	
                                	errorEvent.add(rv_cntr);
                                }
					    }
					    
					    double applicableEvents= arrayEv.length();
						   double non_compl=errorEvent.size();
						    double compl_events=applicableEvents-non_compl;
						    
						    double perc =(compl_events/applicableEvents)*100;
						
						    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Dataimgreq1");
						    ip++;
					    }
					    if(total_Ev>0){
	                      	  errorSrc.add(srcID);
	                        }

						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				logger.log(Level.SEVERE,"SourceType not found for "+srcID);
			}
			
		
		

		}


if(errorSrc.size()>0){
	uPE.ExcelOP(20, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
	
	}	
	
		catch(Exception e){
			e.printStackTrace();
		}
	
	
	
		
	}


	public void Imgreq2() {

utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		

		try{

		 boolean imgFlag = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL("http://telia.uat.ecdapi.net//stores-active/source/filter?numberOfResults=1000&annotation={%22unconditionalAttributes%22:%20[%22source%22,%22sourceId%22,%22sourceType%22,%22broadcaster%22,%22delivery%22]}&{%22term%22:%22internalIds.sourceType%22,%22value%22:%22vod%22}");
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data24_IM2.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    
	    int ip=1;
		for(int i=0;i<array.length();i++){
			
			int total_Ev=0;
			ArrayList errorEvent= new ArrayList();
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			
									 
			
				
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
	for(int k=0;k<arrayEv.length();k++){
		
		int total_EV_event=0;
    	int rv_cntr=0;

    	
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(objEv.has("otherAttributes")){
								
							}
				
				
			else{
					 total_Ev++;
					 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
					 total_EV_event++;
				     
				     
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 total_EV_event++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
							    
							}
							
				
                                   
                                    if(objEv.has("images")){
                                    	Images=(JSONArray) objEv.get("images");
                                    	for(int im=0;im<Images.length();im++){
                                    		
                                    		orientation=Images.getJSONObject(im).get("orientation").toString();
                                    		 shape= Images.getJSONObject(im).get("shape").toString();
                                    		 if(orientation.equalsIgnoreCase(Utilsobject.landscape) || orientation.equalsIgnoreCase(Utilsobject.portrait)){
                                    		
                                    	if(shape.toString().equalsIgnoreCase(Utilsobject.L4) || shape.toString().equalsIgnoreCase(Utilsobject.P4))	{ 
                                    			imgFlag=true;
                                    			break;
                                    		}
                                    		 }
                                    		 
                                    		
                                    	}
                                    	
                                    }
                                    else{
                                    	logger.log(Level.SEVERE,"Images not found for "+srcID);
	     								total_Ev++;
	     								 total_EV_event++;
	     								
	     							}
 
                                   
								if(imgFlag){
									
									
									 if(objEv.has("internalIds") && objEv.has("yearOfProduction")){
	        								
	        								srID=(JSONObject) objEv.get("internalIds");
	        								yearOfProduction=objEv.getInt("yearOfProduction");
	        								
	        								if(srID.has("seriesId")){
	        									srID_q=srID.get("seriesId").toString();
	        														
	        								}
	        								
	        								else{
	        									
	        									total_Ev++;
	        									logger.log(Level.SEVERE,"seriesId not found for "+srcID);
	        									 total_EV_event++;
	   		     								
	        								}
	        								
	        								if(srID.has("seasonId")){
	        									srID_p=srID.get("seasonId").toString();
	        									System.out.println("SEASON ID "+srID_p);
	        									
	        									if(yearOfProduction>2000){
	        									if(srID_p.equalsIgnoreCase("") && srID_q.equalsIgnoreCase("")){
	        										total_Ev++;
	        										logger.log(Level.SEVERE,"seriesId and SEASON ID not found for "+srcID);
	        										 total_EV_event++;
	        		     								
	        									}
	        									}
	        								}
	        								
	        								
	        							}
	        				
								}
								if(total_EV_event>0){
									rv_cntr++;
                                	
                                	errorEvent.add(rv_cntr);
                                }
					    }
	
	 double applicableEvents= arrayEv.length();
	   double non_compl=errorEvent.size();
	    double compl_events=applicableEvents-non_compl;
	    
	    double perc =(compl_events/applicableEvents)*100;
	
	    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Dataimgreq2");
	    ip++;
					    }
	 if(total_Ev>0){
     	  errorSrc.add(srcID);
       }
						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
			
		
		

		}
		if(errorSrc.size()>0){
			uPE.ExcelOP(21, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
		
	
	
		
	
		
	
	}	
	
		catch(Exception e){
			e.printStackTrace();
			}
	
	
	
		
	
		
	}


	public void Data_28() {
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
	    ArrayList errorSrc= new ArrayList();
	    ArrayList errorSrc_29= new ArrayList();
	    
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data28.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			
			 int total_Ev=0;
			 int total_Ev_29=0;
			 
			 ArrayList errorEvent= new ArrayList();
			 
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			 
			 if(dlVry.has("quality")){
			 String QL=dlVry.get("quality").toString();
									 
			if(QL.equalsIgnoreCase("premium")){
				
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
		 for(int k=0;k<arrayEv.length();k++){
			 
			 int total_EV_event=0;
		    	int rv_cntr=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(!objEv.has("otherAttributes")){				
					 total_Ev++;
					 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
				     
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
									
								   
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
								 total_EV_event++;
								
							    
							}
							
				
                                  
                                    
                                    
                     if(objEv.has("otherAttributes")){
                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
                						
                				    	for(int j=0;j<otherAttribute.length();j++){
                				    		
                				    		
                				    		
                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
                				    	
                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
                				    		
                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
                				    		offAirTBA=true;
                				    	}
                				    							    	
                				    	}
                						}
                						
                		else{
                		
                			 total_Ev++;
                			 total_EV_event++;
                				logger.log(Level.SEVERE,"otherAttributes for DATA-29 not found for "+srcID);
                				 total_EV_event++;
                		     
                		}							
                                  	
                                  	
                     if(!offAirTBA){
							
						 
                    
                           if(objEv.has("searchableTitles")){
           								
                               			delivery=(JSONObject) obj.get("delivery");
                   						lanG=delivery.getJSONArray("languages");
                   						
                   						for(int ar=0;ar<lanG.length();ar++){
                   							arStore.add(lanG.getString(ar)+":");
                   							
                   							}
                               			
                               			searchableTitles=(JSONArray) objEv.get("searchableTitles");
           									for(int o=0;o<searchableTitles.length();o++){
           										hierarchyLevel_Serach = (JSONObject) searchableTitles.get(o);
           										
           										hierarchyLevel_Value= hierarchyLevel_Serach.get("hierarchyLevel").toString();
           										
           										        										
           										JSONObject lamgStore=null;
           										
           										
           										if(hierarchyLevel_Value.equalsIgnoreCase("series")){
           											
           											lamgStore=(JSONObject) hierarchyLevel_Serach.get("value");
           			    							String lStore=lamgStore.toString().replaceAll("\"", "");;
           											for(String lan:arStore){
           												if(lStore.contains(lan)){
           													total_Source++;
           													 logger.log(Level.INFO,"Success records for DATA-28_Series Languages "+total_Source);
           													
           												}
           												else{
           													total_Ev++;
           													logger.log(Level.SEVERE,"Languages for DATA-28 not found for "+srcID);
           													total_EV_event++;
           												 total_EV_event++;
          			        								
           												}
           												
           											}
           											
           										}
           									
           										
           										

           										if(hierarchyLevel_Value.equalsIgnoreCase("season")){
           											
           											lamgStore=(JSONObject) hierarchyLevel_Serach.get("value");
           			    							String lStore=lamgStore.toString().replaceAll("\"", "");;
           											for(String lan:arStore){
           												if(lStore.contains(lan)){
           													total_Source++;
           													 logger.log(Level.INFO,"Success records for DATA-29_Season Languages "+total_Source);
           													total_EV_event++;
           												 total_EV_event++;
           													
           												}
           												else{
           													
           													total_Ev_29++;
           													logger.log(Level.SEVERE,"Languages for DATA-29 not found for "+srcID);
           													total_EV_event++;
           												 total_EV_event++;
          			        								
           												}
           												
           											}
           											
           										}
           									
           										
           									}
           								
           								
           						
           								
           							}
                     
		 }
								
                     if(total_EV_event>0){
							rv_cntr++;
                     	
                     	errorEvent.add(rv_cntr);
                     }




								
					    }
		 
		 double applicableEvents= arrayEv.length();
		   double non_compl=errorEvent.size();
		    double compl_events=applicableEvents-non_compl;
		    
		    double perc =(compl_events/applicableEvents)*100;
		
		    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-28");
		    ip++;
		    
					    }
		 if(total_Ev>0){
         	  errorSrc.add(srcID);
         	  
           }
		 if(total_Ev_29>0){
			 errorSrc_29.add(srcID);
        	  
          }
						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
			
		
		}
			 }
		}
	
		if(errorSrc.size()>0){
			uPE.ExcelOP(22, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
			
		} 
		if(errorSrc_29.size()>0){
			uPE.ExcelOP(23, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc_29.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc_29); 
			
		} 
	
	}	
	
		catch(Exception e){
			 e.printStackTrace();
		}
	
		
	
		
	
	
		
	
		
	}


	public void Data_30() {

utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data30.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    
	    int ip=1;
		for(int i=0;i<array.length();i++){
			ArrayList errorEvent= new ArrayList();
			 int total_Ev=0;
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
		
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
		 for(int k=0;k<arrayEv.length();k++){
			 
			 int total_EV_event=0;
		    	int rv_cntr=0;
		    	
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
							if(!objEv.has("otherAttributes")){				
					 total_Ev++;
					 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
				     
				}
							
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 total_EV_event++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
							  
							}
							
				
                                    
                                    
                     if(objEv.has("otherAttributes")){
                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
                						
                				    	for(int j=0;j<otherAttribute.length();j++){
                				    		
                				    		
                				    		
                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
                				    	
                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
                				    		
                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
                				    		offAirTBA=true;
                				    	}
                				    							    	
                				    	}
                						}
                						
                		else{
                		
                			 total_Ev++;
                			 total_EV_event++;
                			 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
                		     
                		}							
                                  	
                                  	
                     if(!offAirTBA){
                    	 
                    	 if(objEv.has("searchableTextItems")){
								
                  			delivery=(JSONObject) obj.get("delivery");
      						lanG=delivery.getJSONArray("languages");
      						
      						for(int ar=0;ar<lanG.length();ar++){
      							arStore.add(lanG.getString(ar)+":");
      							
      							}
                  			
                  			searchableTitles=(JSONArray) objEv.get("searchableTextItems");
									for(int o=0;o<searchableTitles.length();o++){
										hierarchyLevel_Serach = (JSONObject) searchableTitles.get(o);
										
										
										hierarchyLevel_Type= hierarchyLevel_Serach.get("type").toString();
										        										
										JSONObject lamgStore=null;
										
									
											lamgStore=(JSONObject) hierarchyLevel_Serach.get("value");
			    							String lStore=lamgStore.toString().replaceAll("\"", "");
											for(String lan:arStore){
												if(lStore.contains(lan) &&  hierarchyLevel_Type.equalsIgnoreCase("mediumSynopsis")){
													total_Source++;
													 logger.log(Level.INFO,"Success records for DATA-011 Languages "+total_Source);
													
												}
												else{
													total_Ev++;
													 total_EV_event++;
													logger.log(Level.SEVERE,"Languages not found for "+srcID);
			        								
												}
												
											}
											
										
									
										
									}
								
								
						
								
							}
							
							else{
							    
								total_Ev++;
								 total_EV_event++;
								logger.log(Level.SEVERE,"searchableTextItems not found for "+srcID);
								
							}
							
                     }
								
                     if(total_EV_event>0){
							rv_cntr++;
                     	
                     	errorEvent.add(rv_cntr);
                     }
								
					    }
		 
		 double applicableEvents= arrayEv.length();
		   double non_compl=errorEvent.size();
		    double compl_events=applicableEvents-non_compl;
		    
		    double perc =(compl_events/applicableEvents)*100;
		
		    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-30");
		    ip++;
					    }

		 if(total_Ev>0){
		                      	  errorSrc.add(srcID);
		                        }
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
			
				
		}
		if(errorSrc.size()>0){
			uPE.ExcelOP(24, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
		
	
	
		
	
		
	
	}	
	
		catch(Exception e){
			 e.printStackTrace();
		}
	
	}


	public void Data_31() {


utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
		ArrayList errorSrc_33= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data31.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			 int total_Ev=0;
			 int total_Ev_33=0;
			 ArrayList errorEvent= new ArrayList();
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			 if(dlVry.has("quality")){
			 String QL=dlVry.get("quality").toString();
			 
				if(QL.equalsIgnoreCase("premium")){
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
		 for(int k=0;k<arrayEv.length();k++){
			 
			 int total_EV_event=0;
		    	int rv_cntr=0;

							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
						
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
								     
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 total_EV_event++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
							     
							}
							
				
                                    if(objEv.has("tags")){
                                    	tagAR=(JSONArray) objEv.get("tags");
                                    	
                                    	
                                    	for(int z=0;z<tagAR.length();z++){
                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
                                    		
                                    		category=hierarchyLevel.getJSONObject("category");
                                    		
                                    		categoryId=category.get("categoryId").toString();
                                    		
                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                    	
                                    		
                                    		valAR=hierarchyLevel.getJSONObject("value");
                                    		 valueNames=(JSONObject) valAR.get("valueNames");
                                    		 
                                    		 if(valueNames.has("en")){
                                    			String en=valueNames.get("en").toString();
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.contains("News"))
                                        		{
                                        			enChk=true;
                                        			total_Source++;
                                        			break;
                                        			
                                        		}
                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.contains("shop"))
                                        		{
                                        			enChk=true;
                                        			total_Source++;
                                        			break;
                                        			
                                        		}
                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.contains("religion"))
                                        		{
                                        			enChk=true;
                                        			total_Source++;
                                        			break;
                                        			
                                        		}
                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.contains("adult"))
                                        		{
                                        			enChk=true;
                                        			total_Source++;
                                        			break;
                                        			
                                        		}
                                    			
                                    		                                   			
                                    		 }
                                    			if(valueNames.has("in")){
                                    			String in=valueNames.get("in").toString();
                                    			
                                    			 if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&in.contains("News")){
                                    				 enChk=true;
                                         			total_Source++;
                                         			break;
                                    			 }
                                    			 
                                    			 if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&in.contains("shop")){
                                    				 enChk=true;
                                         			total_Source++;
                                         			break;
                                    			 }
                                    			 
                                    			 if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&in.contains("religion")){
                                    				 enChk=true;
                                         			total_Source++;
                                         			break;
                                    			 }
                                    			 
                                    			 if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&in.contains("adult")){
                                    				 enChk=true;
                                         			total_Source++;
                                         			break;
                                    			 }
                                    			 
                                    			                                   			 
                                    			 
                                    			
                                    			}
                                    		
                                    	}
        							                         		
                                    }
     							
     							else{
     								total_Ev++;
     								logger.log(Level.SEVERE,"Tags not found for "+srcID);
     								
     								 total_EV_event++;
     							}
                                    
                                    
                     if(objEv.has("otherAttributes")){
                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
                						
                				    	for(int j=0;j<otherAttribute.length();j++){
                				    		
                				    		
                				    		
                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
                				    	
                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
                				    		
                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
                				    		offAirTBA=true;
                				    	}
                				    							    	
                				    	}
                						}
                						
                		else{
                		
                			 total_Ev++;
                			 total_EV_event++;
                			 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
                		     
                		}							
                                  	
                                  	
                     if(!offAirTBA && !enChk){
                    	 
                    	 if(objEv.has("searchableTextItems")){
								
                  			delivery=(JSONObject) obj.get("delivery");
      						lanG=delivery.getJSONArray("languages");
      						
      						for(int ar=0;ar<lanG.length();ar++){
      							arStore.add(lanG.getString(ar)+":");
      							
      							}
                  			
                  			searchableTitles=(JSONArray) objEv.get("searchableTextItems");
									for(int o=0;o<searchableTitles.length();o++){
										hierarchyLevel_Serach = (JSONObject) searchableTitles.get(o);
										
										
										hierarchyLevel_Type= hierarchyLevel_Serach.get("type").toString();
										hierarchyLevel_Value= hierarchyLevel_Serach.get("hierarchyLevel").toString();
										
										JSONObject lamgStore=null;
										
									if(hierarchyLevel_Value.equalsIgnoreCase("series")){
											lamgStore=(JSONObject) hierarchyLevel_Serach.get("value");
			    							String lStore=lamgStore.toString().replaceAll("\"", "");
											for(String lan:arStore){
												if(lStore.contains(lan) &&  hierarchyLevel_Type.equalsIgnoreCase("shortSynopsis")){
													total_Source++;
													
													
												}
												else{
													
													logger.log(Level.SEVERE,"Language not found for "+srcID);
													total_Ev++;
													 total_EV_event++;
			        								
												}
												
											}
											
									}
									
									
									if(hierarchyLevel_Value.equalsIgnoreCase("season")){
										lamgStore=(JSONObject) hierarchyLevel_Serach.get("value");
		    							String lStore=lamgStore.toString().replaceAll("\"", "");
										for(String lan:arStore){
											if(lStore.contains(lan) &&  hierarchyLevel_Type.equalsIgnoreCase("shortSynopsis")){
												total_Source++;
												 
												
											}
											else{
												total_Ev_33++;
												 total_EV_event++;
												logger.log(Level.SEVERE,"Language not found for DATA-33 "+srcID);
		        								
											}
											
										}
										
								}
									
										
									}
								
								
						
								
							}
							
							else{
								logger.log(Level.SEVERE,"Searchable Text items not found for "+srcID);
								total_Ev++;
								 total_EV_event++;
								 
							}
							
                     }
								
                     if(total_EV_event>0){
							rv_cntr++;
                     	
                     	errorEvent.add(rv_cntr);
                     } 
								
					    }
		 
		 double applicableEvents= arrayEv.length();
		   double non_compl=errorEvent.size();
		    double compl_events=applicableEvents-non_compl;
		    
		    double perc =(compl_events/applicableEvents)*100;
		
		    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-31");
		    ip++;
					    }

		 if(total_Ev>0){
         	  errorSrc.add(srcID);
           }
		 

		 if(total_Ev_33>0){
         	  errorSrc_33.add(srcID);
           }
				 }
				
						 }
								
			else{
				total_Ev++;
				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
			}
					 
				
				
		}
			 }
			else{
				total_Ev++;

				logger.log(Level.SEVERE,"SourceType not found for "+srcID);
			}
				}		
			
		if(errorSrc.size()>0){
			uPE.ExcelOP(25, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
		
		if(errorSrc_33.size()>0){
			uPE.ExcelOP(26, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc_33.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc_33); 
		} 
		
	
	}	
	
		catch(Exception e){
			 e.printStackTrace();
		}
	
	
	}


	public void Data_34() {

utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		


		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
		ArrayList errorSrc_35= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data34.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    
	    int ip=1;
		for(int i=0;i<array.length();i++){
			int total_Ev=0;
			int total_Ev_35=0;
			ArrayList errorEvent= new ArrayList();

			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			 if(dlVry.has("quality")){
			 String QL=dlVry.get("quality").toString();
			 
				if(QL.equalsIgnoreCase("premium")){
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
		 for(int k=0;k<arrayEv.length();k++){
			 
			 int total_EV_event=0;
		    	int rv_cntr=0;


							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
						
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
								   
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
								 total_EV_event++;
							    
							}
							
				
                                    
                     if(objEv.has("otherAttributes")){
                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
                						
                				    	for(int j=0;j<otherAttribute.length();j++){
                				    		
                				    		
                				    		
                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
                				    	
                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
                				    		
                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
                				    		offAirTBA=true;
                				    	}
                				    							    	
                				    	}
                						}
                						
                		else{
                		
                			 total_Ev++;
                		    
                			 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
                			 total_EV_event++;
                		     
                		}							
                                  	
                                  	
                     if(!offAirTBA){
                    	 
                    	 if(objEv.has("searchableTitles")){
								
                  		
                  			
                  			searchableTitles=(JSONArray) objEv.get("searchableTitles");
									for(int o=0;o<searchableTitles.length();o++){
										hierarchyLevel_Serach = (JSONObject) searchableTitles.get(o);
													
										hierarchyLevel_Value= hierarchyLevel_Serach.get("hierarchyLevel").toString();
									
									if(hierarchyLevel_Value.equalsIgnoreCase("series")){
										if(!objEv.has("episodeNumber")){
											 total_Ev++;
											 total_EV_event++;
											 logger.log(Level.SEVERE,"episodeNumber not found for "+srcID);
										}
										
										if(!objEv.has("numberOfEpisodes")){
											total_Ev_35++;
											 total_EV_event++;
											 logger.log(Level.SEVERE,"numberOfEpisodes not found for "+srcID);
										}
									}
																		
									}
								
								
						
								
							}
							
							else{
							    
								total_Ev++;
								 total_EV_event++;
								logger.log(Level.SEVERE,"searchableTitles not found for "+srcID);
								
							}
							
                     }
								
                     if(total_EV_event>0){
							rv_cntr++;
                     	
                     	errorEvent.add(rv_cntr);
                     }
					    }
		 
		 double applicableEvents= arrayEv.length();
		   double non_compl=errorEvent.size();
		    double compl_events=applicableEvents-non_compl;
		    
		    double perc =(compl_events/applicableEvents)*100;
		
		    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-34");
ip++;
					    }
		 if(total_Ev>0){
         	  errorSrc.add(srcID);
           }
		 
		 if(total_Ev_35>0){
        	  errorSrc_35.add(srcID);
          }

				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
				}	
				
			 }
				
		}
		if(errorSrc.size()>0){
			uPE.ExcelOP(27, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		}	
		
		if(errorSrc_35.size()>0){
			uPE.ExcelOP(28, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc_35.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc_35); 
		}	
	
	}	
	
		catch(Exception e){
			 e.printStackTrace();
		}
	
	
	
		
	}


	public void Data_36() {


utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		


		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data36.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    
	    int ip=1;
		for(int i=0;i<array.length();i++){
			ArrayList errorEvent= new ArrayList();
			 int total_Ev=0;
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			 if(dlVry.has("quality")){
			 String QL=dlVry.get("quality").toString();
			 
				if(QL.equalsIgnoreCase("premium")){
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
		 for(int k=0;k<arrayEv.length();k++){
			 
			 int total_EV_event=0;
		    	int rv_cntr=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
						
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
								 total_EV_event++;
							   
							}
							
				
                                    if(objEv.has("tags")){
                                    	tagAR=(JSONArray) objEv.get("tags");
                                    	
                                    	
                                    	for(int z=0;z<tagAR.length();z++){
                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
                                    		
                                    		category=hierarchyLevel.getJSONObject("category");
                                    		
                                    		categoryId=category.get("categoryId").toString();
                                    		
                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                    	
                                    		
                                    		valAR=hierarchyLevel.getJSONObject("value");
                                    		 valueNames=(JSONObject) valAR.get("valueNames");
                                    		 
                                    		 if(valueNames.has("en")){
                                    			String en=valueNames.get("en").toString();
                                    			                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&en.contains("sport"))
                                        		{
                                        			enChk=true;
                                        			total_Source++;
                                        			break;
                                        			
                                        		}
                                    			
                                    		 }
                                    			if(valueNames.has("in")){
                                    			String in=valueNames.get("in").toString();
                                    			
                                    		   if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("contentTag.ericsson")&&in.contains("sport")){
                                    				 enChk=true;
                                         			total_Source++;
                                         			break;
                                    			 }
                                    			 
                                    			 
                                    			
                                    			}
                                    		
                                    	}
        							
                                   if(objEv.has("searchableTextItems")){
                                	 
   									 logger.log(Level.INFO,"Success records for DATA-36 "+total_Source);
                                    }                             
                                    
                                    else{
         									total_Ev++;
         									logger.log(Level.SEVERE,"searchableTextItems not found for "+srcID);
         									 total_EV_event++;
         									 }
                                    
                         		
                                    }
     							
     							else{
     							    
     								total_Ev++;
     								
 									logger.log(Level.SEVERE,"TAGS not found for "+srcID);
 									 total_EV_event++;
     							
     							}
                                    
                                    
                     if(objEv.has("otherAttributes")){
                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
                						
                				    	for(int j=0;j<otherAttribute.length();j++){
                				    		
                				    		
                				    		
                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
                				    	
                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
                				    		
                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
                				    		offAirTBA=true;
                				    	}
                				    							    	
                				    	}
                						}
                						
                		else{
                		
                			 total_Ev++;
                			 total_EV_event++;
                			 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
                		     
                		}							
                                  	
                                  	
                                    
                 	
                     if(!offAirTBA && !enChk){
                    	 if(objEv.has("searchableTitles")){
								
                       		
                   			
                   			searchableTitles=(JSONArray) objEv.get("searchableTitles");
 									for(int o=0;o<searchableTitles.length();o++){
 										hierarchyLevel_Serach = (JSONObject) searchableTitles.get(o);
 													
 										hierarchyLevel_Value= hierarchyLevel_Serach.get("hierarchyLevel").toString();
 									
 									if(hierarchyLevel_Value.equalsIgnoreCase("season")){
 										if(!objEv.has("seasonNumber")){
 											 total_Ev++;
 											 total_EV_event++;
 											 logger.log(Level.SEVERE,"seasonNumber not found for "+srcID);
 				                		     Assert.assertFalse(false);
 										}
 										 										
 									}
 																		
 									}
 								
 								
 						
 								
 							}
                    	 else{
                    		 total_EV_event++;
                    	 }
							
                     }    
                     if(total_EV_event>0){
							rv_cntr++;
                     	
                     	errorEvent.add(rv_cntr);
                     }		
					    }
		 double applicableEvents= arrayEv.length();
		   double non_compl=errorEvent.size();
		    double compl_events=applicableEvents-non_compl;
		    
		    double perc =(compl_events/applicableEvents)*100;
		
		    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-36");
		    ip++;
		 
					    }
		 if(total_Ev>0){
         	  errorSrc.add(srcID);
           }
						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				logger.log(Level.SEVERE,"SourceType not found for "+srcID);
			}
				}	
			 }
				
		}
	
		if(errorSrc.size()>0){
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc);
			uPE.ExcelOP(30, errorSrc.size(), errorSrc);
		} 

	}	
	
		catch(Exception e){
			e.printStackTrace();
		}
	
	
	
		
	
		
	}


	public void Data_37() {


utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		



		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data37.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    
	    int ip=1;
		for(int i=0;i<array.length();i++){
			ArrayList errorEvent= new ArrayList();

			int total_Ev=0;
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			 
			 if(dlVry.has("quality")){
			 String QL=dlVry.get("quality").toString();
			 
				if(QL.equalsIgnoreCase("premium")){
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
		 for(int k=0;k<arrayEv.length();k++){
			 
			 int total_EV_event=0;
		    	int rv_cntr=0;

		    	
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
						
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 total_EV_event++;
								 logger.log(Level.SEVERE,"eventType not matches with source for "+srcID);
							 
							}
							
				
                                    if(objEv.has("tags")){
                                    	tagAR=(JSONArray) objEv.get("tags");
                                    	
                                    	
                                    	for(int z=0;z<tagAR.length();z++){
                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
                                    		
                                    		category=hierarchyLevel.getJSONObject("category");
                                    		
                                    		categoryId=category.get("categoryId").toString();
                                    		
                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                    	
                                    		
                                    		valAR=hierarchyLevel.getJSONObject("value");
                                    		 valueNames=(JSONObject) valAR.get("valueNames");
                                    		 
                                    		 if(valueNames.has("en")){
                                    			String en=valueNames.get("en").toString();
                                    			                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Film")){
                                        			enChk=true;
                                        			
                                        			
                                        		}
                                        		
                                        		
                                        		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Drama series")){
                                        			enChk=true;
                                        			
                                        		}
                                    			
                                    		 }
                                    		
                                    	}
        							
                                
                                    
                         		
                                    }
     							
     							else{
     							    
     								total_Ev++;
     								total_EV_event++;
     								logger.log(Level.SEVERE,"TAGS not found for "+srcID);
     							}
                                    
                                    
                     if(objEv.has("otherAttributes")){
                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
                						
                				    	for(int j=0;j<otherAttribute.length();j++){
                				    		
                				    		
                				    		
                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
                				    	
                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
                				    		
                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
                				    		offAirTBA=true;
                				    	}
                				    							    	
                				    	}
                						}
                						
                		else{
                		
                			 total_Ev++;
                			 total_EV_event++;
                			 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
                		     
                		}							
                                  	
                                  	
                                    
                 	
                     if(!offAirTBA && enChk){
                    	 if(!objEv.has("countriesOfOrigin")){
                    		 total_Ev++;
                    		 total_EV_event++;
                    		 logger.log(Level.SEVERE,"countriesOfOrigin not found for "+srcID);
    							
                    		 
                    	 }
                    	
                     }    
                     if(total_EV_event>0){
							rv_cntr++;
                     	
                     	errorEvent.add(rv_cntr);
                     }
		
					    }
		 
		 double applicableEvents= arrayEv.length();
		   double non_compl=errorEvent.size();
		    double compl_events=applicableEvents-non_compl;
		    
		    double perc =(compl_events/applicableEvents)*100;
		
		    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-37");
		    ip++;
		    


					    }
		 if(total_Ev>0){
         	  errorSrc.add(srcID);
           }
						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				logger.log(Level.SEVERE,"SourceType not found for "+srcID);
			}
				}
			 }
				
		}
		if(errorSrc.size()>0){
			uPE.ExcelOP(31, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
	}	
	
		catch(Exception e){
			e.printStackTrace();
		}
	
	
	
		
	
		
	
		
	}


	public void Data_38() {
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data38.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			ArrayList errorEvent= new ArrayList();
			 int total_Ev=0;
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			 if(dlVry.has("quality")){
			 String QL=dlVry.get("quality").toString();
			 
				if(QL.equalsIgnoreCase("premium")){
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
		 for(int k=0;k<arrayEv.length();k++){
			 
			 int total_EV_event=0;
		    	int rv_cntr=0;
		    	
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
						
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
								 total_EV_event++;
							}
							
				
                                    if(objEv.has("tags")){
                                    	tagAR=(JSONArray) objEv.get("tags");
                                    	
                                    	
                                    	for(int z=0;z<tagAR.length();z++){
                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
                                    		
                                    		category=hierarchyLevel.getJSONObject("category");
                                    		
                                    		categoryId=category.get("categoryId").toString();
                                    		
                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                    	
                                    		
                                    		valAR=hierarchyLevel.getJSONObject("value");
                                    		 valueNames=(JSONObject) valAR.get("valueNames");
                                    		 
                                    		 if(valueNames.has("en")){
                                    			String en=valueNames.get("en").toString();
                                    			                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Film")){
                                        			enChk=true;
                                        			
                                        			
                                        		}
                                        		
                                        		
                                        		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Drama series")){
                                        			enChk=true;
                                        			
                                        		}
                                    			
                                    		 }
                                    		
                                    	}
        							
                                
                                    
                         		
                                    }
     							
     							else{
     								total_Ev++;
     								 logger.log(Level.SEVERE,"TAGS not found for "+srcID);	
     								total_EV_event++;
     							}
                                    
                                    
                     if(objEv.has("otherAttributes")){
                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
                						
                				    	for(int j=0;j<otherAttribute.length();j++){
                				    		
                				    		
                				    		
                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
                				    	
                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
                				    		
                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
                				    		offAirTBA=true;
                				    	}
                				    							    	
                				    	}
                						}
                						
                		else{
                		
                			 total_Ev++;
                			 total_EV_event++;
                			 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
                		     
                		}							
                                  	
                                  	
                                    
                 	
                     if(!offAirTBA && enChk){
                    	 if(!objEv.has("yearOfProduction")){
                    		 total_Ev++;
                    		 logger.log(Level.SEVERE,"yearOfProduction not found for "+srcID); 		   
                    		 total_EV_event++;
                    		 
                    	 }
                    	
                     }    
							
                     if(total_EV_event>0){
							rv_cntr++;
                     	
                     	errorEvent.add(rv_cntr);
                     }


					    }
		 double applicableEvents= arrayEv.length();
		   double non_compl=errorEvent.size();
		    double compl_events=applicableEvents-non_compl;
		    
		    double perc =(compl_events/applicableEvents)*100;
		
		    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-38");
		    ip++;
		 
					    }
		 if(total_Ev>0){
         	  errorSrc.add(srcID);
           }
						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
				}	
			 }
				
		}
	
		if(errorSrc.size()>0){
			uPE.ExcelOP(32, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 

	}	
	
		catch(Exception e){
			e.printStackTrace();
		}
	
		
	}


	public void Data_40() {

utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
		ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data40.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			ArrayList errorEvent= new ArrayList();
			int total_Ev=0;
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			
			 				
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
		 for(int k=0;k<arrayEv.length();k++){
			 
			 int total_EV_event=0;
		    	int rv_cntr=0;

							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
						
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
								   
									
								}
								
							}
							
							else{
													
								total_EV_event++;
								 total_Ev++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
							    
							}
							
				
                                    if(objEv.has("tags")){
                                    	tagAR=(JSONArray) objEv.get("tags");
                                    	
                                    	
                                    	for(int z=0;z<tagAR.length();z++){
                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
                                    		
                                    		category=hierarchyLevel.getJSONObject("category");
                                    		
                                    		categoryId=category.get("categoryId").toString();
                                    		
                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                    	
                                    		
                                    		valAR=hierarchyLevel.getJSONObject("value");
                                    		 valueNames=(JSONObject) valAR.get("valueNames");
                                    		 
                                    		 if(valueNames.has("en")){
                                    			String en=valueNames.get("en").toString();
                                    			                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Film")){
                                        			enChk=true;
                                        			
                                        			
                                        		}
                                        		
                                        		
                                        		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Drama series")){
                                        			enChk=true;
                                        			
                                        		}
                                        		
                                        		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Documentary")){
                                        			enChk=true;
                                        			
                                        		}
                                    			
                                    		 }
                                    		
                                    	}
        							
                                
                                    
                         		
                                    }
     							
     							else{
     							    
     								total_Ev++;
     								total_EV_event++;
     								 logger.log(Level.SEVERE,"tags not found for "+srcID);
     								
     							}
                                    
                                    
                     if(objEv.has("otherAttributes")){
                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
                						
                				    	for(int j=0;j<otherAttribute.length();j++){
                				    		
                				    		
                				    		
                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
                				    	
                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
                				    		
                				    		String hj=otherAttribute_value_valID_VAL.toString();
                				    		
                				    		
                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
                				    		offAirTBA=true;
                				    	}
                				    							    	
                				    	}
                						}
                						
                		else{
                		
                			 total_Ev++;
                			 total_EV_event++;
                			 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
                		     
                		}							
                                  	
                                  	
                                    
                 	
                     if(!offAirTBA && enChk){
                    	 if(objEv.has("relatedMaterial")){
                    	relatedMaterial=(JSONArray) objEv.get("relatedMaterial");
                     		origin= relatedMaterial.getJSONObject(0).get("origin").toString();
                     		
                     		if(!origin.toString().equalsIgnoreCase("IMDB")){
                     			 total_Ev++;
                     			total_EV_event++;
                     			 logger.log(Level.SEVERE,"IMDB not found for "+srcID);
                     		}
                    	 }
                    	 else{
                    		 total_Ev++;
                    		 total_EV_event++;
                    		 logger.log(Level.SEVERE,"relatedMaterial not found for "+srcID);
                		  
                    	 }
                    	 
                     }    
                     
            		 if(total_EV_event>0){
            				rv_cntr++;
                     	
                     	errorEvent.add(rv_cntr);
                     }	
					    }
		 double applicableEvents= arrayEv.length();
		   double non_compl=errorEvent.size();
		    double compl_events=applicableEvents-non_compl;
		    
		    double perc =(compl_events/applicableEvents)*100;
		
		    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-40");
		    ip++;
		
					    }
					    
					   
				
		 if(total_Ev>0){
         	  errorSrc.add(srcID);
           }
						
				 }
								
						
					 
				
				
		}
			else{
				total_Ev++;
				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
			}
						
				
		}
	
		
		if(errorSrc.size()>0){
			uPE.ExcelOP(33, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 

	}	
	
		catch(Exception e){
			e.printStackTrace();
		}
	
		
	
		
	}


	public void Data_41() {

utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		

		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
	    ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data41.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			int total_Ev=0;

ArrayList errorEvent= new ArrayList();
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			 if(dlVry.has("quality")){
			 String QL=dlVry.get("quality").toString();
			 
				if(QL.equalsIgnoreCase("premium")){
			 				
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
		 for(int k=0;k<arrayEv.length();k++){
			 
			 int total_EV_event=0;
		    	int rv_cntr=0;

							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
						
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
								 
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 total_EV_event++;
							 
							}
							
				
                                    if(objEv.has("tags")){
                                    	tagAR=(JSONArray) objEv.get("tags");
                                    	
                                    	
                                    	for(int z=0;z<tagAR.length();z++){
                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
                                    		
                                    		category=hierarchyLevel.getJSONObject("category");
                                    		
                                    		categoryId=category.get("categoryId").toString();
                                    		
                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                    	
                                    		
                                    		valAR=hierarchyLevel.getJSONObject("value");
                                    		 valueNames=(JSONObject) valAR.get("valueNames");
                                    		 
                                    		 if(valueNames.has("en")){
                                    			String en=valueNames.get("en").toString();
                                    			                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Film")){
                                        			enChk=true;
                                        			
                                        			
                                        		}
                                        		
                                        		
                                        		if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Drama series")){
                                        			enChk=true;
                                        			
                                        		}
                                    			
                                    		 }
                                    		
                                    	}
        							
                                
                                    
                         		
                                    }
     							
     							else{
     							    
     								total_Ev++;
     								total_EV_event++;
     								
     							}
                                    
                                    
                     if(objEv.has("otherAttributes")){
                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
                						
                				    	for(int j=0;j<otherAttribute.length();j++){
                				    		
                				    		
                				    		
                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
                				    	
                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
                				    		
                				    		String hj=otherAttribute_value_valID_VAL.toString();
                				    		
                				    		
                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
                				    		offAirTBA=true;
                				    	}
                				    							    	
                				    	}
                						}
                						
                		else{
                		
                			 total_Ev++;
                			 total_EV_event++;
                		     Assert.assertFalse(false);
                		     
                		}							
                                  	
                                  	
                                    
                 	
                     if(!offAirTBA && enChk){
                    	 if(!objEv.has("qualityRatings")){
                    			 total_Ev++;
                    			 total_EV_event++;
                		  
                    	 }
                    	 
                     }    
                     
                     if(total_EV_event>0){
							rv_cntr++;
                     	
                     	errorEvent.add(rv_cntr);
                     }
								
					    }
		 
		 double applicableEvents= arrayEv.length();
		   double non_compl=errorEvent.size();
		    double compl_events=applicableEvents-non_compl;
		    
		    double perc =(compl_events/applicableEvents)*100;
		
		    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-41");
		    ip++;
		    

					    }

		 if(total_Ev>0){
         	  errorSrc.add(srcID);
           }
				 }
				
		}
			else{
				total_Ev++;
				
			}
						
				}	
			 }
		}
		if(errorSrc.size()>0){
			uPE.ExcelOP(34, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
	}	
	
		catch(Exception e){
			
			 e.printStackTrace();
		}
	
		
	
		
	
	}


	public void Data_42() {
utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
	    ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data42.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			int total_Ev=0;
			ArrayList errorEvent= new ArrayList();
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
		 for(int k=0;k<arrayEv.length();k++){
			 
			 int total_EV_event=0;
		    	int rv_cntr=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
						
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
								 total_EV_event++;
							 
							}
							
				
                                    if(objEv.has("tags")){

                                    	tagAR=(JSONArray) objEv.get("tags");
                                    	
                                    	
                                    	for(int z=0;z<tagAR.length();z++){
                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
                                    		
                                    		category=hierarchyLevel.getJSONObject("category");
                                    		
                                    		categoryId=category.get("categoryId").toString();
                                    		
                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                    	
                                    		
                                    		valAR=hierarchyLevel.getJSONObject("value");
                                    		 valueNames=(JSONObject) valAR.get("valueNames");
                                    		 
                                    		 if(valueNames.has("en")){
                                    			String en=valueNames.get("en").toString();
                                    			                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Adult")){
                                        			enChk=true;
                                        			
                                        			
                                        		}
                                    			
                                    		 }
                                    		
                                    	}
        							
                                
                                    
                         		
                                    
                                    }
     							
     							else{
     							    
     								total_Ev++;
     								total_EV_event++;
     								logger.log(Level.SEVERE,"tags not found for "+srcID);
     								
     							}
                                    
                                    
                     if(objEv.has("otherAttributes")){
                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
                						
                				    	for(int j=0;j<otherAttribute.length();j++){
                				    		
                				    		
                				    		
                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
                				    	
                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
                				    		
                				    		String hj=otherAttribute_value_valID_VAL.toString();
                				    		
                				    		
                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
                				    		offAirTBA=true;
                				    	}
                				    							    	
                				    	}
                						}
                						
                		else{
                		
                			 total_Ev++;
                			 total_EV_event++;
                			 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
                		     
                		}							
                                  	
                                  	
                                    
                 	
                     if(!offAirTBA && enChk){
                    	 if(!objEv.has("ageRatings")){
                    			 total_Ev++;
                    			 total_EV_event++;
                    			 logger.log(Level.SEVERE,"ageRatings not found for "+srcID);
                		  
                    	 }
                    	 
                     }  
                     
                     if(total_EV_event>0){
							rv_cntr++;
                     	
                     	errorEvent.add(rv_cntr);
                     }
								
					    }
		 
		 double applicableEvents= arrayEv.length();
		   double non_compl=errorEvent.size();
		    double compl_events=applicableEvents-non_compl;
		    
		    double perc =(compl_events/applicableEvents)*100;
		
		    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-42");
		    ip++;
		    

					    }

		 if(total_Ev>0){
         	  errorSrc.add(srcID);
           }
				 }
				
		}
			else{
				total_Ev++;
				 logger.log(Level.SEVERE,"Source Type not found for "+srcID);	
			}
						
							
		}
		if(errorSrc.size()>0){
			uPE.ExcelOP(35, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
	}	
	
		catch(Exception e){
			
			 e.printStackTrace();
		}
	
	}


	public void Data_45() {

   utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
	    ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data45.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			int total_Ev=0;
			ArrayList errorEvent= new ArrayList();
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			 
			 if(dlVry.has("quality")){
			 String QL=dlVry.get("quality").toString();
			 
				if(QL.equalsIgnoreCase("premium")){
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
		 for(int k=0;k<arrayEv.length();k++){
			 
			 int total_EV_event=0;
		    	int rv_cntr=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
						
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
									
								}
								
							}
							
							else{
																
								 total_Ev++;
								 total_EV_event++;
								 logger.log(Level.SEVERE,"eventType not found for "+srcID);
							 
							}
							
				
                                    if(objEv.has("tags")){


                                    	tagAR=(JSONArray) objEv.get("tags");
                                    	
                                    	
                                    	for(int z=0;z<tagAR.length();z++){
                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
                                    		
                                    		category=hierarchyLevel.getJSONObject("category");
                                    		
                                    		categoryId=category.get("categoryId").toString();
                                    		
                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                    	
                                    		
                                    		valAR=hierarchyLevel.getJSONObject("value");
                                    		 valueNames=(JSONObject) valAR.get("valueNames");
                                    		 
                                    		 if(valueNames.has("en")){
                                    			String en=valueNames.get("en").toString();
                                    			                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("subGenre.ericsson")&&en.equalsIgnoreCase("Animation")){
                                        			enChk=true;
                                        			
                                        		}
                                    			
                                    		 }
                                    		
                                    	}
        							
                                    }
     							
     							else{
     							    
     								total_Ev++;
     								total_EV_event++;
     								 logger.log(Level.SEVERE,"Tags not found for "+srcID);
     								
     							}
                                    
                                    
                     if(objEv.has("otherAttributes")){
                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
                						
                				    	for(int j=0;j<otherAttribute.length();j++){
                				    		
                				    		
                				    		
                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
                				    	
                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
                				    	
                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
                				    		offAirTBA=true;
                				    	}
                				    							    	
                				    	}
                						}
                						
                		else{
                		
                			 total_Ev++;
                		   
                			 total_EV_event++;

                		     logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
                		     
                		}							
                                  	
                                  	
               
                     if(!offAirTBA && enChk){
                    	 
                         if(objEv.has("contributions")){
                        	 
                        	 contributions=(JSONArray) objEv.get("contributions");
                        	 for(int con=0;con<contributions.length();con++){

                         		contributionType=(JSONObject) contributions.get(con);
                         		contributionTypeId=contributionType.get("contributorId").toString();
                         		JSONObject contributionTypeId=(JSONObject) contributionType.get("contributionType");
                         		
                         		String role = contributionTypeId.get("contributionTypeId").toString();
                         		
                         	if(!contributionType.has("contributorId")){
                         		 total_Ev++;
                    		     
                         		total_EV_event++;
                    		     logger.log(Level.SEVERE,"contributorId not found for "+srcID);
                         	}
                         	                       	
                         	if(!role.equalsIgnoreCase("DIRECTOR") && !role.equalsIgnoreCase("PRODUCER")){
                         		 total_Ev++;
                         		total_EV_event++;
                         		 logger.log(Level.SEVERE,"Role is not Director and Producer not found for "+srcID);
                         	}
                         	
                        	 }
                        	
                        	 
                        	 
                         }
                     	
                     }    
                     if(total_EV_event>0){
							rv_cntr++;
                     	
                     	errorEvent.add(rv_cntr);
                     }
		
					    }
		  double applicableEvents= arrayEv.length();
		   double non_compl=errorEvent.size();
		    double compl_events=applicableEvents-non_compl;
		    
		    double perc =(compl_events/applicableEvents)*100;
		
		    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-45");
		    ip++;
		    

					    }

		 if(total_Ev>0){
         	  errorSrc.add(srcID);
           }
				 }
				
		}
			else{
				total_Ev++;

				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
				}		
			 }
							
		}
		if(errorSrc.size()>0){
			uPE.ExcelOP(36, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
	}	
	
		catch(Exception e){
			
			 e.printStackTrace();
		}
	
	
		
	}


	public void Data_46() {


utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
	    ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data46.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  
	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			int total_Ev=0;
			ArrayList errorEvent= new ArrayList();
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			 
			 if(dlVry.has("quality")){
			 String QL=dlVry.get("quality").toString();
			 
				if(QL.equalsIgnoreCase("premium")){
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
		 for(int k=0;k<arrayEv.length();k++){
			 
			 int total_EV_event=0;
		    	int rv_cntr=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
						
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
								 
									
								}
								
							}
							
							else{

								 logger.log(Level.SEVERE,"eventType not found for "+srcID);						
								 total_Ev++;
								 total_EV_event++;
							 
							}
							
				
                                    if(objEv.has("tags")){


                                    	tagAR=(JSONArray) objEv.get("tags");
                                    	
                                    	
                                    	for(int z=0;z<tagAR.length();z++){
                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
                                    		
                                    		category=hierarchyLevel.getJSONObject("category");
                                    		
                                    		categoryId=category.get("categoryId").toString();
                                    		
                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                    	
                                    		
                                    		valAR=hierarchyLevel.getJSONObject("value");
                                    		 valueNames=(JSONObject) valAR.get("valueNames");
                                    		 
                                    		 if(valueNames.has("en")){
                                    			String en=valueNames.get("en").toString();
                                    			                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Film")){
                                        			enChk=true;
                                        			
                                        		}
                                    			
                                    		 }
                                    		
                                    	}
        							
                                    }
     							
     							else{
     							    
     								total_Ev++;
     								total_EV_event++;
     								 logger.log(Level.SEVERE,"TAGS not found for "+srcID);
     								
     							}
                                    
                                    
                     if(objEv.has("otherAttributes")){
                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
                						
                				    	for(int j=0;j<otherAttribute.length();j++){
                				    		
                				    		
                				    		
                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
                				    	
                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
                				    	
                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
                				    		offAirTBA=true;
                				    	}
                				    							    	
                				    	}
                						}
                						
                		else{
                		
                			 total_Ev++;
                			 total_EV_event++;

                			 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
                		     
                		}							
                                  	
                                  	
                
                     if(!offAirTBA && enChk){
                    	 
                         if(objEv.has("contributions")){
                        	 delivery=(JSONObject) obj.get("delivery");
     						lanG=delivery.getJSONArray("languages");
     						
     						for(int ar=0;ar<lanG.length();ar++){
     							arStore.add(lanG.getString(ar)+":");
     							
     							}
     						
     						
                        	 contributions=(JSONArray) objEv.get("contributions");
                        	 
                        	 
                        		
                        	 for(int con=0;con<contributions.length();con++){

                         		contributionType=(JSONObject) contributions.get(con);
                         		contributionTypeId=contributionType.get("contributorId").toString();
                         		
                         		if(!contributionType.has("contributorId")){
                           		 total_Ev++;
                      		     Assert.assertFalse(false);
                      		   total_EV_event++;

                      		   logger.log(Level.SEVERE,"contributorId not found for "+srcID);
                      		    
                           	}
                         		
                         		if(!contributionType.has("contributionType")){
                              		 total_Ev++;
                         		     Assert.assertFalse(false);
                         		    total_EV_event++;
                         		    logger.log(Level.SEVERE,"contributionType not found for "+srcID);
                         		    
                              	}
                         		
                         		JSONObject contributionName=(JSONObject) contributionType.get("contributorNames");
                         		
                         	
                         	 					
            								JSONObject lamgStore=null;
            								
            									lamgStore=contributionName;
            	    							String lStore=lamgStore.toString().replaceAll("\"", "");;
            									for(String lan:arStore){
            										if(lStore.contains(lan)){
            										}
            										else{
            											total_Ev++;
            											total_EV_event++;
            											 logger.log(Level.SEVERE,"Languages not matches with source for "+srcID);
            	        								
            										}
            										
            									}
            					
            					
                        	 }
                        	
                        	 
                        	 
                         }
                     	
                     }    
							
                     if(total_EV_event>0){
							rv_cntr++;
                     	
                     	errorEvent.add(rv_cntr);
                     }
                     
					    }
		 
		 double applicableEvents= arrayEv.length();
		   double non_compl=errorEvent.size();
		    double compl_events=applicableEvents-non_compl;
		    
		    double perc =(compl_events/applicableEvents)*100;
		
		    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-46");
		    ip++;
		    
		 
		 
					    }

		 if(total_Ev>0){
         	  errorSrc.add(srcID);
           }
				 }
				
		}
			else{
				total_Ev++;

				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
				}		
			 }
							
		}
		if(errorSrc.size()>0){
			uPE.ExcelOP(37, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
	}	
	
		catch(Exception e){
			
			 e.printStackTrace();
		}
	
	
		
	
		
	}


	public void Data_47() {



utilsPack pk = new utilsPack();
		
		String stDate=pk.dataDate();
		String enDate=pk.dataDateENd();
		
		
		try{

		 boolean offAirTBA = false;
		 boolean enChk=false;
		
		String theString =null;
		 
		 URL url = new URL(uPE.urlSC_Both());
		 
		 
		try {


			//String result = java.net.URLDecoder.decode(url, "UTF-8");
			
			input = url.openConnection().getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(input, writer, "UTF-8");
			theString = writer.toString();
			
		}

		finally {

		}

		final String JSON_PATH = theString;
	    JSONArray array = new JSONArray(JSON_PATH);
	    int total_SRC=array.length();
	    ArrayList errorSrc= new ArrayList();
	    int total_Source=0;
	    logger.log(Level.INFO,"total records in source "+total_SRC);
	    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data47.log"); 
	    logger.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
	    fh.setFormatter(formatter);  

	    int ip=1;
	    
		for(int i=0;i<array.length();i++){
			int total_Ev=0;
			ArrayList errorEvent= new ArrayList();
			 ArrayList<String> arStore = new ArrayList<String>(); 
			  
			JSONObject obj = (JSONObject) array.get(i);
			System.out.println("ELEM-SOURCE "+obj);
			 
			srcTyp=obj.getString("sourceType");
			 JSONObject dlVry =(JSONObject) obj.get("delivery");
			 
			 if(dlVry.has("quality")){
			 String QL=dlVry.get("quality").toString();
			 
				if(QL.equalsIgnoreCase("premium")){
			if(obj.has("sourceType")){
				 srcTyp=obj.getString("sourceType");
				
				if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
					 
					 srcID=obj.get("sourceId").toString();
						
					 
						System.out.println("sourceId "+srcID);
					
						URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
						
						inputEv = urlEv.openConnection().getInputStream();
						StringWriter writer = new StringWriter();
						IOUtils.copy(inputEv, writer, "UTF-8");
						theString = writer.toString();
						
						final String JSON_PATH_ev = theString;
					    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
					    if(arrayEv.length()>0){
		 for(int k=0;k<arrayEv.length();k++){
			 int total_EV_event=0;
		    	int rv_cntr=0;
							JSONObject objEv = (JSONObject) arrayEv.get(k);
							 
							
						
							if(objEv.has("eventType")){
								EvTyp=objEv.get("eventType").toString();
								
								
								if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
									 total_Ev++;
									 total_EV_event++;
								 
									
								}
								
							}
							
							else{
								total_EV_event++;

								 logger.log(Level.SEVERE,"eventType not found for "+srcID);						
								 total_Ev++;
							 
							}
							
				
                                    if(objEv.has("tags")){


                                    	tagAR=(JSONArray) objEv.get("tags");
                                    	
                                    	
                                    	for(int z=0;z<tagAR.length();z++){
                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
                                    		
                                    		category=hierarchyLevel.getJSONObject("category");
                                    		
                                    		categoryId=category.get("categoryId").toString();
                                    		
                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
                                    	
                                    		
                                    		valAR=hierarchyLevel.getJSONObject("value");
                                    		 valueNames=(JSONObject) valAR.get("valueNames");
                                    		 
                                    		 if(valueNames.has("en")){
                                    			String en=valueNames.get("en").toString();
                                    			                                    			
                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Film")){
                                        			enChk=true;
                                        			
                                        		}
                                    			
                                    		 }
                                    		
                                    	}
        							
                                    }
     							
     							else{
     							    
     								total_Ev++;
     								total_EV_event++;
     								 logger.log(Level.SEVERE,"TAGS not found for "+srcID);
     								
     							}
                                    
                                    
                     if(objEv.has("otherAttributes")){
                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
                						
                				    	for(int j=0;j<otherAttribute.length();j++){
                				    		
                				    		
                				    		
                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
                				    	
                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
                				    	
                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
                				    		offAirTBA=true;
                				    	}
                				    							    	
                				    	}
                						}
                						
                		else{
                		
                			 total_Ev++;
                		   
                			 total_EV_event++;
                			 logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
                		     
                		}							
                                  	
                                  	
           if(objEv.has("yearOfProduction")){ 
        	   
        	   int yop=objEv.getInt("yearOfProduction");
        	   if(yop>2000){
                     if(!offAirTBA && enChk){
                    	 
                         if(objEv.has("contributions")){
                        	 delivery=(JSONObject) obj.get("delivery");
     						lanG=delivery.getJSONArray("languages");
     						
     						for(int ar=0;ar<lanG.length();ar++){
     							arStore.add(lanG.getString(ar)+":");
     							
     							}
     						
     						
                        	 contributions=(JSONArray) objEv.get("contributions");
                        	 
                        	 
                        		
                        	 for(int con=0;con<contributions.length();con++){

                         		contributionType=(JSONObject) contributions.get(con);
                         		contributionTypeId=contributionType.get("contributorId").toString();
                         		
                         		if(!contributionType.has("contributorId")){
                           		 total_Ev++;
                      		     Assert.assertFalse(false);
                      		   total_EV_event++;
                      		   logger.log(Level.SEVERE,"contributorId not found for "+srcID);
                      		    
                           	}
                         		
                         		if(!contributionType.has("contributionType")){
                              		 total_Ev++;
                         		     Assert.assertFalse(false);
                         		    total_EV_event++;
                         		    logger.log(Level.SEVERE,"contributionType not found for "+srcID);
                         		    
                              	}
                         		
                         		JSONObject contributionName=(JSONObject) contributionType.get("contributionType");
                         		JSONObject contributionTypeNames=(JSONObject) contributionName.get("contributionTypeNames");
                         	
                         	 					
            								JSONObject lamgStore=null;
            								
            									lamgStore=contributionTypeNames;
            	    							String lStore=lamgStore.toString().replaceAll("\"", "");;
            									for(String lan:arStore){
            										if(lStore.contains(lan)){
            										}
            										else{
            											total_Ev++;
            											total_EV_event++;
            											 logger.log(Level.SEVERE,"Languages not matches (contributionTypeNames) with source for "+srcID);
            	        								
            										}
            										
            									}
            					
            					
                        	 }
                        	
                        	 
                        	 
                         }
                     	
                     }   
        	   }
                     
           }
           else{
        		total_Ev++;
        		total_EV_event++;
				 logger.log(Level.SEVERE,"YEAR OF PRODN not Presnet for "+srcID);
           }
           if(total_EV_event>0){
				rv_cntr++;
           	
           	errorEvent.add(rv_cntr);
           }		
					    }
		 
		 double applicableEvents= arrayEv.length();
		   double non_compl=errorEvent.size();
		    double compl_events=applicableEvents-non_compl;
		    
		    double perc =(compl_events/applicableEvents)*100;
		
		    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-47");
		    ip++;
		    

		 
		 
					    }

		 if(total_Ev>0){
         	  errorSrc.add(srcID);
           }
				 }
				
		}
			else{
				total_Ev++;

				 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
				
			}
				}		
			 }
							
		}
		if(errorSrc.size()>0){
			//uPE.ExcelOP(38, errorSrc.size(), errorSrc);
			logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
		} 
	}	
	
		catch(Exception e){
			
			 e.printStackTrace();
		}
	
	
		
	
		
	
		
	}


	public void Data_48() {


		   utilsPack pk = new utilsPack();
				
				String stDate=pk.dataDate();
				String enDate=pk.dataDateENd();
				
				
				try{

				 boolean offAirTBA = false;
				 boolean enChk=false;
				
				String theString =null;
				 
				 URL url = new URL(uPE.urlSC_Both());
				 
				 
				try {


					//String result = java.net.URLDecoder.decode(url, "UTF-8");
					
					input = url.openConnection().getInputStream();
					StringWriter writer = new StringWriter();
					IOUtils.copy(input, writer, "UTF-8");
					theString = writer.toString();
					
				}

				finally {

				}

				final String JSON_PATH = theString;
			    JSONArray array = new JSONArray(JSON_PATH);
			    int total_SRC=array.length();
			    ArrayList errorSrc= new ArrayList();
			    int total_Source=0;
			    logger.log(Level.INFO,"total records in source "+total_SRC);
			    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data48.log"); 
			    logger.addHandler(fh);
			    SimpleFormatter formatter = new SimpleFormatter();  
			    fh.setFormatter(formatter);  

			    int ip=1;
			    boolean roleFlag=false;
				for(int i=0;i<array.length();i++){
					int total_Ev=0;
					ArrayList errorEvent= new ArrayList();
					 ArrayList<String> arStore = new ArrayList<String>(); 
					  
					JSONObject obj = (JSONObject) array.get(i);
					System.out.println("ELEM-SOURCE "+obj);
					 
					srcTyp=obj.getString("sourceType");
					 JSONObject dlVry =(JSONObject) obj.get("delivery");
					 
					// if(dlVry.has("quality")){
					// String QL=dlVry.get("quality").toString();
					 
						
					if(obj.has("sourceType")){
						 srcTyp=obj.getString("sourceType");
						
						if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
							 
							 srcID=obj.get("sourceId").toString();
								
							 
								System.out.println("sourceId "+srcID);
							
								URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
								
								inputEv = urlEv.openConnection().getInputStream();
								StringWriter writer = new StringWriter();
								IOUtils.copy(inputEv, writer, "UTF-8");
								theString = writer.toString();
								
								final String JSON_PATH_ev = theString;
							    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
							   
							    if(arrayEv.length()>0){
							    	String timber="";
			if(!timber.equalsIgnoreCase("director")){				    	 
						               
				 for(int k=0;k<arrayEv.length();k++){
					 
					 int total_EV_event=0;
				    	int rv_cntr=0;
									JSONObject objEv = (JSONObject) arrayEv.get(k);
									 
									
								
									if(objEv.has("eventType")){
										EvTyp=objEv.get("eventType").toString();
										
										
										if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
											 total_Ev++;
											 total_EV_event++;
											
										}
										
									}
									
									else{
																		
										 total_Ev++;
										 total_EV_event++;
										 logger.log(Level.SEVERE,"eventType not found for "+srcID);
									 
									}
									
						
		                                    if(objEv.has("tags")){


		                                    	tagAR=(JSONArray) objEv.get("tags");
		                                    	
		                                    	
		                                    	for(int z=0;z<tagAR.length();z++){
		                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
		                                    		
		                                    		category=hierarchyLevel.getJSONObject("category");
		                                    		
		                                    		categoryId=category.get("categoryId").toString();
		                                    		
		                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
		                                    	
		                                    		
		                                    		valAR=hierarchyLevel.getJSONObject("value");
		                                    		 valueNames=(JSONObject) valAR.get("valueNames");
		                                    		 
		                                    		 if(valueNames.has("en")){
		                                    			String en=valueNames.get("en").toString();
		                                    			                                    			
		                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Film")){
		                                        			enChk=true;
		                                        			
		                                        		}
		                                    			
		                                    		 }
		                                    		
		                                    	}
		        							
		                                    }
		     							
		     							else{
		     							    
		     								total_Ev++;
		     								total_EV_event++;
		     								 logger.log(Level.SEVERE,"Tags not found for "+srcID);
		     								
		     							}
		                                    
		                                    
		                     if(objEv.has("otherAttributes")){
		                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
		                						
		                				    	for(int j=0;j<otherAttribute.length();j++){
		                				    		
		                				    		
		                				    		
		                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
		                				    	
		                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
		                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
		                				    	
		                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
		                				    		offAirTBA=true;
		                				    	}
		                				    							    	
		                				    	}
		                						}
		                						
		                		else{
		                		
		                			 total_Ev++;
		                		   
		                		 
		                			 total_EV_event++;
		                		     logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
		                		     
		                		}							
		                                  	
		                  
		                     if(!offAirTBA && enChk){
		                    	
		                         if(objEv.has("contributions")){
		                        	 
		                        	 contributions=(JSONArray) objEv.get("contributions");
		                        	 for(int con=0;con<contributions.length();con++){

		                         		contributionType=(JSONObject) contributions.get(con);
		                         		contributionTypeId=contributionType.get("contributorId").toString();
		                         		JSONObject contributionTypeId=(JSONObject) contributionType.get("contributionType");
		                         		
		                         		String role = contributionTypeId.get("contributionTypeId").toString();
		                         		
		                         	if(!contributionType.has("contributorId")){
		                         		 total_Ev++;
		                    		     
		                         		total_EV_event++;
		                    		     logger.log(Level.SEVERE,"contributorId not found for "+srcID);
		                         	}
		                         	                       	
		                         	if(role.equalsIgnoreCase("DIRECTOR")){
		                         		roleFlag=true;
		                         		break;
		                         	}
		                         	
		                        	 }
		                        	
		                        	 
		                        	 
		                         }
		                         else{
		                        	 total_Ev++;
		                        	 total_EV_event++;

	                    		     logger.log(Level.SEVERE,"Contributions not found for "+srcID);
		                         }
		                         if(!roleFlag){
		                        	 
		                        	 total_Ev++;
		                        	 total_EV_event++;
	                    		     logger.log(Level.SEVERE,"EVEN ONE ROLE of DIRECTOR not found for "+srcID);
		                        	 
		                         }
		                         
		                         if(roleFlag){
		                        	 timber="Director";
		                        	 break;
		                         }
		                     	
		                     }    
		         			
		                     if(total_EV_event>0){
									rv_cntr++;
                             	
                             	errorEvent.add(rv_cntr);
                             }
							    }
				 
				
				 
			}
			
			 double applicableEvents= arrayEv.length();
			   double non_compl=errorEvent.size();
			    double compl_events=applicableEvents-non_compl;
			    
			    double perc =(compl_events/applicableEvents)*100;
			
			    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-48");
			    ip++;
							    }
							  
				 if(total_Ev>0){
		         	  errorSrc.add(srcID);
		           }
						 }
						
				}
					else{
						total_Ev++;

						 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
						
					}
					
									
				}
				if(errorSrc.size()>0){
					uPE.ExcelOP(39, errorSrc.size(), errorSrc);
					logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
				} 
			}	
			
				catch(Exception e){
				
					 e.printStackTrace();
				}
			
			
				
			
		
	}


	public void Data_49() {



		   utilsPack pk = new utilsPack();
				
				String stDate=pk.dataDate();
				String enDate=pk.dataDateENd();
				
				
				try{

				 boolean offAirTBA = false;
				 boolean enChk=false;
				
				String theString =null;
				 
				 URL url = new URL(uPE.urlSC_Both());
				 
				 
				try {


					//String result = java.net.URLDecoder.decode(url, "UTF-8");
					
					input = url.openConnection().getInputStream();
					StringWriter writer = new StringWriter();
					IOUtils.copy(input, writer, "UTF-8");
					theString = writer.toString();
					
				}

				finally {

				}

				final String JSON_PATH = theString;
			    JSONArray array = new JSONArray(JSON_PATH);
			    int total_SRC=array.length();
			    ArrayList errorSrc= new ArrayList();
			    int total_Source=0;
			    logger.log(Level.INFO,"total records in source "+total_SRC);
			    fh = new FileHandler("C:/Users/EZVSXHA/workspace/AO-KPIl/src/test/java/Utils/MyLogFile_Data49.log"); 
			    logger.addHandler(fh);
			    SimpleFormatter formatter = new SimpleFormatter();  
			    fh.setFormatter(formatter);  
			    
			    int roleFlag=0;
			    int ip=1;
				for(int i=0;i<array.length();i++){
					int total_Ev=0;
					ArrayList errorEvent= new ArrayList();
					 ArrayList<String> arStore = new ArrayList<String>(); 
					  
					JSONObject obj = (JSONObject) array.get(i);
					System.out.println("ELEM-SOURCE "+obj);
					 
					srcTyp=obj.getString("sourceType");
					 JSONObject dlVry =(JSONObject) obj.get("delivery");
					 
					// if(dlVry.has("quality")){
					// String QL=dlVry.get("quality").toString();
					 
						
					if(obj.has("sourceType")){
						 srcTyp=obj.getString("sourceType");
						
						if(srcTyp.equalsIgnoreCase(Utilsobject.srcT_vod)||srcTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)){
							 
							 srcID=obj.get("sourceId").toString();
								
							 
								System.out.println("sourceId "+srcID);
							
								URL urlEv = new URL(uPE.urlEV(srcID, stDate, enDate));
								
								inputEv = urlEv.openConnection().getInputStream();
								StringWriter writer = new StringWriter();
								IOUtils.copy(inputEv, writer, "UTF-8");
								theString = writer.toString();
								
								final String JSON_PATH_ev = theString;
							    JSONArray arrayEv = new JSONArray(JSON_PATH_ev);
							    String timber="";
							    if(arrayEv.length()>0){
							    	
			if(!timber.equalsIgnoreCase("Actor")){				    	 
						               
				 for(int k=0;k<arrayEv.length();k++){
					 
					 int total_EV_event=0;
				    	int rv_cntr=0;
									JSONObject objEv = (JSONObject) arrayEv.get(k);
									 
									
								
									if(objEv.has("eventType")){
										EvTyp=objEv.get("eventType").toString();
										
										
										if(!EvTyp.equalsIgnoreCase(Utilsobject.srcT_Linear)&&!EvTyp.equalsIgnoreCase(Utilsobject.srcT_vod)){
											 total_Ev++;
											 total_EV_event++;
										 
											
										}
										
									}
									
									else{
																		
										 total_Ev++;
										 total_EV_event++;
										 logger.log(Level.SEVERE,"eventType not found for "+srcID);
									 
									}
									
						
		                                    if(objEv.has("tags")){


		                                    	tagAR=(JSONArray) objEv.get("tags");
		                                    	
		                                    	
		                                    	for(int z=0;z<tagAR.length();z++){
		                                    		hierarchyLevel=(JSONObject) tagAR.get(z);
		                                    		
		                                    		category=hierarchyLevel.getJSONObject("category");
		                                    		
		                                    		categoryId=category.get("categoryId").toString();
		                                    		
		                                    		hRti=hierarchyLevel.get("hierarchyLevel").toString();
		                                    	
		                                    		
		                                    		valAR=hierarchyLevel.getJSONObject("value");
		                                    		 valueNames=(JSONObject) valAR.get("valueNames");
		                                    		 
		                                    		 if(valueNames.has("en")){
		                                    			String en=valueNames.get("en").toString();
		                                    			                                    			
		                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Film")){
		                                        			enChk=true;
		                                        			
		                                        		}
		                                    			if(hRti.equalsIgnoreCase("program")&&categoryId.equalsIgnoreCase("genre.ericsson")&&en.equalsIgnoreCase("Drama series")){
		                                        			enChk=true;
		                                        			
		                                        		}
		                                    			
		                                    		 }
		                                    		
		                                    	}
		        							
		                                    }
		     							
		     							else{
		     							    
		     								total_Ev++;
		     								total_EV_event++;
		     								 logger.log(Level.SEVERE,"Tags not found for "+srcID);
		     								
		     							}
		                                    
		                                    
		                     if(objEv.has("otherAttributes")){
		                				    	otherAttribute= (JSONArray) objEv.get("otherAttributes");
		                						
		                				    	for(int j=0;j<otherAttribute.length();j++){
		                				    		
		                				    		
		                				    		
		                				    		otherAttribute_value = (JSONObject) otherAttribute.get(j);
		                				    	
		                				    		otherAttribute_value_valID=(JSONObject) otherAttribute_value.get("value");
		                				    		otherAttribute_value_valID_VAL= otherAttribute_value_valID.get("valueId").toString();
		                				    	
		                				    	if(otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.offAir")||otherAttribute_value_valID_VAL.equalsIgnoreCase("eventStatus.tba")){
		                				    		offAirTBA=true;
		                				    	}
		                				    							    	
		                				    	}
		                						}
		                						
		                		else{
		                		
		                			 total_Ev++;
		                		   
		                			 total_EV_event++;

		                		     logger.log(Level.SEVERE,"otherAttributes not found for "+srcID);
		                		     
		                		}							
		                                  	
		                  
		                     if(!offAirTBA && enChk){
		                    	
		                         if(objEv.has("contributions")){
		                        	 
		                        	 contributions=(JSONArray) objEv.get("contributions");
		                        	 for(int con=0;con<contributions.length();con++){

		                         		contributionType=(JSONObject) contributions.get(con);
		                         		contributionTypeId=contributionType.get("contributorId").toString();
		                         		JSONObject contributionTypeId=(JSONObject) contributionType.get("contributionType");
		                         		
		                         		String role = contributionTypeId.get("contributionTypeId").toString();
		                         		
		                         	if(!contributionType.has("contributorId")){
		                         		 total_Ev++;
		                         		total_EV_event++;
		                    		     logger.log(Level.SEVERE,"contributorId not found for "+srcID);
		                         	}
		                         	                       	
		                         	if(role.equalsIgnoreCase("ACTOR")){
		                         		roleFlag++;
		                         		if(roleFlag>=6){
		                         		break;
		                         		}
		                         	}
		                         	
		                        	 }
		                        	
		                        	 
		                        	 
		                         }
		                         else{
		                        	 total_Ev++;
		                        	 total_EV_event++;
	                    		     logger.log(Level.SEVERE,"CONTRIBUTIONS Not found for "+srcID);
		                         }
		                         
		                         if(roleFlag<6){
		                        	 
		                        	 total_Ev++;
		                        	 total_EV_event++;
	                    		     logger.log(Level.SEVERE,"SIX ROLES of ACTOR not found for "+srcID);
		                        	 
		                         }
		                         
		                         if(roleFlag>=6){
		                        	 timber="Actor";
		                        	 logger.log(Level.INFO,"SUCCESS - SIX ROLES of ACTOR ARE found for "+srcID);
		                        	 break;
		                        	 
		                         }
		                     	
		                     }    
		         			
		                     if(total_EV_event>0){
									rv_cntr++;
                             	
                             	errorEvent.add(rv_cntr);
                             }
							    }
				 
			}
			



			   double applicableEvents= arrayEv.length();
								   double non_compl=errorEvent.size();
								    double compl_events=applicableEvents-non_compl;
								    
								    double perc =(compl_events/applicableEvents)*100;
								
								    uPE.ExceEvent(ip,applicableEvents, non_compl, compl_events, perc,srcID,"Data-49");
								    ip++;
							    }
							  
				 if(total_Ev>0){
					 if(!timber.equalsIgnoreCase("Actor")){
						 
		         	  errorSrc.add(srcID);
				 }
		           }
						 }
						
				}
					else{
						total_Ev++;

						 logger.log(Level.SEVERE,"SourceType not found for "+srcID);
						
					}
					
									
				}
				if(errorSrc.size()>0){
					uPE.ExcelOP(40, errorSrc.size(), errorSrc);
					logger.log(Level.SEVERE,"error records for DATACOUNT -FAILED FOR"+errorSrc.size()+" times, and out of "+total_SRC+"SOURCE IDs failed IDs are "+errorSrc); 
				} 
			}	
			
				catch(Exception e){
					
					 e.printStackTrace();
				}
			
			
				
			
		
	
		
	}


public void report() {
		
		
		try{
		 ArrayList<String> fileSheets = new ArrayList<String>();
		 List<ExcelObject> Obje = new ArrayList<ExcelObject>();
		 
		 fileSheets.add("eventReportData-01.xlsx");
		 fileSheets.add("eventReportData-02.xlsx");
		 fileSheets.add("eventReportData-03.xlsx");
		 fileSheets.add("eventReportData-04.xlsx");
		 fileSheets.add("eventReportData-05.xlsx");
		 fileSheets.add("eventReportData-06.xlsx");
		 fileSheets.add("eventReportData-07.xlsx");
		 fileSheets.add("eventReportData-08.xlsx");
		 fileSheets.add("eventReportData-09.xlsx");
		 fileSheets.add("eventReportData-10.xlsx");
		 fileSheets.add("eventReportData-11.xlsx");
		 fileSheets.add("eventReportData-12.xlsx");
		 fileSheets.add("eventReportData-13.xlsx");
		 fileSheets.add("eventReportData-14.xlsx");
		 fileSheets.add("eventReportData-15.xlsx");
		 fileSheets.add("eventReportData-16.xlsx");
		 fileSheets.add("eventReportData-17.xlsx");
		 fileSheets.add("eventReportData-18.xlsx");
		 fileSheets.add("eventReportData-24.xlsx");
		 fileSheets.add("eventReportDataimgreq2.xlsx");
		 fileSheets.add("eventReportDataimgreq1.xlsx");
		 fileSheets.add("eventReportData-28.xlsx");
		 fileSheets.add("eventReportData-30.xlsx");
		 fileSheets.add("eventReportData-31.xlsx");
		 fileSheets.add("eventReportData-34.xlsx");
		 fileSheets.add("eventReportData-36.xlsx");
		 fileSheets.add("eventReportData-37.xlsx");
		 fileSheets.add("eventReportData-38.xlsx");
		 fileSheets.add("eventReportData-40.xlsx");
		 fileSheets.add("eventReportData-41.xlsx");
		 fileSheets.add("eventReportData-42.xlsx");
		 fileSheets.add("eventReportData-45.xlsx");
		 fileSheets.add("eventReportData-46.xlsx");
		 fileSheets.add("eventReportData-47.xlsx");
		 fileSheets.add("eventReportData-48.xlsx");
		 fileSheets.add("eventReportData-49.xlsx");
		 
    
			String dev ="";
			
		 int kpiCounter=1;

		 ArrayList Data01= new ArrayList();
		 
		 ArrayList mismatch_SrIDs= new ArrayList();
		 
		 FileInputStream filein_summary = new FileInputStream("C://Users//EZVSXHA//workspace//AO-KPIl//src//test//resources//Summary_KPI.xlsx");
		 Workbook wb_summary = new XSSFWorkbook(filein_summary);
		 
		 CreationHelper createHelper_summary = wb_summary.getCreationHelper();
			Sheet sheet_summary = wb_summary.getSheetAt(0);

		Map<String,String> map = new HashMap<>();
		Map<String,List<String>> map1 = new HashMap<>();
		
		 for(int z=0;z<fileSheets.size();z++){
			 FileInputStream filein = new FileInputStream("C://Users//EZVSXHA//workspace//AO-KPIl//src//test//resources//"+fileSheets.get(z));
			  Workbook wb = new XSSFWorkbook(filein);
			  
			  CreationHelper createHelper = wb.getCreationHelper();
			Sheet sheet = wb.getSheetAt(0);
			for (int i = 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
				//ExcelObject o = new ExcelObject();
				Row row = sheet.getRow(i);
				if(row != null){
				String per = "0";
				if(row.getCell(4) == null){
					per = "0";
				}else{
					
					 DataFormatter formatter = new DataFormatter();
					 Cell cell = row.getCell(4);
					per = formatter.formatCellValue(cell);
				}
				
				String si = "0";
				if(row.getCell(0) == null){
					si = "0";
				}else{					
					 DataFormatter formatter = new DataFormatter();
					 Cell cell = row.getCell(0);
					 si = formatter.formatCellValue(cell);
				}
				if(map1.containsKey(si)){
					List<String>  list = map1.get(si);
					list.add(z, per);
					map1.put(si,list);
				}else{
					List<String>  list = new ArrayList<>();
					for (int ii = 0; ii < 50; ii++) {
						list.add(ii,"");
					}
					list.add(z, per);
					map1.put(si,list);
				}
				
				//map.put(si+"-"+z, per);
				}
			}	
			
		 }
		 int d = 1;
		 for (Map.Entry<String, String> v : map.entrySet()) {
			 Row row = sheet_summary.getRow(d);
			 if(row == null){
				 row = sheet_summary.createRow(d);
			 }
			 String sourceId = v.getKey().split("-")[0];
			 System.out.println("source id :: "+ sourceId+" key :: "+v.getKey()+ " row ::"+row);
			 int index = Integer.parseInt(v.getKey().split("-")[1]);
			 String percentage = v.getValue();
			 row.createCell(0).setCellValue(sourceId);
			 row.createCell(index).setCellValue(percentage);
			 d++;
		}
		 
		 
		 int d = 1;
		 for (Map.Entry<String, List<String>> v : map1.entrySet()) {
			 Row row = sheet_summary.getRow(d);
			 if(row == null){
				 row = sheet_summary.createRow(d);
			 }
			 String sourceId = v.getKey();
			 System.out.println("source id :: "+ sourceId+" key :: "+v.getKey()+ " row ::"+row+" list size :: "+v.getValue().size());
			 row.createCell(0).setCellValue(sourceId);
			 List<String> per = v.getValue();
			 for (int i = 0; i < per.size(); i++) {
				 row.createCell(i).setCellValue(per.get(i));
			 }			 
			 d++;
		}
		 
	
		 FileOutputStream fileOut = new FileOutputStream("C://Users//EZVSXHA//workspace//AO-KPIl//src//test//resources//Summary_KPI.xlsx");
			wb_summary.write(fileOut);
			
			  fileOut.close();

		 

			   int kpiCounter_miss=1;
		 
		
		
		 for(int z=1;z<fileSheets.size();z++){
			 
			 FileInputStream filein = new FileInputStream("C://Users//EZVSXHA//workspace//AO-KPIl//src//test//resources//"+fileSheets.get(z));
			  Workbook wb = new XSSFWorkbook(filein);
			  
			  CreationHelper createHelper = wb.getCreationHelper();
			Sheet sheet = wb.getSheetAt(0);
			
			
			int ip_mis=1;
			
			
			FileInputStream filein_summary = new FileInputStream("C://Users//EZVSXHA//workspace//AO-KPIl//src//test//resources//Missed_Kpis.xlsx");
			 Workbook wb_summary_miss = new XSSFWorkbook(filein_summary);
			 
			 CreationHelper createHelper_summary = wb_summary_miss.getCreationHelper();
				
		 
		  Sheet sheet_summary_Missmatch = wb_summary_miss.getSheetAt(0);
			
			for(int sum=1;sum<sheet_summary_Missmatch.getLastRowNum();sum++){
				
				Row row = sheet.getRow(ip_mis);
				
				Row row_miss = sheet_summary_Missmatch.getRow(sum);
				
				String sr_ID=row.getCell(0).toString();
				
				if(row.getCell(4)==null){
					break;
				}
				String threshold=row.getCell(4).toString();
				
				if(mismatch_SrIDs.contains(sr_ID) && !threshold.contains("#")){
					
					row_miss.createCell(0).setCellValue(sr_ID);  
					row_miss.createCell(kpiCounter_miss).setCellValue(threshold);
				}
				
				ip_mis++;
				
			}
			
			
			kpiCounter_miss++;
			
			
			FileOutputStream fileOut_miss = new FileOutputStream("C://Users//EZVSXHA//workspace//AO-KPIl//src//test//resources//Missed_Kpis.xlsx");
			wb_summary_miss.write(fileOut_miss);
			fileOut_miss.close();
	 
		 }
		

	
	}
		catch(Exception e){
			
			e.printStackTrace();
		}
	}


	public void report_compare() {
		
		String dev ="";
		String test ="";
		
		try{
		 ArrayList<String> fileSheets = new ArrayList<String>();
		 
	
		

		 ArrayList Data01= new ArrayList();
		 
		 ArrayList mismatch_SrIDs= new ArrayList();
			
			
		 DataFormatter formatter = new DataFormatter();
			
			
			FileInputStream filein_summary = new FileInputStream("C://Users//EZVSXHA//workspace//AO-KPIl//src//test//resources//Summary_KPI_Comparison.xlsx");
			 Workbook wb_summary = new XSSFWorkbook(filein_summary);
			 
			 CreationHelper createHelper_summary = wb_summary.getCreationHelper();
				Sheet sheet_test = wb_summary.getSheetAt(0);
				Sheet sheet_dev = wb_summary.getSheetAt(1);
				
				for(int sum=1;sum<sheet_test.getLastRowNum();sum++){
					
			
					
					Row row_test = sheet_test.getRow(sum);
					Cell sr_ID_test=row_test.getCell(0);
					
					for(int sum_dev=1;sum_dev<sheet_dev.getLastRowNum();sum_dev++){
					
					Row row_dev = sheet_dev.getRow(sum_dev);
					if(row_dev!=null){
					Cell sr_ID_dev=row_dev.getCell(0);
					
					if(sr_ID_test.toString().equalsIgnoreCase(sr_ID_dev.toString())){
						
						for(int ip=1;ip<23;ip++){
							
							if(row_test.getCell(ip) !=null){
													 
							 Cell cell = row_test.getCell(ip);
								test=formatter.formatCellValue(cell);
							}
							
							
							
							if(row_dev.getCell(ip) !=null){
								
								// double valChck = row_dev.getCell(ip).getNumericCellValue();
								Cell cell = row_dev.getCell(ip);
								dev=formatter.formatCellValue(cell);
								
							
							}
						if(!dev.equalsIgnoreCase(test)){
							row_dev.createCell(ip).setCellValue("FALSE");
						
						}
					
						}
					}
					
					}
				}
				
			
			
			
	}
				FileOutputStream fileOut = new FileOutputStream("C://Users//EZVSXHA//workspace//AO-KPIl//src//test//resources//Summary_KPI_Comparison.xlsx");
				wb_summary.write(fileOut);
				  fileOut.close();
				  
		
		}	
	
		
		catch(Exception e){
			
			
			e.printStackTrace();
		}
	    
	
	}
	
}

*/