package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.service.StudentService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/students")
@Tag(name = "Студенты.", description = "CRUD-операции и другие эндпоинты для работы со студентами.")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение студента по id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Студент получен.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Student.class)
                            )
                    }
            )
    })
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    ResponseEntity<StudentDtoOut> getStudent(@PathVariable("id") Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    @Operation(summary = "Добавление студента.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Студент добавлен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Student.class)
                            )
                    }
            )
    })
    ResponseEntity<StudentDtoOut> addStudent(@Valid @RequestBody StudentDtoIn studentDtoIn) {
        return ResponseEntity.ok(studentService.create(studentDtoIn));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение студента по id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Студент изменен.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Student.class)
                            )
                    }
            )
    })
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    ResponseEntity<StudentDtoOut> updateStudent(@PathVariable("id") Long id, @Valid @RequestBody StudentDtoIn studentDtoIn) {
        Student studentReturn = studentService.update(id, studentDtoIn);
        return ResponseEntity.ok(studentReturn);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление студента по id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Студент удален."
            )})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    ResponseEntity<StudentDtoOut> removeStudent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentService.delete(id));
    }


    @GetMapping("/{all}")
    @Operation(summary = "Получение всех студентов.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все студенты получены.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Student.class)
                            )
                    }
            )
    })
    public Collection<StudentDtoOut> getAllStudents(@PathVariable("all") String all) {
        return this.studentService.getAllStudents();
    }

    @GetMapping("/age/{age}")
    @Operation(summary = "Получение студентов по возрасту.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все студенты по возрасту получены.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Student.class)
                            )
                    }
            )
    })
    public ResponseEntity<Collection<StudentDtoOut>> getStudentByAge(@RequestParam(required = false) Integer age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.getStudentsByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
}
