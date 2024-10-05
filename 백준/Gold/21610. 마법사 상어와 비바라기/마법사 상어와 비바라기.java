import java.util.*;
import java.io.*;
public class Main {
	static int N, M, map[][], order[][];
	static boolean[][] checkedMap;
	static Queue<Cloud> clouds = new ArrayDeque<>();
	static int[] DR = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] DC = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] CR = {-1, -1, 1, 1}; //대각
	static int[] CC = {-1, 1, -1, 1};
	
	public static void main(String[] args) {
		input();
		clouds.add(new Cloud(N - 2, 0));
		clouds.add(new Cloud(N - 1, 0));
		clouds.add(new Cloud(N - 2, 1));
		clouds.add(new Cloud(N - 1, 1));
		
		for(int i = 0; i < M; i++) {
			solve(order[i][0], order[i][1]);
		}
		
		calSum();
	}
	
	static void calSum() {
		int res = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				res += map[i][j];
			}
		}
		System.out.println(res);
	}
	
	static void solve(int d, int s) {
		moveAllCloud(d, s);
		rainingAndCheckPos();
		runCopyWaterMagic();
		makeCloud();
	}
	
	static void makeCloud() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(checkedMap[i][j] == false && map[i][j] >= 2) {
					clouds.add(new Cloud(i, j));
					map[i][j] -= 2;
				}
			}
		}
	}
	
	static void runCopyWaterMagic() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(checkedMap[i][j]) { //대각선 체크
					int cnt = copyMagic(i, j);
					map[i][j] += cnt;
				}
			}
		}
	}
	
	static int copyMagic(int r, int c) {
		int cnt = 0;
		for(int i = 0; i < 4; i++) {
			int nr = r + CR[i];
			int nc = c + CC[i];
			if(cantGo(nr, nc) || map[nr][nc] == 0) continue;
			cnt++;
		}
		return cnt;
	}
	
	static void rainingAndCheckPos() {
		checkedMap = new boolean[N][N];
		while(!clouds.isEmpty()) {
			Cloud cloud = clouds.poll();
			int r = cloud.r;
			int c = cloud.c;
			
			map[r][c] += 1;
			checkedMap[r][c] = true;
		}
	}
	
	static void moveAllCloud(int d, int s) {
		int size = clouds.size();
		while(size-- > 0) {
			Cloud cloud = clouds.poll();
			int nr = checkIsOut(cloud.r + (DR[d] * s));
			int nc = checkIsOut(cloud.c + (DC[d] * s));
			cloud.r = nr;
			cloud.c = nc;
			clouds.add(cloud);
		}
	}
	
	
	static boolean cantGo(int r, int c) {
		return r < 0 || c < 0 || r >= N || c >= N;
	}
	
	static int checkIsOut(int num) {
		num %= N;
		if(num < 0) num += N;
		return num;
	}
	
	static void input() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			order = new int[M][2];
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int d = Integer.parseInt(st.nextToken()) - 1;
				int s = Integer.parseInt(st.nextToken());
				order[i][0] = d;
				order[i][1] = s;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

class Cloud {
	public Cloud() {}
	public Cloud(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	int r, c;
}