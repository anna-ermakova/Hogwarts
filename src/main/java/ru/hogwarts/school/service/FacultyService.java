package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDtoIn;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.exception.ExistsException;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.repository.FacultiesRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultiesRepository facultiesRepository;

    public FacultyService(FacultiesRepository facultiesRepository) {
        this.facultiesRepository = facultiesRepository;
    }

    public FacultyDtoOut create(FacultyDtoIn facultyDtoIn) {
        if (faculties.containsValue(facultyDtoIn)) {
            throw new ExistsException("такой факультет уже есть");
        }
        faculties.put(count, facultyDtoIn);
        return facultyDtoIn;
    }

    public FacultyDtoOut getFacultyById(Long id) {
        if (faculties.containsKey(id)) {
            return faculties.get(id);
        } else {
            throw new FacultyNotFoundException(id);
        }
    }

    public Collection<FacultyDtoOut> getAllFaculties() {
        return faculties.values();
    }

    public Faculty delete(Long id) {
        if (faculties.containsKey(id)) {
            return faculties.remove(id);
        } else {
            throw new FacultyNotFoundException(id);
        }
    }

    public FacultyDtoOut update(long id, FacultyDtoIn facultyDtoIn) {
        if (faculties.containsKey(id)) {
            Faculty oldFaculty = faculties.get(id);
            oldFaculty.setName(faculty.getName());
            oldFaculty.setColor(faculty.getColor());
            faculties.replace(id, oldFaculty);
            return oldFaculty;
        } else {
            throw new FacultyNotFoundException(id);
        }
    }


    public Collection<FacultyDtoOut> getAllFacultyByColor(String color) {
        return faculties.values().stream()
                .filter(f -> f.getColor().equals(color)).
                collect(Collectors.toList());
    }
}
