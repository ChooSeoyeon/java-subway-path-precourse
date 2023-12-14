package subway;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import org.junit.jupiter.api.Test;

public class JGraphtTest {
    @Test
    public void getDijkstraShortestPath() {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        List<String> vList = List.of("v1", "v2", "v3");
        List<Integer> vWeight = List.of(2, 2);
        List<String> aList = List.of("v1", "a2", "a3");
        List<Integer> aWeight = List.of(1, 100);

        for (String v : vList) {
            if (!graph.containsVertex(v)) {
                graph.addVertex(v);
            }
        }

        for (String a : aList) {
            if (!graph.containsVertex(a)) {
                graph.addVertex(a);
            }
        }

        for (int i = 0; i < vList.size() - 1; i++) {
            graph.setEdgeWeight(graph.addEdge(vList.get(i), vList.get(i + 1)), vWeight.get(i));
        }

        for (int i = 0; i < aList.size() - 1; i++) {
            graph.setEdgeWeight(graph.addEdge(aList.get(i), aList.get(i + 1)), aWeight.get(i));
        }

//        graph.addVertex("v1");
//        graph.addVertex("v2");
//        graph.addVertex("v3");
//        graph.addVertex("a2");
//        graph.addVertex("a3");
//        graph.setEdgeWeight(graph.addEdge("v1", "v2"), 2);
//        graph.setEdgeWeight(graph.addEdge("v2", "v3"), 2);
//        graph.setEdgeWeight(graph.addEdge("v1", "v3"), 100);
//        graph.setEdgeWeight(graph.addEdge("v1", "a2"), 1);
//        graph.setEdgeWeight(graph.addEdge("a2", "a3"), 100);

        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        // dijkstraShortestPath.getPath("v3", "a3");
        System.out.println(dijkstraShortestPath.getPath("v3", "a3")); // 경로 없으면 null임
        // List<String> shortestPath = dijkstraShortestPath.getPath("v3", "a3").getVertexList();
        // System.out.println("shortestPath = " + shortestPath);
        //assertThat(shortestPath.size()).isEqualTo(3);
    }
}
