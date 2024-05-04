import java.util.*;
class Solution {
    //LURD
    static final int[] DR = {0, -1, 0, 1};
    static final int[] DC = {-1, 0, 1, 0};
    static int[][] map = new int[11][11]; //우상단을 0,0으로 지정
    static boolean[][][] visit = new boolean[11][11][4]; //이전 방향까지 고민(3차원 배열) 
    static int r = 5; //5,5에서 시작
    static int c = 5; 
    static int answer = 0;
    public int solution(String dirs) {
        char[] command = dirs.toCharArray();
        for(char c : command) {
            switch(c) {
                case 'L' : { solve(0); break;}
                case 'U' : { solve(1); break;}
                case 'R' : { solve(2); break;}
                case 'D' : { solve(3); break;}
            }
        }
        return answer;
    }
    
    
    public void solve(int d) {
        int nr = r + DR[d];
        int nc = c + DC[d];
        if(cantGo(nr, nc)) return;
        if(!visit[nr][nc][d]) {
            answer++;
            visit[nr][nc][d] = true; //이쪽 방향 마킹
            visit[r][c][(d + 2) % 4] = true;  //반대로 nr nc에서 오는 방향도 마킹
        }
        r = nr; //이동
        c = nc;
    }
    
    public boolean cantGo(int r, int c) {
        if(r < 0 || c < 0 || r >= 11 || c >= 11) return true;
        return false;
    }
}