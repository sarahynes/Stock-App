package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteEndpointResponse {

    @JsonProperty("Global Quote")
    private GlobalQuote globalQuote;

    public GlobalQuote getGlobalQuote() {
        return globalQuote;
    }
}
