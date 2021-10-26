import entity.*;

import java.util.*;

public class TravelAssistant {
    Map<String, Map<String, Route>> routes  = new HashMap<>();
    Map<String, City> cityByName = new HashMap<>();

    public boolean addCity(String cityName, boolean testRequired, int timeToTest, int nightlyHotelCost) throws IllegalArgumentException {
        City c = new City(cityName, testRequired, timeToTest, nightlyHotelCost);
        if(!CityValidator.isValid(c)) throw new IllegalArgumentException();
        cityByName.put(cityName, c);
        return true;
    }

    public boolean addFlight(String startCity, String destinationCity, int flightTime, int flightCost) throws IllegalArgumentException {
        Map<String, Route> neighbours = routes.getOrDefault(startCity, new HashMap<>());
        Route r = neighbours.getOrDefault(destinationCity, new Route(startCity, destinationCity));
        r.setFlight(new Flight(startCity, destinationCity, flightTime, flightCost));
        neighbours.put(destinationCity, r);
        routes.put(startCity, neighbours);
        return true;
    }

    public boolean addTrain(String startCity, String destinationCity, int trainTime, int trainCost) throws IllegalArgumentException {
        Map<String, Route> neighbours = routes.getOrDefault(startCity, new HashMap<>());
        Route r = neighbours.getOrDefault(destinationCity, new Route(startCity, destinationCity));
        r.setTrain(new Train(startCity, destinationCity, trainTime, trainCost));
        neighbours.put(destinationCity, r);
        routes.put(startCity, neighbours);
        return true;
    }

    List<String> planTrip(String startCity, String destinationCity, boolean isVaccinated, int costImportance,
                          int travelTimeImportance, int travelHopImportance) throws IllegalArgumentException {

        DijkstrasAlgorithm alg = new DijkstrasAlgorithm(routes, cityByName);
        alg.step1(startCity, destinationCity, isVaccinated, costImportance, travelTimeImportance, travelHopImportance);

        return null;
    }
}
