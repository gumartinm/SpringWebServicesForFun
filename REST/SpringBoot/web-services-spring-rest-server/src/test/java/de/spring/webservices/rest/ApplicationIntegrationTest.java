package de.spring.webservices.rest;

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

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class },
				webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationIntegrationTest {

	@LocalServerPort
	private int port;

    @Test
	public void findAllCars() {
        Response response =
                (Response) given()
                    .port(port).accept(ContentType.JSON)
                .when()
                    .contentType(ContentType.JSON).get("/api/cars/")
                .then()
                    .assertThat().statusCode(HttpStatus.OK.value())
		        .extract();

        assertThat(from(response.body().asString()).getString("[0].content"), is("Car: 1"));
        assertThat(from(response.body().asString()).getString("[1].content"), is("Car: 2"));
        assertThat(from(response.body().asString()).getString("[2].content"), is("Car: 3"));
    }

	@Test
	public void findOneCar() {
		Response response =
				(Response) given()
					.port(port).accept(ContentType.JSON)
				.when()
					.contentType(ContentType.JSON).get("/api/cars/1")
				.then()
					.assertThat().statusCode(HttpStatus.OK.value())
				.extract();

		assertThat(from(response.body().asString()).getString("content"), is("Car: 1"));
	}

	@Test
	public void createOneCar() {
		Response response =
				(Response) given()
					.port(port).accept(ContentType.JSON)
				.when()
					.contentType(ContentType.JSON)
		            .body(carDtoAsJson())
		            .post("/api/cars/")
				.then()
					.assertThat().statusCode(HttpStatus.CREATED.value())
				.extract();

		assertThat(from(response.body().asString()).getString("content"), is("Car: 5"));
	}

	private String carDtoAsJson() {
		return "{\"id\":2, \"content\": \"nothing\"}";
	}
}
