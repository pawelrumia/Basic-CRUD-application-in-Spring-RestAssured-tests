package mazurapp.trainingapp;

import mazurapp.trainingapp.assertions.AssertStudent;
import com.example.mazur.p.mazurapp.furthertrainingapp.student.Student;
import com.example.mazur.p.mazurapp.furthertrainingapp.utils.JsonMapper;
import com.example.mazur.p.mazurapp.furthertrainingapp.utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.example.mazur.p.mazurapp.furthertrainingapp.utils.RequestLogger.logged;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestsOldWay {
    private final JsonMapper jsonMapper = new JsonMapper();
    private Requests request = new Requests();
    private PropertyReader PR = new PropertyReader();
    private JsonPath jsonPath;
    private static final String bodyFinal = "{\n" +
            "  \"id\" : 4,\n" +
            "  \"role\" : \"java\",\n" +
            "  \"name\" : \"Gdansk\",\n" +
            "  \"course\" : \"UI\",\n" +
            "  \"adres\" : {\n" +
            "    \"city\" : \"Gdansk\",\n" +
            "    \"street\" : \"Wschodnia\",\n" +
            "    \"homeNumber\" : 1\n" +
            "  },\n" +
            "  \"education\" : {\n" +
            "    \"school\" : \"UG\",\n" +
            "    \"specialization\" : \"MSD\",\n" +
            "    \"yearOfGraduation\" : 1988\n" +
            "  }\n" +
            "}";

    @Test
    public void sprawdzPierwszyRequest() throws IOException {
        RestAssured.baseURI = PR.readProperty("baseUri");

        given().contentType(JSON)
                .when()
                .get("/students/1")
                .then().assertThat()
                .body("name", equalTo("Janek"))
                .body("education.school", equalTo("UL"))
                .body("education.information.hobby", equalTo("chess"))
                .statusCode(200);
    }

    @Test
    public void stillTrying() throws IOException {
        RestAssured.baseURI = PR.readProperty("baseUri");
        RequestSpecification requestSpec = given()
                .pathParam("id", 2);
        Response response = requestSpec.request
                (Method.GET, PR.readProperty("getStudentPath"));
        ResponseBody body = response.getBody();
        Student employeeModel = body.as(Student.class);

        new AssertStudent()
                .statusCodeIsOk(response.getStatusCode())
                .hasIdenticalName(employeeModel.getName(), "Zioooome")
//                .hasIdenticalRole(employeeModel.getRole(), "C")
//                .hasIdenticalFavouriteMovie(employeeModel.getEducation()
//                        .getInformation().getFavouriteMovie(), ("PrisonBrejk"))
                .validateAll();
    }

    @Test(groups = "raz")
    public void getAllEmployees() throws IOException {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri(PR.readProperty("baseUri"))
                .setBasePath(PR.readProperty("allStudents"))
                .build();
        Response response = given().spec(logged(requestSpec))
                .when().get().thenReturn();
        System.out.println(response);
        assertThat(response.getStatusCode()).as("Status code").isEqualTo(200);
    }

    @Test
    public void getSingleStudent() throws IOException {
        RestAssured.baseURI = PR.readProperty("baseUri");
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri(PR.readProperty("baseUri"))
                .setBasePath(PR.readProperty("getStudentsPath"))
                .addPathParam("id", 45)
                .build();
        Response response = given().spec(logged(requestSpec))
                .when().get().thenReturn();
        System.out.println(response);
        assertThat(response.getStatusCode()).as("Status code").isEqualTo(200);
        jsonPath = new JsonPath(response.asString());
        assertThat(jsonPath.getString("course"))
                .isEqualTo("java");
        assertThat(jsonPath.getString("education.kierunek"))
                .isEqualTo("Logis5tyka");
    }

    //    @AfterTest
    @Test
    public void deleteAll() throws IOException {
        RestAssured.baseURI = PR.readProperty("baseUri");
        deleteAllItems();
    }

    private void deleteAllItems() {
        for (int i = 0; i < 500; i++) {
            given().contentType(JSON)
                    .when()
                    .delete("students/" + i);
        }
    }

    @Test
    public void coTuSieDzieje() throws IOException {
        RestAssured.baseURI = PR.readProperty("baseUri");
        System.out.println(given().contentType(JSON)
                .when().get("http://localhost:8000/students/1")
                .then().extract().response());
    }

    @Test
    public void coTuSieDzieje2() {
        Response response = given().contentType(JSON)
                .baseUri("http://localhost:8000/students/1").get().thenReturn();

        System.out.println(response.getBody().as(Student.class));
    }

    @Test
    public void coTuSieDziejePost() {
        RestAssured.defaultParser = Parser.JSON;
        Response response = given().contentType(JSON)
                .baseUri("http://localhost:8000/students")
                .body(bodyFinal)
                .post().thenReturn();

        System.out.println(response.getBody().asString());
    }

    @Test
    public void getStudentSimple() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8000/students/4")
                .build();
        Response response = given().spec(requestSpec).get();
        Student as = response.getBody().as(Student.class);
        System.out.println(as.getAdres().getCity());

//    return new JsonMapper().read(response.getBody().asString(), UsersMetricsModel.class);
    }
}
