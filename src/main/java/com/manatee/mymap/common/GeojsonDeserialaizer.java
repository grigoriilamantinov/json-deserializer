package com.manatee.mymap.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.manatee.mymap.dto.CoordinatePoint;
import com.manatee.mymap.dto.Geojson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeojsonDeserialaizer extends StdDeserializer<Geojson> {
    public GeojsonDeserialaizer() {
        this(null);
    }

    protected GeojsonDeserialaizer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Geojson deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final JsonNode node = p.getCodec().readTree(p);
        final String type = node.get("type").asText();
        List<CoordinatePoint> coordinates = new ArrayList<>();
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

    private List<CoordinatePoint> getFromPoint(final Iterator<JsonNode> pointNode){
        final CoordinatePoint coordinatePoint = new CoordinatePoint();
        coordinatePoint.setLongitude(pointNode.next().asDouble());
        coordinatePoint.setLatitude(pointNode.next().asDouble());

        final List<CoordinatePoint> coordinates = new ArrayList<>();
        coordinates.add(coordinatePoint);

        return coordinates;
    }

    private List<CoordinatePoint> getFromLineString(final Iterator<JsonNode> lineStringNode){
        List<CoordinatePoint> coordinates = new ArrayList<>();
        while (lineStringNode.hasNext()) {
            var pointNode = lineStringNode.next().iterator();
            coordinates.addAll(this.getFromPoint(pointNode));
        }
        return coordinates;
    }

    private List<CoordinatePoint> getFromMultiLineString(final Iterator<JsonNode> multiLineStringNode){
        List<CoordinatePoint> coordinates = new ArrayList<>();
        while (multiLineStringNode.hasNext()) {
            var lineStringNode = multiLineStringNode.next().iterator();
            coordinates.addAll(this.getFromLineString(lineStringNode));
        }
        return coordinates;
    }

    private List<CoordinatePoint> getFromMultiPolygon(final Iterator<JsonNode> coordinatesJSON){
        List<CoordinatePoint> coordinates = new ArrayList<>();
        while (coordinatesJSON.hasNext()) {
            var polygon = coordinatesJSON.next().iterator();
            coordinates.addAll(this.getFromMultiLineString(polygon));
        }
        return coordinates;
    }

}

