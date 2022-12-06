package project.team9;

public class Driver {

	public static void main(String[] args) {
		CurriculumSummaryReader courses = new CurriculumSummaryReader();
		MasterScheduleReader read = new MasterScheduleReader();
		read.masterReader(courses.curriculumReader());
		
		AbsenceWorkbook readAB = new AbsenceWorkbook();
		readAB.workbookReader("Monday", "Week1");
		
		
		
		for(Course var : courses.curriculumReader()) {
			System.out.println(var + "\n");
		}

		
		Course course1 = new Course("categoryIn", "codeIn", "teachableIn", "gradeIn", "pathwayIn");
		Schedule schedule1 = new Schedule(course1, course1, course1, course1);
		RegularTeacher t1 = new RegularTeacher("Dineth", schedule1);
		
		System.out.println(t1.getId());
		
		TalleyBookReaderWriter tally = new TalleyBookReaderWriter();
		tally.readTalleyCountDay(8, "Month1");
		tally.updateTeacherTally("Shuhui Xu", "Month1", 8, 4, true);
		tally.readTalleyCountDay(8, "Month1");
		tally.updateTeacherTally("Shuhui Xu", "Month1", 8, 4, false); //please make sure to undo changes so that the excel files as synched up between our members
	}
}
