package lt.viko.eif.ihasan.Task2.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Represents a Subject that a Student is enrolled in.
 * This class is annotated with JAXB annotations to control how it is marshalled to XML.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subject", propOrder = {
    "name",
    "credits"
})
public class Subject {

    @XmlElement(required = true)
    private String name;

    private int credits;

    /**
     * Default constructor required for JAXB.
     */
    public Subject() {
    }

    /**
     * Parameterized constructor to initialize a Subject.
     *
     * @param name    the name of the subject.
     * @param credits the number of credits for the subject.
     */
    public Subject(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Subject{name='" + name + "', credits=" + credits + "}";
    }
}
