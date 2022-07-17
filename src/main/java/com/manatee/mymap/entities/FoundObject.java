package com.manatee.mymap.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manatee.mymap.entities.geojsons.MultiPolygonGeoJSON;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FoundObject {

    @JsonProperty("place_id")
    private Long placeId;

    @JsonProperty("display_name")
    private String displayName;

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    //    private MultiPolygonGeoJSON geojson;

    @Override
    public String toString() {
        return "FoundObject{" +
            "placeId=" + placeId +
            ", displayName='" + displayName + '\'' +
            ", multiPolygonGeoJSON=" +
            ", type='" + '\'' +
            '}';
    }
}
