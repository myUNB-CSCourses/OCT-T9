package project.team9;

// may be an abstract class if it is then code and name should be type final
public class Teacher{

	private int id;
	private String name;
	private static int unique = 100;
	
	public Teacher(String nameIn){
		id = unique;
		name = nameIn;
		unique++;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return "ID: " +  id + " Name: " + name;
	}
}
