/**
 * Requirement 5: Binary Search Tree, ordered by Student ID,
 * used to organize and display student records in sorted order.
 */
public class StudentBST {

    private class TreeNode {
        Student data;
        TreeNode left, right;

        TreeNode(Student data) {
            this.data = data;
        }
    }

    private TreeNode root;

    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private TreeNode insertRec(TreeNode node, Student student) {
        if (node == null) {
            return new TreeNode(student);
        }
        int cmp = student.getStudentId().compareToIgnoreCase(node.data.getStudentId());
        if (cmp < 0) {
            node.left = insertRec(node.left, student);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, student);
        }
        // cmp == 0: duplicate ID, ignore (linked list already prevents duplicates)
        return node;
    }

    public Student search(String studentId) {
        TreeNode current = root;
        while (current != null) {
            int cmp = studentId.compareToIgnoreCase(current.data.getStudentId());
            if (cmp == 0) {
                return current.data;
            }
            current = (cmp < 0) ? current.left : current.right;
        }
        return null;
    }

    public boolean delete(String studentId) {
        int sizeBefore = countNodes(root);
        root = deleteRec(root, studentId);
        return countNodes(root) < sizeBefore;
    }

    private TreeNode deleteRec(TreeNode node, String studentId) {
        if (node == null) return null;
        int cmp = studentId.compareToIgnoreCase(node.data.getStudentId());
        if (cmp < 0) {
            node.left = deleteRec(node.left, studentId);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, studentId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            TreeNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.data.getStudentId());
        }
        return node;
    }

    private int countNodes(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    /** In-order traversal gives students sorted by Student ID. */
    public void displayInOrder() {
        if (root == null) {
            System.out.println("BST is empty.");
            return;
        }
        System.out.println("---- Students Sorted by ID (BST In-order) ----");
        inOrderRec(root);
    }

    private void inOrderRec(TreeNode node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.println(node.data);
        inOrderRec(node.right);
    }
}
