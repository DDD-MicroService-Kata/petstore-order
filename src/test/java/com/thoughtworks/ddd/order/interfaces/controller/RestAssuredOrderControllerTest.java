package com.thoughtworks.ddd.order.interfaces.controller;

import com.thoughtworks.ddd.order.APIBaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.context.embedded.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredOrderControllerTest extends APIBaseTest {

    @LocalServerPort
    public int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public final void shouldGetTheOrderAfterJustCreated() {
        String location = given()
                .body(withJson("order.json"))
                .with()
                .contentType(ContentType.JSON)
                .when()
                .post("/api/orders")
                .then()
                .statusCode(201)
                .extract()
                .header("location");

        given()
                .get(location)
                .then()
                .statusCode(200)
                .body("data.attributes.customer.name", equalTo("Ryan"));

        given()
                .get(location + "/payments")
                .then()
                .statusCode(200)
                .body("data.attributes.paymentStatus", equalTo("UNPAID"));
    }
}