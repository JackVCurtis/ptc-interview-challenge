package controllers;

import io.javalin.http.Context;

public class HeartbeatController {
    public void handle(Context ctx) {
        ctx.result("I'm alive");
    }
}
