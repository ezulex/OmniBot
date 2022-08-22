package omnibot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Currency {
    public static String getCurrencyRate(String currency) {

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = formatter.format(localDate);

        String currencyRate = "Валюта не найдена";
        Document doc = null;

        try {
            doc = Jsoup.connect("https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements valutes = doc.select("Valute");
        for (Element valute : valutes) {
            if (valute.select("CharCode").text().equalsIgnoreCase(currency)) {
                String val = valute.select("Value").text();
                String valName = valute.select("Name").text();
                String valCount = valute.select("Nominal").text();
                currencyRate = date + ": " + valCount + " " + valName + " стоит " + val;
            }
        }
        return currencyRate;
    }
}
