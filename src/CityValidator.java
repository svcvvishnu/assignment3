import entity.City;

public class CityValidator {
    public static boolean isValid(City city) {
        if (city.cityName == null || city.cityName.trim().equals("")) return false;
        return city.timeToTest >= 0;
    }
}
