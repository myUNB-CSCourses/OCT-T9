package project.team9;

public class Driver {

	public static void main(String[] args) {
//		MasterScheduleReader read = new MasterScheduleReader();
//		read.masterReader();
//		AbsenceWorkbook readAB = new AbsenceWorkbook();
//		readAB.workbookReader("Monday");
		
		CurriculumSummaryReader courses = new CurriculumSummaryReader();
		
		for(Course var : courses.curriculumReader()) {
			System.out.println(var + "\n");
			
		}
		
		
	}
}
