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
		
		ArrayList<String> problemCodes = new ArrayList<String>();
		ArrayList<String> problemCodesLog = new ArrayList<String>();
		
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
//	                System.out.println("Teacher Name: " + teacherName);
//	                System.out.println("Period 1: " + period1);
//	                System.out.println("Period 1 Room: " + p1Room);
//	                System.out.println("Period 2: " + period2);
//	                System.out.println("Period 2 Room: " + p2Room);
//	                System.out.println("Period 3: " + period3);
//	                System.out.println("Period 3 Room: " + p3Room);
//	                System.out.println("Period 4: " + period4);
//	                System.out.println("Period 4 Room: " + p4Room);
//	                System.out.println("");
	                
	                p1 = matchCourse(period1, curr);
	                p2 = matchCourse(period2, curr);
	                p3 = matchCourse(period3, curr);
	                p4 = matchCourse(period4, curr);

	                //========================================================
	                
	                
	                
	                int index = 0;
	                String piece;
	                if (p1 == null) {
	                	if (period1.equals("X") || period1.equals("SPARE") || period1.equals("M-1.00") || period1.equals("L-1.00") || period1.equals("C-1.00") || period1.equals("G-1.00") || period1.equals("T-1.00") || period1.equals("S-1.00")) {
	                		
	                	} else {
	                		if (!problemCodes.contains(period1)) {
	                			problemCodes.add(period1);
	                			
	                			if (period1.length() > 16) {
	                				problemCodesLog.add("code: " + period1 + "\tteacher: " + teacherName + "[1]");
	                			} else if (period1.length() > 9) {
	                				problemCodesLog.add("code: " + period1 + "\t\tteacher: " + teacherName + "[1]");
	                			} else if (period1.length() > 5) {
	                				problemCodesLog.add("code: " + period1 + "\t\t\tteacher: " + teacherName + "[1]");
	                			} else {
	                				problemCodesLog.add("code: " + period1 + "\t\t\t\tteacher: " + teacherName + "[1]");
	                			}
	                		} else {
	                			index = problemCodes.indexOf(period1);
	                			piece = problemCodesLog.get(index) + ", " + teacherName + "[1]";
	                			problemCodesLog.remove(index);
	                			problemCodesLog.add(index, piece);
	                		}
	                	}
	                }
	                if (p2 == null) {
	                	if (period2.equals("X") || period2.equals("SPARE") || period2.equals("M-1.00") || period2.equals("L-1.00") || period2.equals("C-1.00") || period2.equals("G-1.00") || period2.equals("T-1.00") || period2.equals("S-1.00")) {
	                		
	                	} else {
	                		if (!problemCodes.contains(period2)) {
	                			problemCodes.add(period2);
	                			
	                			if (period2.length() > 16) {
	                				problemCodesLog.add("code: " + period2 + "\tteacher: " + teacherName + "[2]");
	                			} else if (period2.length() > 9) {
	                				problemCodesLog.add("code: " + period2 + "\t\tteacher: " + teacherName + "[2]");
	                			} else if (period2.length() > 5) {
	                				problemCodesLog.add("code: " + period2 + "\t\t\tteacher: " + teacherName + "[2]");
	                			} else {
	                				problemCodesLog.add("code: " + period2 + "\t\t\t\tteacher: " + teacherName + "[2]");
	                			}
	                		} else {
	                			index = problemCodes.indexOf(period2);
	                			piece = problemCodesLog.get(index) + ", " + teacherName + "[2]";
	                			problemCodesLog.remove(index);
	                			problemCodesLog.add(index, piece);
	                		}
	                	}
	                }
	                if (p3 == null) {
	                	if (period3.equals("X") || period3.equals("SPARE") || period3.equals("M-1.00") || period3.equals("L-1.00") || period3.equals("C-1.00") || period3.equals("G-1.00") || period3.equals("T-1.00") || period3.equals("S-1.00")) {
	                		
	                	} else {
	                		if (!problemCodes.contains(period3)) {
	                			problemCodes.add(period3);
	                			
	                			if (period3.length() > 16) {
	                				problemCodesLog.add("code: " + period3 + "\tteacher: " + teacherName + "[3]");
	                			} else if (period3.length() > 9) {
	                				problemCodesLog.add("code: " + period3 + "\t\tteacher: " + teacherName + "[3]");
	                			} else if (period3.length() > 5) {
	                				problemCodesLog.add("code: " + period3 + "\t\t\tteacher: " + teacherName + "[3]");
	                			} else {
	                				problemCodesLog.add("code: " + period3 + "\t\t\t\tteacher: " + teacherName + "[3]");
	                			}
	                		} else {
	                			index = problemCodes.indexOf(period3);
	                			piece = problemCodesLog.get(index) + ", " + teacherName + "[3]";
	                			problemCodesLog.remove(index);
	                			problemCodesLog.add(index, piece);
	                		}
	                	}
	                }
	                if (p4 == null) {
	                	if (period4.equals("X") || period4.equals("SPARE") || period4.equals("M-1.00") || period4.equals("L-1.00") || period4.equals("C-1.00") || period4.equals("G-1.00") || period4.equals("T-1.00") || period4.equals("S-1.00")) {
	                		
	                	} else {
	                		if (!problemCodes.contains(period4)) {
	                			problemCodes.add(period4);
	                			
	                			if (period4.length() > 16) {
	                				problemCodesLog.add("code: " + period4 + "\tteacher: " + teacherName + "[4]");
	                			} else if (period4.length() > 9) {
	                				problemCodesLog.add("code: " + period4 + "\t\tteacher: " + teacherName + "[4]");
	                			} else if (period4.length() > 5) {
	                				problemCodesLog.add("code: " + period4 + "\t\t\tteacher: " + teacherName + "[4]");
	                			} else {
	                				problemCodesLog.add("code: " + period4 + "\t\t\t\tteacher: " + teacherName + "[4]");
	                			}
	                		} else {
	                			index = problemCodes.indexOf(period4);
	                			piece = problemCodesLog.get(index) + ", " + teacherName + "[4]";
	                			problemCodesLog.remove(index);
	                			problemCodesLog.add(index, piece);
	                		}
	                	}
	                }
	                //========================================================
	                
	                
	                schedule = new Schedule(p1, p2 ,p3 ,p4);
	                teachers.add(new RegularTeacher(teacherName, schedule));
	            }
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
		
        //========================================================
		problemCodesLog.sort(null);
		System.out.println("\n\n\n====================\n");
		for(int i=0; i<problemCodesLog.size(); i++) {
			System.out.println(problemCodesLog.get(i));
		}
		System.out.println("\n====================\n\n\n");
        //========================================================
		
		
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