package es.upsa.dasi;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class templatesTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/productos")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}