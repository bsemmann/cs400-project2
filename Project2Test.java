// --== CS400 File Header Information ==--
// Name: <Bhuvanesh Reddy Bathala>
// Email: < bbreddy@wisc.edu email address>
// Team: <ff>
// Role: <Test Engineer>
// TA: <Abhay Kumar>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class Project2Test {
  @Test
  void testName() {
    RedBlackTree<Integer> i = new RedBlackTree<>();
    StudentScores j = new StudentScores();
    String s = "20:Walter:White:12:57:35:83";
    String d = "Walter White";
    String[] data = s.split(":");
    String name = data[1] + " " + data[2];
    String data1 = data[0];
    i.insert(Integer.parseInt(data[0]), name, Integer.parseInt(data[3]),
        Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
   
    if(!(name.equals(d))) {
      fail("Name is not added properly");
    }
   
 
}
  @Test
  void testId() {
   
    RedBlackTree<Integer> i = new RedBlackTree<>();
    StudentScores j = new StudentScores();
    String s = "45:Dexter:Morgan:5:7:3:8";
    String d = "45";
    String[] data = s.split(":");
    String name = data[1] + " " + data[2];
    String data1 = data[0];
    i.insert(Integer.parseInt(data[0]), name, Integer.parseInt(data[3]),
        Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
   
    if(!(data1.equals(d))) {
      fail("data is not added properly");
    }
   
  }
  @Test
    void testYear() {
   
    RedBlackTree<Integer> i = new RedBlackTree<>();
    StudentScores j = new StudentScores();
    String s = "99:Jon:Snow:5:70:32:85";
    String d = "5";
    String[] data = s.split(":");
    String name = data[1] + " " + data[2];
    String data1 = data[3];
    i.insert(Integer.parseInt(data[0]), name, Integer.parseInt(data[3]),
        Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
   
    if(!(data1.equals(d))) {
      fail("year is not added properly");
    }
   
    }
  @Test
    void testExam1() {
     
      RedBlackTree<Integer> i = new RedBlackTree<>();
      StudentScores j = new StudentScores();
      String s = "25:Max:Ryan:65:75:98:45";
      String d = "75";
      String[] data = s.split(":");
      String name = data[1] + " " + data[2];
      String data1 = data[4];
      i.insert(Integer.parseInt(data[0]), name, Integer.parseInt(data[3]),
          Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
     
      if(!(data1.equals(d))) {
        fail("exam1 score is not added properly");
      }
     
      }
   
  @Test
void testExam2() {
     
      RedBlackTree<Integer> i = new RedBlackTree<>();
      StudentScores j = new StudentScores();
      String s = "67:Ricky:Morty:57:83:21:35";
      String d = "21";
      String[] data = s.split(":");
      String name = data[1] + " " + data[2];
      String data1 = data[5];
      i.insert(Integer.parseInt(data[0]), name, Integer.parseInt(data[3]),
          Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
     
      if(!(data1.equals(d))) {
        fail("exam2 score is not added properly");
      }
     
      }

  @Test
void testExam3() {
 
  RedBlackTree<Integer> i = new RedBlackTree<>();
  StudentScores j = new StudentScores();
  String s = "67:Ben:Ten:35:12:31:98";
  String d = "98";
  String[] data = s.split(":");
  String name = data[1] + " " + data[2];
  String data1 = data[6];
  i.insert(Integer.parseInt(data[0]), name, Integer.parseInt(data[3]),
      Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
 
  if(!(data1.equals(d))) {
    fail("exam3 score is not added properly");
  }
 
  }

  @Test
void testGetName() {
 RedBlackTree<Integer> i = new RedBlackTree<>();
 StudentScores j = new StudentScores();
 String s = "120:Elinor:Dodd:1:89:92:90";
 if(!(j.getName("120").equals("Elinor Dodd"))) {
   fail("getName method not implemented properly");
}
   
   
   
}
  @Test
void testGetYear() {
 RedBlackTree<Integer> i = new RedBlackTree<>();
 StudentScores j = new StudentScores();
 String s = "204:Mattie:Penia:2:8:15:11";
 if(j.getYear("204")!=2) {
   fail("getYear method not implemented properly");
 
}
   
   
   
}

  @Test
void testGetExam1() {
  RedBlackTree<Integer> i = new RedBlackTree<>();
  StudentScores j = new StudentScores();
  String s = "200:John:Doe:2:74:83:78";
 
  if(j.getExam1("200")!=74) {
    fail("getExam1 method not implemented properly");
 
}
   
   
   
}
  @Test
void testGetExam2() {
  RedBlackTree<Integer> i = new RedBlackTree<>();
  StudentScores j = new StudentScores();
  String s = "203:Theo:Hines:2:99:78:89";
 
 
  if(j.getExam2("203")!=78) {
    fail("getExam2 method not implemented properly");
 
}
   
   
   
}
  @Test
void testGetExam3() {
  RedBlackTree<Integer> i = new RedBlackTree<>();
  StudentScores j = new StudentScores();
  String s = "314:Lilian:Roth:3:89:95:85";
 
  if(j.getExam3("314")!=85) {
    fail("getExam3 method not implemented properly");
 
}
   
   
   
}

}




 
  
    
  
