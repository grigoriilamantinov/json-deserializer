package com.manatee.mymap.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class AveragePoint {
    private Double averageLatitude;
    private Double averageLongitude;

    public AveragePoint(Double latitude, Double longitude) {
        this.averageLatitude = latitude;
        this.averageLongitude = longitude;
    }

    @Override
    public String toString() {
        return String.format("Average Latitude: %.4f Average longitude: %.4f", averageLatitude, averageLongitude);
    }
}
