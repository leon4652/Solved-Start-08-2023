import java.io.*;
import java.util.*;
public class Main {
	static int R, C, M, result;
	static int[] DR = {-1, 1, 0, 0}; //위 아래 오른 왼
	static int[] DC = {0, 0, 1, -1};
	static Queue<Shark>[][] map;
	static Queue<Shark> temp = new ArrayDeque<>();
	static Queue<Shark> pq = new PriorityQueue<>();
	static boolean[][] check;
	static void solve() {
		for(int i = 0; i < C; i++) {
			catchShark(i);
			moveShark();
		}
		System.out.println(result);
	}

	
	static void moveShark() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j].isEmpty()) continue;
				Shark s = map[i][j].poll();
				s.move(DR, DC, R, C);
				temp.add(s);
			}
		}
		
		while(!temp.isEmpty()) {
			Shark s = temp.poll();
			map[s.r][s.c].add(s);
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j].size() > 1) {
					pq.clear();
					while(!map[i][j].isEmpty()) {
						pq.add(map[i][j].poll());
					}
					map[i][j].add(pq.poll());
				}
			}
		}
	}
	
	static void catchShark(int c) {
		for(int i = 0; i < R; i++) {
			if(!map[i][c].isEmpty()) {
				int size = map[i][c].poll().z;
				result += size;
				return;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Queue[R][C];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				map[i][j] = new ArrayDeque<>();
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			map[r][c].add(new Shark(r, c, s, d, z));
		}
		
		solve();
	}
} 
class Shark implements Comparable<Shark> {
	int r, c, s, d, z;
	
	@Override
	public int compareTo(Shark s) {
		return Integer.compare(s.z, this.z);
	}
	
	public void move(int[] DR, int[] DC, int R, int C) {
		int move = 0;

		//상하 이동일 경우
		if(this.d < 2) {
	        int cycle = 2 * (R - 1);
	        move = s % cycle; // 이동 속도 최적화
		}
		
		//좌우 이동일 경우
		if(this.d >= 2) {
			int cycle = 2 * (C - 1);
			move = s % cycle;
		}
		
		for(int i = 0; i < move; i++) {
			int nr = this.r + DR[d];
			int nc = this.c + DC[d];
			if(cantGo(nr, nc, R, C)) changePos(DR, DC);
			else {
				this.r = nr;
				this.c = nc;
			}
		}
	}
	
	void changePos(int[] DR, int[] DC) { //위 아래 오른 왼
		if(d == 0) d = 1;
		else if(d == 1) d = 0;
		else if(d == 2) d = 3;
		else if(d == 3) d = 2;
		
		//한 칸 이동
		this.r += DR[d];
		this.c += DC[d];
	}
	
	boolean cantGo(int r, int c, int R, int C) {
		return r < 0 || c < 0 || r >= R || c >= C;
	}
	
	public Shark(int r, int c, int s, int d, int z) {
		this.r = r;
		this.c = c;
		this.s = s; //속력
		this.d = d; //방향
		this.z = z; //크기
	}
}