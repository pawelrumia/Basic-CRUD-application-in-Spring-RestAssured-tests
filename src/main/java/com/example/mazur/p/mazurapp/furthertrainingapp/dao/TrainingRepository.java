package com.example.mazur.p.mazurapp.furthertrainingapp.dao;

import com.example.mazur.p.mazurapp.furthertrainingapp.student.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Exercise, Integer> {
//    int justWork();
}
