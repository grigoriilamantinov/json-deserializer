package com.manatee.mymap.services;

import com.manatee.mymap.entities.FoundObject;
import com.manatee.mymap.requests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyMapService implements Service {

    final Request request;

    public MyMapService(
        @Autowired Request request
    ) {
        this.request = request;
    }

    @Override
    public List<FoundObject> getSearchResult(final String searchName) {
        return request.getRequest(searchName);
    }

    @Override
    public FoundObject getOneObject(final String objectName) {
        return this.getSearchResult(objectName).stream()
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(
                String.format("There is no object with name %s", objectName)));
    }

}
