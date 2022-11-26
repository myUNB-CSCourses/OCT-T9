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
	private String line;
	private int numCell;
	private static String[] columns = {"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
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
	
public void workbookFill(ArrayList<Teacher> teachers) {
	
}
public void workbookReader(){
	
	try{  
		File file = new File("C:\\Users\\marno\\OneDrive\\Uni\\Year 2\\Sem 1\\CS2043\\D2\\InOutExcel MYL\\ExcelFile.xlsx");   //creating a new file instance. Add path to Excel File
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
				case Cell.CELL_TYPE_STRING:    //field that represents string cell type
					System.out.print(cell.getStringCellValue() + "\t\t\t");
					break;
				case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
					System.out.print(cell.getNumericCellValue() + "\t\t\t");
					break;
				default:
				}
			}
			System.out.println("");  
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
	}
}


