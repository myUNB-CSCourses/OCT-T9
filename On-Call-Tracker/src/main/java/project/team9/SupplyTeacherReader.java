package project.team9;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SupplyTeacherReader {
	public ArrayList<SupplyTeacher> readSupplyTeacher(String day, String week){
		ArrayList<SupplyTeacher> sTeacher = new ArrayList<SupplyTeacher>();
		int column = 100;
	
		
		try {
			ConfigFileReader config = new ConfigFileReader();
			File file = new File(config.configRead("SUPPLY_LIST"));
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet(week);
			Iterator<Row> itr = sheet.iterator();
			
			while(itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
	                case STRING:
	                	if(cell.getStringCellValue().equals(day)) {
	                		column = cell.getColumnIndex();
	                	}
	                	else if(cell.getColumnIndex() == column) {
	                		sTeacher.add(new SupplyTeacher(cell.getStringCellValue()));
	                	}
	                    break;
	                default:
				}
				}
					
				
			}
			wb.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return sTeacher;
	}
}
