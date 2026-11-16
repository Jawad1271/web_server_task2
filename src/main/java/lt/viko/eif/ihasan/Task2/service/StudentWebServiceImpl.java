package lt.viko.eif.ihasan.Task2.service;

import jakarta.jws.WebService;
import lt.viko.eif.ihasan.Task2.model.Student;
import lt.viko.eif.ihasan.Task2.model.Subject;
import lt.viko.eif.ihasan.Task2.util.TransformUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the StudentWebService.
 * Contains the business logic to handle the SOAP requests.
 */
@Service
@WebService(endpointInterface = "lt.viko.eif.ihasan.Task2.service.StudentWebService")
public class StudentWebServiceImpl implements StudentWebService {

    @Autowired
    private TransformUtils transformUtils;

    @Override
    public Student getStudent(String name) {
        // Create mock data for demonstration purposes
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("Web Services", 6));
        subjects.add(new Subject("Information Security", 6));
        subjects.add(new Subject("Software Engineering", 6));

        // Let's assume we search and found the student
        Student student = new Student(
                name != null ? name : "John Doe",
                22,
                8.5f,
                true,
                'A',
                subjects
        );

        return student;
    }

    @Override
    public String getStudentHtml(String name) {
        try {
            Student student = getStudent(name);
            String xmlContent = transformUtils.convertToXml(student);
            return transformUtils.transformToHtmlString(xmlContent, "src/main/resources/student-html.xsl");
        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating HTML: " + e.getMessage();
        }
    }

    @Override
    public byte[] getStudentPdf(String name) {
        try {
            Student student = getStudent(name);
            String xmlContent = transformUtils.convertToXml(student);
            return transformUtils.transformToPdfBytes(xmlContent, "src/main/resources/student-pdf.xsl");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
