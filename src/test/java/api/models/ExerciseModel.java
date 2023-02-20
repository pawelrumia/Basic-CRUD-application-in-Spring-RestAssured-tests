package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class ExerciseModel {
        @JsonProperty("id")
        private long id;
        @JsonProperty("type")
        private String type;
        @JsonProperty("name")
        private String name;
        @JsonProperty("repetitions")
        private int repetitions;
        @JsonProperty("series")
        private int series;
}
