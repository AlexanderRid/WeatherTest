package org.example.services.base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.example.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {

    @Autowired
    protected AuthService authService;

    private final String BASE_URL = "http://api.weatherapi.com/v1";

    public RequestSpecification request() {
        return RestAssured
                .given(new RequestSpecBuilder()
                        .addFilter(new ResponseLoggingFilter())
                        .addFilter(new RequestLoggingFilter())
                        .setBaseUri(BASE_URL)
                        .build()
                        .filter(new AllureRestAssured()));
    }
}
