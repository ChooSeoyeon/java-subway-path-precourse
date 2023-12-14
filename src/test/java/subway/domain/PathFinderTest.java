package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PathFinderTest {
    @BeforeEach
    void setUp() {
        PathRepository.deleteAll();
        PathRepository.addInitPath();
    }

    @Test
    void 경로들을_합할_수_있다() {
        PathFinder pathFinder = new PathFinder();

        WeightedMultigraph<String, DefaultWeightedEdge> graph =
                pathFinder.unionPathToGraph(PathRepository.findAll(), "1");

        assertThat(graph.vertexSet().size()).isEqualTo(7);
        System.out.println("graph = " + graph);
    }
}
