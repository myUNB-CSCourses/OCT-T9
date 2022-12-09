package project.team9;

public class Driver {

	public static void main(String[] args) {
		
		OnCall starter = new OnCall("Monday", 1, "Week1", "Month1");
		
		starter.assignOncalls();
		
		System.out.println("\n\n\n***********Proper Output is saved to the input/Output.xlsx file***********");
	}
}
