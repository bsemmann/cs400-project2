import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StudentScores {
	public RedBlackTree<Integer> studentScores;
	public ArrayList<String> list;
	
	/**
	 * README: This works with my own RedBlackTree implementation. The insert method
	 * I call below uses a modified version of the insert method all our
	 * RedBlackTrees use. I added a few public fields for the name, year, exam 1,
	 * exam 2, and final exam scores while the studentId is still used as the "key".
	 * I added these to the parameter of the insert method which simple assigns the
	 * parameters to the public fields.
	 */
	public StudentScores() {
		try {
			studentScores = new RedBlackTree<Integer>();
			list = new ArrayList<>();
			File file = new File("Scores.txt");
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split(":");
				String name = data[1] + " " + data[2];
				list.add(data[0]);
				studentScores.insert(Integer.parseInt(data[0]), name, Integer.parseInt(data[3]),
						Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
			}
		} catch (FileNotFoundException e1) {
			System.out.println("File could not be found!");
		}
	}

	public String getName(String id)  {
		return studentScores.getName(Integer.parseInt(id));
	}
	
	public int getYear(String id) {
		return studentScores.getYear(Integer.parseInt(id));
	}
	
	public int getExam1(String id) {
		return studentScores.getExam1(Integer.parseInt(id));
	}
	
	public int getExam2(String id) {
		return studentScores.getExam2(Integer.parseInt(id));
	}
	
	public int getExam3(String id) {
		return studentScores.getExam3(Integer.parseInt(id));
	}
	
	public ArrayList<String> getList() {
		return this.list;
	}
}
