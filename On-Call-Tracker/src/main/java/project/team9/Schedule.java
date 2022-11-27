package project.team9;

import java.util.ArrayList;

public class Schedule {
	
	private ArrayList<String> periods = new ArrayList<String>();
	private int spareIndex;
	
	public Schedule(String p1, String p2, String p3, String p4, String p5) {
		periods = new ArrayList<String>();
		periods.add(p1);
		periods.add(p2);
		periods.add(p3);
		periods.add(p4);
		spareIndex = assignSpareIndex();
		
	}
	public int getSpare() {
		return spareIndex;
	}
	
	public int assignSpareIndex() {
		int period = 0;
		
		for(int i = 0; i < periods.size(); i++) {
			if(periods.get(i).equals("Spare")) {
				period = i;
			}
		}
		return period;
	}

}
