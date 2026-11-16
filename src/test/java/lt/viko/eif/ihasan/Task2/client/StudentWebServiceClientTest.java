package lt.viko.eif.ihasan.Task2.client;

import jakarta.xml.ws.Service;
import lt.viko.eif.ihasan.Task2.model.Student;
import lt.viko.eif.ihasan.Task2.service.StudentWebService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.namespace.QName;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentWebServiceClientTest {

    @Test
    void testGetStudentThroughClient() throws Exception {
        // Create URL pointing to the WSDL of the running service
        URL wsdlURL = new URL("http://localhost:8081/ws/student?wsdl");
        
        // QName parameters: 1. Target namespace of the service, 2. Service name
        QName serviceName = new QName("http://service.Task2.ihasan.eif.viko.lt/", "StudentWebServiceImplService");
        
        // Create the JAX-WS Service instance
        Service service = Service.create(wsdlURL, serviceName);
        
        // Get the port/interface to interact with the service
        StudentWebService client = service.getPort(StudentWebService.class);
        
        // Call the service method
        Student student = client.getStudent("TestStudent");
        
        // Verify results
        assertNotNull(student, "Student should not be null");
        assertEquals("TestStudent", student.getName());
        assertTrue(student.isActive(), "Student should be active");
        assertFalse(student.getSubjects().isEmpty(), "Student should have subjects");
    }

    @Test
    void testGetStudentHtmlThroughClient() throws Exception {
        URL wsdlURL = new URL("http://localhost:8081/ws/student?wsdl");
        QName serviceName = new QName("http://service.Task2.ihasan.eif.viko.lt/", "StudentWebServiceImplService");
        Service service = Service.create(wsdlURL, serviceName);
        StudentWebService client = service.getPort(StudentWebService.class);

        String html = client.getStudentHtml("TestStudent");
        assertNotNull(html);
        assertTrue(html.contains("<html>"));
        assertTrue(html.contains("TestStudent"));
    }

    @Test
    void testGetStudentPdfThroughClient() throws Exception {
        URL wsdlURL = new URL("http://localhost:8081/ws/student?wsdl");
        QName serviceName = new QName("http://service.Task2.ihasan.eif.viko.lt/", "StudentWebServiceImplService");
        Service service = Service.create(wsdlURL, serviceName);
        StudentWebService client = service.getPort(StudentWebService.class);

        byte[] pdfBytes = client.getStudentPdf("TestStudent");
        assertNotNull(pdfBytes);
        assertTrue(pdfBytes.length > 0);
    }
}
