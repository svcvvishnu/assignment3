import java.util.*;

/**
 * The Dijkstras Algorithm.
 */
public class DijkstrasAlgorithm {
    private final Map<String, Path> distance;
    private final Set<String> visited;
    private final PriorityQueue<GraphNode> priorityQueue;

    private final int numberOfVertices;
    Map<String, Map<String, Route>> routes;
    Map<String, City> cityMap;
    TripMetadata metadata;


    /**
     * Constructor.
     * @param routes Map of routes
     * @param cityMap Cities by name
     * @param metadata Metadata to use in the algorithm
     */
    public  DijkstrasAlgorithm(Map<String, Map<String, Route>> routes, Map<String, City> cityMap, TripMetadata metadata) {
        this.routes = routes;
        this.numberOfVertices = cityMap.size();
        this.cityMap = cityMap;
        visited = new HashSet<>();
        priorityQueue = new PriorityQueue<>(numberOfVertices);
        this.distance = new HashMap<>();
        this.metadata = metadata;
    }

    /**
     * Main code to loop the vertices of the graph with min distance.
     * @param startCity Starting City
     * @param destinationCity Destination City
     * @return Path of the Journey
     */
    public Path step1(String startCity, String destinationCity)
    {
        Set<String> cities = cityMap.keySet();

        for (String city : cities)
            distance.put(city, new Path(null, Integer.MAX_VALUE, "", ""));

        // Add source node to the priority queue
        priorityQueue.add(new GraphNode(startCity, 0));

        // Distance to the source is 0
        distance.put(startCity, new Path(null, 0, "A", "start "));

        Path finalNode = null;
        while (visited.size() != numberOfVertices) {
            if (priorityQueue.isEmpty())
                break;

            GraphNode node = priorityQueue.remove();
            if (visited.contains(node.cityName))
                continue;

            if (destinationCity.equals(node.cityName)) {
                finalNode = distance.get(destinationCity);
                break;
            }

            visited.add(node.cityName);

            if (!destinationCity.equals(node.cityName)) {
                processNeighbours(node.cityName);
            }
        }
        return finalNode;
    }

    private void processNeighbours(String cityName) {
        ResponseString edgeDistance = null;
        int newDistance = -1;

        for (Map.Entry<String, Route> nb : routes.get(cityName).entrySet()) {
            if (!visited.contains(nb.getKey())) {
                edgeDistance = calculate(nb.getValue());
                if (edgeDistance == null) continue;
                newDistance = distance.get(cityName).weight + edgeDistance.cost;

                if (newDistance < distance.get(nb.getKey()).weight)
                    distance.put(nb.getKey(), new Path(distance.get(cityName), newDistance, nb.getKey(), edgeDistance.how));

                priorityQueue.add(new GraphNode(nb.getKey(), distance.get(nb.getKey()).weight));
            }
        }
    }

    private ResponseString calculate(Route value) {
        City c = cityMap.get(value.dest);
        if (!metadata.isVaccinated) {
            if (c.testRequired && c.timeToTest < 0) return null;
        }
        int cost = 0;
        if (!metadata.isVaccinated && c.testRequired) {
            cost = cost + metadata.travelTimeImportance * c.timeToTest;
            cost = cost + metadata.costImportance * c.timeToTest * c.nightlyHotelCosts ;
        }
        if (value.flight == null ) {
            cost = cost + value.train.getCost()*metadata.costImportance;
            cost = cost + metadata.travelHopImportance;
            cost = cost + value.train.getTime()* metadata.travelTimeImportance;
            return new ResponseString("train ", cost);
        }
        if (value.train == null) {
            cost = cost + value.flight.getCost()*metadata.costImportance;
            cost = cost + metadata.travelHopImportance;
            cost = cost + value.flight.getTime()* metadata.travelTimeImportance;
            return new ResponseString("fly ", cost);
        }

        int f_cost= cost + value.flight.getCost()*metadata.costImportance;
        f_cost = f_cost + metadata.travelHopImportance;
        f_cost = f_cost + value.flight.getTime()* metadata.travelTimeImportance;

        int t_cost= cost + value.train.getCost()*metadata.costImportance;
        t_cost = t_cost + metadata.travelHopImportance;
        t_cost = t_cost + value.train.getTime()* metadata.travelTimeImportance;

        if (f_cost < t_cost) {
            return new ResponseString("fly ", f_cost);
        }
        return new ResponseString("train ", t_cost);
    }
}
