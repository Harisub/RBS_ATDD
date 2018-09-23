package plymp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONReportcmp {
	public static void main(String args[]) throws IOException {
		String JSON_PATH = "C:\\Users\\EZVSXHA\\Downloads\\1515125347576-1515125347577-1515211747577.json";
		JSONObject obj = new JSONObject(FileUtils.readFileToString(new File(
				JSON_PATH)));
		JSONArray array = (JSONArray) (obj.get("results"));
		String xlFile = "C://Users//EZVSXHA//workspace//AO-KPIl//src//test//resources//1515125347576-1515125347577-1515211747577_test.xlsx";
		File f = new File(xlFile);
		if (f.exists()) {
			f.deleteOnExit();
			f.createNewFile();
		}else{
			f.createNewFile();
		}
		Workbook wb = new XSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		Sheet sheet = wb.createSheet("MY_SHEET");

		// set header in first row
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("SourceId");
			JSONObject srcObjs = array.getJSONObject(0);			
			JSONArray kpiArrays = (JSONArray) (srcObjs.get("kpi"));
			for (int j = 0; j < kpiArrays.length(); j++) {
				JSONObject kpiObj = kpiArrays.getJSONObject(j);
				headerRow.createCell(j+1).setCellValue(
						createHelper.createRichTextString(kpiObj.get(
								"name").toString()));
			}
		
		

		for (int i = 0; i < array.length(); i++) {
			JSONObject srcObj = array.getJSONObject(i);
			System.out.println("source id :: " + srcObj.get("sourceId"));
			Row row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue(
					createHelper.createRichTextString(srcObj.get("sourceId")
							.toString()));
			JSONArray kpiArray = (JSONArray) (srcObj.get("kpi"));
			for (int j = 0; j < kpiArray.length(); j++) {
				JSONObject kpiObj = kpiArray.getJSONObject(j);
				if (kpiObj.has("percentage")) {
					System.out.println("name :: " + kpiObj.get("name")
							+ " % :: " + kpiObj.get("percentage"));
					row.createCell(j + 1).setCellValue(
							createHelper.createRichTextString(kpiObj.get(
									"percentage").toString()));
				} else {
					System.out.println("name :: " + kpiObj.get("name")
							+ " % :: na");
					row.createCell(j + 1).setCellValue(
							createHelper.createRichTextString("na"));
				}

			}
		}
		FileOutputStream fileOut = new FileOutputStream(xlFile);
		wb.write(fileOut);
		fileOut.close();
	}

}
