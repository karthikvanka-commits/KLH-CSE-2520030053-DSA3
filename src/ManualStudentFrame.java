import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ManualStudentFrame extends JFrame {

    private MainFrame mainFrame;

    private JTextField idField;

    private JComboBox<String> departmentBox;
    private JComboBox<String> sectionBox;
    private JComboBox<String> slotBox;
    private JComboBox<String> priorityBox;

    private DefaultTableModel tableModel;

    private JTable table;

    private List<Student> studentList =
            new ArrayList<>();

    public ManualStudentFrame(
            MainFrame mainFrame) {

        this.mainFrame =
                mainFrame;

        setTitle(
                "Manual Student Input"
        );

        setSize(
                900,
                600
        );

        setLocationRelativeTo(
                mainFrame
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createInterface();
    }

    private void createInterface() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        // ==========================================
        // TITLE
        // ==========================================

        JLabel title =
                new JLabel(
                        "MANUAL STUDENT INPUT",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        // ==========================================
        // INPUT FORM
        // ==========================================

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                6,
                                10,
                                8
                        )
                );

        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Add Student"
                )
        );

        formPanel.add(
                new JLabel("Student ID")
        );

        formPanel.add(
                new JLabel("Department")
        );

        formPanel.add(
                new JLabel("Section")
        );

        formPanel.add(
                new JLabel("Preferred Slot")
        );

        formPanel.add(
                new JLabel("Priority")
        );

        formPanel.add(
                new JLabel("")
        );

        idField =
                new JTextField();

        departmentBox =
                new JComboBox<>(
                        new String[]{
                                "CSE",
                                "ECE"
                        }
                );

        sectionBox =
                new JComboBox<>(
                        new String[]{
                                "A",
                                "B",
                                "C",
                                "D",
                                "E"
                        }
                );

        slotBox =
                new JComboBox<>(
                        new String[]{
                                "09:00-10:00",
                                "10:00-11:00",
                                "11:00-12:00"
                        }
                );

        priorityBox =
                new JComboBox<>(
                        new String[]{
                                "1",
                                "2"
                        }
                );

        JButton addButton =
                new JButton(
                        "Add"
                );

        formPanel.add(idField);
        formPanel.add(departmentBox);
        formPanel.add(sectionBox);
        formPanel.add(slotBox);
        formPanel.add(priorityBox);
        formPanel.add(addButton);

        // ==========================================
        // TABLE
        // ==========================================

        String[] columns = {
                "Student ID",
                "Department",
                "Section",
                "Preferred Slot",
                "Priority"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        table =
                new JTable(
                        tableModel
                );

        table.setRowHeight(28);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        table.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        table
                );

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        centerPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // ==========================================
        // BOTTOM BUTTONS
        // ==========================================

        JPanel bottomPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                15,
                                0
                        )
                );

        JButton removeButton =
                new JButton(
                        "Remove Selected"
                );

        JButton useButton =
                new JButton(
                        "Use These Students"
                );

        JButton cancelButton =
                new JButton(
                        "Cancel"
                );

        bottomPanel.add(
                removeButton
        );

        bottomPanel.add(
                useButton
        );

        bottomPanel.add(
                cancelButton
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // ==========================================
        // ADD
        // ==========================================

        addButton.addActionListener(
                e -> addStudent()
        );

        // ==========================================
        // REMOVE
        // ==========================================

        removeButton.addActionListener(
                e -> removeSelectedStudent()
        );

        // ==========================================
        // USE STUDENTS
        // ==========================================

        useButton.addActionListener(
                e -> useStudents()
        );

        // ==========================================
        // CANCEL
        // ==========================================

        cancelButton.addActionListener(
                e -> dispose()
        );

        setContentPane(
                mainPanel
        );
    }

    // ==========================================
    // ADD STUDENT
    // ==========================================

    private void addStudent() {

        String id =
                idField
                        .getText()
                        .trim();

        if (id.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a Student ID."
            );

            return;
        }

        // Prevent duplicate IDs
        for (Student student :
                studentList) {

            if (student.getStudentId()
                    .equalsIgnoreCase(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "This Student ID already exists."
                );

                return;
            }
        }

        String department =
                (String)
                departmentBox
                        .getSelectedItem();

        String section =
                (String)
                sectionBox
                        .getSelectedItem();

        String slot =
                (String)
                slotBox
                        .getSelectedItem();

        int priority =
                Integer.parseInt(
                        (String)
                        priorityBox
                                .getSelectedItem()
                );

        Student student =
                new Student(
                        id,
                        department,
                        section,
                        slot,
                        priority
                );

        studentList.add(
                student
        );

        tableModel.addRow(
                new Object[]{
                        id,
                        department,
                        section,
                        slot,
                        priority
                }
        );

        idField.setText("");

        idField.requestFocus();
    }

    // ==========================================
    // REMOVE STUDENT
    // ==========================================

    private void removeSelectedStudent() {

        int selectedRow =
                table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student to remove."
            );

            return;
        }

        studentList.remove(
                selectedRow
        );

        tableModel.removeRow(
                selectedRow
        );
    }

    // ==========================================
    // USE STUDENTS
    // ==========================================

    private void useStudents() {

        if (studentList.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please add at least one student."
            );

            return;
        }

        mainFrame.setManualStudents(
                new ArrayList<>(
                        studentList
                )
        );

        JOptionPane.showMessageDialog(
                this,

                studentList.size()
                + " students loaded successfully.\n\n"
                + "Click 'Run Allocation' on the dashboard "
                + "to perform Maximum Bipartite Matching.",

                "Students Loaded",

                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();
    }
}