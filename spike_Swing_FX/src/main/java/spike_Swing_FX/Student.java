package spike_Swing_FX;

public class Student {

public String name;
public double grade;
public String major;

	public Student(String nameIn, double gradeIn, String majorIn) {
		name = nameIn;
		grade = gradeIn;
		major = majorIn;
	}

	public String getName() {
		return name;
	}

	public double getGrade() {
		return grade;
	}

	public String getMajor() {
		return major;
	}

	public String toString() {
		return name + "" + grade + "" + major + "\n";
	}

}
