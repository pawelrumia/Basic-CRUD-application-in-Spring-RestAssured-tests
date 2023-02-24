package assertions;

import org.assertj.core.api.SoftAssertions;

import static org.apache.http.HttpStatus.SC_OK;

public class ExerciseAssertions implements Validate{
    private final SoftAssertions softAssertions;

    public ExerciseAssertions() {
        this.softAssertions = new SoftAssertions();
    }

    public ExerciseAssertions statusCodeIsOk(int actual) {
        softAssertions.assertThat(actual).isEqualTo(SC_OK);
        return this;
    }

    public ExerciseAssertions assertMessageEquals(String actual, String expected) {
        softAssertions.assertThat(actual).isEqualTo(expected);
        return this;
    }

    public ExerciseAssertions assertMessageContains(String actual, String expected) {
        softAssertions.assertThat(actual).contains(expected);
        return this;
    }

    public ExerciseAssertions assertValueIsNotNull(String valueFromResponse) {
        softAssertions.assertThat(valueFromResponse).isNotEmpty().isNotNull();
        return this;
    }

    @Override
    public void validateAll() {
        softAssertions.assertAll();
    }
}
