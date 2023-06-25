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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.service.FacultyService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/faculties")
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
    ResponseEntity<FacultyDtoOut> getFaculty(@PathVariable("id") Long id) {
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
    ResponseEntity<FacultyDtoOut> addFaculty(@Valid @RequestBody FacultyDtoIn facultyDtoIn) {
        return ResponseEntity.ok(facultyService.create(facultyDtoIn));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение факультета по id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Факультет изменен.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Faculty.class)
                            )
                    }
            )
    })
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public ResponseEntity<FacultyDtoOut> updateFaculty(@PathVariable("id") Long id, @Valid @RequestBody FacultyDtoIn facultyDtoIn) {
        Faculty facultyReturn = facultyService.update(id, facultyDtoIn);
        if (facultyReturn == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
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
    ResponseEntity<FacultyDtoOut> removeFaculty(@PathVariable("id") Long id) {
        return ResponseEntity.ok(facultyService.delete(id));
    }


    @GetMapping("/{all}")
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
    public Collection<FacultyDtoOut> getAllFaculties(@PathVariable("all") String all) {
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
    public ResponseEntity<Collection<FacultyDtoOut>> getAllFacultiesByColor(@RequestParam(required = false) String color) {
        Collection<Faculty> result = facultyService.getAllFacultyByColor(color);
        if (result.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

}
