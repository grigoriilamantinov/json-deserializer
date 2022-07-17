package com.manatee.mymap.entities;

import java.util.List;
import java.util.Objects;

public class Coordinate {
    List<Double> coordinates;

    public Coordinate(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
            "coordinates=" + coordinates +
            '}';
    }
}
