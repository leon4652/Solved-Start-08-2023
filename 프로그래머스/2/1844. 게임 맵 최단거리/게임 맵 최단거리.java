import java.util.*;

class Solution {
    static int N, M;
    static int[] DR = {1, 0, -1, 0}; //DRUL
    static int[] DC = {0, 1, 0, -1};
    public int solution(int[][] maps) {
        int answer = Integer.MAX_VALUE;
        N = maps.length;
        M = maps[0].length;
        boolean[][] visit = new boolean[N][M];
        
        Queue<int[]> bfs = new ArrayDeque<>();
        bfs.add(new int[] {0, 0, 0});
        while(!bfs.isEmpty()) {
            int[] now = bfs.poll();
            int r = now[0];
            int c = now[1];
            int dist = now[2];
            if(cantGo(r, c) || visit[r][c] || (maps[r][c] == 0)) continue;
            visit[r][c] = true;
            if(r == N - 1 && c == M - 1) {
                answer = Math.min(answer, dist);
                continue;
            }
            
            for(int i = 0; i < 4; i++) {
                int nr = r + DR[i];
                int nc = c + DC[i];
                if(cantGo(nr, nc) || visit[nr][nc] || maps[nr][nc] == 0) continue;
                bfs.add(new int[] {nr, nc, dist + 1});
            }
        }
        
        if(answer == Integer.MAX_VALUE) answer = -2;
        return answer + 1;
    }
    
    static boolean cantGo(int r, int c) {
        if(r < 0 || c < 0 || r >= N || c >= M) return true;
        return false;
    }
}