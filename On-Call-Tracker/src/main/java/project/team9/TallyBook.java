package project.team9;

public class TallyBook {
	private String teacherName;
	private int priority;
	private int remaining;
	private int termOnCalls;
	private int monthlyOnCalls;
	
	public TallyBook(String teacherNameIn, int priorityIn, int remainingIn, int termOnCallsIn, int monthlyOnCallsIn) {
		teacherName = teacherNameIn;
		priority = priorityIn;
		remaining = remainingIn;
		termOnCalls = termOnCallsIn;
		monthlyOnCalls = monthlyOnCallsIn;
	}
}
