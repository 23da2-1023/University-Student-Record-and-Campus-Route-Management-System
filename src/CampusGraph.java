import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;

/**
 * Requirements 7-11: Campus locations (vertices) and roads (edges)
 * represented with an adjacency list. Supports add/remove of locations
 * and connections, displaying the network, and BFS/DFS traversal.
 */
public class CampusGraph {

    // adjacency list: location name -> list of connected location names
    private Map<String, List<String>> adjacencyList = new LinkedHashMap<>();

    public boolean addLocation(String location) {
        if (adjacencyList.containsKey(location)) {
            return false; // duplicate location
        }
        adjacencyList.put(location, new ArrayList<>());
        return true;
    }

    public boolean removeLocation(String location) {
        if (!adjacencyList.containsKey(location)) {
            return false;
        }
        adjacencyList.remove(location);
        // also remove this location from every other location's connection list
        for (List<String> neighbours : adjacencyList.values()) {
            neighbours.remove(location);
        }
        return true;
    }

    /** Adds an undirected road between two locations. */
    public boolean addConnection(String from, String to) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            return false; // one or both locations don't exist
        }
        if (adjacencyList.get(from).contains(to)) {
            return false; // connection already exists
        }
        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from);
        return true;
    }

    public boolean removeConnection(String from, String to) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            return false;
        }
        boolean removed = adjacencyList.get(from).remove(to);
        adjacencyList.get(to).remove(from);
        return removed;
    }

    public boolean hasLocation(String location) {
        return adjacencyList.containsKey(location);
    }

    /** Displays every location and its direct connections. */
    public void displayNetwork() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations added yet.");
            return;
        }
        System.out.println("---- Campus Network (Adjacency List) ----");
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    /** Requirement 11: Breadth-First traversal starting from a given location. */
    public void bfs(String start) {
        if (!adjacencyList.containsKey(start)) {
            System.out.println("Location not found: " + start);
            return;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        List<String> order = new ArrayList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            order.add(current);
            for (String neighbour : adjacencyList.get(current)) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
        System.out.println("BFS from " + start + ": " + order);
    }

    /** Requirement 11 (alternative): Depth-First traversal starting from a given location. */
    public void dfs(String start) {
        if (!adjacencyList.containsKey(start)) {
            System.out.println("Location not found: " + start);
            return;
        }
        Set<String> visited = new HashSet<>();
        List<String> order = new ArrayList<>();
        dfsRecursive(start, visited, order);
        System.out.println("DFS from " + start + ": " + order);
    }

    private void dfsRecursive(String current, Set<String> visited, List<String> order) {
        visited.add(current);
        order.add(current);
        for (String neighbour : adjacencyList.get(current)) {
            if (!visited.contains(neighbour)) {
                dfsRecursive(neighbour, visited, order);
            }
        }
    }
}
