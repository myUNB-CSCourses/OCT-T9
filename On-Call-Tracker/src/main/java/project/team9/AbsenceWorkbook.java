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
	private String sheet;
	private int numCell;
@SuppressWarnings("deprecation")

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
	

public void workbookReader(String dayIn){
	day = dayIn;
	
	try{  
		File file = new File("C:\\Fall 2022\\cs2043\\Git Repo\\OCT-T9\\On-Call-Tracker\\src\\inputs\\Absences.xlsx");   //creating a new file instance. Add path to Excel File
		FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
		//creating Workbook instance that refers to .xlsx file  
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
		Iterator<Row> itr = sheet.iterator();    //iterating over excel file
		while (itr.hasNext()){
			Row row = itr.next();
			Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
			while (cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				switch (cell.getCellType()){
				case STRING://field that represents string cell type
					if(cell.getStringCellValue().equals(day)) {
                		lastColumn = cell.getColumnIndex();
					System.out.print(cell.getStringCellValue() + "\t\t\t");
					break;
				}
				
			}
			System.out.println("");  
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
	}
public void workbookWriter() {
	
}
public boolean checkSheetName(String sheetIn) {
	if()
}
}


