public class StudentHashTable {
    private class Node {
        Student data;
        Node next;
        Node(Student data) { this.data = data; }
    }

    private static final int CAPACITY = 101;
    private Node[] buckets;

    @SuppressWarnings("unchecked")
    public StudentHashTable() { buckets = new Node[CAPACITY]; }

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
}