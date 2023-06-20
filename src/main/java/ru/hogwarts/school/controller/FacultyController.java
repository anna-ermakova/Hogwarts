package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/faculty")
@RequiredArgsConstructor
@Tag(name = "Факультеты.", description = "CRUD-операции и другие эндпоинты для работы с факультетами.")
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping("/{id}")
    @Operation(summary = "Получение факультета по id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Факультет получен.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Faculty.class)
                            )
                    }
            )
    })
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    @Operation(summary = "Добавление факультета.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Факультет добавлен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Faculty.class)
                            )
                    }
            )
    })
    ResponseEntity<Faculty> addFaculty(@Valid @RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.addFaculty(faculty));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение факультета по id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Фасультет изменен.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Faculty.class)
                            )
                    }
            )
    })
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @Valid @RequestBody Faculty faculty) {
        Faculty facultyReturn = facultyService.updatFaculty(id, faculty);
        return ResponseEntity.ok(facultyReturn);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление факультета по id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Факультет удален."
            )})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    ResponseEntity<Faculty> removeFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.removeFaculty(id));
    }


    @GetMapping
    @Operation(summary = "Получение всех факультетов.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все факультеты получены.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Faculty.class)
                            )
                    }
            )
    })
    public Collection<Faculty> getAllFaculties() {
        return this.facultyService.getAllFaculties();
    }

    @GetMapping("/color/{color}")
    @Operation(summary = "Получение факультетов по цвету.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все факультеты по цвету получены.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Faculty.class)
                            )
                    }
            )
    })
    public ResponseEntity<Collection<Faculty>> getFacultiesByColor(@PathVariable String color) {
        Collection<Faculty> result = facultyService.getFacultyByColor(color);
        if (result.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

}
