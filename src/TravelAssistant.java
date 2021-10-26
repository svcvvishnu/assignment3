import entity.City;
import entity.Transport;

import java.util.*;

public class TravelAssistant {
    Map<String, List<Transport>> routes  = new HashMap();
    Map<String, City> cityByName = new HashMap<>();

    public boolean addCity(String cityName, boolean testRequired, int timeToTest, int nightlyHotelCost) throws IllegalArgumentException {
        City c = new City(cityName, testRequired, timeToTest, nightlyHotelCost);
        if(!CityValidator.isValid(c)) throw new IllegalArgumentException();
        return true;
    }

    public boolean addFlight(String startCity, String destinationCity, int flightTime, int flightCost) throws IllegalArgumentException {
        return true;
    }

    public boolean addTrain(String startCity, String destinationCity, int trainTime, int trainCost) throws IllegalArgumentException {
        return true;
    }

    List<String> planTrip(String startCity, String destinationCity, boolean isVaccinated, int costImportance, int travelTimeImportance, int travelHopImportance) throws IllegalArgumentException {

        Set<Transport> visitedVertices = new HashSet<>();

        return null;
    }
}
