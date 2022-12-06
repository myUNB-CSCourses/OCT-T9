package project.team9;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CurriculumSummaryReader{

	
	@SuppressWarnings("deprecation")
	public ArrayList<Course> curriculumReader() {
		ArrayList<Course> courses = new ArrayList<Course>();
		String category = null;
		String code = null;
		String teachable = null;
		String grade = null;
		String pathway = null;
		
        try{
        	ConfigFileReader config = new ConfigFileReader();
        	
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(config.configRead("CURRICULUM_SUMMARY")));
            
            Iterator<Row> iterator =  workbook.getSheetAt(0).iterator();
            while (iterator.hasNext()){
                int counter = 0;
                Row row = iterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                
                //since the program is reading the header, we can read the excel file then store each column as an object and 
                //loop through the array filled with the columns and we an ignore the first index of the array and 
                //print the rest.
                
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    	if(cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals("Category")) {
                    		break;
                    	}
                    	else if (cell.getCellType() == CellType.STRING && counter == 0) {
                            category = cell.getStringCellValue();
                            counter++;
                        }else if (cell.getCellType() == CellType.STRING && counter == 1) {
                            code = cell.getStringCellValue();
                            counter++;
                        }else if (cell.getCellType() == CellType.STRING && counter == 2) {
                            teachable = cell.getStringCellValue();
                            counter++;
                        }else if (cell.getCellType() == CellType.NUMERIC && counter == 3) {
                            cell.setCellType(CellType.STRING);
                            grade = cell.getStringCellValue();
                            counter++;
                        }else if (cell.getCellType() == CellType.STRING && counter == 4) {
                            pathway = cell.getStringCellValue();
                            counter++;
                        }
                    }
	                if(category != null) {
	                	courses.add(new Course(category, code, teachable, grade, pathway));
	                }
                }
        }
        catch(Exception e){
           System.out.println("Exception");
           e.printStackTrace();
        }
        return courses;
	}
}