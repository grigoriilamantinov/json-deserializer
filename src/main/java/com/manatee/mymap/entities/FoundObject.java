package com.manatee.mymap.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class FoundObject {

    @JsonProperty("place_id")
    private Long placeId;

    @JsonProperty("display_name")
    private String displayName;

    private String type;

    public FoundObject() {
    }

    public FoundObject(Long placeId, String displayName, String type) {
        this.placeId = placeId;
        this.displayName = displayName;
        this.type = type;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoundObject that = (FoundObject) o;
        return Objects.equals(placeId, that.placeId) && Objects.equals(displayName, that.displayName) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeId, displayName, type);
    }

    @Override
    public String toString() {
        return "FoundObject{" +
            "placeId=" + placeId +
            ", displayName='" + displayName + '\'' +
            ", type='" + type + '\'' +
            '}';
    }
}
