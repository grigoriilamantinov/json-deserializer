package com.manatee.mymap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FoundObject {

    @JsonProperty("place_id")
    private Long placeId;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("geojson")
    private Geojson geojson;

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

    public Geojson getGeojson() {
        return geojson;
    }

    public void setGeojson(Geojson geojson) {
        this.geojson = geojson;
    }

    @Override
    public String toString() {
        return "FoundObject{" +
            "placeId=" + placeId +
            ", displayName='" + displayName + '\'' +
            ", geoson=" + geojson +
            '}';
    }
}
