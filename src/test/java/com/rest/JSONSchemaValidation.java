package com.rest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JSONSchemaValidation {

    @Test
    public void json_schema_validation(){
        given().
                baseUri("https://postman-echo.com").
                log().all().
                when().
                get("/get").
                then().assertThat().statusCode(200).log().all().
                body(matchesJsonSchemaInClasspath("EchoGet.json"));
    }
}
