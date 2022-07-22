package com.manatee.mymap;

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
import java.util.stream.Collectors;

public class GeojsonDeserializer extends StdDeserializer<Geojson> {

    public GeojsonDeserializer() {
        this(null);
    }

    protected GeojsonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Geojson deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        String type = node.get("type").asText();
        List<Double> doubles = new ArrayList<>();
        Point type2 = new Point();
        Double latitudeSum = 0.0;
        Double longitudeSum = 0.0;
        int counter = 0;
        Iterator<JsonNode> type3;
        Double averageLatitude;
        Double averageLongitude;

        switch (type) {
            case "Point":
                type3 = node.findValue("coordinates").iterator();
                while (type3.hasNext()) {
                    doubles.add(Double.parseDouble(type3.next().asText()));
                }
                counter = 0;
                for (int i = 0; i < doubles.size(); i++) {
                    if (this.dividesByTwo(i)) {
                        latitudeSum += doubles.get(i);
                        longitudeSum += doubles.get(i + 1);
                        counter++;
                    }
                }
                averageLatitude = latitudeSum / counter;
                averageLongitude = longitudeSum / counter;
                type2 = new Point(averageLatitude, averageLongitude);
                break;
            case "LineString":
                type3 = node.findValue("coordinates").iterator();
                while (type3.hasNext()) {
                    doubles.add(Double.parseDouble(type3.next().asText()));
                }

                for (int i = 0; i < doubles.size(); i++) {
                    if (this.dividesByTwo(i)) {
                        latitudeSum += doubles.get(i);
                        longitudeSum += doubles.get(i + 1);
                        counter++;
                    }
                }
                averageLatitude = latitudeSum / counter;
                averageLongitude = longitudeSum / counter;
                type2 = new Point(averageLatitude, averageLongitude);
                break;
        }
        return new Geojson(type, type2);
    }

    private boolean dividesByTwo(int a){
        return (a%2 == 0);
    }
}
