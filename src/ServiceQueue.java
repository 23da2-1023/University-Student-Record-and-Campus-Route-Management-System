/**
 * Requirement 4: Custom linked-list-based queue (FIFO) used to
 * manage student service requests in order of arrival.
 */
public class ServiceQueue {

    private class Node {
        String request;
        Node next;

        Node(String request) {
            this.request = request;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    /** Adds a new service request to the back of the queue. */
    public void enqueue(String request) {
        Node newNode = new Node(request);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    /** Removes and returns the next request to be processed (FIFO). */
    public String dequeue() {
        if (isEmpty()) {
            return null;
        }
        String request = front.request;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return request;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    /** Displays all pending requests without removing them. */
    public void displayPending() {
        if (isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }
        System.out.println("---- Pending Service Requests (Queue, arrival order) ----");
        Node current = front;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.request);
            current = current.next;
            count++;
        }
    }
}
