package subway.domain;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class PathFinder {
    public WeightedMultigraph<String, DefaultWeightedEdge> unionPathToGraph(List<Path> paths, String choice) {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        for (Path path : paths) {
            List<Station> stations = path.getStations();
            List<Integer> weights = new ArrayList<>();
            if (choice.equals("1")) { // 최단거리 TODO:  enum으로 빼기
                weights = path.getDistances();
            }
            if (choice.equals("2")) { // 최소시간
                weights = path.getDurations();
            }

            for (Station station : stations) {
                if (!graph.containsVertex(station.getName())) {
                    graph.addVertex(station.getName());
                }
            }
            for (int i = 0; i < stations.size() - 1; i++) {
                graph.setEdgeWeight(graph.addEdge(stations.get(i).getName(), stations.get(i + 1).getName()),
                        weights.get(i));
            }
        }
        return graph;
    }
}
