package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.exception.ExistsException;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long count = 0;

    public Student addStudent(Student student) {
        if (students.containsValue(student)) {
            throw new ExistsException("такой студент уже есть");
        }
        student.setId(count++);
        students.put(student.getId(), student);
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
        if (!students.containsKey(student.getId())) {
            throw new NotFoundException("Нет студента с указанным id");
        }
        students.put(student.getId(), student);
        return student;
    }

    public Collection<Student> getStudentsByAge(int age) {
        return students.values().stream().
                filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}

