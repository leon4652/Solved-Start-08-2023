import java.util.*;
import java.io.*;

class Main {
    static int N, M, V;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static boolean[] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken()) - 1;
        
        visited = new boolean[N];
        setting(); // 그래프 넣기


        dfs(V); // DFS 결과
        Arrays.fill(visited, false); // 방문 기록 초기화
        bw.newLine();
        bfs(); // BFS 결과
        bw.flush();
        bw.close();
    }

    public static void dfs(int v) throws IOException {
        if (visited[v]) return;
        visited[v] = true;
        bw.write((v + 1) + " ");
        for (int i : list.get(v)) {
            if (!visited[i]) dfs(i);
        }
    }

    public static void bfs() throws IOException {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(V);
        visited[V] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            bw.write((v + 1) + " ");
            for (int i : list.get(v)) {
                if (!visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static void setting() throws IOException {
        for (int i = 0; i < N; i++) { // N개의 노드에 대한 리스트 초기화
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()); // 여기서 st 변수를 선언
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            list.get(start).add(end);
            list.get(end).add(start);
        }
        
        for (int i = 0; i < N; i++) {
            Collections.sort(list.get(i)); // 각 리스트 정렬
        }
    }
}
