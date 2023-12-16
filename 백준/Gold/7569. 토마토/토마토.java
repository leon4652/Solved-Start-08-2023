
import java.io.*;
import java.util.*;

//3차원 배열 사용

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static final int DR[] = {0, 0, 0, 0, 1, -1}; //위 아래 왼쪽 오른쪽 앞 뒤
	static final int DC[] = {0, 0, -1, 1, 0, 0};
	static final int DH[] = {-1, 1, 0, 0, 0, 0};
	
	static int N, M, H, result = -1;
	static int tomatoCount;  //익어야 하는 토마토 개수 
	static int map[][][];
	public static void main(String[] args) throws IOException {
		//1. 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N][M];
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < M; k++) {
					int now = Integer.parseInt(st.nextToken());
					map[i][j][k] = now;
					if(now == 0) tomatoCount++; //익지 않은 토마토
				}
			}
		}
		
		//경우의 수 판정
		solve();
		
		if(result == -1) System.out.println(-1);
		else System.out.println(result);
	}
	
	static void solve() {
	    Queue<Pos> fineTomatos = new ArrayDeque<>();
	    Queue<Pos> temp = new ArrayDeque<>();
	    int count = 0; //여기서 익은 토마토
	    int day = 0;
	    boolean change = true;
	    boolean allTomatosFine = true;
	    boolean visit[][][] = new boolean[H][N][M];
	    // 초기 익은 토마토 위치 탐색 및 큐에 추가
	    for(int i = 0; i < H; i++) {
	        for(int j = 0; j < N; j++) {
	            for(int k = 0; k < M; k++) {
	                if(map[i][j][k] == 1) {
	                	fineTomatos.add(new Pos(j, k, i));
	                } else allTomatosFine = false;
	            }
	        }
	    }

	    // 모든 토마토가 처음부터 익어 있으면
	    if(allTomatosFine) {
	    	result = 0;
	    	return;
	    }

	    // 토마토 전파
	    while(count != tomatoCount) {
			//1. 전파했는지 확인
			if(change) change = false; //있다면 초기화
			else {
				return; //없다면 불가능 처리
			}
	    	
			//2. 토마토 전파 시행(Queue에 넣기)
	        int size = fineTomatos.size(); //초기 익은 토마토 위치만큼만 사용

	        for (int s = 0; s < size; s++) {
	            Pos now = fineTomatos.poll();
	            for(int x = 0; x < 6; x++) {
	                int nr = now.r + DR[x];
	                int nc = now.c + DC[x];
	                int nh = now.h + DH[x];

	                if(cantGo(nr, nc, nh) || visit[nh][nr][nc]) continue; // 맵 밖이면 무시
	                if(map[nh][nr][nc] == 0) {
	                	visit[nh][nr][nc] = true;
	                    temp.add(new Pos(nr, nc, nh));
	                }
	            }
	        }

			//3. 트리거 및 맵 재배치, 날짜 증가
			if(!temp.isEmpty()) change = true;
			while(!temp.isEmpty()) {
				Pos now = temp.poll();
				map[now.h][now.r][now.c] = 1;
				fineTomatos.add(now);
				count++; //카운트 증가
			}
	        day++;
	    }

	    result = day;
	}
	

	static boolean cantGo(int r, int c, int h) {
		if(r < 0 || c < 0 || h < 0 || r >= N || c >= M || h >= H) return true;
		return false;
	}
}

class Pos {
	int r, c, h;
	public Pos(int r, int c, int h) {
		this.r = r;
		this.c = c;
		this.h = h;
	}
}


