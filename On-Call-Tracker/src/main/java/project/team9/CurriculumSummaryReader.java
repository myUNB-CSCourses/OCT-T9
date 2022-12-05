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
	public void curriculumReader() {
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("/Users/dineth/repos/OCT-T9/On-Call-Tracker/src/inputs/CurriculumSummary.xlsx"));
            Iterator<Row> iterator =  workbook.getSheetAt(0).iterator();
            int  i = 0;
            while (iterator.hasNext()){
                int counter = 0;
                Row row = iterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                System.out.println("Course " + i + ": ");
                i++;
                
                //since the program is reading the header, we can read the excel file then store each column as an object and 
                //loop through the array filled with the columns and we an ignore the first index of the array and 
                //print the rest.
                
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                        if (cell.getCellType() == CellType.STRING && counter == 0) {
                            System.out.println("Category: " + cell.getStringCellValue());
                            counter++;
                        }else if (cell.getCellType() == CellType.STRING && counter == 1) {
                            System.out.println("Course Code: " + cell.getStringCellValue());
                            counter++;
                        }else if (cell.getCellType() == CellType.STRING && counter == 2) {
                            System.out.println("Teachable: " + cell.getStringCellValue());
                            counter++;
                        }else if (cell.getCellType() == CellType.NUMERIC && counter == 3) {
                            cell.setCellType(CellType.STRING);
                            System.out.println("Grade: " + cell.getStringCellValue());
                            counter++;
                        }else if (cell.getCellType() == CellType.STRING && counter == 4) {
                            System.out.println("Pathway: " + cell.getStringCellValue());
                            counter++;
                        }
                    }
                System.out.println("-----------------------------------");
                }
        }
        catch(Exception e){
           System.out.println("Exception");
           e.printStackTrace();
        }
        
	}
}