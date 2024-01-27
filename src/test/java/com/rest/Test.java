package com.rest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Test {

    @org.testng.annotations.Test
    public void validate_get_status_code(){
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-65b37a978b91a6000175b61b-f94a81a0deb7fb46e0fd09aee0063d2b41").
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @org.testng.annotations.Test
    public void validate_get_response_body(){
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-65b37a978b91a6000175b61b-f94a81a0deb7fb46e0fd09aee0063d2b41").
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name",hasItems("My Workspace","AddActiveDirectory"),
                        "workspaces[0].name",equalTo("My Workspace"),
                        "workspaces[0].name",is(equalTo("My Workspace")),
                        "workspaces.size()",is(equalTo(2)));
    }

    @org.testng.annotations.Test
    public void extract_response(){
        Response response = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-65b37a978b91a6000175b61b-f94a81a0deb7fb46e0fd09aee0063d2b41").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).extract().response();
        System.out.println(response.asString());
    }

    @org.testng.annotations.Test
    public void extract_single_value_from_response(){
        Response response = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-65b37a978b91a6000175b61b-f94a81a0deb7fb46e0fd09aee0063d2b41").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).extract().response();
        // solution-1
        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println(jsonPath.getString("workspaces[0].name"));

        //solution-2
        System.out.println(JsonPath.from(response.asString()).getString("workspaces[0].name"));

        //solution-3
        System.out.println(response.path("workspaces[0].name").toString());
    }
}
