import api.AlphaVantageClient;
import api.GlobalQuote;
import api.OverviewEndpointResponse;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        new Main().run(args);
    }

    /**
     * This application will take a stock ticker as a command line input. For example, "AAPL" is Apple's
     * ticker on the New York Stock Exchange. The program performs 2 real-time API calls to AlphaVantage,
     * an API for sourcing stock data. It will print out information about the stock to the user. Examples
     * of data shown are daily price, low/high price for the day, volume, percent change, etc.
     *
     * @param args stock ticker (example: "AAPL")
     * @throws IOException when JSON parsing fails
     * @throws InterruptedException when API call is interrupted
     */
    public void run(String[] args) throws IOException, InterruptedException {
        if (args.length != 1){
            System.out.println("Please provide single stock ticker as an argument.");
            return;
        }

        String ticker = args[0].toUpperCase();

        ReadFile fileReader = new ReadFile();
        String apiKey = fileReader.getFileText("src/AlphaVantageKey.txt");

        AlphaVantageClient client = new AlphaVantageClient(apiKey);
        GlobalQuote quote = client.getQuote(ticker);
        OverviewEndpointResponse overview = client.getOverview(ticker);

        if (quote.getSymbol() == null){
            System.out.println("Unable to find data for ticker " + ticker + ". Please provide a valid ticker.");
            return;
        }

        System.out.println("Symbol: " + quote.getSymbol());
        System.out.println("Description: " + overview.getDescription().substring(0, 200) + "...");
        System.out.println("Open: " + quote.getOpen());
        System.out.println("High: " + quote.getHigh());
        System.out.println("Low: " + quote.getLow());
        System.out.println("Price: " + quote.getPrice());
        System.out.println("Volume: " + quote.getVolume());
        System.out.println("Latest Trading Day: " + quote.getLatestTradingDay());
        System.out.println("Previous Close: " + quote.getPreviousClose());
        System.out.println("Change: " + quote.getChange());
        System.out.println("Change Percent: " + quote.getChangePercent());
        System.out.println("More Information: https://finance.yahoo.com/quote/" + quote.getSymbol());
    }
}
