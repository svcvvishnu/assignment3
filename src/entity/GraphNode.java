package entity;

public class GraphNode implements Comparable<GraphNode>{
    public String cityName;
    public Integer cost;

    public GraphNode(String cityName, Integer cost) {
        this.cityName = cityName;
        this.cost = cost;
    }



    @Override
    public int compareTo(GraphNode o) {
        return this.cost.compareTo(o.cost);
    }
}
