package com.manatee.mymap.entities;

import java.util.List;
import java.util.Objects;

public class ResponseDTO {
    private List<FoundObject> foundObjects;

    public ResponseDTO() {
    }

    public ResponseDTO(List<FoundObject> foundObjects) {
        this.foundObjects = foundObjects;
    }

    public List<FoundObject> getFoundObjects() {
        return foundObjects;
    }

    public void setFoundObjects(List<FoundObject> foundObjects) {
        this.foundObjects = foundObjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseDTO that = (ResponseDTO) o;
        return Objects.equals(foundObjects, that.foundObjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foundObjects);
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
            "foundObjects=" + foundObjects +
            '}';
    }
}
