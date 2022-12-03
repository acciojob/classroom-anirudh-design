package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void addStudentService(Student student){
        studentRepository.addStudentToDB(student);
    }

    public void addTeacherService(Teacher teacher){
        studentRepository.addTeacherToDB(teacher);
    }

    public void addStudentTeacherPairService(String studentName, String teacherName){
        studentRepository.addStudentTeacherPairToDB(studentName, teacherName);
    }

    public Student getStudentService(String studentName){
        return studentRepository.getStudentFromDB(studentName);
    }

    public Teacher getTeacherService(String teacherName){
        return studentRepository.getTeacherFromDB(teacherName);
    }

    public List<String> getStudentsListService(String teacherName){
        return studentRepository.getStudentsListFromDB(teacherName);
    }

    public List<String> getAllStudentsService(){
        return studentRepository.getAllStudentsFromDB();
    }

    public void deleteTeacherStudentsService(String teacherName){
        studentRepository.deleteTeacherStudentsFromDB(teacherName);
    }

    public void deleteAllTeacherStudentsService(){
        studentRepository.deleteAllTeacherStudentsFromDB();
    }
}
