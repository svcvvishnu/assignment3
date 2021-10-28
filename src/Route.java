/**
 * To represent an edge in the Graph.
 */
public class Route {
    String start;
    String dest;

    Transport flight;
    Transport train;

    public Route(String start, String dest) {
        this.start = start;
        this.dest = dest;
        this.flight = null;
        this.train = null;
    }

    public void setFlight(Transport flight) {
        this.flight = flight;
    }

    public void setTrain(Transport train) {
        this.train = train;
    }
}
