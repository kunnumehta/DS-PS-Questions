import java.util.ArrayDeque;
import java.util.Queue;

public class TreeSumOFDepths {

    public class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
    public class Pair {
        Node node;
        int depth;

        Pair(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public TreeSumOFDepths() {

    }

    public Node initialize() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);
        return root;
    }
    public static void main(String[] args) {
        TreeSumOFDepths treeSumOFDepths = new TreeSumOFDepths();
        Node root = treeSumOFDepths.initialize();
        System.out.println(treeSumOFDepths.sumOfDepthsRootedAtEachNode(root));
    }

    public int sumOfDepthsRootedAtEachNode(Node root) {
        if(root == null) return 0;
        return bfs(root) + sumOfDepthsRootedAtEachNode((root.left)) + sumOfDepthsRootedAtEachNode(root.right);
    }


    public int bfs(Node root) {
        int sum = 0;
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(root, 0));
        while(!queue.isEmpty() ) {
            Pair front  = queue.poll();
            Node top = front.node;
            int depth = front.depth;
            sum+=depth;
            if(top.left !=null)
            queue.add(new Pair(top.left, depth +1));
            if(top.right !=null)
            queue.add(new Pair(top.right, depth +1));
        }
        return sum;
    }
}
