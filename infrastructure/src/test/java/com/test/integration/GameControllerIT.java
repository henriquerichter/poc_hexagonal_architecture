package com.test.integration;

import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.jpa.repositories.GameJpaRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GameControllerIT extends ResourcesIT {

  @Autowired
  private GameJpaRepository gameJpaRepository;

  @Test
  void givenGame_whenCreateGame_thenStatus201() throws Exception {
    // given
    String game = "{\"name\":\"Game 2\",\"releaseDate\":\"2023-03-01\",\"price\":1.11}";
    RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).body(game);

    // when
    Response actualResponse = request.when().post("/game");

    // then
    actualResponse.then()
        .statusCode(201)
        .and()
        .contentType(ContentType.JSON)
        .and()
        .body("id", equalTo(2))
        .and()
        .body("name", equalTo("Game 2"))
        .and()
        .body("releaseDate", equalTo("2023-03-01"))
        .and()
        .body("price", equalTo(1.11F));

    // clean up
    gameJpaRepository.deleteById(2L);
  }

  @Test
  void givenId_whenGetById_thenStatus200() throws Exception {
    // given
    Long id = 1L;
    RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

    // when
    Response actualResponse = request.when().get("/game/" + id);

    // then
    actualResponse.then()
        .statusCode(200)
        .and()
        .contentType(ContentType.JSON)
        .and()
        .body("id", equalTo(id.intValue()))
        .and()
        .body("name", equalTo("Game 1"))
        .and()
        .body("releaseDate", equalTo("2023-03-21"))
        .and()
        .body("price", equalTo(1.01F));
  }

  @Test
  void givenId_whenGetById_thenStatus404() throws Exception {
    // given
    Long id = -2L;
    RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

    // when
    Response actualResponse = request.when().get("/game/" + id);

    // then
    actualResponse.then().statusCode(404);
  }

  @Test
  void givenName_whenGetByName_thenStatus200() throws Exception {
    // given
    String name = "Game 1";
    RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

    // when
    Response actualResponse = request.when().get("/game/name/" + name);

    // then
    actualResponse.then()
        .statusCode(200)
        .and()
        .contentType(ContentType.JSON)
        .and()
        .body("id", equalTo(1))
        .and()
        .body("name", equalTo(name))
        .and()
        .body("releaseDate", equalTo("2023-03-21"))
        .and()
        .body("price", equalTo(1.01F));
  }

  @Test
  void givenName_whenGetByName_thenStatus404() throws Exception {
    // given
    String name = "Game -1";
    RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

    // when
    Response actualResponse = request.when().get("/game/name/" + name);

    // then
    actualResponse.then().statusCode(404);
  }

  @Test
  void givenReleaseDate_whenGetByReleaseDate_thenStatus200() throws Exception {
    // given
    String releaseDate = "2023-03-21";
    RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

    // when
    Response actualResponse = request.when().get("/game/releaseDate/" + releaseDate);

    // then
    actualResponse.then()
        .statusCode(200)
        .and()
        .contentType(ContentType.JSON)
        .and()
        .body("id", equalTo(List.of(1)))
        .and()
        .body("name", equalTo(List.of("Game 1")))
        .and()
        .body("releaseDate", equalTo(List.of(releaseDate)))
        .and()
        .body("price", equalTo(List.of(1.01F)));
  }

  @Test
  void givenReleaseDate_whenGetByReleaseDate_thenStatus200Empty() throws Exception {
    // given
    String releaseDate = "2023-03-22";
    RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

    // when
    Response actualResponse = request.when().get("/game/releaseDate/" + releaseDate);

    // then
    actualResponse.then()
        .statusCode(200)
        .and()
        .contentType(ContentType.JSON)
        .and()
        .body(equalTo("[]"));
  }

  @Test
  void givenPrice_whenGetByPrice_thenStatus200() throws Exception {
    // given
    String price = "1.01";
    RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

    // when
    Response actualResponse = request.when().get("/game/price/" + price);

    // then
    actualResponse.then()
        .statusCode(200)
        .and()
        .contentType(ContentType.JSON)
        .and()
        .body("id", equalTo(List.of(1)))
        .and()
        .body("name", equalTo(List.of("Game 1")))
        .and()
        .body("releaseDate", equalTo(List.of("2023-03-21")))
        .and()
        .body("price", equalTo(List.of(1.01F)));
  }

  @Test
  void givenPrice_whenGetByPrice_thenStatus200Empty() throws Exception {
    // given
    String price = "-0.02";
    RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

    // when
    Response actualResponse = request.when().get("/game/price/" + price);

    // then
    actualResponse.then()
        .statusCode(200)
        .and()
        .contentType(ContentType.JSON)
        .and()
        .body(equalTo("[]"));
  }

  @Test
  void givenId_whenGetAll_thenStatus200() throws Exception {
    // given
    RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

    // when
    Response actualResponse = request.when().get("/game/all");

    // then
    actualResponse.then()
        .statusCode(200)
        .and()
        .contentType(ContentType.JSON)
        .and()
        .body("id", equalTo(List.of(1)))
        .and()
        .body("name", equalTo(List.of("Game 1")))
        .and()
        .body("releaseDate", equalTo(List.of("2023-03-21")))
        .and()
        .body("price", equalTo(List.of(1.01F)));
  }
}
