package project.team9;

import java.util.ArrayList;

public class OnCall {
	private ArrayList<RegularTeacher> rTeacher;
	private ArrayList<SupplyTeacher> sTeacher;
	private ArrayList<AbsenceRecord> aRecord;
	private ArrayList<Course> course;
	private ArrayList<ArrayList<TallyBook>> tallies;
	private CurriculumSummaryReader cRead = new CurriculumSummaryReader();
	private AbsenceWorkbook absence = new AbsenceWorkbook();
	private MasterScheduleReader master = new MasterScheduleReader();
	private TalleyBookReaderWriter tally = new TalleyBookReaderWriter();
	
	public OnCall() {
		course = new ArrayList<Course>();
		course = cRead.curriculumReader();
		rTeacher = new ArrayList<RegularTeacher>();
		rTeacher = master.masterReader(course);
		sTeacher = new ArrayList<SupplyTeacher>();
		aRecord = new ArrayList<AbsenceRecord>();
		aRecord = absence.workbookReader("Monday", "Week1");
		tallies = tally.readTalleyCountDay(1, "Month1");
		
	}
	
	public void assignOncalls() {
		
	}
}
