package com.rest;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class RequestParameters {

    @Test
    public void request_single_query_parameter_using_param(){
        given().param("foo1","bar1").log().all().
                when().
                get("https://postman-echo.com/get").
                then().assertThat().statusCode(200).log().all();
    }

    @Test
    public void request_single_query_parameter_using_query_param(){
        given().queryParam("foo1","bar1").log().all().
                when().
                get("https://postman-echo.com/get").
                then().assertThat().statusCode(200).log().all();
    }

    @Test
    public void request_multiple_query_parameter_using_query_param(){
        given().
                queryParam("foo1","bar1").queryParam("foo2","bar2")
                .log().all().
                when().
                get("https://postman-echo.com/get").
                then().assertThat().statusCode(200).log().all();
    }

    @Test
    public void request_multiple_query_parameter_using_query_params(){
        given().
                queryParams("foo1","bar1","foo2","bar2")
                .log().all().
                when().
                get("https://postman-echo.com/get").
                then().assertThat().statusCode(200).log().all();
    }

    @Test
    public void request_multiple_query_parameter_using_hashmap_in_query_params(){
        HashMap<String,String> queryParams=new HashMap<>();
        queryParams.put("foo1","bar1");
        queryParams.put("foo2","bar2");

        given().
                queryParams(queryParams)
                .log().all().
                when().
                get("https://postman-echo.com/get").
                then().assertThat().statusCode(200).log().all();
    }

    @Test
    public void request_multiValue_query_parameter(){
        given().
                queryParam("foo1","bar1,bar2,bar3")
                .log().all().
                when().
                get("https://postman-echo.com/get").
                then().assertThat().statusCode(200).log().all();
    }

    @Test
    public void path_parameter(){
        given().
                baseUri("https://reqres.in").
                pathParam("userId","2")
                .log().all().
                when().
                get("/api/users/{userId}").
                then().assertThat().statusCode(200).log().all();
    }
}
