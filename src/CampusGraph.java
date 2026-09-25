public class CampusGraph {
    private Map<String, List<String>> adjacencyList = new LinkedHashMap<>();

    public boolean addLocation(String location) {
        if (adjacencyList.containsKey(location)) return false;
        adjacencyList.put(location, new ArrayList<>());
        return true;
    }

public boolean removeLocation(String location) {
        if (!adjacencyList.containsKey(location)) return false;
        adjacencyList.remove(location);
        for (List<String> neighbours : adjacencyList.values()) {
            neighbours.remove(location);
        }
        return true;
    }

    public boolean hasLocation(String location) { return adjacencyList.containsKey(location); }
    public boolean addConnection(String from, String to) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) return false;
        if (adjacencyList.get(from).contains(to)) return false;
        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from);
        return true;
    }
    public boolean removeConnection(String from, String to) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) return false;
        boolean removed = adjacencyList.get(from).remove(to);
        adjacencyList.get(to).remove(from);
        return removed;
    }

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

    public void bfs(String start) {
        if (!adjacencyList.containsKey(start)) {
            System.out.println("Location not found: " + start);
            return;
        }
        java.util.Set<String> visited = new java.util.HashSet<>();
        java.util.Queue<String> queue = new java.util.LinkedList<>();
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

public void dfs(String start) {
        if (!adjacencyList.containsKey(start)) {
            System.out.println("Location not found: " + start);
            return;
        }
        java.util.Set<String> visited = new java.util.HashSet<>();
        List<String> order = new ArrayList<>();
        dfsRecursive(start, visited, order);
        System.out.println("DFS from " + start + ": " + order);
    }

    private void dfsRecursive(String current, java.util.Set<String> visited, List<String> order) {
        visited.add(current);
        order.add(current);
        for (String neighbour : adjacencyList.get(current)) {
            if (!visited.contains(neighbour)) {
                dfsRecursive(neighbour, visited, order);
            }
        }
    }
}