package lt.viko.eif.ihasan.Task2.config;

import jakarta.annotation.PostConstruct;
import jakarta.xml.ws.Endpoint;
import lt.viko.eif.ihasan.Task2.service.StudentWebServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to publish the JAX-WS Web Service endpoint.
 */
@Configuration
public class WebServiceConfig {

    @Autowired
    private StudentWebServiceImpl studentWebService;

    /**
     * Publishes the SOAP web service endpoint once the Spring Context is initialized.
     */
    @PostConstruct
    public void publishEndpoint() {
        String url = "http://localhost:8081/ws/student";
        try {
            Endpoint.publish(url, studentWebService);
            System.out.println("Web Service is published at: " + url + "?wsdl");
        } catch (IllegalArgumentException e) {
            if (e.getMessage() != null && e.getMessage().contains("already exists")) {
                System.out.println("Web Service is already published at: " + url + "?wsdl (reusing existing)");
            } else {
                throw e;
            }
        }
    }
}
