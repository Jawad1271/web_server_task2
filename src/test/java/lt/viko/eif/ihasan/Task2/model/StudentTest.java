package lt.viko.eif.ihasan.Task2.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testStudentGettersAndSetters() {
        Student student = new Student();
        student.setName("Jane Doe");
        student.setAge(20);
        student.setGpa(9.0f);
        student.setActive(true);
        student.setGrade('A');
        
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("Math", 5));
        student.setSubjects(subjects);

        assertEquals("Jane Doe", student.getName());
        assertEquals(20, student.getAge());
        assertEquals(9.0f, student.getGpa());
        assertTrue(student.isActive());
        assertEquals('A', student.getGrade());
        assertEquals(1, student.getSubjects().size());
        assertEquals("Math", student.getSubjects().get(0).getName());
    }

    @Test
    void testSubjectGettersAndSetters() {
        Subject subject = new Subject();
        subject.setName("Physics");
        subject.setCredits(6);

        assertEquals("Physics", subject.getName());
        assertEquals(6, subject.getCredits());
    }
}
