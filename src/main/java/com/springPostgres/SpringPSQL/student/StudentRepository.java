package com.springPostgres.SpringPSQL.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//This is Data Access Layer which intract with DB and should be an Interface
//Here JpaRepository<Student, Interger> Student is type we want to work with and Id type in Integer
//This interface is @Repository because it is reponsible for data access

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
//    @Query("SELECT s FROM Student s WHERE s.email=1?")
    Optional<Student> findStudentByEmail(String email);
    Optional<Student> findStudentById(Integer id);
}


//we will be using this interface inside our StudentService