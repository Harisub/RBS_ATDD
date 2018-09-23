/*package plymp;




import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class xml {
public static String DBSDelivery=null;
public static String DBSDeliverySlot=null;
public static ArrayList<String> DBSDeliveryNumberlist = new ArrayList<String>();
public static ArrayList<String> DBSAvailableDeliverySlotNumberlist = new ArrayList<String>();
public static ArrayList<String> DBNumber = new ArrayList<String>();
public static ArrayList<Integer> reservationsRepititionNo = new ArrayList<Integer>();
public static int DBslotLinesRepititionNo;
public String CalculateFulfillment_Multi(String inputXML) {
String responseXML = null;
DocumentBuilder db;
try 
{
db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
InputSource inputSource = new InputSource();
String nameSpaceRemovedXML = removeNameSpace(inputXML);
inputSource.setCharacterStream(new StringReader(nameSpaceRemovedXML));
Document doc = db.parse(inputSource);
NodeList DBSSlotList = doc.getElementsByTagName("DBSSlot");
DBslotLinesRepititionNo = DBSSlotList.getLength();
for (int i=0; i<DBslotLinesRepititionNo; i++ )
{
Element DBSlotele = (Element) DBSSlotList.item(i);
NodeList DBSSlotIdentifier = DBSlotele.getElementsByTagName("DBSDeliveryNumber");
Element DBSDeliveryNumber = (Element) DBSSlotIdentifier.item(0);
DBSDeliveryNumberlist.add(getCharacterDataFromElement(DBSDeliveryNumber));
NodeList DBSSlotIdentifierAvSlot = DBSlotele.getElementsByTagName("DBSAvailableDeliverySlotNumber");
                Element DBSAvailableDeliverySlotNumber = (Element) DBSSlotIdentifierAvSlot.item(0);
DBSAvailableDeliverySlotNumberlist.add(getCharacterDataFromElement(DBSAvailableDeliverySlotNumber));
if(DBSDeliveryNumber.toString().equalsIgnoreCase(""))
{
DBSDeliveryNumberlist.add("");
}
if(DBSAvailableDeliverySlotNumber.toString().equalsIgnoreCase(""))
{
DBSAvailableDeliverySlotNumberlist.add("");
}
}
responseXML = BuildSuccessResponse();
} 
catch (ParserConfigurationException | SAXException | IOException e) 
{
responseXML = e.getMessage();
}
//ClearStrings();
return responseXML;
}
public static void ClearStrings()
{
DBSDelivery = null;
DBSDeliverySlot=null;
}
private static String BuildSuccessResponse()
{
String responseXML = null;
DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder docBuilder = null;
try 
{
docBuilder = docFactory.newDocumentBuilder();
} 
catch (ParserConfigurationException parserConfigurationException) 
{
responseXML = parserConfigurationException.getMessage();
return responseXML; 
}
Document buildDocument = docBuilder.newDocument();
buildDocument.setXmlStandalone(true);
Element rootElement = buildDocument.createElement("SOAP-ENV:Envelope");
rootElement.setAttribute("xmlns:SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/");
buildDocument.appendChild(rootElement);
Element soapHeader = buildDocument.createElement("SOAP-ENV:Header");
rootElement.appendChild(soapHeader);
Element soapBody = buildDocument.createElement("SOAP-ENV:Body");
rootElement.appendChild(soapBody);
Element DBSBookDeliveryResponse = buildDocument.createElement("tns:DBSBookDeliveryResponse");
DBSBookDeliveryResponse.setAttribute("xmlns:tns", "https://www.johnlewis.com/service/jld/FulfillmentService/");
soapBody.appendChild(DBSBookDeliveryResponse);
Element DBSBookDeliverySlots = buildDocument.createElement("DBSBookDeliverySlots");
DBSBookDeliveryResponse.appendChild(DBSBookDeliverySlots);
for(int i=0;i<DBslotLinesRepititionNo;i++){
Element DBSBookDeliverySlot = buildDocument.createElement("DBSBookDeliverySlot");
DBSBookDeliverySlots.appendChild(DBSBookDeliverySlot);
Element DBSDeliveryID = buildDocument.createElement("DBSDeliveryID");
DBSDeliveryID.appendChild(buildDocument.createTextNode("5436"));
DBSBookDeliverySlot.appendChild(DBSDeliveryID);
Element DeliveryCentreNumber = buildDocument.createElement("DeliveryCentreNumber");
DeliveryCentreNumber.appendChild(buildDocument.createTextNode("346"));
DBSBookDeliverySlot.appendChild(DeliveryCentreNumber);
Element SellingBranchForDelivery = buildDocument.createElement("SellingBranchForDelivery");
SellingBranchForDelivery.appendChild(buildDocument.createTextNode("678"));
DBSBookDeliverySlot.appendChild(SellingBranchForDelivery);
Element DeliveryJournalNumber = buildDocument.createElement("DeliveryJournalNumber");
DeliveryJournalNumber.appendChild(buildDocument.createTextNode("12345"));
DBSBookDeliverySlot.appendChild(DeliveryJournalNumber);

Element DBSSlotIdentifier = buildDocument.createElement("DBSSlotIdentifier");
DBSBookDeliverySlot.appendChild(DBSSlotIdentifier);
Element DBSDeliveryNumber = buildDocument.createElement("DBSDeliveryNumber");
DBSDeliveryNumber.appendChild(buildDocument.createTextNode(DBSDeliveryNumberlist.get(i)));
DBSSlotIdentifier.appendChild(DBSDeliveryNumber);

Element DBSAvailableDeliverySlotNumber = buildDocument.createElement("DBSAvailableDeliverySlotNumber");
DBSAvailableDeliverySlotNumber.appendChild(buildDocument.createTextNode(DBSAvailableDeliverySlotNumberlist.get(i)));
DBSSlotIdentifier.appendChild(DBSAvailableDeliverySlotNumber);

}
responseXML = getStringFromDocument(buildDocument);
return responseXML;
}
private static String getStringFromDocument(Document buildDocument)
{
   try
   {
      DOMSource domSource = new DOMSource(buildDocument);
      StringWriter writer = new StringWriter();
      StreamResult result = new StreamResult(writer);
      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer transformer = tf.newTransformer();
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
      transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
      transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.transform(domSource, result);
      return writer.toString();
   }
   catch(TransformerException ex)
   {
      return ex.getMessage();
   }
}
protected static String GetCurrentDate()
{
Date date = new Date();
GregorianCalendar gregorianCalendar;
gregorianCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
gregorianCalendar.setTime(date);
try 
{
return (DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar)).toString();
} 
catch (DatatypeConfigurationException e) 
{
return e.getMessage();
}
}
public static String getCharacterDataFromElement(Element e) 
{
Node child = e.getFirstChild();
if (child instanceof CharacterData) 
{
CharacterData cd = (CharacterData) child;
return cd.getData();
}
return "";
}
protected String removeNameSpace(String inputXML)
{
Transformer xformer = null;
try 
{
xformer = TransformerFactory.newInstance().newTransformer(new javax.xml.transform.stream.StreamSource(textString()));
}
catch (TransformerConfigurationException| TransformerFactoryConfigurationError e) 
{
e.printStackTrace();
}
StringWriter writer = new StringWriter();
try 
{
StringReader reader = new StringReader(inputXML);
xformer.transform(new javax.xml.transform.stream.StreamSource(reader), new StreamResult(writer));
}
catch (TransformerException e) 
{
e.printStackTrace();
}
return writer.toString();

}
public static StringReader textString() 
{
StringReader nameSpaceRemover = new StringReader(
"<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"> "
+ "<xsl:output method=\"xml\" indent=\"no\"/>"
+ "<xsl:template match=\"/|comment()|processing-instruction()\">"
+ "<xsl:copy>"
+ "<xsl:apply-templates/>"
+ "</xsl:copy>"
+ "</xsl:template>"
+ "<xsl:template match=\"*\"> "
+ "<xsl:element name=\"{local-name()}\"> "
+ "<xsl:apply-templates select=\"@*|node()\"/>"
+ "</xsl:element> "
+ "</xsl:template>"
+ "<xsl:template match=\"@*\">"
+ "<xsl:attribute name=\"{local-name()}\">"
+ "<xsl:value-of select=\".\"/>"
+ "</xsl:attribute>"
+ "</xsl:template>"
+ "</xsl:stylesheet>");
return nameSpaceRemover;
}

}
*/