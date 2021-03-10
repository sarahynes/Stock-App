package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OverviewEndpointResponse {

    @JsonProperty("Description")
    private String description;

    public String getDescription() {
        return description;
    }
}
