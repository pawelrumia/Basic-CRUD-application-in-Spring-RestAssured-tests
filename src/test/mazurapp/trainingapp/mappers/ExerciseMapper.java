package mazurapp.trainingapp.mappers;

import mazurapp.trainingapp.models.ExerciseModel;
import com.example.mazur.p.mazurapp.furthertrainingapp.utils.JsonMapper;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

public class ExerciseMapper {
    public static List<ExerciseModel> getExercisesResponse(Response response) {
        return Arrays.asList(new JsonMapper().read(response.getBody().asString(), ExerciseModel[].class));
    }

    public static ExerciseModel getSingleExercisesResponse(Response response) {
        return new JsonMapper().read(response.getBody().asString(), ExerciseModel.class);
    }
}
