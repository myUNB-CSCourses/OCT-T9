package project.team9;

public class Course {
	private final String code;
	private String category;
	private String teachable;
	private String grade;
	private String pathway;
	
	private boolean coverage;
	
	public Course (String categoryIn, String codeIn, String teachableIn, String gradeIn, String pathwayIn) {
		this.code = codeIn;
		this.category = categoryIn;
		this.teachable = teachableIn;
		this.grade = gradeIn;
		this.pathway = pathwayIn;
		coverage = true;
	}
	
	public String getCode () {
		return code;
	}
	public String getCategory () {
		return category;
	}
	public String getTeachable () {
		return teachable;
	}
	public String getGrade() {
		return grade;
	}
	public String getPathway() {
		return pathway;
	}
	public boolean getCoverage () {
		return coverage;
	}
	public void assignCoverage(boolean coverage) {
		this.coverage = coverage;
	}
	public String toString() {
		String result = "Category: " + getCategory() + "\nCode: " + getCode() 
				+ "\nTeachable: " + getTeachable() + "\nGrade: " + getGrade() + "\nPathway: " + getPathway();
		return result;
	}
}
