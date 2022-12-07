package project.team9;
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

public class TalleyBookReaderWriter {

	public ArrayList<ArrayList<TallyBook>> readTalleyCountDay(int day, String month){
		ArrayList<ArrayList<TallyBook>> allPeriods = new ArrayList<ArrayList<TallyBook>>();
		ArrayList<TallyBook> period1 = new ArrayList<TallyBook>();
		ArrayList<TallyBook> period2 = new ArrayList<TallyBook>();
		ArrayList<TallyBook> period3 = new ArrayList<TallyBook>();
		ArrayList<TallyBook> period4 = new ArrayList<TallyBook>();
		Row row;
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
//			System.out.println("monthlyIndex: " + monthlyIndex);
//			System.out.println("totalIndex: " + totalIndex);
//			System.out.println("remainingIndex: " + remainingIndex);
//			System.out.println("priorityIndex: " + priorityIndex);
//			System.out.println("dateIndex: " + dateIndex + "\n\n\n");
			
			row = itr.next();
			while(itr.hasNext()) {
				row = itr.next();
				String title = row.getCell(1).getStringCellValue();
					
				//If it reaches the summary at the end of the file
				if (title.equals("Period 1") || title.equals("Period 2") || title.equals("Period 3") || title.equals("Period 4")) {
					if (period == 4) {
//						System.out.println("\nTOTALS:");
//						System.out.println(row.getCell(1).getStringCellValue() + ": " + row.getCell(2).getNumericCellValue());
						row = itr.next();
//						System.out.println(row.getCell(1).getStringCellValue() + ": " + row.getCell(2).getNumericCellValue());
						row = itr.next();
//						System.out.println(row.getCell(1).getStringCellValue() + ": " + row.getCell(2).getNumericCellValue());
						row = itr.next();
//						System.out.println(row.getCell(1).getStringCellValue() + ": " + row.getCell(2).getNumericCellValue());
						row = itr.next();
//						System.out.println(row.getCell(1).getStringCellValue() + ": " + row.getCell(2).getNumericCellValue());
						wb.close();
						return null;
					}
					period++;
//					System.out.println("------\nPERIOD " + period + "\n------\n");
					
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
					
//					System.out.println("teacher: " + title + ":");
//					System.out.println("period " + period + ": " + tally);
//					System.out.println("Monthly Total: " + monthly);
//					System.out.println("Total On Calls: " + total);
//					System.out.println("Remaining: " + remaining);
//					System.out.println("Priority: " + priority + "\n");
					if(period == 1) {
						period1.add(new TallyBook(title, priority, remaining, total, monthly));
					}
					else if(period == 2) {
						period2.add(new TallyBook(title, priority, remaining, total, monthly));
					}
					else if(period == 3) {
						period3.add(new TallyBook(title, priority, remaining, total, monthly));
					}
					else if(period == 4) {
						period4.add(new TallyBook(title, priority, remaining, total, monthly));
					}
					
				}
			}
			allPeriods.add(period1);
			allPeriods.add(period2);
			allPeriods.add(period3);
			allPeriods.add(period4);
			wb.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return allPeriods;
	}
	

	//Changes the tally of the selected teacher if they are on the tally book.
	//Returns true if the teacher was found and their tally could be changed.
	//Returns false if the tally couldn't be changed or the teacher couldn't be found
	public boolean updateTeacherTally(String teacherNameIn, String monthIn, int dayIn, int periodIn, boolean tallyIn) {
		String period = "Period " + periodIn;
		String teacherName = teacherNameIn;
		String month = monthIn;
		int day = dayIn;
		boolean tally = tallyIn;
		
		Row row;
		int dateIndex = 0;
		int monthlyIndex = 0;
		int totalIndex = 0;
		int remainingIndex = 0;
		int priorityIndex = 0;
		int totalsRowIndex = 0;
		
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
			totalsRowIndex = vars[5];
			
			row = itr.next();
			String title = row.getCell(1).getStringCellValue();
			//loops until it finds the period
			while(itr.hasNext() && !title.equals(period)) {
				row = itr.next();
				title = row.getCell(1).getStringCellValue();
			}
			//Checks if it found the correct period
			if (title.equals(period)) {
				//loops until it finds the teacher
				//Will then edit the necessary tally
				while(itr.hasNext()) {
					row = itr.next();
					title = row.getCell(1).getStringCellValue();
					if (title.equals(teacherName)) {
						System.out.println("==========\nFound Teacher: " + title + "[" + dateIndex + ", " + row.getRowNum() + "]");
						
						if (row.getCell(dateIndex) == null) {
							System.out.println("Cell is null. no more");
							row.createCell(dateIndex).setCellValue(0);
						}
						if (tally && row.getCell(remainingIndex).getNumericCellValue() > 0) {
							System.out.println("making 1");
							row.getCell(dateIndex).setCellValue(1);
							System.out.println("cell: " + row.getCell(dateIndex).getNumericCellValue());
						} else if (!tally) {
							System.out.println("Trying to make blank");
							row.createCell(dateIndex);
						} else {
							System.out.println("Can't make 1");
						}
						break;
					}
				}
				
				//Updates the cells in the sheet that would've possibly changed after the alteration of the sheet
				XSSFFormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
				formulaEvaluator.evaluateFormulaCell(row.getCell(remainingIndex));
				formulaEvaluator.evaluateFormulaCell(row.getCell(monthlyIndex));
				formulaEvaluator.evaluateFormulaCell(row.getCell(totalIndex));
				formulaEvaluator.evaluateFormulaCell(row.getCell(priorityIndex));
				row = sheet.getRow(totalsRowIndex);
				formulaEvaluator.evaluateFormulaCell(sheet.getRow(totalsRowIndex).getCell(monthlyIndex));
				formulaEvaluator.evaluateFormulaCell(sheet.getRow(totalsRowIndex).getCell(totalIndex));
				formulaEvaluator.evaluateFormulaCell(sheet.getRow(totalsRowIndex+1).getCell(2));
				formulaEvaluator.evaluateFormulaCell(sheet.getRow(totalsRowIndex+2).getCell(2));
				formulaEvaluator.evaluateFormulaCell(sheet.getRow(totalsRowIndex+3).getCell(2));
				formulaEvaluator.evaluateFormulaCell(sheet.getRow(totalsRowIndex+4).getCell(2));
				formulaEvaluator.evaluateFormulaCell(sheet.getRow(totalsRowIndex+5).getCell(2));
				
				
				fis.close();
				FileOutputStream fos = new FileOutputStream(file);
				wb.write(fos);
				fos.close();
				
			}
			wb.close();
		} catch (Exception e) {
						e.printStackTrace();
		}
		return false;
	}
	
	//Configurates the index variables so that the relevant information can easily be retrieved
	public static int[] configurate(XSSFSheet sheet, int day) {
		int[] vars = new int[6];
		vars[4] = 0;
		Row row = sheet.getRow(0);
		Iterator<Row> itr = sheet.rowIterator();
		Iterator<Cell> cellIterator = row.cellIterator();

		while(cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			//System.out.println(counter ++);
			switch (cell.getCellType()) {
			case STRING:
				//System.out.println("cell[" + cell.getColumnIndex() + "]: (" + cell.getStringCellValue() + ")");
				switch (cell.getStringCellValue()){
				case "Monthly \nEnd Total":
					vars[0] = cell.getColumnIndex();
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
				//System.out.println("cell[" + cell.getColumnIndex() + "]: (" + cell.getNumericCellValue() + ")");
				if (cell.getNumericCellValue() == day) {
					vars[4] = cell.getColumnIndex();
				}
				break;
			default:
				break;
			}
		}
		
		//Saves the row index value of the "Totals" block at the very end of the sheet
		boolean finalStretch = false;
		while (itr.hasNext()) {
			row = itr.next();
			try {
				switch (row.getCell(1).getCellType()) {
				case STRING:
					if (row.getCell(1).getStringCellValue().equals("Period 1")) {
						if (finalStretch) {
							vars[5] = row.getRowNum() -1;
						} else {
							finalStretch = true;
						}
					}
					break;
				default:
					break;
				}
			} catch (Exception e){
			}
		}
		return vars;
	}
}