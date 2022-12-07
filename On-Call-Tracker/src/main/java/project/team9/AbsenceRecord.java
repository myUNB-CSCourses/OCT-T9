package project.team9;

public class AbsenceRecord{
	
	private String rTeacher;
	private String day;
	private String week;
	private String p1;
	private String p2;
	private String p3;
	private String p4;
	
	public AbsenceRecord(String rTeacherIn, String dayIn, String weekIn, String p1In, String p2In, String p3In, String p4In) {
		rTeacher = rTeacherIn;
		day = dayIn;
		week = weekIn;
		p1 = p1In;
		p2 = p2In;
		p3 = p3In;
		p4 =p4In;
	}
	
	public String getName() {
		return rTeacher;
	}
	public String getDay() {
		return day;
	}
	
	public String getWeek() {
		return week;
	}
	public String getP1() {
		return p1;
	}
	public String getP2() {
		return p2;
	}
	public String getP3() {
		return p3;
	}
	public String getP4() {
		return p4;
	}
	
}