package com.example.mazur.p.mazurapp.furthertrainingapp.student;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type, name;
    private int repetitions, series;

    public Exercise(String type, String name, int repe, int series) {
        this.type = type;
        this.name = name;
        this.repetitions = repe;
        this.series = series;
    }
}
