import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * University Student Record and Campus Route Management System
 * CIT300 - Graded Practical Assignment 1
 *
 * Menu-driven console application demonstrating:
 * linked list, stack, queue, BST, hashing, and graph (BFS/DFS).
 *
 * TODO (team): replace this skeleton's integration logic with your
 * own tested implementations where needed, and keep committing
 * progressively to GitHub as you go — don't wait for one final commit.
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static StudentLinkedList studentList = new StudentLinkedList();
    private static ActionStack actionStack = new ActionStack();
    private static ServiceQueue serviceQueue = new ServiceQueue();
    private static StudentBST studentBST = new StudentBST();
    private static StudentHashTable studentHashTable = new StudentHashTable();
    private static CampusGraph campusGraph = new CampusGraph();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: addStudentRecord(); break;
                case 2: updateStudentRecord(); break;
                case 3: deleteStudentRecord(); break;
                case 4: studentList.displayAll(); break;
                case 5: addServiceRequest(); break;
                case 6: processNextServiceRequest(); break;
                case 7: actionStack.displayRecent(); break;
                case 8: studentBST.displayInOrder(); break;
                case 9: searchStudentByHashing(); break;
                case 10: addCampusLocation(); break;
                case 11: removeCampusLocation(); break;
                case 12: addCampusConnection(); break;
                case 13: removeCampusConnection(); break;
                case 14: campusGraph.displayNetwork(); break;
                case 15: traverseCampus(); break;
                case 16:
                    running = false;
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-16.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("========================================");
        System.out.println(" University Student Record & Campus Route Management System");
        System.out.println("========================================");
        System.out.println(" 1. Add Student Record");
        System.out.println(" 2. Update Student Record");
        System.out.println(" 3. Delete Student Record");
        System.out.println(" 4. Display All Records (Linked List)");
        System.out.println(" 5. Add Service Request to Queue");
        System.out.println(" 6. Process Next Service Request");
        System.out.println(" 7. Display Recent Actions (Stack)");
        System.out.println(" 8. Display Students (BST, sorted by ID)");
        System.out.println(" 9. Search Student (Hashing)");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations (BFS/DFS)");
        System.out.println("16. Exit");
    }

    // ---------- Student record operations ----------

    private static void addStudentRecord() {
        String id = readNonEmpty("Enter Student ID: ");
        String name = readNonEmpty("Enter Name: ");
        String programme = readNonEmpty("Enter Programme: ");
        double marks = readMarks("Enter Marks (0-100): ");

        Student student = new Student(id, name, programme, marks);
        boolean added = studentList.addStudent(student);
        if (!added) {
            System.out.println("Error: a student with ID " + id + " already exists.");
            return;
        }
        studentBST.insert(student);
        studentHashTable.insert(student);
        actionStack.push("Added student " + id + " (" + name + ")");
        System.out.println("Student added successfully.");
    }

    private static void updateStudentRecord() {
        String id = readNonEmpty("Enter Student ID to update: ");
        if (studentList.searchStudent(id) == null) {
            System.out.println("Error: no student found with ID " + id);
            return;
        }
        String name = readNonEmpty("Enter new Name: ");
        String programme = readNonEmpty("Enter new Programme: ");
        double marks = readMarks("Enter new Marks (0-100): ");

        studentList.updateStudent(id, name, programme, marks);
        // keep BST/hash table in sync with the updated record
        Student updated = studentList.searchStudent(id);
        studentHashTable.remove(id);
        studentHashTable.insert(updated);
        actionStack.push("Updated student " + id);
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudentRecord() {
        String id = readNonEmpty("Enter Student ID to delete: ");
        Student removed = studentList.deleteStudent(id);
        if (removed == null) {
            System.out.println("Error: no student found with ID " + id);
            return;
        }
        studentBST.delete(id);
        studentHashTable.remove(id);
        actionStack.push("Deleted student " + id + " (" + removed.getName() + ")");
        System.out.println("Student deleted successfully.");
    }

    private static void searchStudentByHashing() {
        String id = readNonEmpty("Enter Student ID to search: ");
        Student found = studentHashTable.search(id);
        if (found == null) {
            System.out.println("No student found with ID " + id);
        } else {
            System.out.println("Found: " + found);
        }
    }

    // ---------- Service request (queue) operations ----------

    private static void addServiceRequest() {
        String request = readNonEmpty("Enter service request description (e.g. Student ID + reason): ");
        serviceQueue.enqueue(request);
        actionStack.push("Queued service request: " + request);
        System.out.println("Service request added to queue.");
    }

    private static void processNextServiceRequest() {
        String next = serviceQueue.dequeue();
        if (next == null) {
            System.out.println("No pending service requests.");
            return;
        }
        actionStack.push("Processed service request: " + next);
        System.out.println("Processing: " + next);
    }

    // ---------- Campus graph operations ----------

    private static void addCampusLocation() {
        String location = readNonEmpty("Enter new campus location name: ");
        boolean added = campusGraph.addLocation(location);
        System.out.println(added ? "Location added." : "Error: location already exists.");
    }

    private static void removeCampusLocation() {
        String location = readNonEmpty("Enter campus location to remove: ");
        boolean removed = campusGraph.removeLocation(location);
        System.out.println(removed ? "Location removed." : "Error: location not found.");
    }

    private static void addCampusConnection() {
        String from = readNonEmpty("Enter first location: ");
        String to = readNonEmpty("Enter second location: ");
        boolean added = campusGraph.addConnection(from, to);
        System.out.println(added ? "Connection added." : "Error: check both locations exist and aren't already connected.");
    }

    private static void removeCampusConnection() {
        String from = readNonEmpty("Enter first location: ");
        String to = readNonEmpty("Enter second location: ");
        boolean removed = campusGraph.removeConnection(from, to);
        System.out.println(removed ? "Connection removed." : "Error: connection not found.");
    }

    private static void traverseCampus() {
        String start = readNonEmpty("Enter starting location: ");
        if (!campusGraph.hasLocation(start)) {
            System.out.println("Error: location not found.");
            return;
        }
        System.out.print("Choose traversal type (B = BFS, D = DFS): ");
        String choice = scanner.nextLine().trim().toUpperCase();
        if (choice.equals("B")) {
            campusGraph.bfs(start);
        } else if (choice.equals("D")) {
            campusGraph.dfs(start);
        } else {
            System.out.println("Invalid traversal type.");
        }
    }

    // ---------- Input helper methods (validation) ----------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readMarks(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value < 0 || value > 100) {
                    System.out.println("Invalid marks. Must be between 0 and 100.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This field cannot be empty. Please try again.");
        }
    }
}
