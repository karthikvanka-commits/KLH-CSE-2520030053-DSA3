import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsFrame extends JFrame {

    public StatisticsFrame(
            AllocationResult result,
            List<Lab> labs) {

        setTitle("Allocation Statistics");

        setSize(900, 650);

        setMinimumSize(
                new Dimension(800, 600)
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createInterface(result, labs);
    }

    private void createInterface(
            AllocationResult result,
            List<Lab> labs) {

        JPanel mainPanel =
                new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 25, 25, 25
                )
        );

        // ==========================================
        // TITLE
        // ==========================================

        JLabel title =
                new JLabel(
                        "ALLOCATION STATISTICS",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 5, 15, 5
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        // ==========================================
        // OVERALL STATISTICS
        // ==========================================

        int totalStudents =
                result.getAllocatedCount()
                + result.getUnallocatedCount();

        double allocationRate = 0;

        if (totalStudents > 0) {

            allocationRate =
                    result.getAllocatedCount()
                    * 100.0
                    / totalStudents;
        }

        JPanel overallPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                12,
                                12
                        )
                );

        overallPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Overall Performance"
                )
        );

        overallPanel.add(
                createValueLabel(
                        "Total Students",
                        String.valueOf(
                                totalStudents
                        )
                )
        );

        overallPanel.add(
                createValueLabel(
                        "Allocated",
                        String.valueOf(
                                result.getAllocatedCount()
                        )
                )
        );

        overallPanel.add(
                createValueLabel(
                        "Unallocated",
                        String.valueOf(
                                result.getUnallocatedCount()
                        )
                )
        );

        overallPanel.add(
                createValueLabel(
                        "Allocation Rate",
                        String.format(
                                "%.2f%%",
                                allocationRate
                        )
                )
        );

        // ==========================================
        // CALCULATE LAB COUNTS
        // ==========================================

        Map<String, Integer> labCounts =
                new HashMap<>();

        for (Allocation allocation :
                result.getAllocations()) {

            String labId =
                    allocation.getLabId();

            labCounts.put(
                    labId,
                    labCounts.getOrDefault(
                            labId,
                            0
                    ) + 1
            );
        }

        // ==========================================
        // CALCULATE SLOT COUNTS
        // ==========================================

        Map<String, Integer> slotCounts =
                new HashMap<>();

        for (Allocation allocation :
                result.getAllocations()) {

            String slot =
                    allocation.getSlot();

            slotCounts.put(
                    slot,
                    slotCounts.getOrDefault(
                            slot,
                            0
                    ) + 1
            );
        }

        // ==========================================
        // LAB STATISTICS
        // ==========================================

        JPanel labPanel =
                new JPanel(
                        new GridLayout(
                                0,
                                1,
                                8,
                                8
                        )
                );

        labPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Laboratory Distribution"
                )
        );

        for (Lab lab : labs) {

            int count =
                    labCounts.getOrDefault(
                            lab.getLabId(),
                            0
                    );

            int totalCapacity =
                    lab.getCapacity() * 3;

            double utilization = 0;

            if (totalCapacity > 0) {

                utilization =
                        count * 100.0
                        / totalCapacity;
            }

            JLabel label =
                    new JLabel(
                            "<html>"
                            + "<b>"
                            + lab.getLabId()
                            + " - "
                            + lab.getLabName()
                            + "</b>"
                            + " &nbsp;&nbsp; "
                            + count
                            + " students"
                            + " &nbsp;&nbsp; "
                            + "(Capacity: "
                            + lab.getCapacity()
                            + " per slot)"
                            + " &nbsp;&nbsp; "
                            + String.format(
                                    "%.1f%% utilization",
                                    utilization
                            )
                            + "</html>"
                    );

            label.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            14
                    )
            );

            label.setBorder(
                    BorderFactory.createLineBorder(
                            Color.LIGHT_GRAY
                    )
            );

            label.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    Color.LIGHT_GRAY
                            ),
                            BorderFactory.createEmptyBorder(
                                    8, 10, 8, 10
                            )
                    )
            );

            labPanel.add(label);
        }

        // ==========================================
        // SLOT STATISTICS
        // ==========================================

        JPanel slotPanel =
                new JPanel(
                        new GridLayout(
                                0,
                                1,
                                8,
                                8
                        )
                );

        slotPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Slot Distribution"
                )
        );

        String[] slots = {
                "09:00-10:00",
                "10:00-11:00",
                "11:00-12:00"
        };

        for (String slot : slots) {

            int count =
                    slotCounts.getOrDefault(
                            slot,
                            0
                    );

            JLabel label =
                    new JLabel(
                            "<html>"
                            + "<b>"
                            + slot
                            + "</b>"
                            + " &nbsp;&nbsp; "
                            + count
                            + " students allocated"
                            + "</html>"
                    );

            label.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            14
                    )
            );

            label.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    Color.LIGHT_GRAY
                            ),
                            BorderFactory.createEmptyBorder(
                                    8, 10, 8, 10
                            )
                    )
            );

            slotPanel.add(label);
        }

        // ==========================================
        // CENTER SECTION
        // ==========================================

        JPanel centerPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                15,
                                15
                        )
                );

        centerPanel.add(labPanel);
        centerPanel.add(slotPanel);

        // ==========================================
        // ADD COMPONENTS
        // ==========================================

        JPanel contentPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        contentPanel.add(
                overallPanel,
                BorderLayout.NORTH
        );

        contentPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        setContentPane(mainPanel);
    }

    // ==========================================
    // VALUE LABEL
    // ==========================================

    private JLabel createValueLabel(
            String title,
            String value) {

        JLabel label =
                new JLabel(
                        "<html>"
                        + "<center>"
                        + "<b>"
                        + title
                        + "</b>"
                        + "<br><br>"
                        + "<font size='6'>"
                        + value
                        + "</font>"
                        + "</center>"
                        + "</html>",
                        SwingConstants.CENTER
                );

        label.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.LIGHT_GRAY
                        ),
                        BorderFactory.createEmptyBorder(
                                10, 5, 10, 5
                        )
                )
        );

        return label;
    }
}