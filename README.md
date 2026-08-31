# KLH-CSE-2520030053-DSA3

# Student Lab Allocation

## Data Structures and Algorithms - 3 (25CS2103E)

### Project Overview

**Student Lab Allocation** is a DSA-based project that assigns students to available computer laboratories and time slots while respecting laboratory capacity and scheduling constraints.

The system organizes student requests, processes them systematically, and generates a clear student-to-lab-to-slot allocation. The project demonstrates how data structures and algorithms can be applied to a practical scheduling problem.

## Problem Statement

Design a system that allocates students to available laboratory slots without exceeding lab capacity and while minimizing scheduling conflicts.

## Objectives

* Store and organize student, laboratory, and time-slot information efficiently.
* Allocate students using a systematic DSA-based strategy.
* Ensure that the number of students assigned to a laboratory does not exceed its capacity.
* Generate an understandable final allocation for students and administrators.

## DSA Concepts Used

### Arrays / Lists

Used to store and organize information related to students, laboratories, and available slots.

### Queue

Students can be processed in an organized order using the FIFO (First In, First Out) principle.

### Sorting

Student or allocation data can be sorted to establish a consistent processing order.

### Greedy Allocation

The system can select the best currently feasible laboratory and time slot for each student.

### Constraint Checking

Before assigning a student, the system checks laboratory capacity and scheduling conflicts.

## System Workflow

1. Start the program.
2. Input student, laboratory, capacity, and time-slot data.
3. Sort or queue the students.
4. Select an available laboratory and time slot.
5. Check laboratory capacity and scheduling conflicts.
6. If the allocation is valid, assign the student.
7. If the laboratory is full or a conflict exists, try the next feasible option.
8. Continue until all students are processed.
9. Display the final allocation.
10. End the program.

## Example Output

| Student | Laboratory | Slot        |
| ------- | ---------- | ----------- |
| S01     | Lab-1      | 09:00–10:00 |
| S02     | Lab-1      | 09:00–10:00 |
| S03     | Lab-2      | 09:00–10:00 |
| S04     | Lab-2      | 10:00–11:00 |
| S05     | Lab-1      | 10:00–11:00 |

The final allocation should respect laboratory capacity and avoid scheduling conflicts.

## Input

The system can take the following information:

* Student details
* Laboratory details
* Laboratory capacity
* Available time slots
* Allocation constraints

## Output

The system produces:

* Student-to-lab assignments
* Assigned time slots
* A final allocation list
* Information about valid or rejected allocation attempts

## Hardware Requirements

* Computer or laptop
* Minimum 4 GB RAM
* Standard keyboard and display

## Software Requirements

* Ubuntu / Linux or Windows
* C or C++
* GCC / G++ compiler
* VS Code, Code::Blocks, or another suitable IDE
* Terminal

## Advantages

* Reduces manual allocation effort.
* Organizes students systematically.
* Prevents laboratory capacity from being exceeded.
* Helps reduce scheduling conflicts.
* Produces a clear and understandable allocation.

## Limitations

The current approach is a relatively simple allocation strategy. With a large number of students and complex constraints, it may not always produce a globally optimal schedule.

## Conclusion

Student Lab Allocation demonstrates a practical application of Data Structures and Algorithms to a scheduling problem. By organizing student requests and checking laboratory capacity and conflicts before assignment, the system can reduce manual effort and produce a clear laboratory schedule.
