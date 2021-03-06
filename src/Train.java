/**
 * Train details between cities.
 */
public class Train implements Transport {
    public String startCity;
    public String destinationCity;
    public int time;
    public int cost;

    public Train(String startCity, String destinationCity, int time, int cost) {
        this.startCity = startCity;
        this.destinationCity = destinationCity;
        this.time = time;
        this.cost = cost;
    }


    @Override
    public int getTime() {
        return this.time;
    }

    @Override
    public int getCost() {
        return this.cost;
    }
}
