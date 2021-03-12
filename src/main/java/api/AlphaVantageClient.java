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

    private String apiKey;

    public AlphaVantageClient(String apiKey) {
        this.apiKey = apiKey;
    }

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    /**
     * Returns global quote endpoint response for given stock ticker
     * @param ticker stock ticker to gather data on
     * @return endpoint response containing data for given stock ticker
     * @throws IOException when JSON parsing fails
     * @throws InterruptedException when API call is interrupted
     */
    public GlobalQuote getQuote(String ticker) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + ticker + "&apikey=" + apiKey))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        QuoteEndpointResponse endpointResponse = objectMapper.readValue(response.body(), QuoteEndpointResponse.class);

        return endpointResponse.getGlobalQuote();
    }

    /**
     * Returns overview endpoint response for given stock ticker
     * @param ticker stock ticker to gather data on
     * @return endpoint response containing overview for given stock ticker
     * @throws IOException
     * @throws InterruptedException
     */
    public OverviewEndpointResponse getOverview(String ticker) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + ticker + "&apikey=" + apiKey))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        OverviewEndpointResponse endpointResponse = objectMapper.readValue(response.body(), OverviewEndpointResponse.class);

        return endpointResponse;
    }
}