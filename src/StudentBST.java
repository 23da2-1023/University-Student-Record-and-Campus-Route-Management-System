public class StudentBST {
    private class TreeNode {
        Student data;
        TreeNode left, right;
        TreeNode(Student data) { this.data = data; }
    }

    private TreeNode root;

    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private TreeNode insertRec(TreeNode node, Student student) {
        if (node == null) return new TreeNode(student);
        int cmp = student.getStudentId().compareToIgnoreCase(node.data.getStudentId());
        if (cmp < 0) node.left = insertRec(node.left, student);
        else if (cmp > 0) node.right = insertRec(node.right, student);
        return node;
    }
}