package project.team9;

// may be an abstract class if it is then code and name should be type final
public class Teacher{

	private int code;
	private String name;
	
	public Teacher(int codeIn, String nameIn){
		code = codeIn;
		name = nameIn;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return "Code: " +  code + " Name: " + name;
	}
}
