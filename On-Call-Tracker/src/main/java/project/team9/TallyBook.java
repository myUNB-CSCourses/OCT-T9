package project.team9;

public class TallyBook {
	private String teacherName;
	private int priority;
	private int remaining;
	private int totalOnCalls;
	private int monthlyOnCalls;
	private boolean tally;
	
	public TallyBook(String teacherNameIn, int priorityIn, int remainingIn, int totalOnCallsIn, int monthlyOnCallsIn, boolean tallyIn) {
		teacherName = teacherNameIn;
		priority = priorityIn;
		remaining = remainingIn;
		totalOnCalls = totalOnCallsIn;
		monthlyOnCalls = monthlyOnCallsIn;
		tally = tallyIn;
	}
	
	public String getTeacherName () {
		return teacherName;
	}
	public int getPriority () {
		return priority;
	}
	public int getRemaining () {
		return remaining;
	}
	public int getTotalOnCalls () {
		return totalOnCalls;
	}
	public int getMonthlyOnCalls () {
		return monthlyOnCalls;
	}
	public boolean getTally () {
		return tally;
	}
	
}
