package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathRepository {
    private static final List<Path> paths = new ArrayList<>();

    public static void addInitPath() {
        paths.add(new Path(new Line("2호선"),
                List.of(new Station("교대역"),
                        new Station("강남역"),
                        new Station("역삼역")),
                List.of(2, 2),
                List.of(3, 3)));
        paths.add(new Path(new Line("3호선"),
                List.of(new Station("교대역"),
                        new Station("남부터미널역"),
                        new Station("양재역"),
                        new Station("매봉역")),
                List.of(3, 6, 1),
                List.of(2, 5, 1)));
        paths.add(new Path(new Line("신분당선"),
                List.of(new Station("강남역"),
                        new Station("양재역"),
                        new Station("양재시민의숲역")),
                List.of(2, 10),
                List.of(8, 3)));
    }

    public static List<Path> findAll() {
        return Collections.unmodifiableList(paths);
    }

    public static void addPath(Path path) {
        paths.add(path);
    }

    public static void deleteAll() {
        paths.clear();
    }
}
