package project.team9;

import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CurriculumSummaryReader{
    public static void main(String[] args){

        try{
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("C:\\Program Files\\Java\\jdk1.8.0_241\\bin\\readExcelFiles\\CurriculumSummary.xlsx"));
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
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING && counter == 0) {
                            System.out.println("Category: " + cell.getStringCellValue());
                            counter++;
                        }else if (cell.getCellType() == Cell.CELL_TYPE_STRING && counter == 1) {
                            System.out.println("Course Code: " + cell.getStringCellValue());
                            counter++;
                        }else if (cell.getCellType() == Cell.CELL_TYPE_STRING && counter == 2) {
                            System.out.println("Teachable: " + cell.getStringCellValue());
                            counter++;
                        }else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && counter == 3) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            System.out.println("Grade: " + cell.getStringCellValue());
                            counter++;
                        }else if (cell.getCellType() == Cell.CELL_TYPE_STRING && counter == 4) {
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