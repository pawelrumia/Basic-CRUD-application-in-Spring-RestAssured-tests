package api.mappers;

import api.models.ExerciseModel;
import com.example.mazur.p.mazurapp.furthertrainingapp.utils.JsonMapper;
import io.restassured.response.Response;

public class ExerciseMapper {
    public static ExerciseModel[] getExercisesResponse(Response response) {
        return new JsonMapper().read(response.getBody().asString(), ExerciseModel[].class);
    }
}
