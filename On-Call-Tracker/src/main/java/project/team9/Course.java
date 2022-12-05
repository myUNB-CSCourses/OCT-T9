package project.team9;

public class Course {
	private final String code;
	private String teachable;
	private String room;
	private boolean coverage;
	
	public Course (String code, String teachable) {
		this.code = code;
		this.teachable = teachable;
		coverage = false;
		room = "";
	}
	
	public String getCode () {
		return code;
	}
	public String getTeachable () {
		return teachable;
	}
	
	public String getRoom () {
		return room;
	}
	public boolean getCoverage () {
		return coverage;
	}
	public void assignRoom(String room) {
		this.room = room;
	}
	public void assignCoverage(boolean coverage) {
		this.coverage = coverage;
	}
}