/**
 * Requirement 3: Custom linked-list-based stack (LIFO) used to
 * track recent actions / deleted records for an undo-style history.
 */
public class ActionStack {

    private class Node {
        String action;
        Node next;

        Node(String action) {
            this.action = action;
        }
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
        if (isEmpty()) {
            return null;
        }
        String action = top.action;
        top = top.next;
        size--;
        return action;
    }

    public String peek() {
        return isEmpty() ? null : top.action;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    /** Displays the most recent actions, newest first. */
    public void displayRecent() {
        if (isEmpty()) {
            System.out.println("No recent actions recorded.");
            return;
        }
        System.out.println("---- Recent Actions (Stack, newest first) ----");
        Node current = top;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.action);
            current = current.next;
            count++;
        }
    }
}
