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
	public boolean getCoverage () {
		return coverage;
	}
	public void assignCoverage(boolean coverage) {
		this.coverage = coverage;
	}
	public String toString() {
		return "Category: " + category + "\nCode: " + code + "\nTeachable: " + "\nGrade: " + grade + "\nPathway: " + pathway;
	}
}
