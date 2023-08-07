package mazurapp.trainingapp.assertions;

import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;

public class AssertStudent implements Validate {
    private final SoftAssertions softAssertions;

    public AssertStudent() {
        this.softAssertions = new SoftAssertions();
    }

    public AssertStudent hasId(int id, int expected) {
        softAssertions.assertThat(id).isEqualTo(expected);
        return this;
    }

    public AssertStudent hasIdenticalName(String actualName, String expected) {
        softAssertions.assertThat(actualName).isEqualTo(expected);
        return this;
    }

    public AssertStudent hasIdenticaCourse(String course, String expected) {
        softAssertions.assertThat(course).isEqualTo(expected);
        return this;
    }

    public AssertStudent hasIdenticalSchool(String actualSchool, String expected) {
        softAssertions.assertThat(actualSchool).isEqualTo(expected);
        return this;
    }

    public AssertStudent hasIdenticalSpecialization(String actualSpecialization, String expected) {
        softAssertions.assertThat(actualSpecialization).isEqualTo(expected);
        return this;
    }

    public AssertStudent hasIdenticalyearOfGraduation(int actualyearOfGraduation, int expected) {
        softAssertions.assertThat(actualyearOfGraduation).isEqualTo(expected);
        return this;
    }

    public AssertStudent hasIdenticalCity(String actualCity, String expected) {
        softAssertions.assertThat(actualCity).isEqualTo(expected);
        return this;
    }

    public AssertStudent hasIdenticalStreet(String actualStreet, String expected) {
        softAssertions.assertThat(actualStreet).isEqualTo(expected);
        return this;
    }

    public AssertStudent hasIdenticalHomeNumber(int actualHomeNumber, int expected) {
        softAssertions.assertThat(actualHomeNumber).isEqualTo(expected);
        return this;
    }

    public AssertStudent statusCodeIsOk(int actual) {
        softAssertions.assertThat(actual).isEqualTo(HttpStatus.SC_OK);
        return this;
    }

    @Override
    public void validateAll() {
        softAssertions.assertAll();
    }
}
