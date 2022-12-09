package project.team9;

public class AbsenceRecord{
	
	private String rTeacher;
	private String day;
	private String week;
	private String p1;
	private String p2;
	private String p3;
	private String p4;
	private boolean p1Covered = true;
	private boolean p2Covered = true;
	private boolean p3Covered = true;
	private boolean p4Covered = true;
	
	public AbsenceRecord(String rTeacherIn, String dayIn, String weekIn, String p1In, String p2In, String p3In, String p4In) {
		rTeacher = rTeacherIn;
		day = dayIn;
		week = weekIn;
		p1 = p1In;
		p2 = p2In;
		p3 = p3In;
		p4 = p4In;

		if (p1.equals("A")) {
			p1Covered = false;
		}
		if (p2.equals("A")) {
			p2Covered = false;
		}
		if (p3.equals("A")) {
			p3Covered = false;
		}
		if (p4.equals("A")) {
			p4Covered = false;
		}
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
	public boolean getP1Coverage() {
		return p1Covered;
	}
	public boolean getP2Coverage() {
		return p2Covered;
	}
	public boolean getP3Coverage() {
		return p3Covered;
	}
	public boolean getP4Coverage() {
		return p4Covered;
	}
	
	public void coverP1(boolean cover) {
		p1Covered = cover;
	}
	public void coverP2(boolean cover) {
		p2Covered = cover;
	}
	public void coverP3(boolean cover) {
		p3Covered = cover;
	}
	public void coverP4(boolean cover) {
		p4Covered = cover;
	}
	
	public String toString() {
		String result = "\nTeacher: " + getName();
		result += "\nP1: [" + p1 + "] ";
		result += "P2: [" + p2 + "] ";
		result += "P3: [" + p3 + "] ";
		result += "P4: [" + p4 + "] \n";
		return result;
	}
}