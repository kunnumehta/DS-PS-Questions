import java.util.*;

public class PriorityGraph {
    public static class Node {
        public Integer index;
        public Integer priority;
        public Node parent;
        Map<Node, Integer> adjList;

        public Node(int index, int priority) {
            this.index = index;
            this.priority = priority;
            adjList = new HashMap<>();
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Integer getIndex() {
            return index;
        }

        public Integer getPriority() {
            return priority;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public void addInList(Node node, int distance) {
            adjList.put(node, distance);
        }

        public int getDistance(Node key) {
            Integer d = adjList.get(key);
            return d;
        }

        public Map<Node, Integer> getAdjList() {
            return adjList;
        }

        public void setAdjList(Map<Node, Integer> adjList) {
            this.adjList = adjList;
        }
    }


    public static List<Node> createNodes(int N) {
        List<Node> nodesList = new ArrayList<>();
        int range = N;
        for (int i = 0; i<=N; i++) {
            if (i == 0) {
                Node node = new Node(i, Integer.MIN_VALUE);
                node.setParent(null);
                nodesList.add(node);
            } else {
                int rand = (int) (Math.random() * range) + 1;
                Node node = new Node(i, rand);
                nodesList.add(node);
            }
        }
        return nodesList;
    }

    public static void addEdge(int src, int dest, int distance, List<Node> nodes) {
        nodes.get(src).addInList(nodes.get(dest), distance);
        nodes.get(dest).addInList(nodes.get(src), distance);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        boolean visited[] = new boolean[N+1];
        Arrays.fill(visited, false);
        List<Node> nodes = createNodes(N);
        while(scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] nums = input.split(" ");
            addEdge(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), Integer.parseInt(nums[2]), nodes);
        }
//        zero.addInList(one, 5);
//        zero.addInList(two,4);
//        one.addInList(zero, 5);
//        one.addInList(six, 2);
//        one.addInList(five, 1);
//        two.addInList(zero, 4);
//        two.addInList(six, 3);
//        three.addInList(six, 1);
//        three.addInList(five, 7);
//        four.addInList(five, 2);
//        five.addInList(one, 1);
//        five.addInList(six, 6);
//        five.addInList(three, 7);
//        five.addInList(four, 2);
//        six.addInList(two, 3);
//        six.addInList(one, 2);
//        six.addInList(five, 6);
//        six.addInList(three, 1);
//        zero.setParent(null);
        int score = dfs(nodes.get(0), nodes.get(0).getParent(), visited, 1, N+1, 0);
        System.out.println(score);
    }

    public static int dfs(Node start, Node parent, boolean[] visited, int count, int total, int score) {
        if (count == total) return score;
        if (!visited[start.index]) visited[start.index] = true;
        List<Node> listOfKeys = new ArrayList(start.getAdjList().keySet());
        listOfKeys.sort(Comparator.comparing(Node::getPriority));
        int minD = Integer.MAX_VALUE;
        int minP = Integer.MAX_VALUE;
        Node nextNode = null;
        for(Node node : listOfKeys) {
            if (!visited[node.index]) {
                if (start.getDistance((node)) < minD && node.getPriority() <= minP) {
                    nextNode = node;
                    minD = start.getDistance(node);
                    minP = node.getPriority();
                }
            } else {
                continue;
            }
        }
        if (nextNode != null && !visited[nextNode.index]) {
            count++;
            nextNode.setParent(start);
        }
        if (nextNode == null) {
            nextNode = parent;
            minD = start.getDistance(nextNode);
        }
        score+= minD;
        return dfs(nextNode, nextNode.parent, visited, count, total, score);
    }
}
