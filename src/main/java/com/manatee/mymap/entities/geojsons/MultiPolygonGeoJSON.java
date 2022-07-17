package com.manatee.mymap.entities.geojsons;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class MultiPolygonGeoJSON {

    private String type;

//    @JsonProperty("coordinates")
//    private List<Double[]> coordinates;

    @Override
    public String toString() {
        return "MultiPolygonGeoJSON{" +
            "polygonCoordinate=" +
            ", type='" + type + '\'' +
            '}';
    }
}
