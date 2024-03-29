package com.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class QueryRequestSpecification {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass(){
       /* requestSpecification= with().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-65b37a978b91a6000175b61b-f94a81a0deb7fb46e0fd09aee0063d2b41").
                log().all();*/
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.postman.com");
        requestSpecBuilder.addHeader("X-Api-Key","PMAK-65b37a978b91a6000175b61b-f94a81a0deb7fb46e0fd09aee0063d2b41");
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification=requestSpecBuilder.build();
    }

    @Test
    public void queryTest(){
        QueryableRequestSpecification queryableRequestSpecification= SpecificationQuerier.query(requestSpecification);
        System.out.println(queryableRequestSpecification.getBaseUri());
        System.out.println(queryableRequestSpecification.getHeaders());
    }

    @Test
    public void validate_get_status_code(){
        Response response = given(requestSpecification).get("/workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(),is(equalTo(200)));
    }

}
