package com.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class RequestAndResponseSpecificationExample {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass(){
         requestSpecification= given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-65b37a978b91a6000175b61b-f94a81a0deb7fb46e0fd09aee0063d2b41");
         responseSpecification= RestAssured.expect().statusCode(200).contentType(ContentType.JSON);
    }

    @Test
    public void validate_get_status_code(){
        given().spec(requestSpecification).
        when().
                get("/workspaces").
        then().
                spec(responseSpecification);
    }

    @Test
    public void validate_get_response_body(){
        given(requestSpecification).
                when().
                get("/workspaces").
                then().
                spec(responseSpecification).
                body("workspaces.name",hasItems("My Workspace","AddActiveDirectory"),
                        "workspaces[0].name",equalTo("My Workspace"),
                        "workspaces[0].name",is(equalTo("My Workspace")),
                        "workspaces.size()",is(equalTo(2)));
    }
}
