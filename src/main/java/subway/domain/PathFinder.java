package subway.domain;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.dto.ShortestPath;

public class PathFinder {
    private WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(
            DefaultWeightedEdge.class);
    private WeightedMultigraph<String, DefaultWeightedEdge> durationGraph = new WeightedMultigraph(
            DefaultWeightedEdge.class);

    public void initPathFinder() {
        List<Path> paths = PathRepository.findAll();
        distanceGraph = unionPathToGraph(paths, "1");
        durationGraph = unionPathToGraph(paths, "2");
    }

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

    public ShortestPath findShortestPathBy(String choice, String source,
                                           String target) {
        validateSourceAndTargetDifferent(source, target);
        if (choice.equals("1")) {
            return findShortestPath(distanceGraph, durationGraph, choice, source, target);
        }
        return findShortestPath(durationGraph, distanceGraph, choice, source, target);
    }

    private void validateSourceAndTargetDifferent(String source, String target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("출발역과 도착역이 동일합니다.");
        }
    }

    private ShortestPath findShortestPath(WeightedMultigraph<String, DefaultWeightedEdge> shortGraph,
                                          WeightedMultigraph<String, DefaultWeightedEdge> nonShortGraph,
                                          String choice,
                                          String source,
                                          String target) {
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(shortGraph);
        GraphPath graphShortestPath = dijkstraShortestPath.getPath(source, target);
        if (graphShortestPath == null) {
            throw new IllegalArgumentException("경로가 존재하지 않습니다.");
        }
        List<String> shortestPath = findShortestPath(graphShortestPath);
        int shortTotalWeight = findShortTotalWeight(graphShortestPath);
        int nonShortTotalWeight = findNonShortTotalWeight(nonShortGraph, shortestPath);

        if (choice.equals("1")) { // TODO: dto로 넘기기 (거리, 시간)
            return new ShortestPath(shortestPath, shortTotalWeight, nonShortTotalWeight);
        }
        return new ShortestPath(shortestPath, nonShortTotalWeight, shortTotalWeight);
    }

    private List<String> findShortestPath(GraphPath shortestPath) {
        return shortestPath.getVertexList();
    }

    private int findShortTotalWeight(GraphPath shortestPath) {
        return (int) shortestPath.getWeight();
    }

    private int findNonShortTotalWeight(WeightedMultigraph<String, DefaultWeightedEdge> nonShortGraph,
                                        List<String> shortestPath) {
        double nonShortTotalWeight = 0;
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            nonShortTotalWeight += nonShortGraph.getEdgeWeight(
                    nonShortGraph.getEdge(
                            shortestPath.get(i), shortestPath.get(i + 1)));
        }
        return (int) nonShortTotalWeight;
    }
}
