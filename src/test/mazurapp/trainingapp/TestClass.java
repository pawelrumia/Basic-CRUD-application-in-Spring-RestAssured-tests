package mazurapp.trainingapp;

import com.example.mazur.p.mazurapp.furthertrainingapp.student.Adres;
import com.example.mazur.p.mazurapp.furthertrainingapp.student.Education;
import com.example.mazur.p.mazurapp.furthertrainingapp.student.Student;
import com.example.mazur.p.mazurapp.furthertrainingapp.student.University;
import com.example.mazur.p.mazurapp.furthertrainingapp.utils.JsonMapper;
import com.example.mazur.p.mazurapp.furthertrainingapp.utils.PropertyReader;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.example.mazur.p.mazurapp.furthertrainingapp.utils.ResponseMapper.getAllStudentsResponse;
import static com.example.mazur.p.mazurapp.furthertrainingapp.utils.ResponseMapper.getStudentIdResponse;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class TestClass {
    private final JsonMapper jsonMapper = new JsonMapper();
    private PropertyReader PR = new PropertyReader();
    private Client client = new Client();

    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "testProvider", priority = 1)
    public void sendInitialRequests(int id, String role, String name, String course, String city, String street, int number,
                                    String school, String spec,
                                    int yearOfG) throws IOException {
        client.post(PR.readProperty("baseUri"),
                jsonMapper.write(Requests.createBaseRequest(id, role, name, course,
                        new Adres(city, street, number),
                        new Education(school, spec, yearOfG))));
    }

    @DataProvider(name = "testProvider")
    public Object[][] createData1() {
        return new Object[][]{
                {1, "java", "Gdansk", "UI", "Gdansk", "Wschodnia", 1,
                        "UG", "MSD", 1988},
                {2, "c", "Gdynia", "api", "Gdynia", "Zachodnia", 10,
                        "PG", "DSM", 1999},
                {3, "python", "Sopot", "ui/api", "Sopot", "Poludniowa", 20,
                        "PW", "SMD", 2000},
        };
    }

    @Epic("Students requests")
    @Test(priority = 2)
    public void getOneStudentsById() throws IOException {
        Response response = client.getById(PR.readProperty("getStudentsWithId"), 1);
        Student model = getStudentIdResponse(response);
        assertThat(model.getName()).isEqualTo("Gdansk");
    }

    @Epic("Students requests")
    @Test(priority = 2)
    public void getAllStudents() throws IOException {
        Response response = client.get(PR.readProperty("baseUri"));
        University model = getAllStudentsResponse(response);
        assertThat(model.getStudents().get(2).getRole()).isEqualTo("python");
    }

    /**#@note
     * previous version
     */
    //    @Test(dataProvider = "test1")
//    public void testWithDataProvider(int id, String name, String course, String city, String street, int number,
//                                     String school, String spec,
//                                     int yearOfG) throws IOException {
//        RequestSpecification requestSpec = new RequestSpecBuilder()
//                .setContentType(JSON)
//                .setBaseUri(PR.readProperty("baseUri"))
//                .setBasePath(PR.readProperty("getStudentPath"))
//                .addPathParam("id", 76)
//                .build();
//        Response response = given().spec(logged(requestSpec))
//                .when().get().thenReturn();
//        ResponseBody body = response.getBody();
//        Student employeeModel = body.as(Student.class);
//
//        new AssertStudent()
//                .statusCodeIsOk(response.getStatusCode())
//                .hasId(employeeModel.getId(), id)
//                .hasIdenticalName(employeeModel.getName(), name)
//                .hasIdenticaCourse(employeeModel.getCourse(), course)
//                .hasIdenticalCity(employeeModel.getAdres().getCity(), city)
//                .hasIdenticalStreet(employeeModel.getAdres().getStreet(), street)
//                .hasIdenticalHomeNumber(employeeModel.getAdres().getHomeNumber(), number)
//                .hasIdenticalSchool(employeeModel.getEducation().getSchool(), school)
//                .hasIdenticalSpecialization(employeeModel.getEducation().getSpecialization(), spec)
//                .hasIdenticalyearOfGraduation(employeeModel.getEducation().getYearOfGraduation(), yearOfG)
//                .validateAll();
//    }
}
