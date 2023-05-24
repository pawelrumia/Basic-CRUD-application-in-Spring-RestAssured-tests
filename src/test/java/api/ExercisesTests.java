package api;

import api.models.ExerciseModel;
import assertions.ExerciseAssertions;
import com.example.mazur.p.mazurapp.furthertrainingapp.utils.JsonMapper;
import com.example.mazur.p.mazurapp.furthertrainingapp.utils.PropertyReader;
import io.restassured.response.Response;
import org.springframework.core.SpringVersion;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static api.mappers.ExerciseMapper.getExercisesResponse;
import static api.mappers.ExerciseMapper.getSingleExercisesResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExercisesTests {
    private final JsonMapper jsonMapper = new JsonMapper();
    private final PropertyReader PR = new PropertyReader();
    private final Client client = new Client();
    private final Requests requests = new Requests();

    @Test
    public void getAllExercises() throws IOException {
        Response response = client.get(PR.readProperty("baseExercisesUri"));
        List<ExerciseModel> exerciseList = getExercisesResponse(response);
        new ExerciseAssertions()
                .statusCodeIsOk(response.getStatusCode())
                .assertMessageContains(exerciseList.get(0).getType(), "Pompki")
                .validateAll();
    }

    @Test
    public void getSingleExercise() throws IOException {
        Response response = client.getById(PR.readProperty("getExerciseWithId"), 5);
        ExerciseModel exerciseModel = getSingleExercisesResponse(response);
        new ExerciseAssertions()
                .statusCodeIsOk(response.getStatusCode())
                .assertMessageContains(exerciseModel.getName(), "Lezenie plackiem")
                .assertValueIsNotNull(exerciseModel.getType())
                .validateAll();
    }

    @Test
    public void createSingleExercise() throws IOException {
        Response postResponse = client.post(jsonMapper.write(
                requests.createBasePostRequest("Patiszon", "Prohibicja zla", 5,20)),
                PR.readProperty("postExercise"));

        Response allExercisesResponse = client.get(PR.readProperty("baseExercisesUri"));

        int sizeOfExercisesList = getExercisesResponse(allExercisesResponse).size();
        Response singleExerciseResponse = client.getById(PR.readProperty("getExerciseWithId"), sizeOfExercisesList - 1);
        ExerciseModel exerciseModel = getSingleExercisesResponse(singleExerciseResponse);
        new ExerciseAssertions()
                .statusCodeIsOk(singleExerciseResponse.getStatusCode())
                .assertMessageContains(exerciseModel.getName(), "Prohibicja")
                .assertValueIsNotNull(exerciseModel.getType())
                .validateAll();
    }

    @Test
    public void checkSpringVersion() {
        System.out.println(assertThat(SpringVersion.getVersion()).isEqualToIgnoringCase("5.2.2.RELEASE"));
    }

    @Test
    public void deleteByIdAndCheckResult() throws IOException {
        Response response = client.deleteById(PR.readProperty("getExerciseWithId"), 12);
        assertThat(response.getBody().asString()).isEqualTo("1");
    }
}
