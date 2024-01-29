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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RequestPayloadComplexJson {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://a94bbdbf-a193-468a-94c3-3231719681ba.mock.pstmn.io");
        requestSpecBuilder.addHeader("x-mock-match-request-body","true");
        requestSpecBuilder.addHeader("Content-Type","application/json;charset=utf-8");
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
        List<Integer> idArrayList=new ArrayList<Integer>();
        idArrayList.add(5);
        idArrayList.add(9);

        HashMap<String, Object> batterHashMap2=new HashMap<String,Object>();
        batterHashMap2.put("id",idArrayList);
        batterHashMap2.put("type","Chocolate");

        HashMap<String,Object> batterHashMap1=new HashMap<String,Object>();
        batterHashMap1.put("id","1001");
        batterHashMap1.put("type","Regular");

        List<HashMap<String,Object>> batterArrayList=new ArrayList<HashMap<String,Object>>();
        batterArrayList.add(batterHashMap1);
        batterArrayList.add(batterHashMap2);

        HashMap<String,List<HashMap<String,Object>>> battersHashMap=new HashMap<String, List<HashMap<String,Object>>>();
        battersHashMap.put("batter",batterArrayList);

        List<String> typeArrayList=new ArrayList<String>();
        typeArrayList.add("test1");
        typeArrayList.add("test2");

        HashMap<String,Object> toppingHashMap2=new HashMap<String, Object>();
        toppingHashMap2.put("id","5002");
        toppingHashMap2.put("type",typeArrayList);

        HashMap<String,Object> toppingHashMap1=new HashMap<String,Object>();
        toppingHashMap1.put("id","5001");
        toppingHashMap1.put("type","None");

        List<HashMap<String,Object>> toppingArrayList =new ArrayList<HashMap<String,Object>>();
        toppingArrayList.add(toppingHashMap1);
        toppingArrayList.add(toppingHashMap2);

        HashMap<String,Object> mainHashMap=new HashMap<String,Object>();
        mainHashMap.put("id","0001");
        mainHashMap.put("type","donut");
        mainHashMap.put("name","Cake");
        mainHashMap.put("ppu",0.55);
        mainHashMap.put("batters",battersHashMap);
        mainHashMap.put("topping",toppingArrayList);


        Response response =given(requestSpecification).body(mainHashMap).
                when().
                           post("/postComplexjson").
                then().spec(responseSpecification).extract().response();
        assertThat(response.path("msg"),equalTo("successfull"));
    }
}
