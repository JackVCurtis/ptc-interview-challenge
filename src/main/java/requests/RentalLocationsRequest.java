package requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class RentalLocationsRequest {
    @JsonProperty("locations")
    private ArrayList<RentalLocation> locations;

    @JsonProperty("locations")
    public ArrayList<RentalLocation> getLocations() {
        return locations;
    }
    @JsonProperty("locations")
    public void setLocations(ArrayList<RentalLocation> locations) {
        this.locations = locations;
    }
}
