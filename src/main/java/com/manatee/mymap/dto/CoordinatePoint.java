package com.manatee.mymap.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public class CoordinatePoint {
    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(final Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(final Double longitude) {
        this.longitude = longitude;
    }
}

