/**
 * Requirement 6: Hash table (separate chaining) used for
 * fast O(1) average-case student ID search.
 */
public class StudentHashTable {

    private class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
        }
    }

    private static final int CAPACITY = 101; // prime size, reduces collisions
    private Node[] buckets;

    @SuppressWarnings("unchecked")
    public StudentHashTable() {
        buckets = new Node[CAPACITY];
    }

    /** Simple hash function based on the character codes of the Student ID. */
    private int hash(String studentId) {
        int hash = 0;
        for (char c : studentId.toUpperCase().toCharArray()) {
            hash = (hash * 31 + c) % CAPACITY;
        }
        return Math.abs(hash);
    }

    public void insert(Student student) {
        int index = hash(student.getStudentId());
        Node newNode = new Node(student);
        if (buckets[index] == null) {
            buckets[index] = newNode;
        } else {
            Node current = buckets[index];
            while (current.next != null) current = current.next;
            current.next = newNode;
        }
    }

    public boolean remove(String studentId) {
        int index = hash(studentId);
        Node current = buckets[index];
        Node previous = null;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    /** O(1) average-case search by student ID. */
    public Student search(String studentId) {
        int index = hash(studentId);
        Node current = buckets[index];
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
}
