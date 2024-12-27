import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


class Student{
    private String name;
    private int rollNumber;
    private String grades;
    private String contactInfo;

    public Student(String name, int rollNumber, String grades, String contactInfo) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grades = grades;
        this.contactInfo = contactInfo;
    }

    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public String getGrades() { return grades; }
    public String getContactInfo() { return contactInfo; }

    public void setName(String name) { this.name = name; }
    public void setRollNumber(int rollNumber) { this.rollNumber = rollNumber; }
    public void setGrades(String grades) { this.grades = grades; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
}

class Job {
    private String title;
    private String companyName;
    private String eligibilityCriteria;
    private String applicationDeadline;

    public Job(String title, String companyName, String eligibilityCriteria, String applicationDeadline) {
        this.title = title;
        this.companyName = companyName;
        this.eligibilityCriteria = eligibilityCriteria;
        this.applicationDeadline = applicationDeadline;
    }

    public String getTitle() { return title; }
    public String getCompanyName() { return companyName; }
    public String getEligibilityCriteria() { return eligibilityCriteria; }
    public String getApplicationDeadline() { return applicationDeadline; }

    public void setTitle(String title) { this.title = title; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setEligibilityCriteria(String eligibilityCriteria) { this.eligibilityCriteria = eligibilityCriteria; }
    public void setApplicationDeadline(String applicationDeadline) { this.applicationDeadline = applicationDeadline; }
}

class Application {
    private Student student;
    private Job job;
    private String status;

    public Application(Student student, Job job, String status) {
        this.student = student;
        this.job = job;
        this.status = status;
    }

    public Student getStudent() { return student; }
    public Job getJob() { return job; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}

class DataStorage {
    public static ArrayList<Student> students = new ArrayList<>();
    public static ArrayList<Job> jobs = new ArrayList<>();
    public static ArrayList<Application> applications = new ArrayList<>();

    static {
        // Dummy Data for Testing
        students.add(new Student("priya", 101, "A", "priya@example.com"));
        students.add(new Student("Usha", 102, "B+", "usha@example.com"));
        students.add(new Student("Ram", 105, "A", "Ram@example.com"));
        students.add(new Student("Gopal", 103, "B+", "Gopal@example.com"));
        students.add(new Student("Hari", 104, "A", "Hari@example.com"));
        students.add(new Student("Keshav", 105, "B+", "Keshav@example.com"));


        jobs.add(new Job("Software Engineer", "TechCorp", "Masters", "2024-12-31"));
        jobs.add(new Job("Data Analyst", "DigitalLogic", "Bachelors", "2024-12-30"));
        jobs.add(new Job("Java Full Stack", "TechPoint", "Masters", "2024-12-31"));
        jobs.add(new Job("Lecturer DSA", "TechEdu", "Bachelor's or higher", "2024-12-30"));
        jobs.add(new Job("Python", "KTM", "Bachelors or higher", "2024-12-31"));
        
    }
}

