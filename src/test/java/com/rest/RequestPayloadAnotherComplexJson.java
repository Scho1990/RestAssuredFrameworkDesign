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

public class RequestPayloadAnotherComplexJson {

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
        List<Integer> rgbaArrayList1=new ArrayList<Integer>();
        rgbaArrayList1.add(255);
        rgbaArrayList1.add(255);
        rgbaArrayList1.add(255);
        rgbaArrayList1.add(1);

        HashMap<String,Object> codeHashMap1=new HashMap<>();
        codeHashMap1.put("rgba",rgbaArrayList1);
        codeHashMap1.put("hex","#000");

        HashMap<String,Object> colorsHashMap1=new HashMap<>();
        colorsHashMap1.put("color","black");
        colorsHashMap1.put("category","hue");
        colorsHashMap1.put("type","primary");
        colorsHashMap1.put("code",codeHashMap1);

        List<Integer> rgbaArrayList2=new ArrayList<Integer>();
        rgbaArrayList2.add(0);
        rgbaArrayList2.add(0);
        rgbaArrayList2.add(0);
        rgbaArrayList2.add(1);

        HashMap<String,Object> codeHashMap2=new HashMap<>();
        codeHashMap2.put("rgba",rgbaArrayList2);
        codeHashMap2.put("hex","#FFF");

        HashMap<String,Object> colorsHashMap2=new HashMap<>();
        colorsHashMap2.put("color","white");
        colorsHashMap2.put("category","value");
        colorsHashMap2.put("code",codeHashMap2);

        List<HashMap<String,Object>> colorsArrayList=new ArrayList<>();
        colorsArrayList.add(colorsHashMap1);
        colorsArrayList.add(colorsHashMap2);

        HashMap<String,List<HashMap<String,Object>>> mainHashMap=new HashMap<>();
        mainHashMap.put("colors",colorsArrayList);

        Response response =given(requestSpecification).body(mainHashMap).
                when().
                post("/postAnotherComplexjson").
                then().spec(responseSpecification).extract().response();
        assertThat(response.path("msg"),equalTo("success"));
    }
}
