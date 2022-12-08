package project.team9;
//not sure if correct
public class RegularTeacher extends Teacher{
	
	private AbsenceRecord submittedAbsence;
	private Schedule dailySchedule;
	
	//do we need a term tally
	private String monthlyTally;
	private String weeklyTally;
	
	//the maxes may need to have a type String not int
	public static int monthMax = 4;
	public static int weekMax = 2;
	private boolean absent = false;
	private boolean assigned = false;
	
	public RegularTeacher(String nameIn, Schedule dailyScheduleIn){
		super(nameIn);
		dailySchedule = dailyScheduleIn;
	}
	
	public static void setWeekMax(int weekMaxIn){
		weekMax = weekMaxIn;
	}
	
	public static void setMonthMax(int monthMaxIn){
		monthMax = monthMaxIn;
	}
	
	public void setAssigned(){
		assigned = true;
	}
	
	public boolean getAssigned(){
		return assigned;
	}
	
	public void setToAbsent(){
		absent = true;
	}
	
	public void submitAbsenceSchedule(AbsenceRecord submittedAbsenceIn){
		if(absent){
			submittedAbsence = submittedAbsenceIn;
		}else {
			System.out.println("Teacher not Absent");
		}
	}

	public AbsenceRecord getSubmittedAbsence(){
		return submittedAbsence;
	}
	
	public boolean getAbsent(){
		return absent;
	}
	
	public Schedule getSchedule() {
		return dailySchedule;
	}
	
	public void setWeeklyTally(String weeklyTallyIn){
		 weeklyTally = weeklyTallyIn;
	}
	
	public void setMonthlyTally(String monthlyTallyIn){
		 monthlyTally = monthlyTallyIn;
	}

	public void updateWeeklyTally(){
		setWeeklyTally((Double.parseDouble(weeklyTally)+1)+"");
	}
	
	public void updateMonthlyTally(){
		setMonthlyTally((Double.parseDouble(monthlyTally)+1)+"");
	}
	
	public String getWeeklyTally(){
		return weeklyTally;
	}
	
	public String getMonthlyTally(){
		return monthlyTally;
	}
	
	public boolean checkWeeklyMax(){
		if (weekMax <= Integer.parseInt(weeklyTally)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkMonthlyMax(){
		if (monthMax <= Integer.parseInt(monthlyTally)) {
			return true;
		}else {
			return false;
		}
	}
	
	public String toString(){
		String result = "Teacher: " + getName();
				
		result += "\nSchedule: " + dailySchedule.toString();
		
		if(absent){
			result += "\n\tAbsent: " + submittedAbsence.toString();
		}
		
		result += "\nMonth: " + monthlyTally + 
				  "\nWeek: " + weeklyTally +
				  "\nAssigned: " + getAssigned();
		return result;
	}
}
