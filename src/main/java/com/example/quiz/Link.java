package com.example.quiz;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Link {
    private HttpClient client;
    private  ArrayList<Object> EasyResults;
    public ArrayList<Result> EasyQuestions = new ArrayList<>();


    public String link;

    public Integer category;



    public Link(String link){
        this.link = link;
    }

    public Link(Integer category){
        this.category = category;
    }
    public ArrayList<Result> makeObject(){
        client = HttpClient.newHttpClient();
        String[] diffuclties = {"easy","medium","hard"};
        for(String diff : diffuclties){
            ValidateLink(String.format("https://opentdb.com/api.php?amount=5&category=%d&difficulty=%s&type=multiple",this.category,diff));
            for(Object obj : EasyResults){
                Gson gson = new Gson();
                Result result  = gson.fromJson(gson.toJson(obj),Result.class);
                EasyQuestions.add(result);
        }}
        return EasyQuestions;
    }


    private String ValidateLink(String link){
        //https://opentdb.com/api.php?amount=5&category=9&difficulty=easy&type=multiple
        HttpRequest request = HttpRequest.newBuilder(URI.create(link)).GET().build();
        try{
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            String arr =  response.body();
            Gson gson = new Gson();
            Question question  = gson.fromJson(arr,Question.class);
            //System.out.println(question.results.get(0));
            // Result result = new Result(question.results);
            EasyResults = question.results;

            return "Success";
        } catch (IOException | InterruptedException e ){
            System.out.println(e);
            return String.format("%s -> %s",link, false);
        }

    }


}
