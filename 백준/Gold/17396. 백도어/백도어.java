import java.io.*;
import java.util.*;
class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Node> graph = new ArrayList<>(); //그래프

        // 그래프 초기화
        for (int i = 0; i < n; i++) {
            graph.add(new Node(i));
        }

        // 각 분기점이 적의 시야에 보이는지 여부 체크
        st = new StringTokenizer(br.readLine());
        boolean[] visible = new boolean[n];
        for (int i = 0; i < n; i++) {
            int visibility = Integer.parseInt(st.nextToken());
            visible[i] = (visibility == 1);  // 적의 시야에 보이는지 여부 저장
        }

        graph.get(0).dist = 0; //최초 이동 거리는 0임

        //M개의 분기점
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 적의 시야에 보이는 분기점들로 가는 경로 간선 예외 처리
            if ((visible[start] && start != n - 1) || (visible[end] && end != n - 1)) {
                continue; // 시야에 걸리는 노드로의 간선을 제외
            }

            graph.get(start).edges.add(new Edge(end, weight));
            graph.get(end).edges.add(new Edge(start, weight));

        }

        //PQ 생성
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(graph.get(0)); //최초 노드 삽입
        boolean[] visit = new boolean[n];
        
        while (!pq.isEmpty()) {
            Node now = pq.poll(); //현재 노드
            if (visit[now.idx]) continue;
            visit[now.idx] = true;
                
            if (now.dist > graph.get(now.idx).dist) continue; //큐에 있을 동안 지금 꺼낸 노드보다 낮은 값으로 갱신되었을 수도 있음

            for (int i = 0; i < now.edges.size(); i++) {
                Edge edge = now.edges.get(i); //간선
                Node nextNode = graph.get(edge.dest);

                //nextNode로 가는 dist는 현재 Node의 dist + edge의 weight이다
                long newDist = now.dist + edge.weight;
                if(nextNode.dist > newDist) {
                    nextNode.dist = newDist;
                    pq.add(nextNode);
                }
            }
        }

        long res = graph.get(n - 1).dist;
        if (res == Long.MAX_VALUE) System.out.println(-1);
        else System.out.println(res);
    }


}
class Node implements Comparable<Node> {
    long dist = Long.MAX_VALUE;
    int idx;
    List<Edge> edges = new ArrayList<>();
//    boolean canGo = false;
    public Node(int idx) {
        this.idx = idx;
    }

    @Override
    public int compareTo(Node n) {
        return Long.compare(this.dist, n.dist);
    }
}

class Edge {
    int dest; //도착지 idx
    int weight;

    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}