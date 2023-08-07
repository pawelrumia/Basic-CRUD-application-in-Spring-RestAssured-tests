package mazurapp.trainingapp;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class RequestsSpecification {

    public RequestsSpecification() {
    }

    private final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public Response basicPostRequest(String body, String address) {
        return given().contentType(ContentType.JSON)
                .body(body).when().post(address).then()
                .spec(responseSpec).extract().response();
    }

    public Response basicGetRequest(String address) {
        return given().when().get(address).then().spec(responseSpec).extract().response();
    }

    public Response buildGetRequestWithId(String address, int id) {
        return given().pathParam("id", id).when().get(address)
                .then().spec(responseSpec).extract().response();
    }

    public Response buildDeleteById(String address, int id) {
        return given().pathParam("id", id).when().delete(address).then()
                .spec(responseSpec).log().all().extract().response();
    }
}
