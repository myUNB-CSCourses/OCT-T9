package project.team9;
import java.io.File;  
import java.io.FileInputStream;
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

public class TalleyBookReaderWriter {

	public void readTalleyCountDay(int day, String month){
		Row row;
		Cell cell;
		Iterator<Cell> cellIterator;
		int period = 0;
		int dateIndex = 0;
		int monthlyIndex = 0;
		int totalIndex = 0;
		int remainingIndex = 0;
		int priorityIndex = 0;
		
		int monthly;
		int total;
		int remaining;
		int priority;
		int tally;
		
		try {
			ConfigFileReader config = new ConfigFileReader();
			File file = new File(config.configRead("ON_CALL_TALLIES"));
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet(month);
			Iterator<Row> itr = sheet.iterator();

			//Configurates the index variables so that the relevant information can easily be retrieved
			int[] vars = configurate(sheet, day);
			monthlyIndex = vars[0];
			totalIndex = vars[1];
			remainingIndex = vars[2];
			priorityIndex = vars[3];
			dateIndex = vars[4];
			System.out.println("monthlyIndex: " + monthlyIndex);
			System.out.println("totalIndex: " + totalIndex);
			System.out.println("remainingIndex: " + remainingIndex);
			System.out.println("priorityIndex: " + priorityIndex);
			System.out.println("dateIndex: " + dateIndex + "\n\n\n");
			
			row = itr.next();
			while(itr.hasNext()) {
				row = itr.next();
				cellIterator = row.cellIterator();
				String title = row.getCell(1).getStringCellValue();
					
				//If it reaches the summary at the end of the file
				if (title.equals("Period 1") || title.equals("Period 2") || title.equals("Period 3") || title.equals("Period 4")) {
					if (period == 4) {
						System.out.println("\nTOTALS:");
						System.out.println(row.getCell(1).getStringCellValue() + ": " + row.getCell(2).getNumericCellValue());
						row = itr.next();
						System.out.println(row.getCell(1).getStringCellValue() + ": " + row.getCell(2).getNumericCellValue());
						row = itr.next();
						System.out.println(row.getCell(1).getStringCellValue() + ": " + row.getCell(2).getNumericCellValue());
						row = itr.next();
						System.out.println(row.getCell(1).getStringCellValue() + ": " + row.getCell(2).getNumericCellValue());
						row = itr.next();
						System.out.println(row.getCell(1).getStringCellValue() + ": " + row.getCell(2).getNumericCellValue());
						wb.close();
						return;
					}
					period++;
					System.out.println("------\nPERIOD " + period + "\n------\n");
					
				//Prints out a teacher entry
				} else if (title != null && title.length() != 0) {
					try {
						tally = (int)row.getCell(dateIndex).getNumericCellValue();
					}catch (Exception e) {
						tally = 0;
					}
					
					monthly = (int)row.getCell(monthlyIndex).getNumericCellValue();
					total = (int)row.getCell(totalIndex).getNumericCellValue();
					remaining = (int)row.getCell(remainingIndex).getNumericCellValue();
					priority = (int)row.getCell(priorityIndex).getNumericCellValue();
					
					System.out.println("teacher: " + title + ":");
					System.out.println("period " + period + ": " + tally);
					System.out.println("Monthly Total: " + monthly);
					System.out.println("Total On Calls: " + total);
					System.out.println("Remaining: " + remaining);
					System.out.println("Priority: " + priority + "\n");
				}
			}
			wb.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Configurates the index variables so that the relevant information can easily be retrieved
	public static int[] configurate(XSSFSheet sheet, int day) {
		int[] vars = new int[5];
		vars[4] = 0;
		Row row = sheet.getRow(0);
		Iterator<Cell> cellIterator = row.cellIterator();

		int counter = 0;
		while(cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			System.out.println(counter ++);
			switch (cell.getCellType()) {
			case STRING:
				System.out.println("cell[" + cell.getColumnIndex() + "]: (" + cell.getStringCellValue() + ")");
				switch (cell.getStringCellValue()){
				case "Monthly \nEnd Total":
					vars[0] = cell.getColumnIndex();
					if (vars[4] != 0) {
						return vars;
					}
					break;
				case "Total \nOn Calls":
					vars[1] = cell.getColumnIndex();
					break;
				case "Remaining":
					vars[2] = cell.getColumnIndex();
					break;
				case "High Priority":
					vars[3] = cell.getColumnIndex();
					row = sheet.getRow(1);
					cellIterator = row.cellIterator();
					break;
				default:
					break;
				}
				break;
			case NUMERIC:
				System.out.println("cell[" + cell.getColumnIndex() + "]: (" + cell.getNumericCellValue() + ")");
				if (cell.getNumericCellValue() == day) {
					vars[4] = cell.getColumnIndex();
				}
				break;
			default:
				break;
			}
		}
		return vars;
	}
}