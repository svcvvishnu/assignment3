/**
 * Metadata about the City
 */
public class City {
    public String cityName;
    public boolean testRequired;
    public int timeToTest;
    public int nightlyHotelCosts;

    public City(String cityName, boolean testRequired, int timeToTest, int nightlyHotelCosts) {
        this.cityName = cityName;
        this.testRequired = testRequired;
        this.timeToTest = timeToTest;
        this.nightlyHotelCosts = nightlyHotelCosts;
    }
}
