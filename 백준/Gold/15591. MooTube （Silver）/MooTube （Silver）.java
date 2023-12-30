import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, Q, count;
    static List<ArrayList<int[]>> MooTube; //그래프
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        MooTube = new ArrayList<ArrayList<int[]>>();
        //초기화
        for(int i = 0; i < N; i++) {
            MooTube.add(new ArrayList<int[]>());
        }
        
        //값 넣기
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());     
            //1. 시작점 넣기
            MooTube.get(start - 1).add(new int[] {end - 1, dist});
            
            //2. 끝점 넣기
            MooTube.get(end - 1).add(new int[] {start - 1, dist});
        }
        
        //판별
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int dist = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken()) - 1;
            
            count = 0;
            visited = new boolean[N]; //방문 여부
            dfs(start, dist, Integer.MAX_VALUE); //DFS 시행
            
            bw.write(count + "\n");
        }
        
        bw.flush();
        bw.close();
    }
    
    static void dfs(int node, int dist, int minUSADO) {
        visited[node] = true; //방문 처리

        for(int[] edge : MooTube.get(node)) {
            int nextNode = edge[0];
            int nextDist = edge[1];
            
            if(!visited[nextNode]) {
                int newMinUSADO = Math.min(minUSADO, nextDist); //지금까지 경로의 최저 USADO값
                if(newMinUSADO >= dist) { //그 값이 목표 dist보다 높다면 다시 재귀 시행
                    count++;
                    dfs(nextNode, dist, newMinUSADO);
                }
            }
        }
    }
}
