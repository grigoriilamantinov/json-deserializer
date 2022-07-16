package com.manatee.mymap.requests;

import com.manatee.mymap.entities.FoundObject;
import com.manatee.mymap.entities.ResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RequestToOpenStreetMap implements Request {

    @Value("${FIRST_PART_URL}")
    private String FIRST_PART_URL;

    @Value("${LAST_PART_URL}")
    private String LAST_PART_URL;

    @Override
    public List<FoundObject> getRequest(String string) {
        String url = FIRST_PART_URL + string + LAST_PART_URL;
        RestTemplate restTemplate = new RestTemplate();
        final var responseEntity = restTemplate.getForEntity(url, FoundObject[].class);
        var answer = Objects.requireNonNull(responseEntity.getBody());
        return Arrays.stream(answer).collect(Collectors.toList());
    }
}
