package org.example.springboothibernateproject.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.springboothibernateproject.entity.Student;
import org.example.springboothibernateproject.entity.Teacher;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Student createStudent(Student student) {
        entityManager.persist(student);
        return student;
    }

    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    @Transactional
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = entityManager.find(Student.class, id);
        if (existingStudent != null) {
            existingStudent.setStudentName(updatedStudent.getStudentName());
            existingStudent.setTeachers(updatedStudent.getTeachers());
            entityManager.merge(existingStudent);
            return existingStudent;
        }
        return null;
    }

    @Transactional
    public boolean deleteStudent(Long id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            entityManager.remove(student);
            return true;
        }
        return false;
    }

    @Transactional
    public List<Student> getStudentsWithTeachersWhereIdGreaterThan(Long studentId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);

        Root<Student> studentRoot = criteriaQuery.from(Student.class);

        Join<Student, Teacher> teacherJoin = studentRoot.join("teachers");

        criteriaQuery.select(studentRoot)
                .distinct(true)
                .where(criteriaBuilder.gt(studentRoot.get("studentId"), studentId));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
