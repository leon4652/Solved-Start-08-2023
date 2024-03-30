import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

class Main {
    static List<ArrayList<Edge>> graph;
    static int N, M;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        int dp[] = new int[N]; //각 노드 도착 최소거리
        boolean visit[] = new boolean[N]; //방문 여부
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        PriorityQueue<Edge> pq = new PriorityQueue<>(); //해당 PQ는 Edge Comparable로 인해 항상 최단 경로를 우선해서 꺼냄

        //start : 0 -> end : N;
        dp[0] = 0; //자기자신 거리 0
        pq.add(new Edge(0, 0)); //시작 노드 및 비용

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();  //간선 꺼내옴
            int node = edge.node;   //꺼낸 노드
            if(visit[node]) continue;
            visit[node] = true; //방문 처리 (이미 여기서 최단거리 확정됨)

            ArrayList<Edge> arrayList = graph.get(node); //다음 노드에서 갈 수 있는 간선(다음 목적지)
            for(Edge nextEdge : arrayList) { //간선 순회 (현재 node에서 갈 수 있는 다음 경로)
                //현재 노드(node) -> 다음 노드 비교
                //dp[nextEdge.node] : 다음 node(nextNode)까지 가는데 든 최소 비용 (dp)
                //dp[node] + nextEdge.cost : node까지 오는 최소 비용 + 여기서 다음 node(nextNode)까지 걸리는 비용
                if(dp[nextEdge.node] > dp[node] + nextEdge.cost) {
                    dp[nextEdge.node] = dp[node] + nextEdge.cost; //최소 비용 갱신
                    //경로의 최소 비용이 갱신될 때마다 해당 경로를 큐에 넣어 다음 탐색 대상으로 삼는다.
                    pq.add(new Edge(nextEdge.node, dp[nextEdge.node])); //원래 nextEdge가 아니라 변경된 값으로 큐에 넣는다.
                }
            }
        }

        System.out.println(dp[N - 1]); //N까지의 최단 경로 출력
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i = 0; i < N; i++) graph.add(new ArrayList<>()); //그래프 초기화
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Edge(end, cost));
            graph.get(end).add(new Edge(start, cost)); //양방향 이동 가능함
        }
    }

}

/**
 * Edge : 간선
 * node : 이 Edge를 통해 다음 노드(중간 경유지)의 인덱스를 의미
 * cost : node를 가는 길에 발생하는 비용
 */
class Edge implements Comparable<Edge> {
    int node;
    int cost;

    public Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.cost - edge.cost;
    }
}