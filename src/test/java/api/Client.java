package api;

import io.restassured.response.Response;

public class Client {
    private final RequestsSpecification requestSpec = new RequestsSpecification();

    public Response basicGetRequest(String endpoint) {
        return requestSpec.buildGetRequest(endpoint).get();
    }

    public Response basicPostRequest(String body, String endpoint) {
        return requestSpec.buildPostRequest(body, endpoint).post();
    }

    public Response basicGetRequestWithId(String endpoint, int id) {
        return requestSpec.buildGetRequestWithId(endpoint, id).get();
    }
}
