package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.exception.ExistsException;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long count = 0;

    public Faculty addFaculty(Faculty faculty) {
        if (faculties.containsValue(faculty)) {
            throw new ExistsException("такой факультет уже есть");
        }
        faculty.setId(count++);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty getFacultyById(Long id) {
        if (!faculties.containsKey(id)) {
            throw new NotFoundException("Нет факультета с указанным id");
        }
        return faculties.get(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return faculties.values();
    }

    public Faculty removeFaculty(Long id) {
        if (!faculties.containsKey(id)) {
            throw new NotFoundException("Нет факультета с указанным id");
        }
        return faculties.remove(id);
    }

    public Faculty updatFaculty(Faculty faculty) {
        if (!faculties.containsKey(faculty.getId())) {
            throw new NotFoundException("Нет факультета с указанным id");
        }
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        return faculties.values().stream()
                .filter(f -> f.getColor().equals(color)).
                collect(Collectors.toList());
    }
}
