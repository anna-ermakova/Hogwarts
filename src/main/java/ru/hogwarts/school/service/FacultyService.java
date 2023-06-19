package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.exception.ExistsException;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private Long id = 0L;

    public Faculty addFaculty(Faculty faculty) {
        if (faculties.containsValue(faculty)) {
            throw new ExistsException("такой факультет уже есть");
        }
        faculties.put(id++, faculty);
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

    public Faculty updatFaculty(Long id, Faculty faculty) {
        if (!faculties.containsKey(id)) {
            throw new NotFoundException("Нет факультета с указанным id");
        }
        faculties.put(id, faculty);
        return faculty;
    }
}
