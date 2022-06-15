package Task2;

import java.util.*;

public class Controller {

        public static final Scanner consoleScanner = new Scanner(System.in);

        public static void findingTheShortestRouteToTheCities(){

        System.out.print("Enter number of tests : ");
        int numberOfTests = consoleScanner.nextInt(), numberOfCities;
        String primaryCityName, finishedCityName;
        Map<String, City> nameCitiesMap;
        List<Integer> resultList;

        for (int j = 0; j < numberOfTests ; j++) {

            System.out.println("\nTest " + (j+1));
            nameCitiesMap = new HashMap<>();
            inputCitiesFromConsole(nameCitiesMap);
            resultList = new ArrayList<>();

            System.out.print("Enter number of cities to which we must find a way : ");
            numberOfCities = consoleScanner.nextInt();

            for(int i = 0; i < numberOfCities; i++){

                System.out.print("Enter primary city name : ");
                primaryCityName = consoleScanner.nextLine();
                primaryCityName = primaryCityName.length() == 0 ? consoleScanner.nextLine() : primaryCityName;

                System.out.print("Enter finished city name : ");
                finishedCityName = consoleScanner.nextLine();
                finishedCityName = finishedCityName.length() == 0 ? consoleScanner.nextLine() : finishedCityName;

                computePaths(nameCitiesMap.get(primaryCityName));
                resultList.add(nameCitiesMap.get(finishedCityName).getMinDistance());
            }

            System.out.println("Results fot test " + (j+1) + " :");
            resultList.forEach(System.out::println);
        }
    }
        /*
         *for each test, perform a job
         *enter cities and roads between them
         *enter the cities between which you need to calculate the shortest distance and output it
         */

        public static Map<Integer, City> inputCitiesFromConsole(Map<String, City> nameCitiesMap){

        Map<Integer, City> cities = new HashMap<>();
        String tempName;
        int tempNumberOfRoads, cityNumber, roadWeight;
        City tempCity;

        System.out.print("Enter number of cities : ");
        int numberOfCities = consoleScanner.nextInt();

        for (int i = 0; i < numberOfCities; i++){

            System.out.print("Enter name of city : ");
            tempName = consoleScanner.nextLine();
            tempName = tempName.length() == 0 ? consoleScanner.nextLine() : tempName;
            tempCity = new City(tempName);

            System.out.print("Enter number of roads to " + tempName + " : ");
            tempNumberOfRoads = consoleScanner.nextInt();

            if (tempNumberOfRoads != 0){

                System.out.println("Enter roads : ");
                for (int p = 0; p < tempNumberOfRoads; p++){

                    cityNumber = consoleScanner.nextInt();
                    roadWeight = consoleScanner.nextInt();
                    tempCity.addToMapRoadToCity(cityNumber, roadWeight);
                }
            }
            cities.put(i+1, tempCity);
            nameCitiesMap.put(tempName, tempCity);
        }

        for(int i = 0; i < numberOfCities; i++){
            cities.get(i+1).fillingRoadsArray(numberOfCities, cities);
        }
        return cities;
    }
        /*
         *enter from console cities and roads between them
         *for each township and township an array of roads to them
         *group cities by names and return map
         */

        public static void computePaths(City source){

        source.setMinDistance(0);
        PriorityQueue<City> vertexQueue = new PriorityQueue<>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            City u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Road e : u.getAdjacency()) {

                City v = e.getTarget();
                int weight = e.getWeight();
                int distanceThroughU = u.getMinDistance() + weight;
                if (distanceThroughU < v.getMinDistance()){

                    vertexQueue.remove(v);
                    v.setMinDistance(distanceThroughU);
                    v.setPrevious(u);
                    vertexQueue.add(v);
                }
            }
        }
    }
        /*
         *this method sets all cities the shortest path to the selected city
         */
}
