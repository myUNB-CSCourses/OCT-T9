package project.team9;

public class Driver {

	public static void main(String[] args) {
		CurriculumSummaryReader courses = new CurriculumSummaryReader();
		MasterScheduleReader read = new MasterScheduleReader();
		read.masterReader(courses.curriculumReader());
		
		//AbsenceWorkbook readAB = new AbsenceWorkbook();
		//readAB.workbookReader("Monday", "Week1");
		
		
		
		//for(Course var : courses.curriculumReader()) {
		//	System.out.println(var + "\n");
		
		//}
		
		
	}
}
