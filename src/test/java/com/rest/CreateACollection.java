package com.rest;

import org.rest.pojo.createACollection.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateACollection {

    @Test
    public void complex_json_pojo_creation(){
        Info info= new Info("Test Collection","This collection makes a request to the Postman Echo service to get a list of request headers sent by an HTTP client.",
                "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
        Header header=new Header("Content-Type","application/json");

        List<Header> headerList=new ArrayList<Header>();
        headerList.add(header);

        Request request=new Request("https://echo.getpostman.com/headers","GET",headerList);

        List<String> exec=new ArrayList<>();
        exec.add("pm.test(\\\"Status code is 200\\\", function () {");
        exec.add("    pm.response.to.have.status(200);");
        exec.add("});");

        Script script=new Script("7d2334fc-a84a-4c3d-b26c-7529afa4c0ae",exec,"text/javascript");

        Event event=new Event("test",script);
        List<Event> eventList=new ArrayList<>();
        eventList.add(event);

        Item item=new Item("Test GET Response",eventList,request);
        List<Item> itemList=new ArrayList<>();
        itemList.add(item);

        Collection collection=new Collection(info,itemList);

        Root root=new Root(collection);

        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-65b37a978b91a6000175b61b-f94a81a0deb7fb46e0fd09aee0063d2b41").
                header("Content-Type","application/json").
                body(root).log().all().
                when().
                post("/collections").
                then().
                log().all().
                assertThat().
                statusCode(200);



    }






}
