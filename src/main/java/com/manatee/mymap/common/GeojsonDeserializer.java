package com.manatee.mymap.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.manatee.mymap.dto.Geojson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeojsonDeserializer extends StdDeserializer<Geojson> {

    public GeojsonDeserializer() {
        this(null);
    }

    protected GeojsonDeserializer(Class<?> vc) {
        super(vc);
    }

    private final static Pattern coordinatePattern = Pattern.compile("\\d+[.]\\d+");

    @Override
    public Geojson deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final JsonNode node = p.getCodec().readTree(p);
        final var coordinatesAsString = String.valueOf(node.get("coordinates"));

        final Matcher coordinatesMatcher = coordinatePattern.matcher(coordinatesAsString);
        List<Double> coordinates = new ArrayList<>();

        while (coordinatesMatcher.find()) {
            coordinates.add(Double.parseDouble(coordinatesMatcher.group()));
        }

        final String type = node.get("type").asText();
        return new Geojson(type, coordinates);
    }
}
