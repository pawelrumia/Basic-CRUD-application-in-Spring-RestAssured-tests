package com.example.mazur.p.mazurapp.furthertrainingapp.dao;

import com.example.mazur.p.mazurapp.furthertrainingapp.student.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainingRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Exercise> getAllExercises() {
        return jdbcTemplate.query("select * from exercises.exercise", BeanPropertyRowMapper.newInstance(Exercise.class));
    }

    public Exercise getById(long id) {
        return jdbcTemplate.queryForObject("select * from exercises.exercise where id = ?",
                BeanPropertyRowMapper.newInstance(Exercise.class), id);
    }

    public int save(List<Exercise> movies) {
        movies.forEach(exercise ->
                jdbcTemplate.update("INSERT INTO Exercise (type, name, repetitions, series) " +
                                "VALUES(?, ?, ?, ?)",
                        exercise.getType(), exercise.getName(), exercise.getRepetitions(), exercise.getSeries()));
        return 1;
    }

    public int saveOneExercise(Exercise exercise) {
        return jdbcTemplate.update("INSERT INTO Exercise (type, name, repetitions, series) " +
                        "VALUES(?, ?, ?, ?)",
                exercise.getType(), exercise.getName(), exercise.getRepetitions(), exercise.getSeries());
    }

    public int updateSingleExercise(long id, Exercise exercise) {
        return jdbcTemplate.update("update exercise set type=?, name=?, repetitions=?, series=? where id=?",
                exercise.getType(), exercise.getName(), exercise.getRepetitions(), exercise.getSeries(), id);

    }

    public int deleteExercise(long id) {
        return jdbcTemplate.update("delete from exercise where id = ?", id);
    }
}
