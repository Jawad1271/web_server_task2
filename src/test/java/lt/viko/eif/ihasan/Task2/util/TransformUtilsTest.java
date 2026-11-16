package lt.viko.eif.ihasan.Task2.util;

import lt.viko.eif.ihasan.Task2.model.Student;
import lt.viko.eif.ihasan.Task2.model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransformUtilsTest {

    private TransformUtils transformUtils;
    private Student student;

    @BeforeEach
    void setUp() {
        transformUtils = new TransformUtils();
        
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("Programming", 6));
        
        student = new Student("Test Student", 20, 9.5f, true, 'A', subjects);
    }

    @Test
    void testConvertToXml() {
        try {
            String xml = transformUtils.convertToXml(student);
            assertNotNull(xml);
            assertTrue(xml.contains("<name>Test Student</name>"));
            assertTrue(xml.contains("<age>20</age>"));
        } catch (Exception e) {
            fail("Exception should not be thrown during XML conversion");
        }
    }

    @Test
    void testTransformToHtml() {
        try {
            String xml = transformUtils.convertToXml(student);
            String xsltPath = "src/main/resources/student-html.xsl";
            String outputPath = "student.html";
            
            transformUtils.transformToHtml(xml, xsltPath, outputPath);
            
            File htmlFile = new File(outputPath);
            assertTrue(htmlFile.exists());
            
            // Clean up
            htmlFile.delete();
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
    
    @Test
    void testTransformToPdf() {
        try {
            String xml = transformUtils.convertToXml(student);
            String xsltPath = "src/main/resources/student-pdf.xsl";
            String outputPath = "student.pdf";
            
            transformUtils.transformToPdf(xml, xsltPath, outputPath);
            
            File pdfFile = new File(outputPath);
            assertTrue(pdfFile.exists());
            
            // Clean up
            pdfFile.delete();
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
