package etc;

import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int S, X, Y;
	static int[][] map;
	static boolean[][] visit;
	//상하좌우
	static final int[] DR = {-1, 1, 0, 0};
	static final int[] DC = {0, 0, -1, 1};
	
	//번식 정보
	static HashSet<Integer> hs = new HashSet<>();
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static Queue<Virus> tempQueue = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(hs.contains(map[i][j])) continue;
				hs.add(map[i][j]);
				pq.add(map[i][j]);
			}
		}
		hs.remove(0); //0 제거
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken()) - 1;
		Y = Integer.parseInt(st.nextToken()) - 1;
		
		solve();
		System.out.println(map[X][Y]);
	}
	
	static void solve() {		
		while(S > 0) {
			//tempQueue 넣기
			while(!pq.isEmpty()) {
				int now = pq.poll();
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(map[i][j] != now) continue;
						grow(i, j, map[i][j]);
					}
				}
			}
			//tempQueue 값 지도에 삽입
			fill();
			
			//PQ 리필
			for(Integer element : hs) {
				pq.add(element);
			}
			S--;
		}
	}
	
	static void fill() {
		while(!tempQueue.isEmpty()) {
			Virus now = tempQueue.poll();
			map[now.r][now.c] = now.val;
		}
	}
	
	static void grow(int r, int c, int val) {
		//가장 작은값인 좌표의 4방 탐색
		for(int i = 0; i < 4; i++) {
			int nr = r + DR[i];
			int nc = c + DC[i];
			if(cantGo(nr, nc) || map[nr][nc] != 0 || visit[nr][nc]) continue;
			visit[nr][nc] = true;
			tempQueue.add(new Virus(nr, nc, val));
		}
	}
	
	static boolean cantGo(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= N) return true;
		return false;
	}
}

class Virus {
	int r, c, val;
	Virus(int r, int c, int val) {
		this.r = r;
		this.c = c;
		this.val = val;
	}
	
}
