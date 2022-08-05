package com.manatee.mymap.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.manatee.mymap.common.GeojsonDeserialaizer;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@JsonDeserialize(using = GeojsonDeserialaizer.class)
public class Geojson {
    private String type;
    private List<CoordinatePoint> coordinates;

    public Geojson(final String type, final List<CoordinatePoint> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public AverageCoordinatePoint getAverageCoordinates(final List<CoordinatePoint> coordinates) {
        final double averageLatitude = coordinates.stream()
            .map(coordinatePoint -> coordinatePoint.getLatitude())
            .reduce(0.0, Double::sum) / coordinates.size();

        final var averageLongitude = coordinates.stream()
            .map(CoordinatePoint::getLongitude)
            .reduce(0.0, Double::sum) / coordinates.size();

        return new AverageCoordinatePoint(averageLatitude, averageLongitude);
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public List<CoordinatePoint> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(final List<CoordinatePoint> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "GeoJSON type: " + type + ". " + coordinates;
    }
}
