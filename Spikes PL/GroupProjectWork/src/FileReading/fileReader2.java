package FileReading;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileReader2 {
	private String teacherName;
	private String period1;
	private String period2;
	private String period3;
	private String period4;
	
	private Scanner scf = null;
	
	private int teacherCol;
	private int per1Col;
	private int per2Col;
	private int per3Col;
	private int per4Col;
	
	private String fileName;
	
	public void TeacherParser(String fileName) {
		try{
			this.fileName = fileName;
			int counter = 0;
		
			File f = new File(fileName);
			scf = new Scanner(f);
	
		
			while(scf.hasNext()){
				scf.useDelimiter(",");
				String temp = scf.next();
				counter++;
				if(temp.equals("Teacher Name")){
					teacherCol = counter;
				}
				if(temp.equals("Period 1")){
					per1Col = counter;
				}
				if(temp.equals("Period 2")){
					per2Col = counter;
				}
				if(temp.equals("Period 3")){
					per3Col = counter;
				}
				if(temp.equals("Period 4")){
					per4Col = counter;
				}
			}
		}
		catch(FileNotFoundException a){
			System.out.println("Usage: java TemperatureStats.csv");
			System.exit(1);
		}
	}
	
	public void parseLine(){
		String line = scf.nextLine();
		Scanner lineSc = new Scanner(line);
		lineSc.useDelimiter(",");
		boolean found = false;
		int i = 0;
		while(lineSc.hasNext() && !found){
			String temp = lineSc.next();
			i++;
			if(i == teacherCol){
				teacherName = temp;
			}
			if(i == per1Col){
				period1 = temp;
				found = true;
			}
			//add more for the other periods. should be possible to get all info per line. Create a teacher obj possibly.
		}
	}
	public boolean getLines(){
		if(scf.hasNext()){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
}
