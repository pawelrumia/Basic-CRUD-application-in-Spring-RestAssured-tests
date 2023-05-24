package api;

import io.restassured.response.Response;

public class Client {
    private final RequestsSpecification requestSpec = new RequestsSpecification();

    public Response get(String endpoint) {
        return requestSpec.basicGetRequest(endpoint);
    }

    public Response post(String body, String endpoint) {
       return requestSpec.basicPostRequest(body, endpoint);
    }

    public Response getById(String endpoint, int id) {
        return requestSpec.buildGetRequestWithId(endpoint, id);
    }

    public Response deleteById(String endpoint, int id) {
        return requestSpec.buildDeleteById(endpoint, id);
    }
}
