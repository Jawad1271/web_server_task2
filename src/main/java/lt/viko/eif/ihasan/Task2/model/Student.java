package lt.viko.eif.ihasan.Task2.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Represents a Student entity with various data types.
 * Annotated with JAXB to allow it to be serialized into XML for the Web Service.
 */
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "student", propOrder = {
    "name",
    "age",
    "gpa",
    "isActive",
    "grade",
    "subjects"
})
public class Student {

    @XmlElement(required = true)
    private String name;

    private int age;

    private float gpa;

    @XmlElement(name = "active")
    private boolean isActive;

    private char grade;

    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
    private List<Subject> subjects;

    /**
     * Default constructor required by JAXB.
     */
    public Student() {
    }

    /**
     * Parameterized constructor.
     */
    public Student(String name, int age, float gpa, boolean isActive, char grade, List<Subject> subjects) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        this.isActive = isActive;
        this.grade = grade;
        this.subjects = subjects;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gpa=" + gpa +
                ", isActive=" + isActive +
                ", grade=" + grade +
                ", subjects=" + subjects +
                '}';
    }
}
