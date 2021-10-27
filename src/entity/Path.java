package entity;

/**
 * Path Node of the planned trip from the algo.
 */
public class Path {
    public Path parent;
    public int weight;
    public String city;
    public String how;
    public int hops;

    public Path(Path parent, int weight, String city, String how) {
        this.parent = parent;
        this.weight = weight;
        this.city = city;
        this.how = how;
        if (parent == null) {
            this.hops = 0;
        } else {
            this.hops = this.parent.hops + 1;
        }

    }
}
