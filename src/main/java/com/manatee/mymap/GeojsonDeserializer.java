package com.manatee.mymap;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.manatee.mymap.entities.Geojson;
import com.manatee.mymap.entities.Point;

import java.io.IOException;
import java.util.Iterator;

public class GeojsonDeserializer extends StdDeserializer<Geojson> {

    public GeojsonDeserializer() {
        this(null);
    }

    protected GeojsonDeserializer(Class<?> vc) {
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

        switch (type) {
            case "Point":
                double latitudeSum = 0.0;
                double longitudeSum = 0.0;
                coordinatesJSON = node.findValue("coordinates").iterator();
                    latitudeSum += coordinatesJSON.next().asDouble();
                    longitudeSum += coordinatesJSON.next().asDouble();
                averagePoint = new Point(latitudeSum, longitudeSum);
                break;

            case "LineString":
                averagePoint = this.getFromLineString(node);
                break;

            case "MultiPolygon":
                averagePoint = this.getFromMultiPolygon(node);
                break;

        }
        return new Geojson(type, averagePoint);
    }

    private Point getFromLineString(JsonNode node){
        double latitudeSum = 0.0;
        double longitudeSum = 0.0;
        int counter = 0;

        coordinatesJSON = node.findValue("coordinates").iterator();
        if (node.get("type").asText().equals("MultiPolygon")) {
            coordinatesJSON.next().get(0).iterator();
        }
        while (coordinatesJSON.hasNext()) {
            var pointNode = coordinatesJSON.next();
            latitudeSum += pointNode.get(0).asDouble();
            longitudeSum += pointNode.get(1).asDouble();
            counter++;
        }
        averageLatitude = latitudeSum / counter;
        averageLongitude = longitudeSum / counter;
        return new Point(averageLatitude, averageLongitude);
    }

    private Point getFromMultiPolygon(JsonNode node){
        double latitudeSum = 0.0;
        double longitudeSum = 0.0;
        int counter = 0;
        coordinatesJSON = node.findValue("coordinates").iterator();
        while (coordinatesJSON.hasNext()) {
            var polygon = coordinatesJSON.next().get(0).iterator();

            while (polygon.hasNext()) {
            var pointNode = polygon.next();
            latitudeSum += pointNode.get(0).asDouble();
            longitudeSum += pointNode.get(1).asDouble();
            counter++;
            }

        }
        averageLatitude = latitudeSum / counter;
        averageLongitude = longitudeSum / counter;
        return new Point(averageLatitude, averageLongitude);
    }
}
