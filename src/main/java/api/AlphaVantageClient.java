package api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class AlphaVantageClient{

    private final String apiKey;

    public AlphaVantageClient(String apiKey) {
        this.apiKey = apiKey;
    }

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private HttpResponse<String> getResponse(String ticker, String function) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.alphavantage.co/query?function=" + function + "&symbol=" + ticker + "&apikey=" + apiKey))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * Returns global quote endpoint response for given stock ticker
     * @param ticker stock ticker to gather data on
     * @return endpoint response containing data for given stock ticker
     * @throws IOException when JSON parsing fails
     * @throws InterruptedException when API call is interrupted
     */
    public GlobalQuote getQuote(String ticker) throws IOException, InterruptedException {

        HttpResponse<String> response = getResponse(ticker, "GLOBAL_QUOTE");

        ObjectMapper objectMapper = new ObjectMapper();
        QuoteEndpointResponse endpointResponse = objectMapper.readValue(response.body(), QuoteEndpointResponse.class);

        return endpointResponse.getGlobalQuote();
    }

    /**
     * Returns overview endpoint response for given stock ticker
     * @param ticker stock ticker to gather data on
     * @return endpoint response containing overview for given stock ticker
     * @throws IOException when JSON parsing fails
     * @throws InterruptedException when API call is interrupted
     */
    public OverviewEndpointResponse getOverview(String ticker) throws IOException, InterruptedException {

        HttpResponse<String> response = getResponse(ticker, "OVERVIEW");

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper.readValue(response.body(), OverviewEndpointResponse.class);
    }
}