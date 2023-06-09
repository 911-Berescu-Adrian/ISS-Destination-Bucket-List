package com.example.BucketList.controller;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.service.DestinationService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/destination")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @PostMapping("/save")
    public ResponseEntity<Destination> saveDestination(@RequestBody Destination destination) {
        return new ResponseEntity<Destination>(destinationService.saveDestination(destination), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Destination>> getAllDestinations() {
        return new ResponseEntity<List<Destination>>(destinationService.getAllDestinations(), HttpStatus.OK);
    }

    @GetMapping("/{destinationId}")
    public ResponseEntity<Destination> getSingleDestination(@PathVariable Long destinationId) {
        return new ResponseEntity<Destination>(destinationService.getSingleDestination(destinationId), HttpStatus.OK);
    }

    @GetMapping("/hardcodeDestinations")
    public List<Destination> addDestinationsInDatabase() {
        List<String> titles = new ArrayList<>();
        List<Destination> destinations = new ArrayList<>();

        titles.add("Madrid");
        titles.add("Cluj-Napoca");
        titles.add("Prague");
        titles.add("Berlin");
        titles.add("Milano");
        titles.add("Roma");
        titles.add("Napoli");
        titles.add("Stockholm");
        titles.add("London");
        titles.add("Budapest");
        titles.add("Bucharest");
        titles.add("Lisbon");
        titles.add("Torino");
        titles.add("Barcelona");
        titles.add("Valencia");
        titles.add("Frankfurt");

        for (String city : titles) {
            Destination destination = new Destination();
            destination.setTitle(city);
            destination.setImage(this.getImage(city));
//            destination.setDescription(this.getDescription(city));
            destination.setGeolocation(this.getGeolocation(city));
            destinations.add(destination);
        }

        return destinations;
    }

    public String getGeolocation(String city) {
        try {
            String encodedDestination = URLEncoder.encode(city, StandardCharsets.UTF_8);
            String url = "https://geocode.maps.co/search?q=" + encodedDestination;

            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                String geolocationData = response.toString();
                double[] coords = extractCoordinates(response.toString());
                String lat = String.valueOf(coords[0]);
                String lon = String.valueOf(coords[1]);
                return lat + "," + lon;
            } else {
                return "Request failed. Response Code: " + responseCode;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Caught exception: " + e;
        }
    }

    private static double[] extractCoordinates(String geolocationData) {
        try {
            JSONArray jsonArray = new JSONArray(geolocationData);

            if (jsonArray.length() > 0) {
                JSONObject firstObject = jsonArray.getJSONObject(0);
                double lat = firstObject.getDouble("lat");
                double lon = firstObject.getDouble("lon");
                return new double[]{lat, lon};
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getImage(String city) {
        String encodedCityName = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String apiKey = "36998343-211d018d1523f85df7020a1f6";
        String url = "https://pixabay.com/api/?key=" + apiKey + "&q=" + encodedCityName + "&category=places";

        try {

            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject json = new JSONObject(response.toString());
                JSONArray hits = json.getJSONArray("hits");

                if (hits.length() > 0) {
                    int randomIndex = (int) (Math.random() * hits.length());
                    return hits.getJSONObject(randomIndex).getString("webformatURL");
                } else {
                    return "No image found for the city.";
                }
            }

            connection.disconnect();

            return "Request failed. Response Code: " + responseCode;
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDescription(String city) {
        try {
            String encodedCityName = URLEncoder.encode(city, StandardCharsets.UTF_8);
            String url = "http://api.geonames.org/wikipediaSearchJSON?q=" + encodedCityName +
                    "&maxRows=1&username=callisto8008";

            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject json = new JSONObject(response.toString());
                JSONArray geonames = json.getJSONArray("geonames");

                if (geonames.length() > 0) {
                    String description = geonames.getJSONObject(0).getString("summary");
                    return cutLastWord(description) + "...";
                } else {
                    return "No description found for the city.";
                }
            }

            connection.disconnect();
            return "Request failed. Response Code: " + responseCode;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return "Caught exception: " + e;
        }
    }

    private static String cutLastWord(String str) {
        String[] words = str.trim().split(" ");

        if (words.length > 0) {
            // Create a new array without the last word
            String[] trimmedWords = Arrays.copyOfRange(words, 0, words.length - 1);

            // Join the trimmed words with a space separator
            return String.join(" ", trimmedWords);
        } else {
            return "";
        }
    }


}
