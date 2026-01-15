package com.infosys.testingFramework.execution;


import static io.restassured.RestAssured.given;

public class ApiTestRunner {

    public boolean run(String endpoint, String method) {
        try {
            if ("GET".equalsIgnoreCase(method)) {
                given().when().get(endpoint).then().statusCode(200);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
