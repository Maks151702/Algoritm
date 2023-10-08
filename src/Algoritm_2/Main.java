package Algoritm_2;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode('A');
        root.left = new TreeNode('B');
        root.right = new TreeNode('C');
        root.left.left = new TreeNode('D');
        root.left.right = new TreeNode('E');
        root.right.left = new TreeNode('F');
        root.right.right = new TreeNode('G');

        System.out.println("Обход в глубину:");
        depthFirstTraversal(root);

        System.out.println("\nОбход в ширину:");
        breadthFirstTraversal(root);
    }

    // Метод для обхода дерева в глубину (рекурсивный обход)
    public static void depthFirstTraversal(TreeNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            depthFirstTraversal(node.left);
            depthFirstTraversal(node.right);
        }
    }

    // Метод для обхода дерева в ширину (итеративный обход)
    public static void breadthFirstTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.data + " ");

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}

class TreeNode {
    char data;
    TreeNode left;
    TreeNode right;

    public TreeNode(char data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
