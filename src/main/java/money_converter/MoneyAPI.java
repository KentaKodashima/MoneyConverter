package money_converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MoneyAPI {

  private int[] rates;
  private String[] currencies;

  String baseURLString = "https://openexchangerates.org/api/";
  String latestURLString = "latest.json";
  String currenciesURLString = "currencies.json";
  String appIDString = "?app_id=7c21805a603041edaa9a4215751f75e0";
//  URL latestURL;
//  URL currenciesURL;

  StringBuilder latestString = new StringBuilder();
  StringBuilder currenciesString = new StringBuilder();

  // Making api url string
  String latestURL = baseURLString + latestURLString + appIDString;
  String currenciesURL = baseURLString + currenciesURLString + appIDString;

  // Making api request
  {
    try {
      HttpResponse<JsonNode> jsonResponse = Unirest.get(latestURL).asJson();
    } catch (UnirestException e) {
        e.printStackTrace();
    }
  }

//  // Making api url
//  {
//    try {
//      latestURL = new URL(baseURLString + latestURLString + appIDString);
//      currenciesURL = new URL(baseURLString + currenciesURLString + appIDString);
//    } catch (MalformedURLException e) {
//      e.printStackTrace();
//    }
//  }
//
//  // Making api request
//  {
//    try {
//      HttpURLConnection latestRequest = (HttpURLConnection) latestURL.openConnection();
//      HttpURLConnection currenciesRequest = (HttpURLConnection) currenciesURL.openConnection();
//
//      InputStream latestInput = latestRequest.getInputStream();
//      InputStream currenciesInput = currenciesRequest.getInputStream();
//
//      BufferedReader latestReader = new BufferedReader(new InputStreamReader(latestInput));
//      BufferedReader currenciesReader = new BufferedReader(new InputStreamReader(currenciesInput));
//
//      String latestLine;
//      String currenciesLine;
//
//      while ((latestLine = latestReader.readLine()) != null) {
//        latestString.append(latestLine);
//      }
//
//      while ((currenciesLine = currenciesReader.readLine()) != null) {
//        currenciesString.append(currenciesLine);
//      }
//
//      latestReader.close();
//      currenciesReader.close();
//
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }

  ObjectMapper mapper = new ObjectMapper();


}
