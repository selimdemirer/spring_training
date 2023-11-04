package com.cydeo.controller;

import com.cydeo.dto.User;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/cydeo")
public class Consume_RestTemplate { // Now, I am trying the consume third party API. I created this end point (localhost:8080/cydeo) and as an output of this end point I am gonna show data, but this data is consumed by another end point which is "jsonplaceholder...". I use Rest Template. I already created a bean.

    private final String URI = "https://jsonplaceholder.typicode.com/users";

    private final RestTemplate restTemplate;

    public Consume_RestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<User[]> readAllUsers(){

        return restTemplate.getForEntity(URI, User[].class); // 1.getForEntity: Basically get the output, map it to your DTO and then show the output from your end point // If you have your own DTO use this method!
    }

    @GetMapping("{id}")
    public Object readUser(@PathVariable("id") Integer id){

        String URL = URI + "/id";

        return restTemplate.getForObject(URL, Object.class,id); // 2.getForObject: You don't have DTO, it is just taking from the third party API and then whatever Json output result is you see in your end point // If you don't have your own DTO use this method!

    }

    @GetMapping("/test")
    public ResponseEntity<Object> consumePostFromDummyApi(){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("app-id","6298ebfecd0551211fce37a6");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("https://dummyapi.io/data/v1/user?limit=10", HttpMethod.GET,entity,Object.class); // 3.exchange: I created my end point (test), I wanna consume api (dummyapi...) and I passed my id in the header to be able to see the output // If you want to pass any header while you are consuming third party API use this method!

    }


}
