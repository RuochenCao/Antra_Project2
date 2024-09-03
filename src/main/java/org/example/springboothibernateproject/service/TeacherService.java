package org.example.springboothibernateproject.service;

import org.example.springboothibernateproject.entity.Teacher;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class TeacherService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Teacher createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
        return teacher;
    }

    public Teacher getTeacherById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    public List<Teacher> getAllTeachers() {
        return entityManager.createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList();
    }

    @Transactional
    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        Teacher existingTeacher = entityManager.find(Teacher.class, id);
        if (existingTeacher != null) {
            existingTeacher.setTeacherName(updatedTeacher.getTeacherName());
            existingTeacher.setStudents(updatedTeacher.getStudents());
            entityManager.merge(existingTeacher);
            return existingTeacher;
        }
        return null;
    }

    @Transactional
    public boolean deleteTeacher(Long id) {
        Teacher teacher = entityManager.find(Teacher.class, id);
        if (teacher != null) {
            entityManager.remove(teacher);
            return true;
        }
        return false;
    }
}
