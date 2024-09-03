package org.example.springboothibernateproject.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String studentName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_teacher", // intermediate table
            joinColumns = @JoinColumn(name = "student_id"), // current table's prime key is foreign key in intermediate table
            inverseJoinColumns = @JoinColumn(name = "teacher_id") // relation table's prime key is foreign key in intermediate table
    )
    private Set<Teacher> teachers = new HashSet<>();

    public Student() {
    }

    public Student(String studentName) {
        this.studentName = studentName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}
