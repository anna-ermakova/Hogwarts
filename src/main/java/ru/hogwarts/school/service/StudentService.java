package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.exception.ExistsException;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private Long id = 0L;

    public Student addStudent(Student student) {
        if (students.containsValue(student)) {
            throw new ExistsException("такой студент уже есть");
        }
        students.put(id++, student);
        return student;
    }

    public Student getStudentById(Long id) {
        if (!students.containsKey(id)) {
            throw new NotFoundException("Нет студента с указанным id");
        }
        return students.get(id);
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public Student removeStudent(Long id) {
        if (!students.containsKey(id)) {
            throw new NotFoundException("Нет студента с указанным id");
        }
        return students.remove(id);
    }

    public Student updatStudent(Long id, Student student) {
        if (!students.containsKey(id)) {
            throw new NotFoundException("Нет студента с указанным id");
        }
        students.put(id, student);
        return student;
    }
}
