package subway.domain;

public class Station {
    private String name;

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Station) {
            Station station = (Station) obj;
            return this.name.equals(station.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public boolean isSameName(String inputStation) {
        return this.name.equals(inputStation);
    }
}
