import java.util.Arrays;
import java.util.List;

public class MaximumBipartiteMatching {

    private SeatBipartiteGraph graph;

    private int[] studentMatch;
    private int[] seatMatch;

    private int matchingCount;

    public MaximumBipartiteMatching(
            SeatBipartiteGraph graph) {

        this.graph = graph;

        int studentCount =
                graph.getStudents().size();

        int seatCount =
                graph.getSeats().size();

        studentMatch =
                new int[studentCount];

        seatMatch =
                new int[seatCount];

        Arrays.fill(
                studentMatch,
                -1
        );

        Arrays.fill(
                seatMatch,
                -1
        );

        matchingCount = 0;
    }

    // ==========================================
    // FIND AUGMENTING PATH
    // ==========================================

    public boolean findAugmentingPath(
            int studentIndex,
            boolean[] visited) {

        List<Integer> connections =
                graph.getConnections(
                        studentIndex
                );

        if (connections.isEmpty()) {
            return false;
        }

        /*
         * Instead of always starting from the first
         * seat (which usually belongs to LAB01),
         * start at a different position for each
         * student.
         *
         * This keeps the maximum matching algorithm
         * unchanged while improving lab distribution.
         */

        int startPosition =
                studentIndex
                        % connections.size();

        for (int offset = 0;
             offset < connections.size();
             offset++) {

            int position =
                    (startPosition + offset)
                            % connections.size();

            int seatIndex =
                    connections.get(position);

            if (visited[seatIndex]) {
                continue;
            }

            visited[seatIndex] = true;

            /*
             * If the seat is free, assign it.
             *
             * Otherwise, try to move the student
             * currently occupying that seat through
             * an augmenting path.
             */

            if (seatMatch[seatIndex] == -1
                    || findAugmentingPath(
                            seatMatch[seatIndex],
                            visited
                    )) {

                studentMatch[studentIndex] =
                        seatIndex;

                seatMatch[seatIndex] =
                        studentIndex;

                return true;
            }
        }

        return false;
    }

    // ==========================================
    // MAXIMUM BIPARTITE MATCHING
    // ==========================================

    public int maximumMatching() {

        matchingCount = 0;

        /*
         * Process every student.
         *
         * Each student gets a fresh visited array
         * because every student starts a new
         * augmenting-path search.
         */

        for (int studentIndex = 0;
             studentIndex < studentMatch.length;
             studentIndex++) {

            boolean[] visited =
                    new boolean[
                            seatMatch.length
                    ];

            if (findAugmentingPath(
                    studentIndex,
                    visited
            )) {

                matchingCount++;
            }
        }

        return matchingCount;
    }

    // ==========================================
    // GET STUDENT MATCH
    // ==========================================

    public int[] getStudentMatch() {

        return studentMatch;
    }

    // ==========================================
    // GET SEAT MATCH
    // ==========================================

    public int[] getSeatMatch() {

        return seatMatch;
    }

    // ==========================================
    // GET MATCHING COUNT
    // ==========================================

    public int getMatchingCount() {

        return matchingCount;
    }
}