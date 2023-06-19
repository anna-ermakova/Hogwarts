package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @GetMapping
    public String hello() {
        return "Приложение запущено";
    }

    @GetMapping("info")
    String showInfo() {
        return "Автор: Ермакова Анна. <br>" +
                "Название проекта: Hogwarts. Students and faculties. <br>" +
                "Дата создания проекта: 2023г. <br> " +
                "Описание проекта: Приложение для Хогвардса.";
    }
}