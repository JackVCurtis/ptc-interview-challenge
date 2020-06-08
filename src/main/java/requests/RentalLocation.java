package requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RentalLocation {
    @JsonProperty("name")
    private String name;
    @JsonProperty()
    private Double xCoord;
    @JsonProperty()
    private Double yCoord;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("xCoord")
    public Double getxCoord() {
        return xCoord;
    }
    @JsonProperty("xCoord")
    public void setxCoord(Double xCoord) {
        this.xCoord = xCoord;
    }

    @JsonProperty("yCoord")
    public Double getyCoord() {
        return yCoord;
    }
    @JsonProperty("yCoord")
    public void setyCoord(Double yCoord) {
        this.yCoord = yCoord;
    }
}
