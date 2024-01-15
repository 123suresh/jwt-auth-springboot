package com.springPostgres.SpringPSQL.student;

//will have all of the resources required for student class.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/student")
public class StudentController {
    private final StudentService studentService;
    //but how do you tell above StudentService should be injected into below constructor StudentService
    //and solution is using Autowired
    //but we should tell that StudentService should be instanciate and that we will do inside StudentService class.
    //we have to tell that StudentService class is spring bean.
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId,
                              @RequestBody(required = true) UpdateStudent updateStudent
//                              @RequestBody(required = false) String email
    ){
        studentService.updateStudent(studentId, updateStudent);
    }
}

