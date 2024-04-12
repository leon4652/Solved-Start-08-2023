
import java.util.*;
import java.io.*;
class Main {
	static int N, M, L; 
	static int[][] map; //필드
	static boolean[][] check; //'#' 체크(결과값 체크용)
	static List<CCTV> cctvList = new ArrayList<>(); //CCTV 정보
	
	//상하좌우 방향
	static final int[] DR = {-1, 1, 0, 0}; 
	static final int[] DC = {0, 0, -1, 1};
	
	static int res = Integer.MAX_VALUE; //최종 결과
	
	//모든 회전 가능한 경우의 수 모음
	static final int[][][] DCCTV = {
			{{0}, {1}, {2}, {3}}, 	//CCTV 번호 1 - 네방향 회전 가능
			{{0, 1}, {2, 3}}, //CCTV 번호 2 - 상하, 좌우
			{{0, 3}, {3, 1}, {1, 2}, {2, 0}}, //3 - 상우, 우하, 하좌, 좌상 
			{{2, 0, 3}, {0, 3, 1}, {3, 1, 2}, {1, 2, 0}}, //4 - 좌상우 / 상우하 / 우하좌 / 하좌상
			{{0, 1, 2, 3}}, //5 - 회전 불가(단일값)
	};
	
	public static void main(String[] args) throws Exception {
		input();
		
		//CCTV 체크 및 생성
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0 && map[i][j] != 6) {
					cctvList.add(new CCTV(i, j, map[i][j] - 1));
				}
			}
		}
		
		L = cctvList.size();
		int[] conditions = new int[L]; //백트래킹에서 사용
		backTracking(0, conditions); //모든 CCTV에 대한 경우의 수 생성
		
		System.out.println(res);
	}
	
	static void solve(int[] conditions) {
		check = new boolean[N][M]; //'#' 체크용 boolean
//		System.out.println(Arrays.toString(conditions));
		for(int i = 0; i < L; i++) { //존재하는 모든 CCTV 마킹 수행
			CCTV now = cctvList.get(i);
			int num = now.num; //번호 (입력값에 나온 1~5, 하지만 여기서는 인덱스 고려하여 0~4임)
			int res[] = DCCTV[num][conditions[i]]; //감시해야 할 방향 모음
//			System.out.println("D :" + Arrays.toString(res));
			
			//DCCTV의 경우의 수대로 '#' 마킹 -> check true 표시
			int r = now.r;
			int c = now.c;
			for(int k = 0; k < res.length; k++) { //방향 개수만큼 반복
				int d = res[k]; //방향
				int level = 1;	//점점 늘어나는 길이
				while(true) {
					int nr = r + (level * DR[d]);
					int nc = c + (level * DC[d]);
					if(cantGo(nr, nc) || map[nr][nc] == 6) break; //맵 밖으로 나감 or 벽 만남 
					check[nr][nc] = true;
					level++;
				}
			}
		}
		
		//정산
		int nowRes = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0 && check[i][j] == false) nowRes++; //감시 못한 공간
			}
		}
		res = Math.min(res, nowRes); //최저값 저장
	}
	
	static void backTracking(int cnt, int[] conditions) {
		if(cnt == L) {
			solve(conditions);
			return;
		}

        // 현재 조건을 회전하지 않는 경우
		int num = cctvList.get(cnt).num;
		int size = DCCTV[num].length; //배열의 길이
		for(int i = 0; i < size; i++) {
	        conditions[cnt] = i; //인덱스 번호 매핑
	        backTracking(cnt + 1, conditions);
		}

	
	}
	
	//값 입력받기
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static boolean cantGo(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return true;
		return false;
	}
}

class CCTV {
	int r, c, num;

	public CCTV(int r, int c, int num) {
		this.r = r;
		this.c = c;
		this.num = num;
	}
}