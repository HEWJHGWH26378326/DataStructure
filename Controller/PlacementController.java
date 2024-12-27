import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;






class PlacementController {
    public void addStudent(String name, int rollNumber, String grades, String contactInfo) {
        DataStorage.students.add(new Student(name, rollNumber, grades, contactInfo));
    }

    public void updateStudent(int index, String name, int rollNumber, String grades, String contactInfo) {
        Student student = DataStorage.students.get(index);
        student.setName(name);
        student.setRollNumber(rollNumber);
        student.setGrades(grades);
        student.setContactInfo(contactInfo);
    }

    public void deleteStudent(int index) {
        DataStorage.students.remove(index);
    }

    public void addJob(String title, String companyName, String criteria, String deadline) {
        DataStorage.jobs.add(new Job(title, companyName, criteria, deadline));
    }

    public void updateJob(int index, String title, String companyName, String criteria, String deadline) {
        Job job = DataStorage.jobs.get(index);
        job.setTitle(title);
        job.setCompanyName(companyName);
        job.setEligibilityCriteria(criteria);
        job.setApplicationDeadline(deadline);
    }

    public void deleteJob(int index) {
        DataStorage.jobs.remove(index);
    }

    public void addApplication(Student student, Job job, String status) {
        DataStorage.applications.add(new Application(student, job, status));
    }

    public void updateApplicationStatus(int index, String status) {
        Application application = DataStorage.applications.get(index);
        application.setStatus(status);
    }
}
