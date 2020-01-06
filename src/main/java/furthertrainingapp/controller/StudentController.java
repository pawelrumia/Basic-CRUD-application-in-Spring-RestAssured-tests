package furthertrainingapp.controller;

import furthertrainingapp.exceptionshandle.ProductNotFoundException;
import furthertrainingapp.service.StudentService;
import furthertrainingapp.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

//    @RequestMapping(method = RequestMethod.GET)
//    public Collection<Student> getAllStudents(){
//        return studentService.getAllStudents();
//    }

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
        if(id <= 0){
            throw new ProductNotFoundException();
        }
        studentService.updateStudentById(id, student);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStudent(@RequestBody Student student) {
        studentService.insertStudent(student);
        System.out.println("Bang!");
    }
}
