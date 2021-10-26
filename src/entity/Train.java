package entity;

public class Train implements Transport {
    public City startCity;
    public City destinationCity;
    public int time;
    public int cost;

    public Train(City startCity, City destinationCity, int time, int cost) {
        this.startCity = startCity;
        this.destinationCity = destinationCity;
        this.time = time;
        this.cost = cost;
    }
}
