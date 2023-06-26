package ru.hogwarts.school.service;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDtoIn;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.mapper.FacultyMapper;
import ru.hogwarts.school.repository.FacultiesRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultiesRepository facultiesRepository;
    private final FacultyMapper facultyMapper;

    public FacultyService(FacultiesRepository facultiesRepository, FacultyMapper facultyMapper) {
        this.facultiesRepository = facultiesRepository;
        this.facultyMapper = facultyMapper;
    }

    public FacultyDtoOut create(FacultyDtoIn facultyDtoIn) {
        return facultyMapper.toDto(
                facultiesRepository.save(
                        facultyMapper.toEntity(facultyDtoIn)));
    }

    public FacultyDtoOut update(long id, FacultyDtoIn facultyDtoIn) {
        return facultiesRepository.findById(id)
                .map(oldFaculty -> {
                            oldFaculty.setColor(facultyDtoIn.getColor());
                            oldFaculty.setName(facultyDtoIn.getName());
                            return facultyMapper.toDto(facultiesRepository.save(oldFaculty));
                        }
                )
                .orElseThrow(() -> new FacultyNotFoundException(id));
    }

    public FacultyDtoOut getFacultyById(Long id) {
        return facultiesRepository.findById(id)
                .map(facultyMapper::toDto)
                .orElseThrow(() -> new FacultyNotFoundException(id));
    }

    public FacultyDtoOut delete(Long id) {
        Faculty faculty = facultiesRepository.findById(id)
                .orElseThrow(() -> new FacultyNotFoundException(id));
        facultiesRepository.delete(faculty);
        return facultyMapper.toDto(faculty);
    }


    public List<FacultyDtoOut> getAllFacultyByColor(@Nullable String color) {
        return Optional.ofNullable(color)
                .map(facultiesRepository::findAllByColor)
                .orElseGet(facultiesRepository::findAll)
                .stream()
                .map(facultyMapper::toDto)
                .collect(Collectors.toList());
    }
}
