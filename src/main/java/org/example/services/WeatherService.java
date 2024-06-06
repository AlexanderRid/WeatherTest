package org.example.services;


import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import org.example.models.currentjson.CurrentJsonResponse;
import org.example.models.errors.ErrorResponse;
import org.example.services.base.BaseService;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class WeatherService extends BaseService {

    private final String BASE_PATH_CURRENT_JSON = "/current.json";

    @Step("Вызов сервиса :" + BASE_PATH_CURRENT_JSON)
    public CurrentJsonResponse getCurrentJson (Map<String, ?> queryParam) {
        return request()
                .queryParams(authService.getAuthorization())
                .params(queryParam)
                .get(BASE_PATH_CURRENT_JSON)
                .then()
                .extract().as(CurrentJsonResponse.class);
    }

    @Step("Вызов сервиса: " + BASE_PATH_CURRENT_JSON)
    public ErrorResponse getCurrentJsonError (Map<String, ?> queryParam, boolean authorized, int statusCode) {
        RequestSpecification requestSpecification = request();
        if (authorized){
            requestSpecification.queryParams(authService.getAuthorization());
        }
        return requestSpecification
                .params(queryParam)
                .get(BASE_PATH_CURRENT_JSON)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract().as(ErrorResponse.class);
    }

    @Step("Вызов сервиса :" + BASE_PATH_CURRENT_JSON)
    public ErrorResponse getCurrentJsonErrorWithOutAuthorization (Map<String, ?> queryParam, int statusCode) {
        return request()
                .params(queryParam)
                .get(BASE_PATH_CURRENT_JSON)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract().as(ErrorResponse.class);
    }
}
