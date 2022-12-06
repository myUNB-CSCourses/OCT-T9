package project.team9;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MasterScheduleReader {
	@SuppressWarnings("deprecation")
	public ArrayList<RegularTeacher> masterReader(ArrayList<Course> curr) {
		
		ArrayList<RegularTeacher> teachers = new ArrayList<RegularTeacher>();
		Schedule schedule;
	    Course p1;
	    Course p2;
	    Course p3;
	    Course p4;
	    
	    
		String teacherName = null;
	    String period1 = null;
	    String period2 = null;
	    String period3 = null;
	    String period4 = null;
	    String p1Room = null;
	    String p2Room = null;
	    String p3Room = null;
	    String p4Room = null;
	    
		try {
				ConfigFileReader config = new ConfigFileReader();
				int lastColumn = 100;
				boolean end = true;
	            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(config.configRead("MASTER_SCHEDULE")));
	            Iterator<Row> iterator =  workbook.getSheetAt(0).iterator();
	            Row row = iterator.next();
	            while (iterator.hasNext() && end) {
	            	
	                row = iterator.next();
	                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
	                
	                while (cellIterator.hasNext()) {
	                	
	                    
	                    Cell cell = cellIterator.next();
	                    switch (cell.getCellType()) {
		                    case STRING:
		                    	if(cell.getStringCellValue().equals("M= Special Education (Monitoring)")) {
		                    		lastColumn = cell.getColumnIndex();
		                    		break;
		                    	}
		                    	else if(cell.getColumnIndex() == lastColumn || cell.getColumnIndex() == lastColumn+1) {
		                    		break;
		                    	}
		                    	else if(cell.getStringCellValue().equals("End")) {
		                    		end = false;
		                    		break;
		                    	}
		                    	else if(cell.getColumnIndex() == 0) {
		                    		teacherName = cell.getStringCellValue();
		                    	}
		                    	else if(cell.getColumnIndex() == 1) {
		                    		period1 = cell.getStringCellValue();
		                    	}
		                    	else if(cell.getColumnIndex() == 2) {
		                    		p1Room = cell.getStringCellValue();
		                    	}
		                    	else if(cell.getColumnIndex() == 3) {
		                    		period2 = cell.getStringCellValue();
		                    	}
		                    	else if(cell.getColumnIndex() == 4) {
		                    		p2Room = cell.getStringCellValue();
		                    	}
		                    	else if(cell.getColumnIndex() == 5) {
		                    		period3 = cell.getStringCellValue();
		                    	}
		                    	else if(cell.getColumnIndex() == 6) {
		                    		p3Room = cell.getStringCellValue();
		                    	}
		                    	else if(cell.getColumnIndex() == 7) {
		                    		period4 = cell.getStringCellValue();
		                    	}
		                    	else if(cell.getColumnIndex() == 8) {
		                    		p4Room = cell.getStringCellValue();
		                    	}
		                        break;
		                    case NUMERIC:
		                        cell.setCellType(CellType.STRING);
		                        if(cell.getColumnIndex() == 2) {
		                    		p1Room = cell.getStringCellValue();
		                    	}
		                    	else if(cell.getColumnIndex() == 4) {
		                    		p2Room = cell.getStringCellValue();
		                    	}
		                    	else if(cell.getColumnIndex() == 6) {
		                    		p3Room = cell.getStringCellValue();
		                    	}
		                    	else if(cell.getColumnIndex() == 8) {
		                    		p4Room = cell.getStringCellValue();
		                    	}
		                        break;
		                    case BLANK:
		                    	if(cell.getColumnIndex() == 2) {
		                    		p1Room = "NO ROOM";
		                    	}
		                    	else if(cell.getColumnIndex() == 4) {
		                    		p2Room = "NO ROOM";
		                    	}
		                    	else if(cell.getColumnIndex() == 6) {
		                    		p3Room = "NO ROOM";
		                    	}
		                    	else if(cell.getColumnIndex() == 8) {
		                    		p4Room = "NO ROOM";
		                    	}
		                    	else if(cell.getColumnIndex() == 1) {
		                    		period1 = "SPARE";
		                    	}
		                    	else if(cell.getColumnIndex() == 3) {
		                    		period2 = "SPARE";
		                    	}
		                    	else if(cell.getColumnIndex() == 5) {
		                    		period3 = "SPARE";
		                    	}
		                    	else if(cell.getColumnIndex() == 7) {
		                    		period4 = "SPARE";
		                    	}
		                        break;
		                    default:
	                    }
	                }
	                System.out.println("Teacher Name: " + teacherName);
	                System.out.println("Period 1: " + period1);
	                System.out.println("Period 1 Room: " + p1Room);
	                System.out.println("Period 2: " + period2);
	                System.out.println("Period 2 Room: " + p2Room);
	                System.out.println("Period 3: " + period3);
	                System.out.println("Period 3 Room: " + p3Room);
	                System.out.println("Period 4: " + period4);
	                System.out.println("Period 4 Room: " + p4Room);
	                System.out.println("");
	                
	                p1 = matchCourse(period1, curr);
	                p2 = matchCourse(period2, curr);
	                p3 = matchCourse(period3, curr);
	                p4 = matchCourse(period4, curr);
	                schedule = new Schedule(p1, p2 ,p3 ,p4);
	                teachers.add(new RegularTeacher(teacherName, schedule));
	            }
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
		return teachers;
	}

	//Returns a course that it receives by matching a course-code from the MasterSchedule excel file to the 
	//codes that the courses in the curr[iculum] arrayList have from the CurriculumSummary excel file
	Course matchCourse(String fullCode, ArrayList<Course> curr) {
		Course course;
		Course result = null;
		if(fullCode.equals("X") || fullCode.equals("SPARE")){
			return null;
		}
		String specialCode = fullCode.substring(0, 1);
		String code;
		boolean coverage = true;
		
		if (specialCode.equals("M-") || specialCode.equals("L-") || specialCode.equals("C-") || specialCode.equals("G-") || specialCode.equals("T-")
				|| specialCode.equals("S-")) {
			coverage = false;
			code = fullCode;
		} else {
			code = fullCode.substring(0, 5);
		}
		for(int i=0; i<curr.size(); i++) {
			course = curr.get(i);
			if (course.getCode().length() > 3) {
				if (code.equals(curr.get(i).getCode().substring(0, 5))) {
					result = course;
					result.assignCoverage(coverage);
					return result;
				}	
			} else if (code.equals(course.getCode().substring(0,4))){
				result = course;
				result.assignCoverage(coverage);
				return result;
			}
		}
		return result;
	}
}