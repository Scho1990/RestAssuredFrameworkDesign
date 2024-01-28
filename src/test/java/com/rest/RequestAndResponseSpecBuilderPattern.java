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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

public class RequestAndResponseSpecBuilderPattern {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.postman.com");
        requestSpecBuilder.addHeader("X-Api-Key","PMAK-65b37a978b91a6000175b61b-f94a81a0deb7fb46e0fd09aee0063d2b41");
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200).
        expectContentType(ContentType.JSON).
        log(LogDetail.ALL);
        responseSpecification=responseSpecBuilder.build();
    }

    @Test
    public void validate_get_status_code(){
        given(requestSpecification).get("/workspaces").
                then().spec(responseSpecification).extract().response();
    }

    @Test
    public void validate_get_response_body(){
        Response response =given(requestSpecification).get("/workspaces").
                then().spec(responseSpecification).extract().response();
        assertThat(response.path("workspaces[0].name"),equalTo("My Workspace"));
    }
}
