package com.example.mazur.p.mazurapp.furthertrainingapp.controller;

import com.example.mazur.p.mazurapp.furthertrainingapp.dao.TrainingRepository;
import com.example.mazur.p.mazurapp.furthertrainingapp.student.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExercisesController {
    @Autowired
    private TrainingRepository trainingRepository;

    @GetMapping("")
    public List<Exercise> getAll() {
        return trainingRepository.getAllExercises();
    }

    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable("id") long id) {
        return trainingRepository.getById(id);
    }

    @PostMapping("")
    public int addExercise(@RequestBody List<Exercise> exercise) {
        return trainingRepository.save(exercise);
    }

    @PostMapping("/")
    public int addSingleExercise(@RequestBody Exercise exercise) {
        return trainingRepository.saveOneExercise(exercise);
    }

    @PutMapping("/{id}")
    public int updateExercise(@PathVariable("id") long id, @RequestBody Exercise exercise) {
        Exercise myExercise = trainingRepository.getById(id);
        if (myExercise != null) {
            myExercise.setType(exercise.getType());
            myExercise.setName(exercise.getName());
            myExercise.setRepetitions(exercise.getRepetitions());
            myExercise.setSeries(exercise.getSeries());
        } else {
            return -1;
        }
        return trainingRepository.updateSingleExercise(id, exercise);
    }

    @DeleteMapping("/{id}")
    public int deleteExercise(@PathVariable("id") long id) {
        return trainingRepository.deleteExercise(id);
    }

    @PatchMapping("/{id}")
    public int partiallyUpdateExercise(@PathVariable("id") long id, @RequestBody Exercise exercise) {
        Exercise myExercise = trainingRepository.getById(id);
        if(myExercise != null) {
            if (exercise.getType() != null) {
                myExercise.setType(exercise.getType());
            }
            if (exercise.getName() != null) {
                myExercise.setName(exercise.getName());
            }
            if (exercise.getRepetitions() > 0) {
                myExercise.setRepetitions(exercise.getRepetitions());
            }
            if (exercise.getSeries() > 0) {
                myExercise.setSeries(exercise.getSeries());
            }
        }else {
            return -1;
        }
        return trainingRepository.updateSingleExercise(id, myExercise);
    }
}
