// --== CS400 File Header Information ==--
// Name: Adam Tupitza
// Email: tupitza@wisc.edu
// Team: FF
// Role: Front End Developer
// TA: Abhay Kumar
// Lecturer: Gary Dahl
// Notes to Grader: n/a
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class allows for a user to interact with the Student Exam Database, providing a handful of
 * options for the user to view information about students and overall class performance on exams.
 * 
 * @author Adam Tupitza
 *
 */
public class StudentDriver {
  
  /**
   * Prompts the user for a value by displaying prompt.
   *
   * After prompting the user, the method will consume an entire line of input while reading an
   * int. If the value read is between min and max (inclusive), that value is returned. Otherwise,
   * "Invalid value." terminated by a new line is output and the user is prompted again.
   *
   * @param sc     The Scanner instance to read from System.in.
   * @param prompt The name of the value for which the user is prompted.
   * @param min    The minimum acceptable int value (inclusive).
   * @param max    The maximum acceptable int value (inclusive).
   * @return Returns the value read from the user.
   */
  public static int promptInt(Scanner sc, String prompt, int min, int max) {
      Integer nextInt; // first valid integer token will be stored here
      Integer inputVal = null; // do-while loop runs until this is no longer null

      do {
          // start each loop cycle by showing user prompt
          System.out.print(prompt);

          // when first token isn't an integer, output error msg and consume line of input
          if (!sc.hasNextInt()) {
              System.out.println("Please enter a number between " + min + " and " + max + ".");
              sc.nextLine();
              continue; // jump back to beginning of the loop
          }

          // first token on the line is an integer, so read it in
          nextInt = sc.nextInt();

          // int token is within min and max, save it to inputVal to break out of the loop
          if (nextInt >= min && nextInt <= max) {
              inputVal = nextInt;
              sc.nextLine();
          }

          // int token is not within min and max, output error msg and consume line of input
          else {
              System.out.println("Please enter a number between " + min + " and " + max + ".");
              sc.nextLine(); // inputVal is still null, so loop will continue
          }

      } while (inputVal == null);

      return inputVal;
  }
  
  /**
   * This method takes in an integer and returns the class standing that corresponds to the integer.
   * 
   * @param year the integer representation of the class standing to be decoded
   * @return the String representation of the student's year
   */
  public static String decodeStudentYear(int year) {
    switch (year) {
      case 0:
        return "Freshman";
      case 1:
        return "Sophomore";
      case 2:
        return "Junior";
      case 3:
        return "Senior";
      default:
        return "Student Year Unknown";
        }
    }
  
  /**
   * This method calculates the overall class performance of a single student by finding the average
   * performance of that student over all 3 exams.
   * 
   * @param exam1Grade student's exam 1 score
   * @param exam2Grade student's exam 2 score
   * @param exam3Grade student's exam 3 score
   * @return a string of the student's overall class percentage 
   */
  public static String calculateOverallGrade(int exam1Grade, int exam2Grade, int exam3Grade) {
    final double NUMBER_OF_EXAMS = 3.0; // number of exams for this particular class is 3
    double overallGrade = (exam1Grade + exam2Grade + exam3Grade) / NUMBER_OF_EXAMS;
    return String.format("%.2f", overallGrade);
  }
  
  /**
   * This method calculates the mean performance for a group of students, either on a particular
   * exam or for all the exams combined. The parameter examNumber is used to indicate which exam
   * to calculate the average for (1-3), or the number 4 should be entered as a sentinel to indicate
   * that the average for all 3 exams combined should be calculated.
   * 
   * @param classList the list of students to perform the exam calculations with
   * @param examNumber exam number (or sentinel indicating all exams) to calculate the average for
   * @param scoreTree the StudentScores object containing this class's information
   * @return a string of this group of students' average performance on the specified exam(s)
   */
  public static String calculateExamAverage(ArrayList<String> classList, int examNumber, 
      StudentScores scoreTree) {
    
    // determine the total number of exams taken for later average calculation
    int numOfExamsTaken = (examNumber == 4) ? classList.size() * 3 : classList.size();
    
    double scoreTotal = 0.0; // used to total up raw student scores prior to averaging
    
    if (examNumber == 1) {
      for (String ID : classList) {
        scoreTotal += scoreTree.getExam1(ID);
        }
    } else if (examNumber == 2) {
      for (String ID : classList) {
        scoreTotal += scoreTree.getExam2(ID);
        }
    } else if (examNumber == 3) {
      for (String ID : classList) {
        scoreTotal += scoreTree.getExam3(ID);
        }
    } else if (examNumber == 4) { // add up the raw scores of all 3 exams
      for (String ID: classList) {
        scoreTotal += (scoreTree.getExam1(ID) + scoreTree.getExam2(ID) + scoreTree.getExam3(ID));
      }
    }
    
    double overallAverage = scoreTotal / numOfExamsTaken; // divide raw total by exams taken
    return String.format("%.2f", overallAverage);  
  }

  /**
   * Main method for StudentDriver.java that takes in user input using a scanner and displays
   * options and requested information to the user.
   * 
   * @param args not used by this program.
   */
  public static void main(String[] args) {
    
    Scanner scnr = new Scanner(System.in);
    StudentScores scoreTree = new StudentScores();
    
    // various prompts to be passed to the promptInt method for applicable situations
    String startPrompt = "\nOptions:\n1. Get information for a specific student\n"
        + "2. Mean grade for a specific exam\n3. Overall mean semester grade\n"
        + "4. Exam grade breakdown by student year\n5. Print class roster"
        + "\n6. Exit the database\nEnter a number for one of the above options: ";
    String idPrompt = "Please enter a 3-digit student ID number: ";
    String examPrompt = "For which exam would you like to calculate the class mean? ";
    String filteredExamPrompt = "For which exam(s) would you like to calculate this grade's mean for?"
        + "\n(Enter an appropriate exam number, or enter 4 to calculate the average for all exams): ";
    String yearPrompt = "For what grade of students would you like exam data for?\n"
        + "(Enter 0 for Freshmen, 1 for Sophomores, 2 for Juniors, or 3 for Seniors): ";
    
    int userChoice; // stores what program option user is currently requesting
    int requestedID; // stores ID of a particular student that the user wants information about
    String formattedStringID; // a valid formattedStringID is a 3-digit number from 000 to 999
    final int ALL_EXAMS_SENTINEL = 4; // passed to calculateExamAverage for AllExams average request
        
    System.out.println("****************************************************\n"
        + "Opening the student exam database...");
    
    userChoice = promptInt(scnr, startPrompt, 1, 6); // initial request by the user
    
    while(userChoice != 6) { // end loop if user picks option 6 ("Exit the application")
      
      // user chooses to look up information about a particular student
      if (userChoice == 1) {
        
        requestedID = promptInt(scnr, idPrompt, 1, 999); // ask user for a valid userID
        formattedStringID = String.format("%03d", requestedID); // get ID into correct format
        
        if (scoreTree.getName(formattedStringID).equals("Invalid ID number")) {
          System.out.println("\nNo student with this ID number present in the exam database.");
        } else {
          System.out.println("\n******************************\n"
              + "Information for Student #" + formattedStringID + ":\n"
              + "Name: " + scoreTree.getName(formattedStringID) + "\n"
              + "Year: " + decodeStudentYear(scoreTree.getYear(formattedStringID)) + "\n"
              + "Overall Grade: " + calculateOverallGrade(scoreTree.getExam1(formattedStringID),
                  scoreTree.getExam2(formattedStringID), scoreTree.getExam3(formattedStringID))
              + "%\n******************************");         
        }
        
      } else if (userChoice == 2) { // user wants to calculate mean class performance for an exam
        
        int examToCalculate = promptInt(scnr, examPrompt, 1, 3); // ask for which exam to calculate
        ArrayList<String> classList = scoreTree.getList();
        System.out.println("\nOverall class average for Exam " + examToCalculate +": "
            + calculateExamAverage(classList, examToCalculate, scoreTree) + "%");
        
      } else if (userChoice == 3) { // user wants to calculate mean class performance over all exams
        ArrayList<String> classList = scoreTree.getList();
        System.out.println("\nOverall class average for all exams: "
            + calculateExamAverage(classList, ALL_EXAMS_SENTINEL, scoreTree) + "%");

      } else if (userChoice == 4) { // user wants class performance filtered by student class standing
        ArrayList<String> classList = scoreTree.getList();
        ArrayList<String> filteredList = new ArrayList<String>();
        int desiredClassYear = promptInt(scnr, yearPrompt, 0, 3); // ask user for the year filter
        
        // only add students that are in the desired year to the filtered ArrayList
        for (String ID : classList) {
          if (scoreTree.getYear(ID) == desiredClassYear) {
            filteredList.add(ID);
          }        
        }
        
        if (filteredList.size() != 0) {
          
          // ask what exam(s) to get information for the selected year of students
          int examToCalculate = promptInt(scnr, filteredExamPrompt, 1, 4);
          
          if (examToCalculate == 4) { // get info for all exams for the selected year
            System.out.println("\nOverall " + decodeStudentYear(desiredClassYear)
                + " average for all exams: " 
                + calculateExamAverage(filteredList, examToCalculate, scoreTree) + "%");
            
          } else { // get info for only a specific exam for the selected year
          System.out.println("\nOverall " + decodeStudentYear(desiredClassYear)
              + " average for exam " + examToCalculate +": " 
              + calculateExamAverage(filteredList, examToCalculate, scoreTree) + "%");
          }
          
        } else { // output if no students of the requested grade exist in database
          System.out.println("\nThere are no students of this class standing in the class.");
        }
        
      } else if (userChoice == 5) { // user wants a class roster displayed
        ArrayList<String> classList = scoreTree.getList();
        System.out.println("\n**************************\nClass Roster:");
        
        for (String ID : classList) {
          System.out.println(scoreTree.getName(ID) + " - #" + ID);
        }
        
        System.out.println("**************************");
      }
      
      userChoice = promptInt(scnr, startPrompt, 1, 6); // get next user request to restart while loop
    }
    
    System.out.println("\nClosing the student exam database.\n"
        + "****************************************************");
    scnr.close();
  }
}
