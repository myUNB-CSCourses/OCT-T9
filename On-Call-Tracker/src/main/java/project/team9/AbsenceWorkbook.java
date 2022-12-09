package project.team9;

import java.io.File;  
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

public class AbsenceWorkbook{
	private String day;
	private String week;

	public void workbookGen() {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Absences");
	
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 17);
		headerFont.setColor(IndexedColors.RED.getIndex());
	
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
	
		Row headerRow = sheet.createRow(0);
	
	}
	

	public ArrayList<AbsenceRecord> workbookReader(String dayIn, String weekIn){
		ArrayList<AbsenceRecord> record = new ArrayList<AbsenceRecord>();
		day = dayIn;
		week = weekIn;
		
		int period1Ind = 0;
		int period2Ind = 0;
		int period3Ind = 0;
		int period4Ind = 0;
		
		
		
		try{
			ConfigFileReader config = new ConfigFileReader();
			File file = new File(config.configRead("ABSENCE_WORKBOOK"));   //creating a new file instance. Add path to Excel File
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet(week);     //creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file
		
			int[] vars = configurate(sheet, day);
			
			period1Ind = vars[0];
			period2Ind = vars[1];
			period3Ind = vars[2];
			period4Ind = vars[3];
			
//			System.out.println("Day: " + day);
			Row row =itr.next();
			row =itr.next();
			while (itr.hasNext()){
				String period1 = null;
				String period2 = null;
				String period3 = null;
				String period4 = null;
				row = itr.next();
				String teacherName = row.getCell(0).getStringCellValue();
				if(teacherName.length() > 0) {
//					System.out.println("Teacher: " + teacherName);
	
					try {
						period1 = row.getCell(period1Ind).getStringCellValue();
					}catch (Exception e) {
						System.out.println("Period 1: " + " ");
					}
					try {
						period2 = row.getCell(period2Ind).getStringCellValue();
					}catch (Exception e) {
						System.out.println("Period 2: " + " ");
					}
					try {
						period3 =  row.getCell(period3Ind).getStringCellValue();	
					}catch (Exception e) {
						System.out.println("Period 3: " + " ");
					}
					try {
						period4 =  row.getCell(period4Ind).getStringCellValue();	
					}catch (Exception e) {
						System.out.println("Period 4: " + " ");
					}
				}	
				if(period1 != null || period2 != null || period3 != null || period4 != null) {
					if(period1 == null) {
						period1 = "NA";
					}
					if(period2 == null) {
						period2 = "NA";
					}
					if(period3 == null) {
						period3 = "NA";
					}
					if(period4 == null) {
						period4 = "NA";
					}
					record.add(new AbsenceRecord(teacherName, day, week, period1, period2, period3, period4));
				}
			}
			wb.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return record;
	}

	public static int[] configurate(XSSFSheet sheet, String day) {
	    int[] vars = new int[4];
	    Row row = sheet.getRow(0);
	    Iterator<Cell> cellIterator = row.cellIterator();
	    while(cellIterator.hasNext()) {
	        Cell cell = cellIterator.next();
	        switch (cell.getCellType()) {
	        case STRING:
	            if (cell.getStringCellValue().equals(day)) {
	                vars[0] = cell.getColumnIndex();
	                vars[1] = vars[0]+1;
	                vars[2] = vars[1]+1;
	                vars[3] = vars[2]+1;
	               return vars;
	            }
	        default:
	            break;
	        }
	    }
	    return vars;
	}
}