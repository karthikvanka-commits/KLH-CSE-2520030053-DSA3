import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class DataTableFrame extends JFrame {

    public DataTableFrame(
            String title,
            String[] columns,
            Object[][] data) {

        setTitle(title);

        setSize(1100, 650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 20, 20
                )
        );

        // ==========================================
        // TITLE
        // ==========================================

        JLabel titleLabel =
                new JLabel(
                        title,
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // ==========================================
        // TABLE
        // ==========================================

        DefaultTableModel model =
                new DefaultTableModel(
                        data,
                        columns
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        JTable table =
                new JTable(model);

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

        table.setAutoCreateRowSorter(true);

        // ==========================================
        // SEARCH
        // ==========================================

        JPanel searchPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        JLabel searchLabel =
                new JLabel("Search:");

        searchLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        JTextField searchField =
                new JTextField();

        searchField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        searchPanel.add(
                searchLabel,
                BorderLayout.WEST
        );

        searchPanel.add(
                searchField,
                BorderLayout.CENTER
        );

        TableRowSorter<DefaultTableModel> sorter =
                new TableRowSorter<>(model);

        table.setRowSorter(sorter);

        searchField.getDocument()
                .addDocumentListener(
                        new javax.swing.event.DocumentListener() {

                            private void filter() {

                                String text =
                                        searchField
                                                .getText()
                                                .trim();

                                if (text.isEmpty()) {

                                    sorter.setRowFilter(
                                            null
                                    );

                                } else {

                                    sorter.setRowFilter(
                                            RowFilter
                                                    .regexFilter(
                                                            "(?i)"
                                                            + java.util.regex.Pattern
                                                                    .quote(text)
                                                    )
                                    );
                                }
                            }

                            @Override
                            public void insertUpdate(
                                    javax.swing.event.DocumentEvent e) {
                                filter();
                            }

                            @Override
                            public void removeUpdate(
                                    javax.swing.event.DocumentEvent e) {
                                filter();
                            }

                            @Override
                            public void changedUpdate(
                                    javax.swing.event.DocumentEvent e) {
                                filter();
                            }
                        }
                );

        JScrollPane scrollPane =
                new JScrollPane(table);

        // ==========================================
        // CENTER
        // ==========================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        centerPanel.add(
                searchPanel,
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

        setContentPane(mainPanel);
    }

    // ==========================================
    // STUDENTS
    // ==========================================

    public static void showStudents(
            List<Student> students) {

        String[] columns = {
                "Student ID",
                "Department",
                "Section",
                "Preferred Slot",
                "Priority"
        };

        Object[][] data =
                new Object[students.size()][5];

        for (int i = 0;
             i < students.size();
             i++) {

            Student student =
                    students.get(i);

            data[i][0] =
                    student.getStudentId();

            data[i][1] =
                    student.getDepartment();

            data[i][2] =
                    student.getSection();

            data[i][3] =
                    student.getPreferredSlot();

            data[i][4] =
                    student.getPriority();
        }

        DataTableFrame frame =
                new DataTableFrame(
                        "Student Details",
                        columns,
                        data
                );

        frame.setVisible(true);
    }

    // ==========================================
    // LABS
    // ==========================================

    public static void showLabs(
            List<Lab> labs) {

        String[] columns = {
                "Lab ID",
                "Lab Name",
                "Capacity",
                "Available Slots"
        };

        Object[][] data =
                new Object[labs.size()][4];

        for (int i = 0;
             i < labs.size();
             i++) {

            Lab lab =
                    labs.get(i);

            data[i][0] =
                    lab.getLabId();

            data[i][1] =
                    lab.getLabName();

            data[i][2] =
                    lab.getCapacity();

            data[i][3] =
                    lab.getAvailableSlots();
        }

        DataTableFrame frame =
                new DataTableFrame(
                        "Laboratory Details",
                        columns,
                        data
                );

        frame.setVisible(true);
    }

    // ==========================================
    // ALLOCATIONS
    // ==========================================

    public static void showAllocations(
            AllocationResult result) {

        String[] columns = {
                "Student ID",
                "Department",
                "Section",
                "Preferred Slot",
                "Lab ID",
                "Lab Name",
                "Slot",
                "Seat"
        };

        List<Allocation> allocations =
                result.getAllocations();

        Object[][] data =
                new Object[allocations.size()][8];

        for (int i = 0;
             i < allocations.size();
             i++) {

            Allocation allocation =
                    allocations.get(i);

            Student student =
                    allocation.getStudent();

            data[i][0] =
                    student.getStudentId();

            data[i][1] =
                    student.getDepartment();

            data[i][2] =
                    student.getSection();

            data[i][3] =
                    student.getPreferredSlot();

            data[i][4] =
                    allocation.getLabId();

            data[i][5] =
                    allocation.getLabName();

            data[i][6] =
                    allocation.getSlot();

            data[i][7] =
                    allocation.getSeatNumber();
        }

        DataTableFrame frame =
                new DataTableFrame(
                        "Student Allocation Results",
                        columns,
                        data
                );

        frame.setVisible(true);
    }
}