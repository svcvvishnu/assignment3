package entity;

public class Path {
    public Path parent;
    public int weight;

    public Path(Path parent, int weight) {
        this.parent = parent;
        this.weight = weight;
    }
}
