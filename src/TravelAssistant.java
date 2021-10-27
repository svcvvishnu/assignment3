import entity.*;

import java.util.*;

/**
 * Travel Assistant Class to add city, flight, train details and plan trip the trip depending on the requirement.
 */
public class TravelAssistant {
    //Store the available routes.
    Map<String, Map<String, Route>> routes  = new HashMap<>();

    //Store the cities added.
    Map<String, City> cityByName = new HashMap<>();

    /**
     * Add City to the TravelAssistant.
     * @param cityName City Name
     * @param testRequired If Test is required
     * @param timeToTest Time to test
     * @param nightlyHotelCost Nightly Hotel Cost
     * @return True if city is added
     * @throws IllegalArgumentException if there are any invalid parameters passed.
     */
    public boolean addCity(String cityName, boolean testRequired, int timeToTest, int nightlyHotelCost) throws IllegalArgumentException {
        City c = new City(cityName, testRequired, timeToTest, nightlyHotelCost);
        if(!CityValidator.isValid(c)) throw new IllegalArgumentException();
        cityByName.put(cityName, c);
        return true;
    }

    /**
     * Add the Flight details between Cities.
     * @param startCity Starting City
     * @param destinationCity Destination City
     * @param flightTime Flight time in minutes
     * @param flightCost Flight cost
     * @return IF the flight is added to the Travel Assistant
     * @throws IllegalArgumentException if there are any invalid parameters passed.
     */
    public boolean addFlight(String startCity, String destinationCity, int flightTime, int flightCost) throws IllegalArgumentException {
        Map<String, Route> neighbours = routes.getOrDefault(startCity, new HashMap<>());
        Route r = neighbours.getOrDefault(destinationCity, new Route(startCity, destinationCity));
        r.setFlight(new Flight(startCity, destinationCity, flightTime, flightCost));
        neighbours.put(destinationCity, r);
        routes.put(startCity, neighbours);
        return true;
    }

    /**
     * Add the train details between cities
     * @param startCity Starting City
     * @param destinationCity Destination City
     * @param trainTime Train time in minutes
     * @param trainCost Train cost
     * @return IF the train is added to the Travel Assistant
     * @throws IllegalArgumentException if there are any invalid parameters passed.
     */
    public boolean addTrain(String startCity, String destinationCity, int trainTime, int trainCost) throws IllegalArgumentException {
        Map<String, Route> neighbours = routes.getOrDefault(startCity, new HashMap<>());
        Route r = neighbours.getOrDefault(destinationCity, new Route(startCity, destinationCity));
        r.setTrain(new Train(startCity, destinationCity, trainTime, trainCost));
        neighbours.put(destinationCity, r);
        routes.put(startCity, neighbours);
        return true;
    }

    /**
     * Plan the trip and provide the details.
     * @param startCity Starting City
     * @param destinationCity Destination City
     * @param isVaccinated Is Vaccinated
     * @param costImportance Cost Importance of the trip
     * @param travelTimeImportance Travel Time importance of the trip
     * @param travelHopImportance Travel Hop Importance of the trip
     * @return List of all paths with flight or train to take
     * @throws IllegalArgumentException if there are any invalid parameters passed.
     */
    List<String> planTrip(String startCity, String destinationCity, boolean isVaccinated, int costImportance,
                          int travelTimeImportance, int travelHopImportance) throws IllegalArgumentException {

        DijkstrasAlgorithm alg = new DijkstrasAlgorithm(routes, cityByName, new TripMetadata(costImportance, travelTimeImportance, travelHopImportance, isVaccinated));
        Path res = alg.step1(startCity, destinationCity );

        List<String> response = new ArrayList<>();
        while(res != null) {
            response.add(res.how + res.city);
            res = res.parent;
        }
        Collections.reverse(response);
        return response;
    }
}
