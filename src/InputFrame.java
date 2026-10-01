import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class InputFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private MainFrame mainFrame;

    private JTextField countField;

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

    private final Color LIGHT_BLUE =
            new Color(235, 242, 250);

    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public InputFrame(
            MainFrame mainFrame) {

        this.mainFrame =
                mainFrame;

        setTitle(
                "Student Input"
        );

        setSize(
                650,
                500
        );

        setMinimumSize(
                new Dimension(
                        600,
                        460
                )
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(
                mainFrame
        );

        createInterface();
    }

    // ==========================================
    // CREATE INTERFACE
    // ==========================================

    private void createInterface() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        // ==========================================
        // HEADER
        // ==========================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                PRIMARY
        );

        headerPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                PRIMARY_DARK
                        ),
                        new EmptyBorder(
                                15,
                                20,
                                15,
                                20
                        )
                )
        );

        JLabel title =
                new JLabel(
                        "STUDENT INPUT",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        23
                )
        );

        title.setForeground(
                Color.WHITE
        );

        headerPanel.add(
                title,
                BorderLayout.CENTER
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // ==========================================
        // CENTER CONTENT
        // ==========================================

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
        // CURRENT INPUT
        // ==========================================

        JPanel currentPanel =
                createCardPanel(
                        "Current Input"
                );

        JLabel currentText =
                new JLabel(
                        "<html>"
                        + "<b>Student data can be loaded using "
                        + "Random Input or Manual Input.</b><br>"
                        + "After changing the input, click "
                        + "<b>'Run Allocation'</b> on the dashboard "
                        + "to generate new allocations."
                        + "</html>"
                );

        currentText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        currentText.setForeground(
                TEXT
        );

        currentPanel.add(
                currentText,
                BorderLayout.CENTER
        );

        centerPanel.add(
                currentPanel
        );

        centerPanel.add(
                Box.createVerticalStrut(
                        12
                )
        );

        // ==========================================
        // RANDOM INPUT
        // ==========================================

        JPanel randomPanel =
                createCardPanel(
                        "Generate Random Students"
                );

        JPanel randomContent =
                new JPanel(
                        new BorderLayout(
                                12,
                                10
                        )
                );

        randomContent.setOpaque(
                false
        );

        JLabel countLabel =
                new JLabel(
                        "Number of Students:"
                );

        countLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        countLabel.setForeground(
                TEXT
        );

        countField =
                new JTextField(
                        "20"
                );

        countField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        countField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                7,
                                8,
                                7,
                                8
                        )
                )
        );

        JButton randomButton =
                createButton(
                        "Generate Random Students",
                        true
                );

        randomContent.add(
                countLabel,
                BorderLayout.WEST
        );

        randomContent.add(
                countField,
                BorderLayout.CENTER
        );

        randomContent.add(
                randomButton,
                BorderLayout.EAST
        );

        randomPanel.add(
                randomContent,
                BorderLayout.CENTER
        );

        JLabel randomNote =
                new JLabel(
                        "Generated data replaces the current student input."
                );

        randomNote.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        randomNote.setForeground(
                MUTED
        );

        randomPanel.add(
                randomNote,
                BorderLayout.SOUTH
        );

        centerPanel.add(
                randomPanel
        );

        centerPanel.add(
                Box.createVerticalStrut(
                        12
                )
        );

        // ==========================================
        // MANUAL INPUT
        // ==========================================

        JPanel manualPanel =
                createCardPanel(
                        "Manual Input"
                );

        JPanel manualContent =
                new JPanel(
                        new BorderLayout(
                                15,
                                5
                        )
                );

        manualContent.setOpaque(
                false
        );

        JLabel manualText =
                new JLabel(
                        "<html>"
                        + "<b>Add students manually.</b><br>"
                        + "Enter multiple students and review "
                        + "them before running allocation."
                        + "</html>"
                );

        manualText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        manualText.setForeground(
                TEXT
        );

        JButton manualButton =
                createButton(
                        "Enter Students Manually",
                        false
                );

        manualContent.add(
                manualText,
                BorderLayout.CENTER
        );

        manualContent.add(
                manualButton,
                BorderLayout.EAST
        );

        manualPanel.add(
                manualContent,
                BorderLayout.CENTER
        );

        centerPanel.add(
                manualPanel
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // ==========================================
        // ACTION BUTTONS
        // ==========================================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        bottomPanel.setBackground(
                BACKGROUND
        );

        JButton closeButton =
                createButton(
                        "Close",
                        false
                );

        bottomPanel.add(
                closeButton
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // ==========================================
        // RANDOM ACTION
        // ==========================================

        randomButton.addActionListener(
                e -> generateRandomStudents()
        );

        // ==========================================
        // MANUAL ACTION
        // ==========================================

        manualButton.addActionListener(
                e -> {

                    ManualStudentFrame frame =
                            new ManualStudentFrame(
                                    mainFrame
                            );

                    frame.setVisible(
                            true
                    );
                }
        );

        // ==========================================
        // CLOSE ACTION
        // ==========================================

        closeButton.addActionListener(
                e -> dispose()
        );

        // ==========================================
        // ENTER KEY
        // ==========================================

        getRootPane().setDefaultButton(
                randomButton
        );

        setContentPane(
                mainPanel
        );
    }

    // ==========================================
    // CARD PANEL
    // ==========================================

    private JPanel createCardPanel(
            String title) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                12,
                                10
                        )
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
                                title
                        ),
                        new EmptyBorder(
                                12,
                                15,
                                12,
                                15
                        )
                )
        );

        return panel;
    }

    // ==========================================
    // BUTTON STYLE
    // ==========================================

    private JButton createButton(
            String text,
            boolean primaryButton) {

        JButton button =
                new JButton(
                        text
                );

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

        button.setOpaque(
                true
        );

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                primaryButton
                                        ? PRIMARY_DARK
                                        : BORDER
                        ),
                        new EmptyBorder(
                                8,
                                14,
                                8,
                                14
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
    // RANDOM GENERATION
    // ==========================================

    private void generateRandomStudents() {

        try {

            int count =
                    Integer.parseInt(
                            countField
                                    .getText()
                                    .trim()
                    );

            if (count <= 0) {

                showInputError(
                        "Invalid Number",
                        "Enter a number greater than 0."
                );

                return;
            }

            List<Student> generated =
                    RandomStudentGenerator
                            .generateStudents(
                                    count
                            );

            mainFrame.setStudents(
                    generated
            );

            showSuccessMessage(
                    count
                    + " random students generated successfully.\n\n"
                    + "The new input is ready.\n"
                    + "Click 'Run Allocation' on the dashboard "
                    + "to perform Maximum Bipartite Matching."
            );

            dispose();

        } catch (NumberFormatException ex) {

            showInputError(
                    "Invalid Number",
                    "Please enter a valid whole number."
            );
        }
    }

    // ==========================================
    // INPUT ERROR
    // ==========================================

    private void showInputError(
            String title,
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                title,
                JOptionPane.WARNING_MESSAGE
        );
    }

    // ==========================================
    // SUCCESS MESSAGE
    // ==========================================

    private void showSuccessMessage(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Random Data Generated",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}