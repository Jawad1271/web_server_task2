package lt.viko.eif.ihasan.Task2.service;

import lt.viko.eif.ihasan.Task2.model.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentWebServiceImplTest {

    @Test
    void testGetStudent() {
        StudentWebServiceImpl service = new StudentWebServiceImpl();
        Student student = service.getStudent("Alice");
        
        assertNotNull(student);
        assertEquals("Alice", student.getName());
        assertEquals(22, student.getAge());
        assertTrue(student.getSubjects().size() > 0);
    }
}
