package project.team9;

// may be an abstract class if it is then code and name should be type final
public class Teacher{

	private int id;
	private String name;
	
	public Teacher(int idIn, String nameIn){
		id = idIn;
		name = nameIn;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return "Code: " +  code + " Name: " + name;
	}
}
