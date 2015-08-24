package io.pivotal.labsboot.domain;

public class Container {
    private Integer units;
    private String type;
    private String volume;

    public Integer getUnits() {
        return units;
    }

    public void setUnits(final Integer units) {
        this.units = units;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(final String volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Container container = (Container) o;

        if (units != null ? !units.equals(container.units) : container.units != null) return false;
        if (type != null ? !type.equals(container.type) : container.type != null) return false;
        return !(volume != null ? !volume.equals(container.volume) : container.volume != null);
    }

    @Override
    public int hashCode() {
        int result = units != null ? units.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Container{" +
                "units=" + units +
                ", type='" + type + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }
}
