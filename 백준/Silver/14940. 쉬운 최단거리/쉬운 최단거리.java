import java.util.*;
import java.io.*;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int[] DR = {-1, 0, 1, 0};
    static final int[] DC = {0, 1, 0, -1};
    static int N, M, resultMap[][];
    static boolean[][] map, visit;
    static HashSet<Integer> hs = new HashSet<>();
    public static void main(String[] args) throws Exception {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());

    	map = new boolean[N][M];
    	visit = new boolean[N][M];
    	resultMap = new int[N][M];
    	
    	Pos start = null; //시작 좌표
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < M; j++) {
    			int now = Integer.parseInt(st.nextToken());
    			if(now == 0) map[i][j] = true; //벽 = true
    			else if(now == 2) {
    				map[i][j] = true;
    				start = new Pos(i, j, 0);
    			}
    		}
    	}
    	
    	Queue<Pos> bfsQ = new ArrayDeque<>();
    	bfsQ.add(start); //시작 좌표
    	while(!bfsQ.isEmpty()) {
    		Pos now = bfsQ.poll();
    		int r = now.r;
    		int c = now.c;
    		int dist = now.dist;
    		if(visit[r][c]) continue;
    		visit[r][c] = true;
    		resultMap[r][c] = dist;
    		
    		for(int i = 0; i < 4; i++) {
    			int nr = r + DR[i];
    			int nc = c + DC[i];
    			
    			if(cantGo(nr, nc) || visit[nr][nc] || map[nr][nc]) continue;
    			bfsQ.add(new Pos(nr, nc, dist + 1));
    		}
    	}
    	
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			if(resultMap[i][j] == 0) { //원래 갈수 없는 땅 or 갈수있는데 도달할 수 없는 위치
    				if(map[i][j]) bw.write("0 ");
    				else bw.write("-1 ");
    			}
    			else bw.write(resultMap[i][j] + " ");
    		}
    		bw.write("\n");
    	}
    	
    	bw.close();
	}
    
    static boolean cantGo(int r, int c) {
    	if(r < 0 || c < 0 || r >= N || c >= M) return true;
    	return false;
    }
}

class Pos {
	int r, c, dist; 
	public Pos() {}
	public Pos(int r, int c, int dist) {
		this.r = r;
		this.c = c;
		this.dist = dist;
	}
}
