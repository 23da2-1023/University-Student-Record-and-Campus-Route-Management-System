public class ServiceQueue {
    private class Node {
        String request;
        Node next;
        Node(String request) { this.request = request; }
    }

    private Node front, rear;
    private int size;

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
}