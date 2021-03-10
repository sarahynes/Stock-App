import api.AlphaVantageClient;
import api.GlobalQuote;
import api.OverviewEndpointResponse;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        new Main().run(args);
    }

    /**
     * Returns data for given stock ticker
     * @param args stock ticker
     * @throws IOException
     * @throws InterruptedException
     */
    public void run(String[] args) throws IOException, InterruptedException {
        if (args.length == 0){
            System.out.println("Please provide single stock ticker as an argument.");
            return;
        }

        String ticker = args[0].toUpperCase();

        ReadFile fileReader = new ReadFile();
        String apiKey = fileReader.getFileText("src/AlphaVantageKey.txt");

        AlphaVantageClient client = new AlphaVantageClient(apiKey);
        GlobalQuote quote = client.getQuote(ticker);
        OverviewEndpointResponse overview = client.getOverview(ticker);

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
