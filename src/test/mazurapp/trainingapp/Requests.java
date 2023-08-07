package mazurapp.trainingapp;

import com.example.mazur.p.mazurapp.furthertrainingapp.student.Adres;
import com.example.mazur.p.mazurapp.furthertrainingapp.student.Education;
import com.example.mazur.p.mazurapp.furthertrainingapp.student.Exercise;
import com.example.mazur.p.mazurapp.furthertrainingapp.student.Student;

class Requests {
    static Student createBaseRequest(int id, String role, String name, String course, Adres adres, Education education) {
        return new Student(id, role, name, course, adres, education);
    }

    public Exercise createBasePostRequest(String type, String name, int repetitions, int series) {
        return new Exercise()
                .withType(type)
                .withName(name)
                .withRepetitions(repetitions)
                .withSeries(series);
    }
}
