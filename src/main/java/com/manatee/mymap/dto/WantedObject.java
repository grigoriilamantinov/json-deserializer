package com.manatee.mymap.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WantedObject {
    private String objectName;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(final String objectName) {
        this.objectName = objectName;
    }

    @Override
    public String toString() {
        return "WantedObject{" +
            "objectName='" + objectName + '\'' +
            '}';
    }
}
