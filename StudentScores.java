import java.io.File;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;

public class StudentScores {
	public RedBlackTree<Integer> studentScores;
	public RedBlackTree.Node node;
	public String s;
	public LinkedList<RedBlackTree.Node> list = new LinkedList<>();

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
			File file = new File("Scores.txt");
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split(":");
				String name = data[1] + " " + data[2];
				node = new RedBlackTree.Node(Integer.parseInt(data[0]), name, Integer.parseInt(data[3]),
						Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
				list.add(node);
				studentScores.insert(Integer.parseInt(data[0]), name, Integer.parseInt(data[3]),
						Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
			}
		} catch (FileNotFoundException e1) {
			System.out.println("File could not be found!");
		}
	}

	public String preOrder(RedBlackTree tree, RedBlackTree.Node node, String id) {
		if (node == null)
			return "";
		s += node.data + " ";
		preOrder(tree, node.leftChild, id);
		preOrder(tree, node.rightChild, id);
		return s;
	}

	public int getYear(String id) throws NoSuchElementException {
		if (preOrder(studentScores, node, id).contains(id))
			return node.year;
		throw new NoSuchElementException("Student could not be found in list!");
	}

	public String getName(String id) throws NoSuchElementException {
		if (preOrder(studentScores, node, id).contains(id))
			return node.name;
		throw new NoSuchElementException("Student could not be found in list!");
	}

	public int getExam1(String id) throws NoSuchElementException {
		if (preOrder(studentScores, node, id).contains(id))
			return node.exam1;
		throw new NoSuchElementException("Student could not be found in list!");
	}

	public int getExam2(String id) throws NoSuchElementException {
		if (preOrder(studentScores, node, id).contains(id))
			return node.exam2;
		throw new NoSuchElementException("Student could not be found in list!");
	}

	public int getExam3(String id) throws NoSuchElementException {
		if (preOrder(studentScores, node, id).contains(id))
			return node.exam3;
		throw new NoSuchElementException("Student could not be found in list!");
	}
}
