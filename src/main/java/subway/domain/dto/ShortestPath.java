package subway.domain.dto;

import java.util.List;

public class ShortestPath {
    private List<String> path;
    private int distance;
    private int duration;

    public ShortestPath(List<String> path, int distance, int duration) {
        this.path = path;
        this.distance = distance;
        this.duration = duration;
    }

    public List<String> getPath() {
        return path;
    }

    public int getDistance() {
        return distance;
    }

    public int getDuration() {
        return duration;
    }
}
