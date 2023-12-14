package subway.domain;

import java.util.List;

public class Path {
    private Line line;
    private List<Station> stations;
    private List<Integer> distances;
    private List<Integer> durations;

    public Path(Line line, List<Station> stations, List<Integer> distances, List<Integer> durations) {
        this.line = line;
        this.stations = stations;
        this.distances = distances;
        this.durations = durations;
    }

    public Line getLine() {
        return line;
    }

    public List<Station> getStations() {
        return stations;
    }

    public List<Integer> getDistances() {
        return distances;
    }

    public List<Integer> getDurations() {
        return durations;
    }
}
