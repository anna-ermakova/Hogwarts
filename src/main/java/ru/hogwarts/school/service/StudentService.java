package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.ExistsException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.entity.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private long count = 1;

    public Student  create(Student student) {
        if (!students.containsValue(student)) {
            throw new ExistsException("такой студент уже есть");
        }
        student.setId(count++);
        students.put(count, student);
        return student;
    }

    public Student getStudentById(Long id) {
        if (students.containsKey(id)) {
            return students.get(id);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }


    public Student delete(Long id) {
        if (students.containsKey(id)) {
            return students.remove(id);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    public Student update(long id, Student student) {
        if (students.containsKey(id)) {
            Student oldStudent = students.get(id);
            oldStudent.setName(student.getName());
            oldStudent.setAge(student.getAge());
            students.replace(id, oldStudent);
            return oldStudent;
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    public Collection<Student> getStudentsByAge(int age) {
        return students.values().stream().
                filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}

