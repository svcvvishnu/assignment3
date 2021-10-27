package entity;

import java.util.*;

public class DijkstrasAlgorithm {
    private Map<String, Path> distance;
    private Set<String> visited;
    private PriorityQueue<GraphNode> priorityQueue;

    private int numberOfVertices;
    Map<String, Map<String, Route>> routes;
    Map<String, City> cityMap;
    TripMetadata metadata;



    public  DijkstrasAlgorithm(Map<String, Map<String, Route>> routes, Map<String, City> cityMap, TripMetadata metadata) {
        this.routes = routes;
        this.numberOfVertices = cityMap.size();
        this.cityMap = cityMap;
        visited = new HashSet<>();
        priorityQueue = new PriorityQueue<>(numberOfVertices);
        this.distance = new HashMap<>();
        this.metadata = metadata;
    }

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
        Temp edgeDistance = null;
        int newDistance = -1;

        for (Map.Entry<String, Route> nb : routes.get(cityName).entrySet()) {
            if (!visited.contains(nb.getKey())) {
                edgeDistance = getCost(nb.getValue());
                if (edgeDistance == null) continue;
                newDistance = distance.get(cityName).weight + edgeDistance.cost;

                if (newDistance < distance.get(nb.getKey()).weight)
                    distance.put(nb.getKey(), new Path(distance.get(cityName), newDistance, nb.getKey(), edgeDistance.how));

                priorityQueue.add(new GraphNode(nb.getKey(), distance.get(nb.getKey()).weight));
            }
        }
    }

    private Temp getCost(Route value) {
        City c = cityMap.get(value.dest);
        if (!metadata.isVaccinated) {
            if (c.testRequired && c.timeToTest < 0) return null;
        }
        if (value.flight == null) return new Temp("train ", value.train.getCost());
        if (value.train == null) return new Temp("fly ", value.flight.getCost());
        if (value.flight.getCost() < value.train.getCost()) {
            return new Temp("fly ", value.flight.getCost());
        }
        return new Temp("train ", value.train.getCost());
    }
}
