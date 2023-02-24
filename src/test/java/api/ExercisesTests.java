package api;

import api.models.ExerciseModel;
import assertions.ExerciseAssertions;
import com.example.mazur.p.mazurapp.furthertrainingapp.utils.JsonMapper;
import com.example.mazur.p.mazurapp.furthertrainingapp.utils.PropertyReader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static api.mappers.ExerciseMapper.getExercisesResponse;
import static api.mappers.ExerciseMapper.getSingleExercisesResponse;

public class ExercisesTests {
    private final JsonMapper jsonMapper = new JsonMapper();
    private PropertyReader PR = new PropertyReader();
    private Client client = new Client();
    private Requests requests = new Requests();

    @Test
    public void getAllExercises() throws IOException {
        Response response = client.basicGetRequest(PR.readProperty("baseExercisesUri"));
        List<ExerciseModel> exerciseList = getExercisesResponse(response);
        new ExerciseAssertions()
                .statusCodeIsOk(response.getStatusCode())
                .assertMessageContains(exerciseList.get(0).getType(), "Pompki")
                .validateAll();
    }

    @Test
    public void getSingleExercise() throws IOException {
        Response response = client.basicGetRequestWithId(PR.readProperty("getExerciseWithId"), 5);
        ExerciseModel exerciseModel = getSingleExercisesResponse(response);
        new ExerciseAssertions()
                .statusCodeIsOk(response.getStatusCode())
                .assertMessageContains(exerciseModel.getName(), "Lezenie plackiem")
                .assertValueIsNotNull(exerciseModel.getType())
                .validateAll();
    }

    @Test
    public void createSingleExercise() throws IOException {
        Response response = client.basicPostRequest(jsonMapper.write(
                requests.createBasePostRequest("Deseczka", "test", 2,2)),
                PR.readProperty("postExercise"));
        new ExerciseAssertions()
                .statusCodeIsOk(response.getStatusCode())
                .validateAll();

        Response singleExerciseResponse = client.basicGetRequestWithId(PR.readProperty("getExerciseWithId"), 6);
        ExerciseModel exerciseModel = getSingleExercisesResponse(singleExerciseResponse);
        new ExerciseAssertions()
                .statusCodeIsOk(singleExerciseResponse.getStatusCode())
                .assertMessageContains(exerciseModel.getName(), "Test")
                .assertValueIsNotNull(exerciseModel.getType())
                .validateAll();
    }
}
