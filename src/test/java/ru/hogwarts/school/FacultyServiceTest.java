package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FacultyServiceTest {
    FacultyService out;

    @BeforeEach
    void setupFaculties() {
        out = new FacultyService();
        out.addFaculty(new Faculty(1L, "One", "red"));
        out.addFaculty(new Faculty(2L, "Two", "blue"));
        out.addFaculty(new Faculty(3L, "Three", "green"));
        out.addFaculty(new Faculty(4L, "Four", "pink"));
        out.addFaculty(new Faculty(5L, "Five", "brown"));
        out.addFaculty(new Faculty(6L, "Six", "black"));
    }

    @Test
    void addFacultyTest() {
        int size = out.getAllFaculties().size();
        Faculty f = new Faculty(7L, "Seven", "orange");
        assertEquals(f, out.addFaculty(f));
        assertEquals(size + 1, out.getAllFaculties().size());
    }

    @Test
    void getFacultyPositiveTest() {
        assertEquals(new Faculty(1L, "One", "red"), out.getFacultyById(1L));
    }

   // @Test
   // void getFacultyNegativeTest() {

   // }

    @Test
    void updateFacultyPositiveTest() {
        Faculty f = new Faculty(3L, "Three", "green");
        int size = out.getAllFaculties().size();
        assertEquals(f, out.updatFaculty(f));
        assertEquals(size, out.getAllFaculties().size());
    }

 // @Test
 // void updateFacultyNegativeTest() {
  //     Faculty f = new Faculty(7L, "OOO", "ooo");
  //     int size = out.getAllFaculties().size();
  ////      assertNull(out.updatFaculty(f));
  //     assertEquals(size, out.getAllFaculties().size());
 //  }

    @Test
    void removeFacultyPositiveTest() {
        Faculty f = new Faculty(3L, "Three", "green");
        int size = out.getAllFaculties().size();
        assertEquals(f, out.removeFaculty(3L));
        assertEquals(size - 1, out.getAllFaculties().size());
    }

   // @Test
   // void removeFacultyNegativeTest() {
        //int size = out.getAllFaculties().size();
       // assertNull(out.removeFaculty(7L));
      //  assertEquals(size, out.getAllFaculties().size());
  //  }

    @Test
    void getFacultyByColorPositiveTest() {
        Faculty f = new Faculty(7L, "OOO", "green");
        out.addFaculty(f);
        List<Faculty> test = List.of(new Faculty(3L, "Three", "green"), f);
        assertIterableEquals(test, out.getFacultyByColor("green"));
    }

    @Test
    void getFacultyByColorNegativeTest() {
        List<Faculty> test = Collections.emptyList();
        assertIterableEquals(test, out.getFacultyByColor("ooo"));
    }
}
