package entity;

import java.util.*;

public class DijkstrasAlgorithm {
    private Map<String, Path> distance;
    private Set<String> visited;
    private PriorityQueue<GraphNode> priorityQueue;

    private int numberOfVertices;
    Map<String, Map<String, Route>> routes;
    Map<String, City> cityMap;



    public  DijkstrasAlgorithm(Map<String, Map<String, Route>> routes, Map<String, City> cityMap) {
        this.routes = routes;
        this.numberOfVertices = cityMap.size();
        this.cityMap = cityMap;
        visited = new HashSet<>();
        priorityQueue = new PriorityQueue<>(numberOfVertices);
    }

    public Path step1(String startCity, String destinationCity, boolean isVaccinated, int costImportance, int travelTimeImportance, int travelHopImportance)
    {
        Set<String> cities = cityMap.keySet();

        for (String city : cities)
            distance.put(city, new Path(null, Integer.MAX_VALUE));

        // Add source node to the priority queue
        priorityQueue.add(new GraphNode(startCity, 0));

        // Distance to the source is 0
        distance.put(startCity, new Path(null, 0));

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
        int edgeDistance = -1;
        int newDistance = -1;

        for (Map.Entry<String, Route> nb : routes.get(cityName).entrySet()) {
            if (!visited.contains(nb.getKey())) {
                edgeDistance = getCost(nb.getValue());
                newDistance = distance.get(cityName).weight + edgeDistance;

                if (newDistance < distance.get(nb.getKey()).weight)
                    distance.put(nb.getKey(), new Path(distance.get(cityName), newDistance));

                priorityQueue.add(new GraphNode(nb.getKey(), distance.get(nb.getKey()).weight));
            }
        }
    }

    private int getCost(Route value) {
        return Math.min(value.flight.getCost(), value.train.getCost());
    }
}
