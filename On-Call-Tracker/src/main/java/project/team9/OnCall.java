package project.team9;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class OnCall {
	private ArrayList<RegularTeacher> rTeachers;
	private ArrayList<SupplyTeacher> sTeachers;	
	private ArrayList<AbsenceRecord> aRecord;
	private ArrayList<Course> curriculum;
	private ArrayList<ArrayList<TallyBook>> tallies;
	private CurriculumSummaryReader cRead = new CurriculumSummaryReader();
	private AbsenceWorkbook absence = new AbsenceWorkbook();
	private MasterScheduleReader master = new MasterScheduleReader();
	private TalleyBookReaderWriter tally = new TalleyBookReaderWriter();
	private SupplyTeacherReader supply = new SupplyTeacherReader();

	private int date;
	private String day;
	private String week;
	private String month;
	
	public OnCall(String dayIn, int dateIn, String weekIn, String monthIn) {
		this.date = dateIn;
		this.week = weekIn;
		this.day = dayIn;
		this.month = monthIn;
		
		curriculum = new ArrayList<Course>();
		curriculum = cRead.curriculumReader();
		rTeachers = new ArrayList<RegularTeacher>();
		rTeachers = master.masterReader(curriculum);
		sTeachers = new ArrayList<SupplyTeacher>();
		sTeachers = supply.readSupplyTeacher(dayIn, weekIn);
		aRecord = new ArrayList<AbsenceRecord>();
		aRecord = absence.workbookReader(day, week);
		tallies = tally.readTalleyCountDay(date, month);
	}

	public void assignOncalls() {
//		System.out.println(tallies);
//		System.out.println("assignOnCalls\n===========");
		ArrayList<SupplyTeacher> sTeachersP1 = cloneSupplyTeachers(sTeachers);
		ArrayList<SupplyTeacher> sTeachersP2 = cloneSupplyTeachers(sTeachers);
		ArrayList<SupplyTeacher> sTeachersP3 = cloneSupplyTeachers(sTeachers);
		ArrayList<SupplyTeacher> sTeachersP4 = cloneSupplyTeachers(sTeachers);	
		
		int dayI = 0;
		int periodI = 1;
		int subjectI = 2;
		int supplyI = 3;
		int replacementI = 4;
		int replacementIdI = 5;
		int absenteeI = 6;
		int absenteeIdI = 7;
		
		Cell cell;
		Row row;
		AbsenceRecord record;

		Course course = null;
		int period = 0;
		Teacher coveringTeacher = null;
		
		try {
			ConfigFileReader config = new ConfigFileReader();
			File file = new File(config.configRead("OUTPUT"));
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet(week);
			Iterator<Row> itr = sheet.rowIterator();
			row = itr.next();
			
			Iterator<Cell> itc = row.cellIterator();
			while(itc.hasNext()) {
				cell = itc.next();
				String label = cell.getStringCellValue();
				
				if (label.equals("Day")) {
					dayI = (int)cell.getColumnIndex();
				} else if (label.equals("Absentee Period")) {
					periodI = (int)cell.getColumnIndex();
				} else if (label.equals("Subject")) {
					subjectI = (int)cell.getColumnIndex();
				} else if (label.equals("Supply")) {
					supplyI = (int)cell.getColumnIndex();
				} else if (label.equals("Replacement")) {
					replacementI = (int)cell.getColumnIndex();
				} else if (label.equals("Replacement ID")) {
					replacementIdI = (int)cell.getColumnIndex();
				} else if (label.equals("Absentee")) {
					absenteeI = (int)cell.getColumnIndex();
				} else if (label.equals("Absentee ID")) {
					absenteeIdI = (int)cell.getColumnIndex();
				}
			}

//			System.out.println("======Done configurating");
			
			boolean end = false;
			for(int x=1; !end && x<50; x++) {
				row = sheet.createRow(x);
				for (int i=0; i<aRecord.size(); i++) {
					record = aRecord.get(i);
//					System.out.println("======Checking: " + record.getName());
					if (record.getP1().equals("A") && !record.getP1Coverage()) {
//						System.out.println("\tAbsent P1");
						course = getTeacherCourse(record.getName(), 1);
						period = 1;
						record.coverP1(true);
					} else if (record.getP2().equals("A") && !record.getP2Coverage()) {
//						System.out.println("\tAbsent P2");
						course = getTeacherCourse(record.getName(), 2);
						period = 2;
						record.coverP2(true);
					} else if (record.getP3().equals("A") && !record.getP3Coverage()) {
//						System.out.println("\tAbsent P3");
						course = getTeacherCourse(record.getName(), 3);
						period = 3;
						record.coverP3(true);
					} else if (record.getP4().equals("A") && !record.getP4Coverage()) {
//						System.out.println("\tAbsent P4");
						course = getTeacherCourse(record.getName(), 4);
//						try {
//							System.out.println(course);							
//						} catch (Exception e) {
//							System.out.println("Cannot get course");
//						}
						period = 4;
						record.coverP4(true);
					}
					if (course != null){
//						System.out.println("Adding new Row[" + row.getRowNum() + "]");
						
						row.createCell(dayI).setCellValue(date);
						row.createCell(periodI).setCellValue(period);
						row.createCell(subjectI).setCellValue(course.getCode());
						row.createCell(absenteeI).setCellValue(record.getName());
						
						for (int j=0; j<rTeachers.size(); j++) {
							if (rTeachers.get(j).getName().equals(record.getName())) {
								System.out.println("teacher id: " + rTeachers.get(j).getId());
								row.createCell(absenteeIdI).setCellValue(rTeachers.get(j).getId());
								break;
							} else if (j+1 == rTeachers.size()) {
								System.out.println("cannot find teacher");
								row.createCell(absenteeIdI).setCellValue("N/A");
							}
						}

//						System.out.println("day: " + date);
//						System.out.println("period: " + period);
//						System.out.println("subject: " + course.getCode());
						
						if ((sTeachersP1.size() != 0 && period == 1)
								|| (sTeachersP2.size() != 0 && period == 2) 
								|| (sTeachersP3.size() != 0 && period == 3) 
								|| (sTeachersP4.size() != 0 && period == 4)) {
							
							if (period == 1) {
								coveringTeacher = sTeachersP1.get(0);
								sTeachersP1.remove(0);
							} else if (period == 2) {
								coveringTeacher = sTeachersP2.get(0);
								sTeachersP2.remove(0);
							} else if (period == 3) {
								coveringTeacher = sTeachersP3.get(0);
								sTeachersP3.remove(0);
							} else if (period == 4) {
								coveringTeacher = sTeachersP4.get(0);
								sTeachersP4.remove(0);
							}
							
							row.createCell(supplyI).setCellValue("Y");
							row.createCell(replacementI).setCellValue(coveringTeacher.getName());
							row.createCell(replacementIdI).setCellValue(coveringTeacher.getId());
//							System.out.println("Y");
//							System.out.println("teacher: " + coveringTeacher.getName());
						} else  {
							coveringTeacher = getRegularTeacher(period);
							row.createCell(supplyI).setCellValue("N");
//							System.out.println("N");
							if (coveringTeacher != null) {
								row.createCell(replacementI).setCellValue(coveringTeacher.getName());
								row.createCell(replacementIdI).setCellValue(coveringTeacher.getId());
//								System.out.println("teacher: " + coveringTeacher.getName());
							} else {
								row.createCell(replacementI).setCellValue("N/A");
								row.createCell(replacementIdI).setCellValue("N/A");
//								System.out.println("N/A");
							}
						}
//						System.out.println("\n");
						coveringTeacher = null;
						course = null;
						period = 0;
						break;
					} else {
//						System.out.println("Is null");
						coveringTeacher = null;
						course = null;
						period = 0;
					}
					if (i+1==aRecord.size()) {
						end = true;
					}
				}
			}
			
			fis.close();
			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);
			fos.close();
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Teacher getRegularTeacher(int period) {
		RegularTeacher result = null;
		TallyBook bestCandidate = null;
		ArrayList<TallyBook> talliesP = tallies.get(period-1);
		
//		System.out.print("\nSearching for best candidate:");
		for (int i=0; i<talliesP.size(); i++) {
			TallyBook x = talliesP.get(i);
//			System.out.print(", " + i);
			if (!x.getTally() && x.getRemaining() > 0) {
//				System.out.print("C");
				if (bestCandidate == null) {
//					System.out.println("hosen");
					bestCandidate = x;
				} else if (x.getPriority() < bestCandidate.getPriority()) {
//					System.out.println("hosen: " + x.getPriority() + " < " + bestCandidate.getPriority());
					bestCandidate = x;
				}
			}
		}
//		System.out.println("");
		for (int i=0; i<rTeachers.size(); i++) {
			try {
//				System.out.print(", " + i);
				if (rTeachers.get(i).getName().equals(bestCandidate.getTeacherName())) {
//					System.out.println("");
					result = rTeachers.get(i);
					tally.updateTeacherTally(result.getName(), month, date, period, true);
					tallies = tally.readTalleyCountDay(date, month);
					break;
				}	
			} catch (Exception e) {
				
			}
			
		}
		return result;
	}

	public Course getTeacherCourse(String teacherName, int period) {
		Course result = null;
		
		for(int i=0; i<rTeachers.size(); i++) {
			RegularTeacher x = rTeachers.get(i);
			if (x.getName().equals(teacherName)) {
				if (period == 1) {
					result = x.getSchedule().getPeriod1();
				} else if (period == 2) {
					result = x.getSchedule().getPeriod2();
				} else if (period == 3) {
					result = x.getSchedule().getPeriod3();
				} else if (period == 4) {
					result = x.getSchedule().getPeriod4();
				}
				return result;
			}
		}
		return result;
	}
	
	public ArrayList<SupplyTeacher> cloneSupplyTeachers(ArrayList<SupplyTeacher> list){
		ArrayList<SupplyTeacher> result = new ArrayList<SupplyTeacher>();
		
		for (int i=0; i<list.size(); i++) {
			result.add(list.get(i));
		}
		return result;
	}
}