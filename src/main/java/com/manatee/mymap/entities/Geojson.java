package com.manatee.mymap.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.manatee.mymap.GeojsonDeserializer;

@JsonDeserialize(using = GeojsonDeserializer.class)
public class Geojson {
    String type;
    AveragePoint averagePoint;

    public Geojson() {
    }

    public Geojson(String type, AveragePoint point) {
        this.type = type;
        this.averagePoint = point;
    }

    public AveragePoint getAveragePoint() {
        return averagePoint;
    }

    public void setAveragePoint(AveragePoint averagePoint) {
        this.averagePoint = averagePoint;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GeoJSON type: " + type + ". " + averagePoint;
    }
}
