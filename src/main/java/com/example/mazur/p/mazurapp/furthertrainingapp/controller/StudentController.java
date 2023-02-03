package com.example.mazur.p.mazurapp.furthertrainingapp.controller;

import com.example.mazur.p.mazurapp.furthertrainingapp.dao.TrainingRepository;
import com.example.mazur.p.mazurapp.furthertrainingapp.exceptionshandle.ProductNotFoundException;
import com.example.mazur.p.mazurapp.furthertrainingapp.service.StudentService;
import com.example.mazur.p.mazurapp.furthertrainingapp.student.Student;
import com.example.mazur.p.mazurapp.furthertrainingapp.student.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TrainingRepository trainingRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable("id") int id) {
        return studentService.getStudentById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") int id) {
        studentService.removeStudentById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void updateStudenta(@PathVariable("id") int id, @RequestBody Student student) {
        if (id <= 0) {
            throw new ProductNotFoundException();
        }
        studentService.updateStudentById(id, student);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStudent(@RequestBody Student student) {
        studentService.insertStudent(student);
        System.out.println("Great success!");
    }

    @GetMapping("/thymeleaf")
    public String home() {
        return "home";
    }

    @GetMapping(path="/dzialaj")
    public @ResponseBody Iterable<Exercise> getAllExercises() {
        // This returns a JSON or XML with the users
        return trainingRepository.findAll();
    }

    @PostMapping(path="/dzialaj") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String type
            , @RequestParam String name, @RequestParam int reps,  @RequestParam int series) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Exercise ex = new Exercise();
        ex.setType(type);
        ex.setName(name);
        ex.setRepetitions(reps);
        ex.setSeries(series);
        trainingRepository.save(ex);
        return "Saved";
    }
}
