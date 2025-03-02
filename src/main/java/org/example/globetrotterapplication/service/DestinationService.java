package org.example.globetrotterapplication.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.example.globetrotterapplication.api.model.Destination;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DestinationService {


    private final List<Destination> destinations;


    public DestinationService() {

        String jsonInput = "[{\"city\":\"Paris\",\"country\":\"France\",\"clues\":[\"This city is home to a famous tower that sparkles every night.\",\"Known as the 'City of Love' and a hub for fashion and art.\"],\"fun_fact\":[\"The Eiffel Tower was supposed to be dismantled after 20 years but was saved because it was useful for radio transmissions!\",\"Paris has only one stop sign in the entire city—most intersections rely on priority-to-the-right rules.\"],\"trivia\":[\"This city is famous for its croissants and macarons. Bon appétit!\",\"Paris was originally a Roman city called Lutetia.\"]},{\"city\":\"Tokyo\",\"country\":\"Japan\",\"clues\":[\"This city has the busiest pedestrian crossing in the world.\",\"You can visit an entire district dedicated to anime, manga, and gaming.\"],\"fun_fact\":[\"Tokyo was originally a small fishing village called Edo before becoming the bustling capital it is today!\",\"More than 14 million people live in Tokyo, making it one of the most populous cities in the world.\"],\"trivia\":[\"The city has over 160,000 restaurants, more than any other city in the world.\",\"Tokyo’s subway system is so efficient that train delays of just a few minutes come with formal apologies.\"]},{\"city\":\"New York\",\"country\":\"USA\",\"clues\":[\"Home to a green statue gifted by France in the 1800s.\",\"Nicknamed 'The Big Apple' and known for its Broadway theaters.\"],\"fun_fact\":[\"The Statue of Liberty was originally a copper color before oxidizing to its iconic green patina.\",\"Times Square was once called Longacre Square before being renamed in 1904.\"],\"trivia\":[\"New York City has 468 subway stations, making it one of the most complex transit systems in the world.\",\"The Empire State Building has its own zip code: 10118.\"]}]";


        destinations = new ArrayList<>();
        JSONArray destinationsJsonArray = new JSONArray(jsonInput);

        int size = destinationsJsonArray.length();
        for (int i = 0; i < size; i++) {
            JSONObject destinationJson = destinationsJsonArray.getJSONObject(i);

            String city = destinationJson.getString("city");
            String country = destinationJson.getString("country");
            List<String> clues = jsonArrayToList(destinationJson.getJSONArray("clues"));
            List<String> funFact = jsonArrayToList(destinationJson.getJSONArray("fun_fact"));
            List<String> trivia = jsonArrayToList(destinationJson.getJSONArray("trivia"));

            Destination destination = new Destination(city, country, clues, funFact, trivia);
            destinations.add(destination);
        }

        for (int i = 0; i < size; i++) {
           Destination destination = destinations.get(i);
           List<String> incorrectOptions = getIncorrectOptions(i,3,size);
           int randomIndex = ThreadLocalRandom.current().nextInt(0, 4);
           incorrectOptions.add(randomIndex,destination.getCity());
           destination.setOptions(incorrectOptions);
        }

    }

    private List<String> getIncorrectOptions(Integer rightOption,Integer num,Integer destCount){
        List<String> incorrectOptions = new ArrayList<>();
        while (num > 0){
            int randomIndex = ThreadLocalRandom.current().nextInt(0, destCount);
            if(randomIndex != rightOption){
                incorrectOptions.add(destinations.get(randomIndex).getCity());
                num--;
            }

        }
        return incorrectOptions;
    }

    private List<String> jsonArrayToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }

    public Destination getDestination(int index) {
        return destinations.get(index);
    }
}
