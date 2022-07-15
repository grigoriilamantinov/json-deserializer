package com.manatee.mymap.entities;

import java.util.Objects;

public class WantedObject {
    private String objectName;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WantedObject that = (WantedObject) o;
        return Objects.equals(objectName, that.objectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectName);
    }
}
