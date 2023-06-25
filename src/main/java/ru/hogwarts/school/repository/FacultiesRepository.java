package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.entity.Faculty;

public interface FacultiesRepository extends JpaRepository<Faculty,Long> {
}
