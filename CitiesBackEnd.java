// --== CS400 File Header Information ==--
// Name: Adam Tupitza
// Email: tupitza@wisc.edu
// Team: FF
// Role: Back End Developer 1
// TA: Abhay Kumar
// Lecturer: Gary Dahl
// Notes to Grader: n/a
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * This class contains the back end operations used to extract information from a directed graph 
 * about the geographical relationships between various cities in Wisconsin.
 * 
 * @author Adam Tupitza
 *
 */
public class CitiesBackEnd {
  CS400Graph<String> cityGraph; // the graph that stores all of the inter-city route information
  
  /**
   * BackEnd object constructor that initializes the CS400Graph of Strings using the data loaded
   * by a Cities object.
   */
  public CitiesBackEnd() {
    Cities cityData = new Cities(); // create Cities object to load in city data
    cityGraph = cityData.cities; // save the resulting Cities graph into this object's graph
  }
  
  /**
   * This method determines the best route from a city to another city, if one is available, and
   * returns an ordered list of all cities to pass through along that route. The first item in the
   * resulting list is the origin city and the last item is the destination city. If originCity and
   * destinationCity are identical, the method returns a 1-item list containing that city's name.
   * 
   * @param  originCity city to start the route from
   * @param  destinationCity city to find a route to
   * @return ordered List of Strings containing the best route from originCity to destinationCity
   * @throws NoSuchElementException if a route between the two cities does not exist, or at least
   *         one of the cities is not a vertex in the graph
   */
  public List<String> getRoute(String originCity, String destinationCity) {
    return cityGraph.shortestPath(originCity, destinationCity);
  }
  
  /**
   * This method determines the best route from a city to another city, if one is available, and
   * returns the integer value representing the total length in miles of that route. If originCity
   * and destinationCity are identical, the method returns 0.
   * 
   * @param  originCity city to start the route from
   * @param  destinationCity city to find the distance of the shortest route to
   * @return an integer representation of the distance in miles of the shortest route between
   *         originCity and destinationCity
   * @throws NoSuchElementException if a route between the two cities does not exist, or at least
   *         one of the cities is not a vertex in the graph
   */
  public int getDistance(String originCity, String destinationCity) {
    return cityGraph.getPathCost(originCity, destinationCity);    
  }
  
  /**
   * This static method calculates the expected travel time (in minutes) of a trip.
   * 
   * @param tripDistance the length in miles of a proposed trip
   * @param milesPerHour the expected average speed (in mph) corresponding to a mode of transportation
   * @return an integer value of the expected travel time of the trip, in minutes
   * @throws IllegalArgumentException if the entered distance is negative or the speed is non-positive
   */
  public static int getTravelTime(int tripDistance, int milesPerHour) {
    // check to make sure the arguments fall within the method requirements
    if (tripDistance < 0 || milesPerHour <= 0) {
      throw new IllegalArgumentException("Distance cannot be negative, and speed must be positive.");
    }
    
    double milesPerMinute = milesPerHour / 60.0; // convert mph to miles per minute
    return (int)Math.round(tripDistance / milesPerMinute); // return the projected travel time (in min)
  }
  
  /**
   * Given an origin city, this method randomly finds another suggested city in the graph to visit. 
   * The method will only return a suggestion of a city that a path to from the originCity exists.
   * 
   * @param originCity the city to find a random destination from
   * @return the String name of the random destination city suggestion
   * @throws IllegalArgumentException if the originCity is not a vertex in the graph
   * @throws IllegalStateException if no path to another city from the originCity exists
   */
  public String getRandomDestination(String originCity) {
    // check to make sure a vertex of the originCity is present in the graph
    if (!cityGraph.containsVertex(originCity)) {
      throw new IllegalArgumentException("Origin city is not present in the graph");
    }
    
    // check to make sure there are multiple vertices in the graph and that at least 1 edge exists
    if (cityGraph.getVertexCount() == 1 || cityGraph.getEdgeCount() < 1) {
      throw new IllegalStateException("A path to another city from this city does not exist.");
    }
    
    Random rand = new Random(); // random object used to randomly pick a destination city
    boolean destinationFound = false;
    int counter = 0;
    String currentCity = "";
    
    // loop runs until a valid destination city has been identified
    while (!destinationFound) {
      
      // generate random integer based on how many vertices are in the graph
      int randInt = rand.nextInt(cityGraph.getVertexCount() - 1);
      
      // loop through the vertices a (randInt + 1) number of times, save the currentCity, and break
      for (Enumeration<String> cityNames = cityGraph.vertices.keys(); cityNames.hasMoreElements();) {
        currentCity = cityNames.nextElement();
        
        if (counter == randInt) break;
        
        counter += 1;
      }
      
      // destination cannot be identical to the origin city, so restart the while loop and try again
      if (currentCity.equals(originCity)) continue;
      
      // make sure a route can be identified between the origin city and proposed random destination
      try {
        this.getDistance(originCity, currentCity);
        destinationFound = true;
      } catch (NoSuchElementException nsee) { // no route identified, so restart loop and try again
        continue;
      }
    }
    
    return currentCity; // valid proposed random destination has been located, return its String
  }
}
