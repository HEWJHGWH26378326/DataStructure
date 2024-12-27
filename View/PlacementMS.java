import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

class StudentManagementPanel extends JPanel {
    private JTable studentTable;
    private JTextField nameField, rollField, gradeField, contactField;
    private JButton addButton, updateButton, deleteButton, viewButton;
    private PlacementController controller;
    private int selectedRow = -1;

    public StudentManagementPanel(PlacementController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Roll Number:"));
        rollField = new JTextField();
        formPanel.add(rollField);

        formPanel.add(new JLabel("Grades:"));
        gradeField = new JTextField();
        formPanel.add(gradeField);

        formPanel.add(new JLabel("Contact Info:"));
        contactField = new JTextField();
        formPanel.add(contactField);

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        viewButton = new JButton("View");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        formPanel.add(buttonPanel);

        // Table
        studentTable = new JTable();
        JScrollPane tablePane = new JScrollPane(studentTable);

        add(formPanel, BorderLayout.NORTH);
        add(tablePane, BorderLayout.CENTER);

        // Button Actions
        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        viewButton.addActionListener(e -> viewStudent());

        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = studentTable.getSelectedRow();
            }
        });

        refreshTable();
    }

    private void addStudent() {
        try {
            String name = nameField.getText();
            int rollNumber = Integer.parseInt(rollField.getText());
            String grades = gradeField.getText();
            String contactInfo = contactField.getText();
            controller.addStudent(name, rollNumber, grades, contactInfo);
            refreshTable();
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid roll number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        if (selectedRow != -1) {
            try {
                String name = nameField.getText();
                int rollNumber = Integer.parseInt(rollField.getText());
                String grades = gradeField.getText();
                String contactInfo = contactField.getText();
                controller.updateStudent(selectedRow, name, rollNumber, grades, contactInfo);
                refreshTable();
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid roll number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No student selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        if (selectedRow != -1) {
            controller.deleteStudent(selectedRow);
            refreshTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "No student selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewStudent() {
        if (selectedRow != -1) {
            Student student = DataStorage.students.get(selectedRow);
            nameField.setText(student.getName());
            rollField.setText(String.valueOf(student.getRollNumber()));
            gradeField.setText(student.getGrades());
            contactField.setText(student.getContactInfo());
        } else {
            JOptionPane.showMessageDialog(this, "No student selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTable() {
        String[][] data = new String[DataStorage.students.size()][4];
        for (int i = 0; i < DataStorage.students.size(); i++) {
            Student s = DataStorage.students.get(i);
            data[i][0] = s.getName();
            data[i][1] = String.valueOf(s.getRollNumber());
            data[i][2] = s.getGrades();
            data[i][3] = s.getContactInfo();
        }

        studentTable.setModel(new DefaultTableModel(data, new String[]{"Name", "Roll Number", "Grades", "Contact Info"}));
    }

    private void clearFields() {
        nameField.setText("");
        rollField.setText("");
        gradeField.setText("");
        contactField.setText("");
    }
}

class JobManagementPanel extends JPanel {
    private JTable jobTable;
    private JTextField titleField, companyField, criteriaField, deadlineField;
    private JButton addButton, updateButton, deleteButton, viewButton;
    private PlacementController controller;
    private int selectedRow = -1;

    public JobManagementPanel(PlacementController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Job Title:"));
        titleField = new JTextField();
        formPanel.add(titleField);

        formPanel.add(new JLabel("Company Name:"));
        companyField = new JTextField();
        formPanel.add(companyField);

        formPanel.add(new JLabel("Eligibility Criteria:"));
        criteriaField = new JTextField();
        formPanel.add(criteriaField);

        formPanel.add(new JLabel("Application Deadline:"));
        deadlineField = new JTextField();
        formPanel.add(deadlineField);

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        viewButton = new JButton("View");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        formPanel.add(buttonPanel);

        // Table
        jobTable = new JTable();
        JScrollPane tablePane = new JScrollPane(jobTable);

        add(formPanel, BorderLayout.NORTH);
        add(tablePane, BorderLayout.CENTER);

        // Button Actions
        addButton.addActionListener(e -> addJob());
        updateButton.addActionListener(e -> updateJob());
        deleteButton.addActionListener(e -> deleteJob());
        viewButton.addActionListener(e -> viewJob());

        jobTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = jobTable.getSelectedRow();
            }
        });

        refreshTable();
    }

    private void addJob() {
        String title = titleField.getText();
        String company = companyField.getText();
        String criteria = criteriaField.getText();
        String deadline = deadlineField.getText();
        controller.addJob(title, company, criteria, deadline);
        refreshTable();
        clearFields();
    }

    private void updateJob() {
        if (selectedRow != -1) {
            String title = titleField.getText();
            String company = companyField.getText();
            String criteria = criteriaField.getText();
            String deadline = deadlineField.getText();
            controller.updateJob(selectedRow, title, company, criteria, deadline);
            refreshTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "No job selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteJob() {
        if (selectedRow != -1) {
            controller.deleteJob(selectedRow);
            refreshTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "No job selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewJob() {
        if (selectedRow != -1){
            Job job = DataStorage.jobs.get(selectedRow);
            titleField.setText(job.getTitle());
            companyField.setText(job.getCompanyName());
            criteriaField.setText(job.getEligibilityCriteria());
            deadlineField.setText(job.getApplicationDeadline());
        } else {
            JOptionPane.showMessageDialog(this, "No job selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTable() {
        String[][] data = new String[DataStorage.jobs.size()][4];
        for (int i = 0; i < DataStorage.jobs.size(); i++) {
            Job j = DataStorage.jobs.get(i);
            data[i][0] = j.getTitle();
            data[i][1] = j.getCompanyName();
            data[i][2] = j.getEligibilityCriteria();
            data[i][3] = j.getApplicationDeadline();
        }

        jobTable.setModel(new DefaultTableModel(data, new String[]{"Title", "Company", "Criteria", "Deadline"}));
    }

    private void clearFields() {
        titleField.setText("");
        companyField.setText("");
        criteriaField.setText("");
        deadlineField.setText("");
    }
}
class ApplicationTrackingPanel extends JPanel {
    private JTable applicationTable;
    private JComboBox<String> studentComboBox, jobComboBox;
    private JTextField statusField;
    private JButton addButton, updateStatusButton;
    private PlacementController controller;

    public ApplicationTrackingPanel(PlacementController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Student:"));
        studentComboBox = new JComboBox<>();
        for (Student student : DataStorage.students) {
            studentComboBox.addItem(student.getName());
        }
        formPanel.add(studentComboBox);

        formPanel.add(new JLabel("Job:"));
        jobComboBox = new JComboBox<>();
        for (Job job : DataStorage.jobs) {
            jobComboBox.addItem(job.getTitle());
        }
        formPanel.add(jobComboBox);

        formPanel.add(new JLabel("Status:"));
        statusField = new JTextField();
        formPanel.add(statusField);

        addButton = new JButton("Add Application");
        updateStatusButton = new JButton("Update Status");
        formPanel.add(addButton);
        formPanel.add(updateStatusButton);

        add(formPanel, BorderLayout.NORTH);

        // Table
        applicationTable = new JTable();
        JScrollPane tablePane = new JScrollPane(applicationTable);
        add(tablePane, BorderLayout.CENTER);

        // Button Actions
        addButton.addActionListener(e -> addApplication());
        updateStatusButton.addActionListener(e -> updateApplicationStatus());

        refreshTable();
    }

    private void addApplication() {
        try {
            int studentIndex = studentComboBox.getSelectedIndex();
            int jobIndex = jobComboBox.getSelectedIndex();
            String status = statusField.getText();

            if (studentIndex >= 0 && jobIndex >= 0) {
                Student student = DataStorage.students.get(studentIndex);
                Job job = DataStorage.jobs.get(jobIndex);
                controller.addApplication(student, job, status);
                refreshTable();
                statusField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid selection.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding application.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateApplicationStatus() {
        int selectedRow = applicationTable.getSelectedRow();
        if (selectedRow != -1) {
            String status = statusField.getText();
            controller.updateApplicationStatus(selectedRow, status);
            refreshTable();
            statusField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "No application selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTable() {
        String[][] data = new String[DataStorage.applications.size()][3];
        for (int i = 0; i < DataStorage.applications.size(); i++) {
            Application app = DataStorage.applications.get(i);
            data[i][0] = app.getStudent().getName();
            data[i][1] = app.getJob().getTitle();
            data[i][2] = app.getStatus();
        }

        applicationTable.setModel(new DefaultTableModel(data, new String[]{"Student", "Job", "Status"}));
    }
}
public class PlacementMS {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Placement Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane();
            PlacementController controller = new PlacementController();

            tabbedPane.addTab("Students", new StudentManagementPanel(controller));
            tabbedPane.addTab("Jobs", new JobManagementPanel(controller));
            tabbedPane.addTab("Applications", new ApplicationTrackingPanel(controller));

            frame.add(tabbedPane, BorderLayout.CENTER);
            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}