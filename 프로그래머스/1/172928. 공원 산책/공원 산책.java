import java.util.*;
class Solution {
    static int sr, sc;
    static int[] DR = {0, 0, 1, -1}; //동서남북
    static int[] DC = {1, -1, 0, 0};
    public int[] solution(String[] park, String[] routes) {
        int C = park[0].length();
        int R = park.length;
        boolean[][] map = new boolean[R][C];
        for(int i = 0; i < R; i++) {
            String now = park[i];
            for(int j = 0; j < C; j++) {
                if(now.charAt(j) == 'S') {
                    sr = i;
                    sc = j;
                } else if(now.charAt(j) == 'X') {
                    map[i][j] = true; //갈 수 없음
                }
            }
        }
        
        for(int i = 0; i < routes.length; i++) {
            String[] route = routes[i].split(" ");
            int d = getDist(route[0]);
            int s = Integer.parseInt(route[1]);
            
            boolean canGo = true;
            for(int j = 1; j <= s; j++) {
                int nr = sr + (DR[d] * j);
                int nc = sc + (DC[d] * j);
                if(nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc]) {
                    canGo = false;
                    break;
                }
            }
            

            if(canGo) {
                sr += (DR[d] * s);
                sc += (DC[d] * s);
            }
        }
        
        return new int[] {sr, sc};
    }
    
    int getDist(String s) {
        if(s.equals("E")) return 0;
        if(s.equals("W")) return 1;
        if(s.equals("S")) return 2;
        if(s.equals("N")) return 3;
        else throw new RuntimeException("ERR");
    }
}