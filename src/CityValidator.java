/**
 * Validator.
 */
public class CityValidator {
    public static boolean isValid(City city) {
        return city.cityName != null && !city.cityName.trim().equals("");
    }
}
