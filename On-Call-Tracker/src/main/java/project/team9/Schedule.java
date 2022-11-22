package project.team9;

import java.util.ArrayList;

public class Schedule {
	private ArrayList<Course> periods = new ArrayList<Course>();	//Feel like it could be better as a normal array
	//private time;		not necessary
	private int date;
	private int spare;	//their code I am guessing
	
	public Schedule (ArrayList<Course> periods, int date) {
		this.periods = periods;
		this.date = date;
	}
	public Schedule (ArrayList<Course> periods, int date, int spare) {
		this.periods = periods;
		this.date = date;
		this.spare = spare;
	}
	public int getDate() {
		return date;
	}
	public int getSpare() {
		return spare;
	}
	public void assignPeriod(Course course, int period){
		periods.set(period, course);
	}
	public void assignSpare(int ID) {
		this.spare = ID;
	}
	public void assignDate(int date) {
		this.date = date;
	}
	
	public Course getCourse(int period) {
		return periods.get(period);
	}
}
