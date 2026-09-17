import java.util.ArrayList;
import java.util.List;

/**
 * Requirement 2: Singly linked list used as the primary storage
 * for student records (add, update, delete, search, display).
 */
public class StudentLinkedList {

    private class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    /** Adds a new student to the end of the list. Returns false if the ID already exists. */
    public boolean addStudent(Student student) {
        if (searchStudent(student.getStudentId()) != null) {
            return false; // duplicate ID
        }
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    /** Updates an existing student's details. Returns false if not found. */
    public boolean updateStudent(String studentId, String name, String programme, double marks) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                current.data.setName(name);
                current.data.setProgramme(programme);
                current.data.setMarks(marks);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /** Deletes a student by ID. Returns the removed Student, or null if not found. */
    public Student deleteStudent(String studentId) {
        Node current = head;
        Node previous = null;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.data;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    /** Linear search by student ID. */
    public Student searchStudent(String studentId) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /** Displays all student records in insertion order. */
    public void displayAll() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("---- Student Records (Linked List) ----");
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    /** Returns all students as a List, useful for building the BST/hash table. */
    public List<Student> toList() {
        List<Student> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    public int size() {
        return size;
    }
}
