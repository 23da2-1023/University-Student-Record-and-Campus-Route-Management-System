public class StudentLinkedList {

    private class Node {
        Student data;
        Node next;
        Node(Student data) { this.data = data; }
    }

    private Node head;
    private int size;

    public boolean addStudent(Student student) {
        if (searchStudent(student.getStudentId()) != null) return false;
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) current = current.next;
            current.next = newNode;
        }
        size++;
        return true;
    }

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

    public Student deleteStudent(String studentId) {
        Node current = head;
        Node previous = null;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                if (previous == null) head = current.next;
                else previous.next = current.next;
                size--;
                return current.data;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

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

    public int size() { return size; }
}