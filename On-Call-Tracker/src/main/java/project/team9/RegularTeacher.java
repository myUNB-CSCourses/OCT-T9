package project.team9;
//not sure if correct
public class RegularTeacher extends Teacher{
	
	private Schedule dailySchedule;
	
	//do we need a term tally
	private String monthlyTally;
	private String weeklyTally;
	
	//the maxes may need to have a type String not int
	public static int monthMax = 4;
	public static int weekMax = 2;
	
	public RegularTeacher(String nameIn, Schedule dailyScheduleIn){
		super(nameIn);
		dailySchedule = dailyScheduleIn;
	}
	
	//Setters
	public static void setWeekMax(int weekMaxIn){
		weekMax = weekMaxIn;
	}
	
	public static void setMonthMax(int monthMaxIn){
		monthMax = monthMaxIn;
	}
	
	public void setWeeklyTally(String weeklyTallyIn){
		 weeklyTally = weeklyTallyIn;
	}
	
	public void setMonthlyTally(String monthlyTallyIn){
		 monthlyTally = monthlyTallyIn;
	}
	
	//Getters
	public Schedule getSchedule() {
		return dailySchedule;
	}
	
	public String getMonthlyTally(){
		return monthlyTally;
	}
	
	public String getWeeklyTally(){
		return weeklyTally;
	}
	
	
	public void updateWeeklyTally(){
		setWeeklyTally((Double.parseDouble(weeklyTally)+1)+"");
	}
	
	public void updateMonthlyTally(){
		setMonthlyTally((Double.parseDouble(monthlyTally)+1)+"");
	}
	
	
	
	public String toString(){
		String result = super.toString();
		return result;
	}
}
