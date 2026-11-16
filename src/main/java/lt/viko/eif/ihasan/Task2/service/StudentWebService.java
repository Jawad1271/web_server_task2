package lt.viko.eif.ihasan.Task2.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lt.viko.eif.ihasan.Task2.model.Student;

/**
 * Service Endpoint Interface  for the Student Web Service.
 * Defines the operations exposed by the SOAP web service.
 */
@WebService
public interface StudentWebService {

    /**
     * Retrieves a student by their name.
     *
     * @param name the name of the student to retrieve.
     * @return the Student object containing their details and subjects.
     */
    @WebMethod
    Student getStudent(@WebParam(name = "name") String name);

    /**
     * Retrieves a student's data as an HTML string.
     *
     * @param name the name of the student to retrieve.
     * @return the HTML representation of the student.
     */
    @WebMethod
    String getStudentHtml(@WebParam(name = "name") String name);

    /**
     * Retrieves a student's data as a PDF document.
     *
     * @param name the name of the student to retrieve.
     * @return the PDF representation of the student as a byte array.
     */
    @WebMethod
    byte[] getStudentPdf(@WebParam(name = "name") String name);
}
