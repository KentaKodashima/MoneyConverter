package money_converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MoneyAPI {

  private ArrayList<String> countries;
  private ArrayList<Double> currencyRates;
  private Map<String, Double> moneyObj;

  public MoneyAPI() {
    moneyObj = new HashMap<String, Double>();
    countries = new ArrayList<>();
    currencyRates = new ArrayList<>();
  }

  private void addCurrency(String country, Double rate) {
    countries.add(country);
    currencyRates.add(rate);
    moneyObj.put(country, rate);
  }

  // Getters
  public ArrayList<String> getCountries() {
    return countries;
  }

  public ArrayList<Double> getRates() {
    return currencyRates;
  }

  public Map<String, Double> getMoneyObj() {
    return moneyObj;
  }



  public void connectToAPI() {
    String baseURLString = "https://openexchangerates.org/api/";
    String latestURLString = "latest.json";
    //String currenciesURLString = "currencies.json";
    String appIDString = "?app_id=7c21805a603041edaa9a4215751f75e0";

    // Making api url string
    String latestURL = baseURLString + latestURLString + appIDString;

    // Making api requests and make an object out of the response
    try {
      HttpResponse<JsonNode> jsonForLatest = Unirest.get(latestURL).asJson();

      if (jsonForLatest.getStatus() == 200 || jsonForLatest.getStatus() == 201) {
        JsonNode node = jsonForLatest.getBody();

        JSONObject currencyObj = node.getObject().getJSONObject("rates");

        Double jpy = currencyObj.getDouble("JPY");
        Double cad = currencyObj.getDouble("CAD");
        Double usd = currencyObj.getDouble("USD");

        addCurrency("JPY", jpy);
        addCurrency("CAD", cad);
        addCurrency("USD", usd);
      }
    } catch (UnirestException e) {
      e.printStackTrace();
    }

  }

}
