package com.manatee.mymap.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Point {
    Double latitude;
    Double longitude;

    public Point(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Point() {
    }

    @Override
    public String toString() {
        return "Point{" +
            "latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
    }
}
