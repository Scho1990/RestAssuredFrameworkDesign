package com.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class POSTBDDStyle {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("http://216.10.245.166");
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        responseSpecification=responseSpecBuilder.build();
    }

    @Test
    public void validate_post_response_body(){
        Response response =given(requestSpecification).
                           body("{\n" +
                                   "\"name\":\"Automation by Santosh\",\n" +
                                   "\"isbn\":\"acd1232hhh4353\",\n" +
                                   "\"aisle\":\"227\",\n" +
                                   "\"author\":\"John foe\"\n" +
                                   "}").
                 when().
                           post("/Library/Addbook.php").
                then().spec(responseSpecification).extract().response();
        assertThat(response.path("Msg"),equalTo("successfully added"));
        assertThat(response.path("ID"),is(equalTo("acd1232hhh4353227")));
    }
}
