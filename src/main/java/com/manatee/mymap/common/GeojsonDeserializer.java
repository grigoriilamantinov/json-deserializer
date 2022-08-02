package com.manatee.mymap.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.manatee.mymap.entities.Geojson;
import com.manatee.mymap.entities.AveragePoint;

import java.io.IOException;
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
        double latitudeSum = 0.0;
        double longitudeSum = 0.0;
        int counter = 0;

        for (int i = 0; coordinatesMatcher.find(); i++) {
            if (i %2 == 0) {
                latitudeSum += Double.parseDouble(coordinatesMatcher.group());
                counter++;
            } else {
                longitudeSum += Double.parseDouble(coordinatesMatcher.group());
            }
        }

        final double averageLatitude = latitudeSum / counter;
        final double averageLongitude = longitudeSum / counter;
        final String type = node.get("type").asText();

        return new Geojson(type, new AveragePoint(averageLatitude, averageLongitude));
    }

//    private Point getFromLineString(JsonNode node){
//        double latitudeSum = 0.0;
//        double longitudeSum = 0.0;
//        int counter = 0;
//
//        coordinatesJSON = node.findValue("coordinates").iterator();
//
//        while (coordinatesJSON.hasNext()) {
//            var pointNode = coordinatesJSON.next();
//            latitudeSum += pointNode.get(0).asDouble();
//            longitudeSum += pointNode.get(1).asDouble();
//            counter++;
//        }
//        averageLatitude = latitudeSum / counter;
//        averageLongitude = longitudeSum / counter;
//        return new Point(averageLatitude, averageLongitude);
//    }
//
//    private Point getFromMultiPolygon(JsonNode node){
//        double latitudeSum = 0.0;
//        double longitudeSum = 0.0;
//        int counter = 0;
//        Iterator<JsonNode> polygon;
//        coordinatesJSON = node.findValue("coordinates").iterator();
//        while (coordinatesJSON.hasNext()) {
//            if(node.get("type").asText().equals("Polygon")) {
//                polygon = coordinatesJSON.next().get(0).iterator();
//            } else {
//                polygon = coordinatesJSON.next().iterator();
//            }
//
//            while (polygon.hasNext()) {
//            var pointNode = polygon.next();
//            latitudeSum += pointNode.get(0).asDouble();
//            longitudeSum += pointNode.get(1).asDouble();
//            counter++;
//            }
//
//        }
//        averageLatitude = latitudeSum / counter;
//        averageLongitude = longitudeSum / counter;
//        return new Point(averageLatitude, averageLongitude);
//    }
}
