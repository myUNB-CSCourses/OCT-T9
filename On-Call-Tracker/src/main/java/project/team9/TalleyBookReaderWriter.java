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
		boolean configuration = true;
		Row row;
		Cell cell;
		Iterator<Cell> cellIterator;
		int period = 0;
		int dateIndex = 0;
		int monthlyIndex = 0;
		int totalIndex = 0;
		int remainingIndex = 0;
		int priorityIndex = 0;
		
		try {
			File file = new File("C:\\Users\\marno\\OneDrive\\Uni\\Year 2\\Sem 1\\CS2043\\sandbox\\test\\src\\input\\On-call_Tallies.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet(month);
			Iterator<Row> itr = sheet.iterator();
			
			while(itr.hasNext()) {
				row = itr.next();
				cellIterator = row.cellIterator();
				if (configuration) {
					int[] vars = configurate(sheet, day);
					configuration = false;
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
				} else {
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
						System.out.println("teacher: " + title + ":");
						try {
							System.out.println("period " + period + ": " + (int)row.getCell(dateIndex).getNumericCellValue());
						}catch (Exception e) {
							System.out.println("period " + period + ": 0");
						}
						
						System.out.println("Monthly Total: " + (int)row.getCell(monthlyIndex).getNumericCellValue());
						System.out.println("Total On Calls: " + (int)row.getCell(totalIndex).getNumericCellValue());
						System.out.println("Remaining: " + (int)row.getCell(remainingIndex).getNumericCellValue());
						System.out.println("Priority: " + (int)row.getCell(priorityIndex).getNumericCellValue() + "\n");
					}
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
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