package com.rest;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class MultiPartFormData {

    @Test
    public void multi_part_form_data(){
        given().
                baseUri("https://postman-echo.com").
                multiPart("foo1","bar1").
                multiPart("foo2","bar2").
                log().all().
                when().
                post("/post").
                then().assertThat().statusCode(200).log().all();
    }

    @Test
    public void upload_file_multi_part_form_data(){
        String attributes="{\n" +
                "  \"name\":\"File+Handling.txt\",\n" +
                "  \"parent\":{\n" +
                "    \"id\":\"123456\"\n" +
                "  }\n" +
                "}";
        given().
                baseUri("https://postman-echo.com").
                multiPart("file",new File("File+Handling.txt")).
                multiPart("attributes",attributes).
                log().all().
                when().
                post("/post").
                then().assertThat().statusCode(200).log().all();
    }
}
