import java.io.*;
import java.util.*;

//3차원 배열 사용

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static PriorityQueue<Virus> virusQueue = new PriorityQueue<>();
	
	static final int DR[] = {0, 0, 1, -1}; 
	static final int DC[] = {-1, 1, 0, 0};

	
	static int N, K;
	static int map[][];
	static boolean visit[][];
	public static void main(String[] args) throws IOException {
		//1. 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int now = Integer.parseInt(st.nextToken());
				if(now != 0) {
					map[i][j] = now;
					visit[i][j] = true;
					virusQueue.add(new Virus(i, j, now));
				}
			}
		}
	
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		
		solve(S); //메인 로직
		
		System.out.println(map[r][c]);
		
	}

	static void solve(int S) {
		Queue<Virus> temp = new ArrayDeque<>();
		while(S-- > 0) {
			
			while(!virusQueue.isEmpty()) {
				Virus now = virusQueue.poll();
				
				for(int i = 0; i < 4; i++) {
					int nr = now.r + DR[i];
					int nc = now.c + DC[i];
					
					if(cantGo(nr, nc) || visit[nr][nc]) continue;
					visit[nr][nc] = true;
					map[nr][nc] = now.num;
					temp.add(new Virus(nr, nc, now.num));
				}
			}
			
			while(!temp.isEmpty()) {
				virusQueue.add(temp.poll());
			}

		}
	}
	

	static boolean cantGo(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= N) return true;
		return false;
	}
}

class Virus implements Comparable<Virus> {
	int r, c, num;
	public Virus(int r, int c, int num) {
		this.r = r;
		this.c = c;
		this.num = num;
	}
	
	public int compareTo(Virus v) { //num 낮은 순대로 전파
		return this.num - v.num;
	}
}


