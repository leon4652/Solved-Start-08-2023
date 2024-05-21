import java.util.*;
import java.io.*;

class Main {
    static int M, N;
    static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) {
        input();
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(dfs(0, 0));
    }

    static int dfs(int r, int c) {
        // 도착 지점에 도달하면 경로 수 1 반환
        if (r == M - 1 && c == N - 1) {
            return 1;
        }

        // 이미 계산된 경로 수가 있으면 그 값을 반환
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        dp[r][c] = 0;

        // 상하좌우 탐색
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 유효한 인접 지점인지 확인하고, 현재 지점보다 낮은 지점으로만 이동
            if (nr >= 0 && nc >= 0 && nr < M && nc < N && map[r][c] > map[nr][nc]) {
                dp[r][c] += dfs(nr, nc);
            }
        }

        return dp[r][c];
    }

    private static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            map = new int[M][N];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
