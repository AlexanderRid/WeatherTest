package org.example.services;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class AuthService {
    //Для простоты, без полноценной реализации авторизации

    @Value("${auth.token}")
    private String token;

    public Map<String, String> authorization = new HashMap<>();

    @SneakyThrows
    private void setAuthorization() {
        if (token.isBlank())
            throw new Exception("Не установлен токен доступа в файле application.properties");
        authorization.put("key", token);
    }

    @Step("Получение авторизационного токена")
    public Map<String, String> getAuthorization() {
        if (!authorization.isEmpty())
            return authorization;

        setAuthorization();
        return authorization;
    }
}
