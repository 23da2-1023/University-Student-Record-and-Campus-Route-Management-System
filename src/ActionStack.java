public class ActionStack {
    private class Node {
        String action;
        Node next;
        Node(String action) { this.action = action; }
    }

    private Node top;
    private int size;

    public void push(String action) {
        Node newNode = new Node(action);
        newNode.next = top;
        top = newNode;
        size++;
    }

public String pop() {
        if (isEmpty()) return null;
        String action = top.action;
        top = top.next;
        size--;
        return action;
    }

    public String peek() { return isEmpty() ? null : top.action; }
    public boolean isEmpty() { return top == null; }
    public int size() { return size; }
}