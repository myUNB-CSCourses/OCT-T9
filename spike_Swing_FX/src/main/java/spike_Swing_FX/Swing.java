package spike_Swing_FX;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
 
public class Swing {
    public static void main(String[] args) throws IOException{
        ArrayList<Student> array = new ArrayList<Student>();
       
        Scanner sc = new Scanner(new FileReader("src/inputs/input1.txt"));
        
		String line1;
		
        while (sc.hasNext()) {
			line1 = sc.nextLine();
			String[] splitting = line1.split("\\s+");
			array.add(new Student(splitting[0], Double.parseDouble(splitting[1]), splitting[2]));
        }
		
		sc.close();
		String[][] data = new String[array.size()][3];
		
		for(int i = 0; i < array.size(); i++) {
			data[i][0] = array.get(i).getName();
			data[i][1] = Double.toString(array.get(i).getGrade());
			data[i][2] = array.get(i).getMajor();
		}
		
		JFrame frame = new JFrame();
       
		frame.setTitle("Table");

       String[] columns = { "Name", "Grade", "Studying" };

       JTable table = new JTable(data, columns);
       table.setBounds(50, 50, 300, 300);
       JScrollPane pane = new JScrollPane(table);
       frame.add(pane);
       frame.setSize(500, 200);
       frame.setVisible(true);
    }
}
