package com.rest;

import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.given;

public class DownloadFile {
    @Test
    public void multi_part_form_data() throws IOException {
        InputStream is = given().
                baseUri("https://github.com/appium-boneyard").
                log().all().
                when().
                get("/sample-code/raw/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk").
                then().log().all().extract().response().asInputStream();
        OutputStream os=new FileOutputStream(new File("ApiDemos-debug.apk"));
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        os.write(bytes);
        os.close();
        new File("ApiDemos-debug.apk").delete();
    }
}
