import controllers.HeartbeatController;
import controllers.RentalLocationsController;
import io.github.jdiemke.triangulation.Vector2D;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.enableCorsForAllOrigins();
        }).start(8080);
        Map<String, Vector2D> triangulatorData = new HashMap<>();

        HeartbeatController heartbeatController = new HeartbeatController();
        RentalLocationsController rentalLocationsController = new RentalLocationsController(triangulatorData);

        app.get("/", heartbeatController::handle);
        app.post("/rentals/region", rentalLocationsController::handlePost);
        app.get("/rentals/region", rentalLocationsController::handleGet);
    }
}
