
import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int start, end;
    private static boolean[] visited;
    private static int[] dp; //최단거리
    static List<ArrayList<Edge>> graph = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        input();
        logic(); //다익스트라 시작
        System.out.println(dp[end]);
    }

    static void logic() {
        //저장 값들 초기화
        visited = new boolean[N]; //방문
        dp = new int[N]; //최단거리
        Arrays.fill(dp, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0)); //자기 자신의 거리 집어넣기
        dp[start] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if(visited[now.node]) continue;
            visited[now.node] = true; //방문 처리 (이미 여기서 최단거리 확정됨)

            ArrayList<Edge> edges = graph.get(now.node); //현재 노드에서 연결된 간선들
            for(Edge next : edges) {
                //next까지의 거리 > 현재 노드(최단거리)에서 next까지의 거리
                if(dp[next.node] > dp[now.node] + next.cost) {
                    dp[next.node] = (dp[now.node] + next.cost); //최단거리 갱신
                    pq.add(new Edge(next.node, dp[next.node]));
                }
            }
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) graph.add(new ArrayList<>()); //그래프 초기화

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nowStart = Integer.parseInt(st.nextToken()) - 1;
            int nowEnd = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            graph.get(nowStart).add(new Edge(nowEnd, cost)); //그래프에 간선 추가
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()) - 1;
        end = Integer.parseInt(st.nextToken()) - 1;
    }
}
class Edge implements Comparable<Edge>{
    int node;
    int cost;

    public Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
}