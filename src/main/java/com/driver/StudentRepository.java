package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    Map<Teacher, List<Student>> hm=new HashMap<>();

    public void addStudentToDB(Student Student){
        List<Student> Students;
        if(hm.size()==0){
            Students = new ArrayList<>();
        }
        else{
            for(Teacher d:hm.keySet()){
                List<Student> x=hm.get(d);
                for(Student m:x){
                    if(m.getName().equals(Student.getName())) return;
                }
            }
            Students = hm.get(null);
        }
        Students.add(Student);
        hm.put(null, Students);
    }

    public void addTeacherToDB(Teacher Teacher){
        if(hm.containsKey(Teacher)) return;
        hm.put(Teacher, new ArrayList<Student>());
    }

    public void addStudentTeacherPairToDB(String StudentName, String TeacherName){
        List<Student> x=null;
        for(Teacher d:hm.keySet()) {
            if (d != null && d.getName().equals(TeacherName)) {
                x = hm.get(d);
                break;
            }
        }
        Student m1=null;
        List<Student> Students=hm.get(null);
        for(Student m:Students){
            if(m.getName().equals(StudentName)) {
                m1 = m;
                break;
            }
        }
        if(m1==null) return;
        if(x.contains(m1)) return;
        List<Student> y=hm.get(null);
        y.remove(m1);
        hm.put(null, y);
        if(hm.get(null).size()==0) hm.remove(null);
        if(!x.contains(m1)) x.add(m1);
        for(Teacher d:hm.keySet()){
            if (d != null && d.getName().equals(TeacherName)) hm.put(d, x);
        }
    }

    public Student getStudentFromDB(String StudentName){
        for(Teacher d:hm.keySet()){
            List<Student> Students=hm.get(d);
            for(Student m:Students) if(m.getName().equals(StudentName)) return m;
        }
        return new Student();
    }

    public Teacher getTeacherFromDB(String TeacherName){
        for(Teacher d:hm.keySet()) if(d!=null && d.getName().equals(TeacherName)) return d;
        return new Teacher();
    }

    public List<String> getStudentsListFromDB(String TeacherName){
        List<String> res=new ArrayList<>();
        for(Teacher d:hm.keySet()){
            if(d!=null && d.getName().equals(TeacherName)){
                List<Student> Students=hm.get(d);
                for(Student m:Students) res.add(m.getName());
                break;
            }
        }
        return res;
    }

    public List<String> getAllStudentsFromDB(){
        List<String> allStudents=new ArrayList<>();
        for(Teacher d:hm.keySet()){
            for(Student m:hm.get(d)) allStudents.add(m.getName());
        }
        return allStudents;
    }

    public void deleteTeacherStudentsFromDB(String TeacherName){
        for(Teacher d:new ArrayList<>(hm.keySet())){
            if(d!=null && d.getName().equals(TeacherName)){
                hm.remove(d);
            }
        }
    }

    public void deleteAllTeacherStudentsFromDB(){
        if(hm.size()==0) return;
        for(Teacher d:new ArrayList<>(hm.keySet())){
            if(d!=null) hm.remove(d);
        }
    }
}
