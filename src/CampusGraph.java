import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
}