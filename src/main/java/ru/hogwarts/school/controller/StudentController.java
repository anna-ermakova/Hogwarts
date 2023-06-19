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
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Tag(name = "Студенты.", description = "CRUD-операции и другие эндпоинты для работы со студентами.")
public class StudentController {


    private final StudentService studentService;

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
    ResponseEntity<Student> getStudent(@PathVariable Long id) {
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
    ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentService.addStudent(student));
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
    ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        Student studentReturn = studentService.updatStudent(id, student);
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
    ResponseEntity<Student> removeStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.removeStudent(id));
    }


    @GetMapping
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
    public Collection<Student> getAllStudents() {
        return this.studentService.getAllStudents();
    }

}
