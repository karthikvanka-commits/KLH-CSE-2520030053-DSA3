import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel studentCountLabel;
    private JLabel allocatedCountLabel;
    private JLabel unallocatedCountLabel;

    private JLabel statusLabel;

    private JLabel studentsLoadedLabel;
    private JLabel labsLabel;
    private JLabel seatsLabel;
    private JLabel inputModeLabel;
    private JLabel allocationRateLabel;

    private List<Student> students;
    private List<Lab> labs;
    private List<LabSeat> seats;

    private AllocationResult result;

    private boolean allocationCompleted = true;

    private String currentInputMode =
            "CSV Dataset";

    // ==========================================
    // COLORS
    // ==========================================

    private final Color BACKGROUND =
            new Color(245, 247, 250);

    private final Color CARD =
            Color.WHITE;

    private final Color TEXT =
            new Color(35, 40, 45);

    private final Color MUTED =
            new Color(100, 110, 120);

    private final Color BORDER =
            new Color(220, 225, 230);

    private final Color PRIMARY =
            new Color(45, 95, 160);

    private final Color PRIMARY_DARK =
            new Color(35, 75, 130);

    private final Color SUCCESS =
            new Color(40, 145, 85);

    private final Color WARNING =
            new Color(200, 125, 25);

    private final Color DANGER =
            new Color(190, 70, 70);

    private final Color LIGHT_BLUE =
            new Color(235, 242, 250);

    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public MainFrame() {

        setTitle(
                "Automated Student Lab Allocation System"
        );

        setSize(1100, 800);

        setMinimumSize(
                new Dimension(950, 720)
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        loadCSVData();

        createInterface();
    }

    // ==========================================
    // LOAD CSV DATA
    // ==========================================

    private void loadCSVData() {

        String studentFile =
                "data/student_lab_allocation_students.csv";

        String labFile =
                "data/student_lab_allocation_labs.csv";

        students =
                CSVReader.readStudents(studentFile);

        labs =
                CSVReader.readLabs(labFile);

        seats =
                LabSeatGenerator.generateSeats(labs);

        currentInputMode =
                "CSV Dataset";

        runMatching();
    }

    // ==========================================
    // RUN MATCHING
    // ==========================================

    private void runMatching() {

        SeatBipartiteGraph graph =
                new SeatBipartiteGraph(
                        students,
                        seats
                );

        graph.buildGraph();

        MaximumBipartiteMatching matching =
                new MaximumBipartiteMatching(graph);

        matching.maximumMatching();

        AllocationManager manager =
                new AllocationManager(
                        students,
                        seats,
                        matching
                );

        result =
                manager.createResult();

        allocationCompleted = true;
    }

    // ==========================================
    // CREATE INTERFACE
    // ==========================================

    private void createInterface() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                18,
                                18
                        )
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        22,
                        28,
                        22,
                        28
                )
        );

        mainPanel.add(
                createHeaderPanel(),
                BorderLayout.NORTH
        );

        JPanel centerPanel =
                new JPanel();

        centerPanel.setLayout(
                new BoxLayout(
                        centerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        centerPanel.setBackground(
                BACKGROUND
        );

        // ==========================================
        // STATISTICS CARDS
        // ==========================================

        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                16,
                                0
                        )
                );

        statsPanel.setBackground(
                BACKGROUND
        );

        studentCountLabel =
                createStatCard(
                        "TOTAL STUDENTS",
                        students.size(),
                        PRIMARY
                );

        allocatedCountLabel =
                createStatCard(
                        "ALLOCATED",
                        result.getAllocatedCount(),
                        SUCCESS
                );

        unallocatedCountLabel =
                createStatCard(
                        "UNALLOCATED",
                        result.getUnallocatedCount(),
                        DANGER
                );

        statsPanel.add(
                studentCountLabel
        );

        statsPanel.add(
                allocatedCountLabel
        );

        statsPanel.add(
                unallocatedCountLabel
        );

        centerPanel.add(
                statsPanel
        );

        centerPanel.add(
                Box.createVerticalStrut(15)
        );

        // ==========================================
        // STATUS
        // ==========================================

        centerPanel.add(
                createStatusPanel()
        );

        centerPanel.add(
                Box.createVerticalStrut(15)
        );

        // ==========================================
        // INFORMATION
        // ==========================================

        centerPanel.add(
                createInformationPanel()
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // ==========================================
        // ACTION BUTTONS
        // ==========================================

        mainPanel.add(
                createButtonPanel(),
                BorderLayout.SOUTH
        );

        setContentPane(
                mainPanel
        );
    }

    // ==========================================
    // HEADER
    // ==========================================

    private JPanel createHeaderPanel() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                PRIMARY
        );

        header.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                PRIMARY_DARK
                        ),
                        new EmptyBorder(
                                18,
                                25,
                                18,
                                25
                        )
                )
        );

        JPanel textPanel =
                new JPanel();

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );

        textPanel.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Automated Student Lab Allocation System"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        title.setForeground(
                Color.WHITE
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel subtitle =
                new JLabel(
                        "Maximum Bipartite Matching  •  "
                        + "Intelligent Laboratory Allocation"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        subtitle.setForeground(
                new Color(
                        225,
                        235,
                        248
                )
        );

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        textPanel.add(
                title
        );

        textPanel.add(
                Box.createVerticalStrut(6)
        );

        textPanel.add(
                subtitle
        );

        header.add(
                textPanel,
                BorderLayout.CENTER
        );

        return header;
    }

    // ==========================================
    // STAT CARD
    // ==========================================

    private JLabel createStatCard(
            String title,
            int value,
            Color accent) {

        JLabel label =
                new JLabel(
                        createStatHTML(
                                title,
                                value
                        ),
                        SwingConstants.CENTER
                );

        label.setOpaque(true);

        label.setBackground(
                CARD
        );

        label.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                4,
                                0,
                                0,
                                0,
                                accent
                        ),
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(
                                        BORDER
                                ),
                                new EmptyBorder(
                                        15,
                                        10,
                                        15,
                                        10
                                )
                        )
                )
        );

        return label;
    }

    private String createStatHTML(
            String title,
            int value) {

        return "<html>"
                + "<div style='text-align:center;'>"
                + "<font face='Arial' size='4' "
                + "color='#646E78'>"
                + "<b>"
                + title
                + "</b>"
                + "</font>"
                + "<br><br>"
                + "<font face='Arial' size='7' "
                + "color='#23282D'>"
                + "<b>"
                + value
                + "</b>"
                + "</font>"
                + "</div>"
                + "</html>";
    }

    // ==========================================
    // STATUS PANEL
    // ==========================================

    private JPanel createStatusPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        panel.setBackground(
                CARD
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                13,
                                18,
                                13,
                                18
                        )
                )
        );

        JLabel heading =
                new JLabel(
                        "SYSTEM STATUS"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        heading.setForeground(
                MUTED
        );

        statusLabel =
                new JLabel();

        updateStatusLabel();

        panel.add(
                heading,
                BorderLayout.WEST
        );

        panel.add(
                statusLabel,
                BorderLayout.CENTER
        );

        return panel;
    }

    private void updateStatusLabel() {

        if (allocationCompleted) {

            statusLabel.setText(
                    "●  Allocation completed successfully"
            );

            statusLabel.setForeground(
                    SUCCESS
            );

        } else {

            statusLabel.setText(
                    "●  Input changed — Run Allocation required"
            );

            statusLabel.setForeground(
                    WARNING
            );
        }

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );
    }

    // ==========================================
    // INFORMATION PANEL
    // ==========================================

    private JPanel createInformationPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                CARD
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(
                                        BORDER
                                ),
                                "System Information"
                        ),
                        new EmptyBorder(
                                10,
                                15,
                                15,
                                15
                        )
                )
        );

        JPanel information =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                25,
                                12
                        )
                );

        information.setBackground(
                CARD
        );

        information.add(
                createInfoLabel(
                        "Algorithm",
                        "Maximum Bipartite Matching"
                )
        );

        studentsLoadedLabel =
                createInfoLabel(
                        "Students Loaded",
                        String.valueOf(
                                students.size()
                        )
                );

        information.add(
                studentsLoadedLabel
        );

        labsLabel =
                createInfoLabel(
                        "Laboratories",
                        String.valueOf(
                                labs.size()
                        )
                );

        information.add(
                labsLabel
        );

        seatsLabel =
                createInfoLabel(
                        "Available Seats",
                        String.valueOf(
                                seats.size()
                        )
                );

        information.add(
                seatsLabel
        );

        inputModeLabel =
                createInfoLabel(
                        "Input Mode",
                        currentInputMode
                );

        information.add(
                inputModeLabel
        );

        allocationRateLabel =
                createInfoLabel(
                        "Allocation Rate",
                        String.format(
                                "%.2f%%",
                                getAllocationRate()
                        )
                );

        information.add(
                allocationRateLabel
        );

        panel.add(
                information,
                BorderLayout.CENTER
        );

        return panel;
    }

    private JLabel createInfoLabel(
            String title,
            String value) {

        JLabel label =
                new JLabel(
                        createInfoHTML(
                                title,
                                value
                        )
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        label.setForeground(
                TEXT
        );

        return label;
    }

    private String createInfoHTML(
            String title,
            String value) {

        return "<html>"
                + "<b>"
                + title
                + ":</b> "
                + value
                + "</html>";
    }

    // ==========================================
    // ALLOCATION RATE
    // ==========================================

    private double getAllocationRate() {

        if (!allocationCompleted
                || students.isEmpty()) {

            return 0;
        }

        return result.getAllocatedCount()
                * 100.0
                / students.size();
    }

    // ==========================================
    // BUTTON PANEL
    // ==========================================

    private JPanel createButtonPanel() {

        JPanel outerPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                8
                        )
                );

        outerPanel.setBackground(
                BACKGROUND
        );

        JLabel actionLabel =
                new JLabel(
                        "ACTIONS"
                );

        actionLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        actionLabel.setForeground(
                MUTED
        );

        outerPanel.add(
                actionLabel,
                BorderLayout.NORTH
        );

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                2,
                                5,
                                10,
                                10
                        )
                );

        panel.setBackground(
                BACKGROUND
        );

        JButton studentsButton =
                createButton(
                        "View Students",
                        false
                );

        JButton labsButton =
                createButton(
                        "View Labs",
                        false
                );

        JButton allocationButton =
                createButton(
                        "View Allocations",
                        false
                );

        JButton statisticsButton =
                createButton(
                        "Statistics",
                        false
                );

        JButton inputButton =
                createButton(
                        "Input Data",
                        false
                );

        JButton runButton =
                createButton(
                        "Run Allocation",
                        true
                );

        JButton exportButton =
                createButton(
                        "Export Results",
                        false
                );

        JButton resetButton =
                createButton(
                        "Reset to CSV",
                        false
                );

        JButton aboutButton =
                createButton(
                        "About",
                        false
                );

        JButton exitButton =
                createButton(
                        "Exit",
                        false
                );

        panel.add(studentsButton);
        panel.add(labsButton);
        panel.add(allocationButton);
        panel.add(statisticsButton);
        panel.add(inputButton);

        panel.add(runButton);
        panel.add(exportButton);
        panel.add(resetButton);
        panel.add(aboutButton);
        panel.add(exitButton);

        // ==========================================
        // VIEW STUDENTS
        // ==========================================

        studentsButton.addActionListener(e -> {

            DataTableFrame.showStudents(
                    students
            );
        });

        // ==========================================
        // VIEW LABS
        // ==========================================

        labsButton.addActionListener(e -> {

            DataTableFrame.showLabs(
                    labs
            );
        });

        // ==========================================
        // VIEW ALLOCATIONS
        // ==========================================

        allocationButton.addActionListener(e -> {

            if (!allocationCompleted) {

                showWarningDialog(
                        "Allocation Required",
                        "New input data has been loaded.\n\n"
                        + "Run the allocation before "
                        + "viewing the results."
                );

                return;
            }

            DataTableFrame.showAllocations(
                    result
            );
        });

        // ==========================================
        // STATISTICS
        // ==========================================

        statisticsButton.addActionListener(e -> {

            if (!allocationCompleted) {

                showWarningDialog(
                        "Allocation Required",
                        "The current input has not been "
                        + "allocated yet.\n\n"
                        + "Click 'Run Allocation' first."
                );

                return;
            }

            StatisticsFrame frame =
                    new StatisticsFrame(
                            result,
                            labs
                    );

            frame.setVisible(true);
        });

        // ==========================================
        // INPUT DATA
        // ==========================================

        inputButton.addActionListener(e -> {

            InputFrame frame =
                    new InputFrame(
                            this
                    );

            frame.setVisible(true);
        });

        // ==========================================
        // RUN ALLOCATION
        // ==========================================

        runButton.addActionListener(e -> {

            if (students == null
                    || students.isEmpty()) {

                showWarningDialog(
                        "No Students",
                        "There are no students available "
                        + "for allocation.\n\n"
                        + "Please load or enter student data "
                        + "first."
                );

                return;
            }

            setCursor(
                    Cursor.getPredefinedCursor(
                            Cursor.WAIT_CURSOR
                    )
            );

            runButton.setEnabled(false);

            try {

                runMatching();

                updateDashboard();

                showAllocationCompleteDialog();

            } finally {

                runButton.setEnabled(true);

                setCursor(
                        Cursor.getDefaultCursor()
                );
            }
        });

        // ==========================================
        // EXPORT
        // ==========================================

        exportButton.addActionListener(e -> {

            if (!allocationCompleted) {

                showWarningDialog(
                        "Allocation Required",
                        "The current input has not been "
                        + "allocated yet.\n\n"
                        + "Run the allocation before "
                        + "exporting results."
                );

                return;
            }

            String outputFile =
                    "output/allocation_result.csv";

            AllocationCSVWriter.write(
                    result,
                    outputFile
            );

            showExportCompleteDialog(
                    outputFile
            );
        });

        // ==========================================
        // RESET CSV
        // ==========================================

        resetButton.addActionListener(e -> {

            boolean confirmed =
                    showConfirmDialog(
                            "Reset to CSV",
                            "This will replace the current input "
                            + "with the original CSV dataset.\n\n"
                            + "Do you want to continue?",
                            "Reset",
                            WARNING
                    );

            if (confirmed) {

                loadCSVData();

                updateDashboard();

                showSuccessDialog(
                        "Dataset Reset",
                        "The original CSV dataset has "
                        + "been loaded successfully.\n\n"
                        + "Students Loaded: "
                        + students.size()
                );
            }
        });

        // ==========================================
        // ABOUT
        // ==========================================

        aboutButton.addActionListener(e -> {

            showAboutDialog();
        });

        // ==========================================
        // EXIT
        // ==========================================

        exitButton.addActionListener(e -> {

            boolean confirmed =
                    showConfirmDialog(
                            "Exit Application",
                            "Are you sure you want to exit "
                            + "the application?",
                            "Exit",
                            DANGER
                    );

            if (confirmed) {
                System.exit(0);
            }
        });

        outerPanel.add(
                panel,
                BorderLayout.CENTER
        );

        return outerPanel;
    }

    // ==========================================
    // BUTTON STYLE
    // ==========================================

    private JButton createButton(
            String text,
            boolean primaryButton) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(
                primaryButton
                        ? Color.WHITE
                        : TEXT
        );

        button.setBackground(
                primaryButton
                        ? PRIMARY
                        : CARD
        );

        button.setFocusPainted(
                false
        );

        button.setOpaque(true);

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                primaryButton
                                        ? PRIMARY_DARK
                                        : BORDER
                        ),
                        new EmptyBorder(
                                9,
                                8,
                                9,
                                8
                        )
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        // ==========================================
        // HOVER EFFECT
        // ==========================================

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e) {

                        if (button.isEnabled()) {

                            button.setBackground(
                                    primaryButton
                                            ? PRIMARY_DARK
                                            : LIGHT_BLUE
                            );
                        }
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e) {

                        button.setBackground(
                                primaryButton
                                        ? PRIMARY
                                        : CARD
                        );
                    }
                }
        );

        return button;
    }

    // ==========================================
    // ALLOCATION COMPLETE DIALOG
    // ==========================================

    private void showAllocationCompleteDialog() {

        int total =
                students.size();

        int allocated =
                result.getAllocatedCount();

        int unallocated =
                result.getUnallocatedCount();

        double rate = 0;

        if (total > 0) {

            rate =
                    allocated * 100.0 / total;
        }

        JPanel panel =
                createDialogPanel(
                        "ALLOCATION COMPLETED",
                        SUCCESS
                );

        panel.add(
                createDialogMessage(
                        "Student allocation has been "
                        + "completed successfully."
                )
        );

        panel.add(
                Box.createVerticalStrut(12)
        );

        JPanel stats =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                10,
                                8
                        )
                );

        stats.setOpaque(false);

        addDialogStat(
                stats,
                "Total Students",
                String.valueOf(total)
        );

        addDialogStat(
                stats,
                "Allocated",
                String.valueOf(allocated)
        );

        addDialogStat(
                stats,
                "Unallocated",
                String.valueOf(unallocated)
        );

        addDialogStat(
                stats,
                "Allocation Rate",
                String.format(
                        "%.2f%%",
                        rate
                )
        );

        panel.add(
                stats
        );

        showCustomDialog(
                "Allocation Complete",
                panel,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ==========================================
    // EXPORT COMPLETE DIALOG
    // ==========================================

    private void showExportCompleteDialog(
            String outputFile) {

        JPanel panel =
                createDialogPanel(
                        "EXPORT COMPLETED",
                        SUCCESS
                );

        panel.add(
                createDialogMessage(
                        "Allocation results have been "
                        + "exported successfully."
                )
        );

        panel.add(
                Box.createVerticalStrut(12)
        );

        JLabel fileLabel =
                new JLabel(
                        "<html><b>File:</b> "
                        + outputFile
                        + "</html>"
                );

        fileLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        fileLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(
                fileLabel
        );

        showCustomDialog(
                "Export Complete",
                panel,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ==========================================
    // SUCCESS DIALOG
    // ==========================================

    private void showSuccessDialog(
            String title,
            String message) {

        JPanel panel =
                createDialogPanel(
                        title.toUpperCase(),
                        SUCCESS
                );

        panel.add(
                createDialogMessage(
                        message
                )
        );

        showCustomDialog(
                title,
                panel,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ==========================================
    // WARNING DIALOG
    // ==========================================

    private void showWarningDialog(
            String title,
            String message) {

        JPanel panel =
                createDialogPanel(
                        title.toUpperCase(),
                        WARNING
                );

        panel.add(
                createDialogMessage(
                        message
                )
        );

        showCustomDialog(
                title,
                panel,
                JOptionPane.WARNING_MESSAGE
        );
    }

    // ==========================================
    // CUSTOM DIALOG PANEL
    // ==========================================

    private JPanel createDialogPanel(
            String heading,
            Color accent) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(
                CARD
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                5,
                                0,
                                0,
                                0,
                                accent
                        ),
                        new EmptyBorder(
                                18,
                                22,
                                18,
                                22
                        )
                )
        );

        JLabel headingLabel =
                new JLabel(
                        heading,
                        SwingConstants.CENTER
                );

        headingLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        headingLabel.setForeground(
                TEXT
        );

        headingLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(
                headingLabel
        );

        panel.add(
                Box.createVerticalStrut(12)
        );

        return panel;
    }

    private JLabel createDialogMessage(
            String message) {

        String formatted =
                "<html><div style='text-align:center;'>"
                + message.replace(
                        "\n",
                        "<br>"
                )
                + "</div></html>";

        JLabel label =
                new JLabel(
                        formatted,
                        SwingConstants.CENTER
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        label.setForeground(
                TEXT
        );

        label.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return label;
    }

    private void addDialogStat(
            JPanel panel,
            String title,
            String value) {

        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        titleLabel.setForeground(
                MUTED
        );

        JLabel valueLabel =
                new JLabel(
                        value
                );

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        valueLabel.setForeground(
                TEXT
        );

        panel.add(
                titleLabel
        );

        panel.add(
                valueLabel
        );
    }

    // ==========================================
    // CUSTOM CONFIRMATION DIALOG
    // ==========================================

    private boolean showConfirmDialog(
            String title,
            String message,
            String confirmText,
            Color accent) {

        final JDialog dialog =
                new JDialog(
                        this,
                        title,
                        true
                );

        dialog.setDefaultCloseOperation(
                JDialog.DISPOSE_ON_CLOSE
        );

        JPanel outer =
                new JPanel(
                        new BorderLayout()
                );

        outer.setBackground(CARD);

        outer.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );

        JPanel content =
                createDialogPanel(
                        title.toUpperCase(),
                        accent
                );

        content.add(
                createDialogMessage(message)
        );

        content.add(
                Box.createVerticalStrut(18)
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        final boolean[] confirmed =
                {false};

        JButton confirmButton =
                new JButton(confirmText);

        JButton cancelButton =
                new JButton("Cancel");

        styleDialogButton(
                confirmButton,
                accent,
                Color.WHITE
        );

        styleDialogButton(
                cancelButton,
                CARD,
                TEXT
        );

        confirmButton.addActionListener(e -> {
            confirmed[0] = true;
            dialog.dispose();
        });

        cancelButton.addActionListener(e ->
                dialog.dispose()
        );

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        content.add(buttonPanel);

        outer.add(
                content,
                BorderLayout.CENTER
        );

        dialog.setContentPane(outer);
        dialog.pack();

        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this);

        dialog.getRootPane().setDefaultButton(
                confirmButton
        );

        dialog.setVisible(true);

        return confirmed[0];
    }

    private void styleDialogButton(
            JButton button,
            Color background,
            Color foreground) {

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(foreground);
        button.setBackground(background);
        button.setFocusPainted(false);
        button.setOpaque(true);

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                background.equals(CARD)
                                        ? BORDER
                                        : background
                        ),
                        new EmptyBorder(
                                8,
                                18,
                                8,
                                18
                        )
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
    }

    // ==========================================
    // SHOW CUSTOM DIALOG
    // ==========================================

    private void showCustomDialog(
            String title,
            JPanel panel,
            int messageType) {

        final JDialog dialog =
                new JDialog(
                        this,
                        title,
                        true
                );

        dialog.setDefaultCloseOperation(
                JDialog.DISPOSE_ON_CLOSE
        );

        JPanel outer =
                new JPanel(
                        new BorderLayout()
                );

        outer.setBackground(CARD);

        outer.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );

        JButton closeButton =
                new JButton("Close");

        styleDialogButton(
                closeButton,
                CARD,
                TEXT
        );

        closeButton.addActionListener(e ->
                dialog.dispose()
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                0,
                                12
                        )
                );

        buttonPanel.setOpaque(false);
        buttonPanel.add(closeButton);

        outer.add(
                panel,
                BorderLayout.CENTER
        );

        outer.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        dialog.setContentPane(outer);
        dialog.pack();

        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this);

        dialog.getRootPane().setDefaultButton(
                closeButton
        );

        dialog.setVisible(true);
    }

    // ==========================================
    // ABOUT DIALOG
    // ==========================================

    private void showAboutDialog() {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(
                CARD
        );

        panel.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        JLabel title =
                new JLabel(
                        "Automated Student Lab Allocation System"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        title.setForeground(
                PRIMARY
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(
                title
        );

        panel.add(
                Box.createVerticalStrut(12)
        );

        JLabel algorithm =
                new JLabel(
                        "<html><b>Algorithm:</b> "
                        + "Maximum Bipartite Matching</html>"
                );

        algorithm.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        algorithm.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(
                algorithm
        );

        JLabel technique =
                new JLabel(
                        "<html><b>Technique:</b> "
                        + "Augmenting Path</html>"
                );

        technique.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        technique.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(
                Box.createVerticalStrut(8)
        );

        panel.add(
                technique
        );

        panel.add(
                Box.createVerticalStrut(12)
        );

        JLabel description =
                new JLabel(
                        "<html>"
                        + "<div style='text-align:center;'>"
                        + "Automatically assigns students to "
                        + "available laboratory seats based on "
                        + "their preferred time slot while "
                        + "respecting laboratory capacity."
                        + "</div>"
                        + "</html>",
                        SwingConstants.CENTER
                );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        description.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(
                description
        );

        showCustomDialog(
                "About Project",
                panel,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ==========================================
    // UPDATE DASHBOARD
    // ==========================================

    private void updateDashboard() {

        studentCountLabel.setText(
                createStatHTML(
                        "TOTAL STUDENTS",
                        students.size()
                )
        );

        int allocated =
                allocationCompleted
                        ? result.getAllocatedCount()
                        : 0;

        int unallocated =
                allocationCompleted
                        ? result.getUnallocatedCount()
                        : students.size();

        allocatedCountLabel.setText(
                createStatHTML(
                        "ALLOCATED",
                        allocated
                )
        );

        unallocatedCountLabel.setText(
                createStatHTML(
                        "UNALLOCATED",
                        unallocated
                )
        );

        studentsLoadedLabel.setText(
                createInfoHTML(
                        "Students Loaded",
                        String.valueOf(
                                students.size()
                        )
                )
        );

        labsLabel.setText(
                createInfoHTML(
                        "Laboratories",
                        String.valueOf(
                                labs.size()
                        )
                )
        );

        seatsLabel.setText(
                createInfoHTML(
                        "Available Seats",
                        String.valueOf(
                                seats.size()
                        )
                )
        );

        inputModeLabel.setText(
                createInfoHTML(
                        "Input Mode",
                        currentInputMode
                )
        );

        allocationRateLabel.setText(
                createInfoHTML(
                        "Allocation Rate",
                        String.format(
                                "%.2f%%",
                                getAllocationRate()
                        )
                )
        );

        updateStatusLabel();
    }

    // ==========================================
    // SET RANDOM STUDENTS
    // ==========================================

    public void setStudents(
            List<Student> students) {

        this.students =
                students;

        currentInputMode =
                "Random Data";

        allocationCompleted =
                false;

        updateDashboard();
    }

    // ==========================================
    // SET MANUAL STUDENTS
    // ==========================================

    public void setManualStudents(
            List<Student> students) {

        this.students =
                students;

        currentInputMode =
                "Manual Input";

        allocationCompleted =
                false;

        updateDashboard();
    }

    // ==========================================
    // MAIN
    // ==========================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    MainFrame frame =
                            new MainFrame();

                    frame.setVisible(true);
                }
        );
    }
}