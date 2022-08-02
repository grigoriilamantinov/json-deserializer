package com.manatee.mymap.common;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.manatee.mymap.entities.Geojson;
import com.manatee.mymap.entities.Point;

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

    double averageLatitude;
    double averageLongitude;
    Iterator<JsonNode> coordinatesJSON;
    Point averagePoint = new Point();

    @Override
    public Geojson deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        String type = node.get("type").asText();

        Geojson geojson = new Geojson("0", new ArrayList<>());
        switch (type) {
//            case "Point":
//                double latitudeSum = 0.0;
//                double longitudeSum = 0.0;
//                coordinatesJSON = node.findValue("coordinates").iterator();
//                latitudeSum += coordinatesJSON.next().asDouble();
//                longitudeSum += coordinatesJSON.next().asDouble();
//                averagePoint = new Point(latitudeSum, longitudeSum);
//                break;

            case "LineString":
                geojson = this.getFromLineString(node);
                break;

            case "MultiPolygon":
                geojson = this.getFromMultiPolygon(node);
                break;

        }
        return geojson;
    }

    private Geojson getFromLineString(JsonNode node){
        var coordinatesJSON = node.findValue("coordinates").iterator();
        List<Double> coordinates = new ArrayList<>();
        while (coordinatesJSON.hasNext()) {
            var pointNode = coordinatesJSON.next();
            coordinates.add(pointNode.get(0).asDouble());
            coordinates.add(pointNode.get(1).asDouble());
        }
        String type = node.get("type").asText();
        return new Geojson(type, coordinates);
    }

    private Geojson getFromMultiPolygon(JsonNode node){
        coordinatesJSON = node.findValue("coordinates").iterator();
        List<Double> coordinates = new ArrayList<>();

        while (coordinatesJSON.hasNext()) {
            var polygon = coordinatesJSON.next().get(0).iterator();

        while (polygon.hasNext()) {
        var pointNode = polygon.next();
        coordinates.add(pointNode.get(0).asDouble());
        coordinates.add(pointNode.get(1).asDouble());
    }

        }
        String type = node.get("type").asText();
        return new Geojson(type, coordinates);
    }
}

