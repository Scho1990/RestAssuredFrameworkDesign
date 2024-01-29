package com.rest;

import io.restassured.config.EncoderConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class FormURLEncodedRequestPayload {

    @Test
    public void multi_part_form_data(){
        given().
                baseUri("https://postman-echo.com").
                config(config.encoderConfig(EncoderConfig.encoderConfig().
                        appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                formParam("key1","value1").
                formParam("key 2","value 2").
                log().all().
                when().
                post("/post").
                then().assertThat().statusCode(200).log().all();
    }
}
