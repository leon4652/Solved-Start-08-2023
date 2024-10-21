import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Node> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new Node(i));

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).edges.add(new Edge(b, c));
            graph.get(b).edges.add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()) - 1;
        int t = Integer.parseInt(st.nextToken()) - 1;
        graph.get(s).dist = 0; //출발 위치

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(graph.get(s));

        while (!pq.isEmpty()) {
            Node nowNode = pq.poll();

            if (nowNode.dist > graph.get(nowNode.no).dist) continue; //최단거리 이미 갱신되었음

            for (Edge edge : nowNode.edges) {
                long nowDist = nowNode.dist + edge.weight;
                Node nextNode = graph.get(edge.dest);
                if(nextNode.dist > nowDist) {
                    nextNode.dist = nowDist;
                    pq.add(nextNode);
                }
            }
        }

        System.out.println(graph.get(t).dist);
    }

}
class Node implements Comparable<Node> {
    long dist = Long.MAX_VALUE;
    int no;
    List<Edge> edges = new ArrayList<>();

    public Node(int no) {
        this.no = no;
    }

    @Override
    public int compareTo(Node n) {
        return Long.compare(this.dist, n.dist);
    }
}

class Edge {
    int dest;
    int weight;

    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}