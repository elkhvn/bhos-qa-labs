import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ClassroomTest {

    Student tom = new Student("Tom", "Johnson", "tom1@gmail.com", "IT2021");
    Student anna = new Student("Anna", "Bell", "anna1@gmail.com", "IT2021");
    Student layla = new Student("Layla", "Smith", "layla@gmail.com", "IT2021");
    Student david = new Student("David", "Brown", "dev@gmail.com", "IT2021");

    List<Student> students = new ArrayList<>(List.of(tom, anna, layla));
    Classroom it2021 = new Classroom(students);


    @DisplayName("Test for addStudent() method")
    @Test
    void addStudent() {
        Student david = new Student("David", "Brown", "dev@gmail.com", "IT2021");
        Assertions.assertTrue(it2021.addStudent(david));
    }


    @DisplayName("Test for deleteStudent() method")
    @Test
    void deleteStudent() {
        Student david = new Student("David", "Brown", "dev@gmail.com", "IT2021");
        Assertions.assertTrue(it2021.deleteStudent(david));

    }

    @DisplayName("Test for studentCount() method")
    @Test
    void studentCount() {
        Assertions.assertEquals(3, it2021.studentCount());
    }

}
