package Task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class City implements Comparable<City>{

    private final String name;
    private Road[] adjacency; //array of roads(edges) between cities array
    private int minDistance = (int)Double.POSITIVE_INFINITY;// minimum stretch from this to the selected city
    private City previous; // next city(Vertex)
    private Map<Integer, Integer> citiesAndRoads = new HashMap<>();// a map of cities(Vertex) and the length of roads to
    // this city(Vertex)

    public City(String name){
        this.name = name;
    }

    public String toString() {return name;}

    public void addToMapRoadToCity(int cityNumber, int weightOfRoadToCity){
        this.citiesAndRoads.put(cityNumber, weightOfRoadToCity);
    }
    /*
     * this method add new city and road to this city to
     * map of cities(Vertex) and the length of roads to this city(Vertex)
     */

    public int getWeightOfRoadToCity(int cityNumber){
        return this.citiesAndRoads.get(cityNumber);
    }

    public int compareTo(City other) {
        return Double.compare(minDistance, other.minDistance);
    }

    public String getName() {return name;}

    public Road[] getAdjacency() {return adjacency;}
    public void setAdjacency(Road[] adjacency) {this.adjacency = adjacency;}

    public int getMinDistance() {return minDistance;}
    public void setMinDistance(int minDistance) {this.minDistance = minDistance;}

    public City getPrevious() {return previous;}
    public void setPrevious(City previous) {this.previous = previous;}

    public void fillingRoadsArray(int numberOfCities, Map<Integer, City> cities){

        Integer cityNumber;
        List<Road> roads = new ArrayList<>();

        for(int i = 0; i < numberOfCities; i++){

            cityNumber = this.citiesAndRoads.get(i+1);
            if (cityNumber != null){

                roads.add(new Road(cities.get(i+1), citiesAndRoads.get(i+1)));
            }
        }
        this.adjacency = roads.toArray(new Road[roads.size()]);
    }
    /*
     * this method transform map of cities and the length of roads to this city
     * to array of roads between cities array
     */
}