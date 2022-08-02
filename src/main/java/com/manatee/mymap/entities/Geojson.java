package com.manatee.mymap.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.manatee.mymap.common.GeojsonDeserializer;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@JsonDeserialize(using = GeojsonDeserializer.class)
public class Geojson {
    String type;
    List<Double> coordinates;

    public Geojson(String type, List<Double> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public AveragePoint getAverageCoordinates(List<Double> coordinates) {
        double latitudeSum = 0.0;
        double longitudeSum = 0.0;
        int counter = 0;

        for (int i = 0; i < coordinates.size(); i++) {
            if (i %2 == 0) {
                latitudeSum += coordinates.get(i);
                counter++;
            } else {
                longitudeSum += coordinates.get(i);
            }
        }

        final double averageLatitude = latitudeSum / counter;
        final double averageLongitude = longitudeSum / counter;
        return new AveragePoint(averageLatitude, averageLongitude);
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GeoJSON type: " + type + ". " + coordinates;
    }
}
