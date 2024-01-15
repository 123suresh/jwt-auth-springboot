
//Service layer is for writing business logics.

package com.springPostgres.SpringPSQL.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Component
//@Component is for instantiating so we have reference in StudentController.
@Service
//@Service and @Component are same but more generic is @Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    public  void deleteStudent(Integer studentId){
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with id "+studentId+" does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    //@Transactional helps us to manipulate without query as well
    //also rollback query
    //When we have this annotation entity goes into the manage state
    @Transactional
    public void updateStudent(Integer studentId, UpdateStudent updateStudent){
        Optional<Student> studentOptional = studentRepository.findStudentById(studentId);
        if(studentOptional.isEmpty()){
            return;
        }
        studentOptional.get().setEmail(updateStudent.getEmail());
        studentOptional.get().setName(updateStudent.getName());
        studentRepository.save(studentOptional.get());
//        boolean exists = studentRepository.existsById(studentId);
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new IllegalStateException(
//                        "Student with id "+studentId+ " does not exists."
//                ));
//
//        if(name != null && name.length() > 0 && !Objects.equals(updateStudent.getName(), name)){
//            updateStudent.setName(name);
//        }
//
//        if(email != null && email.length() > 0 && !Objects.equals(updateStudent.getEmail(), email)){
//            Optional<Student> studentOptional = studentRepository
//                    .findStudentByEmail(email);
//            if(studentOptional.isPresent()){
//                throw new IllegalStateException("Email already exists");
//            }
//            updateStudent.setEmail(email);
//        }
    }
}
