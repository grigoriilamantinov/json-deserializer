package com.manatee.mymap.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.manatee.mymap.dto.Geojson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeojsonDeserialaizerLegacy extends StdDeserializer<Geojson> {

    public GeojsonDeserialaizerLegacy() {
        this(null);
    }

    protected GeojsonDeserialaizerLegacy(Class<?> vc) {
        super(vc);
    }

    @Override
    public Geojson deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final JsonNode node = p.getCodec().readTree(p);
        final String type = node.get("type").asText();
        List<Double> coordinates = new ArrayList<>();
        final var coordinatesJSON = node.findValue("coordinates").iterator();
        switch (type) {
            case "Point":
                coordinates = this.getFromPoint(coordinatesJSON);
                break;
            case "MultiPoint":
            case "LineString":
                coordinates = this.getFromLineString(coordinatesJSON);
                break;
            case "Polygon":
            case "MultiLineString":
                coordinates = this.getFromMultiLineString(coordinatesJSON);
                break;
            case "MultiPolygon":
                coordinates = this.getFromMultiPolygon(coordinatesJSON);
                break;
        }
        return new Geojson(type, coordinates);
    }

    private List<Double> getFromPoint(final Iterator<JsonNode> pointNode){
        List<Double> coordinates = new ArrayList<>();
            coordinates.add(pointNode.next().asDouble());
            coordinates.add(pointNode.next().asDouble());
        return coordinates;
    }

    private List<Double> getFromLineString(final Iterator<JsonNode> lineStringNode){
        List<Double> coordinates = new ArrayList<>();
        while (lineStringNode.hasNext()) {
            var pointNode = lineStringNode.next();
            coordinates.add(pointNode.get(0).asDouble());
            coordinates.add(pointNode.get(1).asDouble());
        }
        return coordinates;
    }

    private List<Double> getFromMultiLineString(final Iterator<JsonNode> multiLineStringNode){
        List<Double> coordinates = new ArrayList<>();
        while (multiLineStringNode.hasNext()) {
            var lineStringNode = multiLineStringNode.next().iterator();
            coordinates.addAll(this.getFromLineString(lineStringNode));
        }
        return coordinates;
    }

    private List<Double> getFromMultiPolygon(final Iterator<JsonNode> coordinatesJSON){
        List<Double> coordinates = new ArrayList<>();
        while (coordinatesJSON.hasNext()) {
            var polygon = coordinatesJSON.next().iterator();
            coordinates.addAll(this.getFromMultiLineString(polygon));
        }
        return coordinates;
    }

}

