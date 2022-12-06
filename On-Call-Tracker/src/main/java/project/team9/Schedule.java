package project.team9;

public class Schedule {
	
	private Course period1;
	private Course period2;
	private Course period3;
	private Course period4;
	private String p1Room;
	private String p2Room;
	private String p3Room;
	private String p4Room;
	
	public Schedule(Course period1In, Course period2In, Course period3In, Course period4In) {
		period1 = period1In;
		period2 = period2In;
		period2 = period3In;
		period2 = period4In;
	}
	
	
	public Schedule(Course period1In, Course period2In, Course period3In, Course period4In, String p1RoomIn, String p2RoomIn, String p3RoomIn, String p4RoomIn) {
		period1 = period1In;
		period2 = period2In;
		period2 = period3In;
		period2 = period4In;
		p1Room = p1RoomIn;
		p2Room = p2RoomIn;
		p3Room = p3RoomIn;
		p4Room = p4RoomIn;
	}
	public Course getPeriod1() {
		return period1;
	}
	public Course getPeriod2() {
		return period2;
	}
	public Course getPeriod3() {
		return period3;
	}
	public Course getPeriod4() {
		return period4;
	}

	public String getPeriod1Room() {
		return p1Room;
	}
	public String getPeriod2Room() {
		return p2Room;
	}
	public String getPeriod3Room() {
		return p3Room;
	}
	public String getPeriod4Room() {
		return p4Room;
	}

	public void assignPeriod1(Course course) {
		period1 = course;
	}
	public void assignPeriod2(Course course) {
		period2 = course;
	}
	public void assignPeriod3(Course course) {
		period3 = course;
	}
	public void assignPeriod4(Course course) {
		period4 = course;
	}

	public void assignP1Room(String room) {
		p1Room = room;
	}
	public void assignP2Room(String room) {
		p2Room = room;
	}
	public void assignP3Room(String room) {
		p3Room = room;
	}
	public void assignP4Room(String room) {
		p4Room = room;
	}
	
}
