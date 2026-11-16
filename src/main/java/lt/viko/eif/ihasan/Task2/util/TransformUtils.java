package lt.viko.eif.ihasan.Task2.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lt.viko.eif.ihasan.Task2.model.Student;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Utility class to handle XML, HTML, and PDF transformations.
 * Conforms to Single Responsibility Principle (part of S.O.L.I.D.) by only handling document transformation logic.
 */
@Component
public class TransformUtils {

    /**
     * Converts a Student object to an XML String using JAXB.
     *
     * @param student the student object to convert
     * @return the XML representation of the student
     * @throws JAXBException if conversion fails
     */
    public String convertToXml(Student student) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(student, writer);
        return writer.toString();
    }

    /**
     * Transforms an XML string to an HTML string using an XSL template.
     *
     * @param xmlContent the XML content as a String
     * @param xsltPath   the path to the XSL template for HTML
     * @return the HTML content as a String
     * @throws Exception if transformation fails
     */
    public String transformToHtmlString(String xmlContent, String xsltPath) throws Exception {
        Source xmlSource = new StreamSource(new StringReader(xmlContent));
        Source xsltSource = new StreamSource(new File(xsltPath));

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xsltSource);

        StringWriter writer = new StringWriter();
        Result outputTarget = new StreamResult(writer);
        transformer.transform(xmlSource, outputTarget);
        
        return writer.toString();
    }

    /**
     * Transforms an XML string to a PDF byte array using an XSL-FO template and Apache FOP.
     *
     * @param xmlContent the XML content as a String
     * @param xsltPath   the path to the XSL-FO template for PDF
     * @return the PDF content as a byte array
     * @throws Exception if transformation fails
     */
    public byte[] transformToPdfBytes(String xmlContent, String xsltPath) throws Exception {
        File baseDir = new File(".");
        FopFactory fopFactory = FopFactory.newInstance(baseDir.toURI());
        
        try (java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream()) {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(new File(xsltPath)));
            Source src = new StreamSource(new StringReader(xmlContent));
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(src, res);
            return out.toByteArray();
        }
    }

    /**
     * Transforms an XML string to an HTML file using an XSL template.
     *
     * @param xmlContent   the XML content as a String
     * @param xsltPath     the path to the XSL template for HTML
     * @param outputHtmlPath the path where the HTML file should be saved
     * @throws Exception if transformation fails
     */
    public void transformToHtml(String xmlContent, String xsltPath, String outputHtmlPath) throws Exception {
        Source xmlSource = new StreamSource(new StringReader(xmlContent));
        Source xsltSource = new StreamSource(new File(xsltPath));

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xsltSource);

        Result outputTarget = new StreamResult(new File(outputHtmlPath));
        transformer.transform(xmlSource, outputTarget);
    }

    /**
     * Transforms an XML string to a PDF file using an XSL-FO template and Apache FOP.
     *
     * @param xmlContent   the XML content as a String
     * @param xsltPath     the path to the XSL-FO template for PDF
     * @param outputPdfPath the path where the PDF file should be saved
     * @throws Exception if transformation fails
     */
    public void transformToPdf(String xmlContent, String xsltPath, String outputPdfPath) throws Exception {
        // Setup directories
        File baseDir = new File(".");
        File outDir = new File(baseDir, "out");
        outDir.mkdirs();

        // Setup input and output files
        File xsltFile = new File(xsltPath);
        File pdfFile = new File(outputPdfPath);

        // Setup FOP
        FopFactory fopFactory = FopFactory.newInstance(baseDir.toURI());
        
        try (OutputStream out = new java.io.BufferedOutputStream(new FileOutputStream(pdfFile))) {
            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            // Setup input for XSLT transformation
            Source src = new StreamSource(new StringReader(xmlContent));

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            transformer.transform(src, res);
        }
    }
}
