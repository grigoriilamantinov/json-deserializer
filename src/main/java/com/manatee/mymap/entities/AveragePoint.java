package com.manatee.mymap.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AveragePoint {
    Double averageLatitude;
    Double averageLongitude;

    public AveragePoint(Double latitude, Double longitude) {
        this.averageLatitude = latitude;
        this.averageLongitude = longitude;
    }

    public AveragePoint() {
    }

    @Override
    public String toString() {
        return String.format("Average Latitude: %.4f Average longitude: %.4f", averageLatitude, averageLongitude);
    }
}
