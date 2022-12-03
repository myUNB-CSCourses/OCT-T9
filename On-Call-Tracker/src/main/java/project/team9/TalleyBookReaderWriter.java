package project.team9;
import java.io.File;  
import java.io.FileInputStream;
import java.util.ArrayList;
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
			File file = new File("C:\\Users\\marno\\OneDrive\\Uni\\Year 2\\Sem 1\\CS2043\\repo\\OCT-T9\\On-Call-Tracker\\src\\inputs\\On-call_Tallies.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet(month);
			Iterator<Row> itr = sheet.iterator();
			
			while(itr.hasNext()) {
				row = itr.next();
				cellIterator = row.cellIterator();
				
				if (configuration) {
					outerloop:
					while(cellIterator.hasNext()) {
						cell = cellIterator.next();
						switch (cell.getCellType()) {
						case STRING:
							switch (cell.getStringCellValue()){
							case "Monthly":
								monthlyIndex = cell.getColumnIndex();
								if (dateIndex != 0) {
									configuration = false;
								}
								break;
							case "Total":
								totalIndex = cell.getColumnIndex();
								break;
							case "Remaining":
								remainingIndex = cell.getColumnIndex();
								break;
							case "High Priority":
								priorityIndex = cell.getColumnIndex();
								break;
							default:
								break;
							}
							break;
						case NUMERIC:
							if (cell.getNumericCellValue() == day) {
								dateIndex = cell.getColumnIndex();
								break outerloop;
							}
							break;
						default:
							break;
						}
					}
				} else {
					String title = row.getCell(1).getStringCellValue();
					if (title.equals("Period 1") || title.equals("Period 2") || title.equals("Period 3") || title.equals("Period 4")) {
						if (period == 4) {
							System.out.println("\nTOTALS:");
							row = itr.next();
							System.out.println("Period 1" + row.getCell(2).getNumericCellValue());
							row = itr.next();
							System.out.println("Period 2" + row.getCell(2).getNumericCellValue());
							row = itr.next();
							System.out.println("Period 3" + row.getCell(2).getNumericCellValue());
							row = itr.next();
							System.out.println("Period 4" + row.getCell(2).getNumericCellValue());
							break;
						}
						period++;
						System.out.println("------\nPERIOD " + period + "\n------\n");
					} else if (title != null) {
						System.out.println("teacher: " + title + ":");
						System.out.println("period " + period + ": " + (int)row.getCell(dateIndex).getNumericCellValue());
						System.out.println("Monthly Total: " + (int)row.getCell(monthlyIndex).getNumericCellValue());
						System.out.println("Total On Calls: " + (int)row.getCell(totalIndex).getNumericCellValue());
						System.out.println("Remaining: " + (int)row.getCell(remainingIndex).getNumericCellValue());
						System.out.println("Priority: " + (int)row.getCell(priorityIndex).getNumericCellValue() + "\n");
					}
				}
			}
			wb.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	/*
	@SuppressWarnings("unlikely-arg-type")
	public ArrayList<Teacher> readTalleyCount(String path) {
		
		boolean configuration = true;
		int initialsCollumnNum = 0;
		int endCollNum = 0;
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		ArrayList<String> datesD = new ArrayList<String>();
		ArrayList<Integer> datesN = new ArrayList<Integer>();
		Row row;
		Cell cell;
		Iterator<Cell> cellIterator;
		
		try{  
			File file = new File("C:\\Users\\marno\\OneDrive\\Uni\\Year 2\\Sem 1\\CS2043\\D2\\InOutExcel MYL\\ExcelFile.xlsx");   //creating a new file instance. Add path to Excel File
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);	//get excel file
			XSSFSheet sheet = wb.getSheetAt(0);     //get sheet
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file
			
			while (itr.hasNext()){
				
				if (configuration) {
					row = itr.next();
					cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						cell = cellIterator.next();
						switch (cell.getCellType()) {
						case STRING:
							if (cell.equals("OFFICE USE")) {
								initialsCollumnNum = cell.getColumnIndex();
							} else if (cell.equals("Monthly")) {
								endCollNum = cell.getColumnIndex();
							} else if (cell.equals("M") || cell.equals("T") || cell.equals("W") || cell.equals("Th") || cell.equals("F")) {
								datesD.add(cell.getStringCellValue());
							} else if (cell.equals("Max")) {
								configuration = false;
							}
							break;
						case NUMERIC:
							datesN.add((int) cell.getNumericCellValue());
							break;
						default:
							break;
						}
					}
				} else {
					row = itr.next();	//get row
					if (row.getCell(initialsCollumnNum).equals("P1 High Priority")) {
						//new period
						//Max value next
					}
					String teacherName = row.getCell(initialsCollumnNum).getStringCellValue();
					int monthly = (int)row.getCell(endCollNum).getNumericCellValue();
					int onCalls = (int)row.getCell(endCollNum+1).getNumericCellValue();
					
					
					cellIterator = row.cellIterator();   //iterating over each column
					while (cellIterator.hasNext()){
						cell = cellIterator.next();	//get cell
						switch (cell.getCellType()){
						case NUMERIC:    //field that represents number cell type
							if (cell.getColumnIndex() < endCollNum) {
								datesN.add((int) cell.getNumericCellValue());
							}
							break;
						default:
						}
					}
					System.out.println("");
				}
				
			}
			wb.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return teachers;
		
	}
	
	public void writeTalleyCount(String path, String fileName) {}*/
}
