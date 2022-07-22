package com.manatee.mymap.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.manatee.mymap.GeojsonDeserializer;

@JsonDeserialize(using = GeojsonDeserializer.class)
public class Geojson {
    String type;
    Point point;

    public Geojson() {
    }

    public Geojson(String type, Point point) {
        this.type = type;
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Geojson{" +
            "type='" + type + '\'' +
            ", point=" + point +
            '}';
    }
}
