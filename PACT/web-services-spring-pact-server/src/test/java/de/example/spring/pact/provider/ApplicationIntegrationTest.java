package de.example.spring.pact.provider;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import de.example.spring.pact.provider.Application;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class },
                webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationIntegrationTest {

    @LocalServerPort
    private int port;
    
    @Test
    public void shouldFindAllLocations() {
        Response response =
                (Response) given()
                	.port(port).accept(ContentType.JSON)
                .when()
                	.contentType(ContentType.JSON).get("/cars/")
                .then()
                    .assertThat().statusCode(HttpStatus.OK.value())
                .extract();

        String brand = from(response.body().asString()).getString("[0].brand");
        String engine = from(response.body().asString()).getString("[0].engine");

        assertThat(brand, is("Ford"));
        assertThat(engine, is("Diesel"));
    }
}
