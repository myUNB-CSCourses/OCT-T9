package project.team9;

public class Course {
	private final String code;
	private String name;
	private String room;
	private boolean coverage;
	
	public Course (String code, String name, String room) {
		this.code = code;
		this.name = name;
		this.room = room;
	}
	
	public String getCode () {
		return code;
	}
	public String getName () {
		return name;
	}
	public String getRoom () {
		return room;
	}
	public boolean getCoverage () {
		return coverage;
	}
	public void assignCoverage(boolean coverage) {
		this.coverage = coverage;
	}
}