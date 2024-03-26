package poc.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class SystemInfoControllerIT extends ResourcesIT {

    @Test
    void whenGetSystemInfo_thenStatus200() {
        // given
        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

        // when
        Response actualResponse = request.when().get("/system-info");

        // then
        actualResponse.then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("cpuCount", notNullValue())
                .and()
                .body("totalMemory", notNullValue())
                .and()
                .body("freeMemory", notNullValue())
                .and()
                .body("allocatedMemory", notNullValue())
                .and()
                .body("maxMemory", notNullValue());
    }
}
