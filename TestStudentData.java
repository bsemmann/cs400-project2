// --== CS400 File Header Information ==--
// Name: Brian Semmann
// Email: bsemmann@wisc.edu
// Team: FF
// TA: Abhay
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * Test class using JUnit that tests methods in other
 * classes to insure they work properly and pass and fail
 * when they are supposed to when given different scenarios
 * 
 * @author Brian Semmann
 *
 */
public class TestStudentData {
	
	
	/**
	 * tests to see that the students name can both be added
	 * and retrieved properly. Has two tests 1) to see if you can 
	 * add a new student to the red black tree and it has the correct
	 * name 2) to see if you can call and access an already existing
	 * students name given their student id.
	 */
	@Test
	void testStudentName() {
	    RedBlackTree<Integer> studentRBT = new RedBlackTree<>();
	    StudentScores scores = new StudentScores();
	    String string = "132:Brian:Semmann:1:82:64:72";
	    String testName = "Brian Semmann";
	    String[] studentData = string.split(":");
	    String name = studentData[1] + " " + studentData[2];
	    studentRBT.insert(Integer.parseInt(studentData[0]), name, Integer.parseInt(studentData[3]),
		        Integer.parseInt(studentData[4]), Integer.parseInt(studentData[5]), Integer.parseInt(studentData[6]));
	    
	    String realDataName = "John Doe";
	    String getName = scores.getName("200");
	    
	    //test 1
	    if(!(name.equals(testName))) {
		      fail("The name added was not the intended name");
		    }
	    
	    //test 2
	    if(!(getName.equals(realDataName))) {
	      fail("Did not get the name that was intended");
	    }
	    
	}
	
	/**
	 * tests to see that if you insert a new student into the
	 * red black tree, they will have the student id that you
	 * intended them on having. Doesn't need to test a get function
	 * because you'd have to know the id to call the get. 
	 */
	@Test
	void testStudentID() {
		RedBlackTree<Integer> studentRBT = new RedBlackTree<>();
	    //StudentScores scores = new StudentScores();
	    String string = "117:Arman:Khan:4:89:92:73";
	    String testId = "117";
	    String[] studentData = string.split(":");
	    String name = studentData[1] + " " + studentData[2];
	    String id = studentData[0];
	    studentRBT.insert(Integer.parseInt(studentData[0]), name, Integer.parseInt(studentData[3]),
		        Integer.parseInt(studentData[4]), Integer.parseInt(studentData[5]), Integer.parseInt(studentData[6]));
	    
	    if(!(id.equals(testId))) {
		      fail("The id added was not the intended id");
		    }
	    
	  
	}
	
	/**
	 * tests to see that the students year can both be added
	 * and retrieved properly. Has two tests 1) to see if you can 
	 * add a new student to the red black tree and it has the correct
	 * year 2) to see if you can call and access an already existing
	 * students year given their student id.
	 */
	void testStudentYear() {
	    RedBlackTree<Integer> studentRBT = new RedBlackTree<>();
	    StudentScores scores = new StudentScores();
	    String string = "143:Danny:Miron:2:86:69:75"; 
	    String testYear = "2";
	    String[] studentData = string.split(":");
	    String name = studentData[1] + " " + studentData[2];
	    String year = studentData[3];
	    studentRBT.insert(Integer.parseInt(studentData[0]), name, Integer.parseInt(studentData[3]),
		        Integer.parseInt(studentData[4]), Integer.parseInt(studentData[5]), Integer.parseInt(studentData[6]));
	    
	    int realDataYear = 2;
	    int getYear = scores.getYear("203");
	    
	    //test 1
	    if(!(year.equals(testYear))) {
		      fail("The year added was not the intended year");
		    }
	    
	    //test 2
	    if(getYear != realDataYear) {
	      fail("Did not get the year that was intended");
	    }
	    
	}
	
	
	/**
	 * tests to see that the students exam 1 score can both be added
	 * and retrieved properly. Has two tests 1) to see if you can 
	 * add a new student to the red black tree and it has the correct
	 * exam 1 score 2) to see if you can call and access an already existing
	 * students exam 1 score given their student id.
	 */
	@Test
	void testExam1() {
	    RedBlackTree<Integer> studentRBT = new RedBlackTree<>();
	    StudentScores scores = new StudentScores();
	    String string = "209:Jacob:Rozum:1:87:62:56"; 
	    String testScore = "87";
	    String[] studentData = string.split(":");
	    String name = studentData[1] + " " + studentData[2];
	    String examScore = studentData[4];
	    studentRBT.insert(Integer.parseInt(studentData[0]), name, Integer.parseInt(studentData[3]),
		        Integer.parseInt(studentData[4]), Integer.parseInt(studentData[5]), Integer.parseInt(studentData[6]));
	    
	    int realDataExam1 = 49;
	    int getExam1 = scores.getExam1("126");
	    
	    //test 1
	    if(!(examScore.equals(testScore))) {
		      fail("The exam 1 score added was not the intended score");
		    }
	    
	    //test 2
	    if(getExam1 != realDataExam1) {
	      fail("Did not get the exam score that was intended");
	    }
	    
	}
	
	/**
	 * tests to see that the students exam 2 score can both be added
	 * and retrieved properly. Has two tests 1) to see if you can 
	 * add a new student to the red black tree and it has the correct
	 * exam 2 score 2) to see if you can call and access an already existing
	 * students exam 2 score given their student id.
	 */
	@Test
	void testExam2() {
	    RedBlackTree<Integer> studentRBT = new RedBlackTree<>();
	    StudentScores scores = new StudentScores();
	    String string = "102:Griffin:Vizgirda:1:91:37:53"; 
	    String testScore = "37";
	    String[] studentData = string.split(":");
	    String name = studentData[1] + " " + studentData[2];
	    String examScore = studentData[5];
	    studentRBT.insert(Integer.parseInt(studentData[0]), name, Integer.parseInt(studentData[3]),
		        Integer.parseInt(studentData[4]), Integer.parseInt(studentData[5]), Integer.parseInt(studentData[6]));
	    
	    int realDataExam2 = 92;
	    int getExam2 = scores.getExam2("120");
	    
	    //test 1
	    if(!(examScore.equals(testScore))) {
		      fail("The exam 2 score added was not the intended score");
		    }
	    
	    //test 2
	    if(getExam2 != realDataExam2) {
	      fail("Did not get the exam score that was intended");
	    }
	    
	}
	
	/**
	 * tests to see that the students exam 3 score can both be added
	 * and retrieved properly. Has two tests 1) to see if you can 
	 * add a new student to the red black tree and it has the correct
	 * exam 3 score 2) to see if you can call and access an already existing
	 * students exam 3 score given their student id.
	 */
	@Test
	void testExam3() {
	    RedBlackTree<Integer> studentRBT = new RedBlackTree<>();
	    StudentScores scores = new StudentScores();
	    String string = "214:Anna:Mussara:3:73:68:84"; 
	    String testScore = "84";
	    String[] studentData = string.split(":");
	    String name = studentData[1] + " " + studentData[2];
	    String examScore = studentData[6];
	    studentRBT.insert(Integer.parseInt(studentData[0]), name, Integer.parseInt(studentData[3]),
		        Integer.parseInt(studentData[4]), Integer.parseInt(studentData[5]), Integer.parseInt(studentData[6]));
	    
	    int realDataExam3 = 70;
	    int getExam3 = scores.getExam3("114");
	    
	    //test 1
	    if(!(examScore.equals(testScore))) {
		      fail("The exam 3 score added was not the intended score");
		    }
	    
	    //test 2
	    if(getExam3 != realDataExam3) {
	      fail("Did not get the exam score that was intended");
	    }
	    
	}

}
