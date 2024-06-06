import io.qameta.allure.Allure
import io.qameta.allure.Feature
import org.assertj.core.api.Assertions
import org.example.Constants
import org.example.Main
import org.example.models.currentjson.CurrentJsonResponse
import org.example.models.errors.ErrorResponse
import org.example.services.WeatherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(classes = Main.class)
@Feature("Проверка сервиса Weather")
class WeatherTest extends Specification {

    @Autowired
    private WeatherService service;

    @Unroll
    def "positivTests"() {
        Allure.getLifecycle().updateTestCase { t ->
            t.setName("Позитивные проверки ...")
        }

        given:
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("q", city)

        when:
        CurrentJsonResponse response = service.getCurrentJson(queryParam)

        then:
        Assertions.assertThat(response.getLocation().getName())
                .as("Имя локация не соответствует ожидаемому")
                .isEqualTo(expected)
//        assert response.getLocation().getName() == result

        where:
        city          || expected
        "Saratov"     || "Saratov"
        "Moscow"      || "Moscow"
        "Novosibirsk" || "Novosibirsk"
        //Сломаное значение для демонстрации неожиданного поведения в логах
        "Pskov"       || "not Pskov"
    }


    @Unroll
    def "negativTest"() {
        Allure.getLifecycle().updateTestCase { t ->
            t.setName("Негативные проверки ...")
        }

        given:
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(queryParamName, queryParamValue)

        when:
        ErrorResponse response = service.getCurrentJsonError(queryParam, true, 400)

        then:
        Assertions.assertThat(response.getError().getCode())
                .as(Constants.HandlerDescription.CODE_IS_INVALID)
                .isEqualTo(codeExpected)

        Assertions.assertThat(response.getError().getMessage())
                .as(Constants.HandlerDescription.MESSAGE_IS_INVALID)
                .isEqualTo(messageExpected)

        where:
        queryParamName | queryParamValue || codeExpected             | messageExpected
        "q"            | "asdasdasdasd"  || Constants.Code.CODE_1006 | Constants.Message.NO_MATCHING_LOCATION_FOUND
        "a"            | "Saratov"       || Constants.Code.CODE_1003 | Constants.Message.PARAMETER_Q_IS_MISSING
    }


    @Unroll
    def "negativAuthorizationTest"() {
        Allure.getLifecycle().updateTestCase { t ->
            t.setName("Негативные проверки авторизации ...")
        }

        given:
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(queryParamName, queryParamValue)

        when:
        ErrorResponse response = service.getCurrentJsonErrorWithOutAuthorization(queryParam, statusCode)

        then:
        Assertions.assertThat(response.getError().getCode())
                .as(Constants.HandlerDescription.CODE_IS_INVALID)
                .isEqualTo(codeResult)

        Assertions.assertThat(response.getError().getMessage())
                .as(Constants.HandlerDescription.MESSAGE_IS_INVALID)
                .isEqualTo(messageResult)

        where:
        queryParamName | queryParamValue || statusCode | codeResult               | messageResult
        "q"            | null            || 401        | Constants.Code.CODE_1002 | Constants.Message.API_KEY_INVALID
        "key"          | "a"             || 403        | Constants.Code.CODE_2008 | Constants.Message.API_KEY_HAS_BEEN_DISABLE
    }


}

