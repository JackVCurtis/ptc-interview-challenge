package controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jdiemke.triangulation.DelaunayTriangulator;
import io.github.jdiemke.triangulation.NotEnoughPointsException;
import io.github.jdiemke.triangulation.Vector2D;
import io.javalin.http.Context;
import requests.RentalLocationsRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RentalLocationsController {
    private Map<String, Vector2D> triangulatorData;
    private ObjectMapper mapper = new ObjectMapper();

    public RentalLocationsController(Map<String, Vector2D> triangulatorData) {
        this.triangulatorData = triangulatorData;
    }

    public void handlePost(Context ctx) {
        RentalLocationsRequest request = ctx.bodyAsClass(RentalLocationsRequest.class);
        request.getLocations().forEach(loc -> {
            this.triangulatorData.put(loc.getName(), new Vector2D(loc.getxCoord(), loc.getyCoord()));
        });
        ctx.result("Ok");
    }

    public void handleGet(Context ctx) {
        DelaunayTriangulator triangulator = new DelaunayTriangulator(new ArrayList<Vector2D>(this.triangulatorData.values()));
        try {
            triangulator.triangulate();
        } catch (NotEnoughPointsException e) {
            ctx.result("Not enough points");
        }

        try {
            ctx.result(this.mapper.writeValueAsString(triangulator.getPointSet()));
        } catch (JsonProcessingException e) {
            ctx.status(500);
        }
    }
}
