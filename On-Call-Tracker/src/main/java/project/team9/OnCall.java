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
	
	private int date;
	private String week;
	
	public OnCall(String day, int date, String week, String month) {
		this.date = date;
		this.week = week;
		
		curriculum = new ArrayList<Course>();
		curriculum = cRead.curriculumReader();
		rTeachers = new ArrayList<RegularTeacher>();
		rTeachers = master.masterReader(curriculum);
		sTeachers = new ArrayList<SupplyTeacher>();
		aRecord = new ArrayList<AbsenceRecord>();
		aRecord = absence.workbookReader(day, week);
		tallies = tally.readTalleyCountDay(1, month);
	}

	public void assignOncalls() {
		System.out.println(aRecord);
		System.out.println("assignOnCalls\n===========");
		int dayI = 0;
		int periodI = 1;
		int subjectI = 2;
		int supplyI = 3;
		int replacementI = 4;
		
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
				}
			}

			System.out.println("======Done configurating");
			
			while(itr.hasNext()) {
				row = itr.next();
				for (int i=0; i<aRecord.size(); i++) {
					record = aRecord.get(i);
					System.out.println("======Checking: " + record.getName());
					if (record.getP1().equals("A") && !record.getP1Coverage()) {
						System.out.println("Absent P1");
						course = getTeacherCourse(record.getName(), 1);
						period = 1;
					} else if (record.getP2().equals("A") && !record.getP2Coverage()) {
						System.out.println("Absent P2");
						course = getTeacherCourse(record.getName(), 2);
						period = 2;
					} else if (record.getP3().equals("A") && !record.getP3Coverage()) {
						System.out.println("Absent P3");
						course = getTeacherCourse(record.getName(), 3);
						period = 3;
					} else if (record.getP4().equals("A") && !record.getP4Coverage()) {
						System.out.println("Absent P4");
						course = getTeacherCourse(record.getName(), 4);
						period = 4;
					}
					if (course != null){
						System.out.println("Adding new Row[" + row.getRowNum() + "]");
						
						row.createCell(dayI).setCellValue(date);
						row.createCell(periodI).setCellValue(period);
						row.createCell(subjectI).setCellValue(course.getCode());

						System.out.println("day: " + date);
						System.out.println("period: " + period);
						System.out.println("subject: " + course.getCode());
						
						if (sTeachers.size() != 0) {
							coveringTeacher = sTeachers.get(0);
							sTeachers.remove(0);
							row.createCell(supplyI).setCellValue("Y");
							row.createCell(replacementI).setCellValue(coveringTeacher.getName());
							System.out.println("Y");
							System.out.println("teacher: " + coveringTeacher.getName());
						} else  {
							coveringTeacher = getRegularTeacher(period);
							row.createCell(supplyI).setCellValue("N");
							System.out.println("N");
							if (coveringTeacher != null) {
								row.createCell(replacementI).setCellValue(coveringTeacher.getName());
								System.out.println("teacher: " + coveringTeacher.getName());
							} else {
								row.createCell(replacementI).setCellValue("N/A");
								System.out.println("N/A");
							}
						}
						System.out.println("\n");
						coveringTeacher = null;
						course = null;
						period = 0;
						break;
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
	
	private Teacher getRegularTeacher(int period) {
		RegularTeacher result = null;
		TallyBook bestCandidate = null;
		ArrayList<TallyBook> talliesP = tallies.get(period-1);
		
		for (int i=0; i<tallies.size(); i++) {
			TallyBook x = talliesP.get(i);
			if (!x.getTally() && x.getRemaining() > 0) {
				if (bestCandidate == null) {
					bestCandidate = x;
				} else if (x.getPriority() < bestCandidate.getPriority()) {
					bestCandidate = x;
				}
			}
		}
		for (int i=0; i<rTeachers.size(); i++) {
			if (rTeachers.get(i).getName().equals(bestCandidate.getTeacherName())) {
				result = rTeachers.get(i);
				break;
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
}
