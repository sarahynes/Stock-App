package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GlobalQuote {

    @JsonProperty("01. symbol")
    private String symbol;

    @JsonProperty("02. open")
    private String open;

    @JsonProperty("03. high")
    private String high;

    @JsonProperty("04. low")
    private String low;

    @JsonProperty("05. price")
    private String price;

    @JsonProperty("06. volume")
    private String volume;

    @JsonProperty("07. latest trading day")
    private String latestTradingDay;

    @JsonProperty("08. previous close")
    private String previousClose;

    @JsonProperty("09. change")
    private String change;

    @JsonProperty("10. change percent")
    private String changePercent;

    public String getSymbol() {
        return symbol;
    }

    public String getOpen() {
        return open;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getPrice() {
        return price;
    }

    public String getVolume() {
        return volume;
    }

    public String getLatestTradingDay() {
        return latestTradingDay;
    }

    public String getPreviousClose() {
        return previousClose;
    }

    public String getChange() {
        return change;
    }

    public String getChangePercent() {
        return changePercent;
    }
}
