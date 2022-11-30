package project.team9;

public class Driver {

	public static void main(String[] args) {
		MasterScheduleReader readMaster = new MasterScheduleReader();
		
		CurriculumSummaryReader readCourses = new CurriculumSummaryReader();
		readCourses.curriculumReader();
		//read.masterReader();

	}

}
