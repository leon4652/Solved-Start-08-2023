import java.util.*;
import java.io.*;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static final int[] DR = {0, -1, 0, 1};
    static final int[] DC = {-1, 0, 1, 0};
    
    static final int COUNT = 3;
    static int N, M, res;
    static int[] number;
    static int map[][], tempMap[][];
    public static void main(String[] args) throws Exception {
    	List<Pos> emptyPos = setting(); //빈 공간 리스트
    	number = new int[COUNT]; //조합 결과 담을 배열
    	comb(0, 0, emptyPos, emptyPos.size()); //메인 로직
    	
    	System.out.println(res);
	}
    
    public static void solve() {
	
    	//바이러스(2) 찾아서 해당 좌표에서 전파 진행
    	Queue<Pos> startQ = new ArrayDeque<>();
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			if(tempMap[i][j] == 2) startQ.add(new Pos(i, j));
    		}
    	}
    	
    	//전파 로직
    	while(!startQ.isEmpty()) {
    		spread(startQ.poll());
    	}

    	//안전장소 체크
    	int nowRes = 0;
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			if(tempMap[i][j] == 0) nowRes++;
    		}
    	}

    	res = Math.max(res, nowRes);
    }
    
    public static void spread(Pos virus) {
    	Queue<Pos> bfsQ = new ArrayDeque<>();
    	boolean[][] visit = new boolean[N][M];
    	
    	bfsQ.add(virus);
    	while(!bfsQ.isEmpty()) {
    		Pos now = bfsQ.poll();
    		int r = now.r;
    		int c = now.c;
    		if(visit[r][c]) continue;
    		visit[r][c] = true;

    		tempMap[r][c] = 2;

    		for(int i = 0; i < 4; i++) {
    			int nr = r + DR[i];
    			int nc = c + DC[i];
    			if(cantGo(nr, nc) || visit[nr][nc] || tempMap[nr][nc] != 0) continue;
    			bfsQ.add(new Pos(nr, nc));
    		}
    	}
    }
    
    public static void comb(int cnt, int start, List<Pos> emptyPos, int max) {
    	if(cnt == COUNT) {
    		tempMap = new int[N][M];
    		//기존 맵 복제
        	for(int i = 0; i < N; i++) {
        		for(int j = 0; j < M; j++) {
        			tempMap[i][j] = map[i][j];
        		}
        	}

    		//벽 3개 세우기
    		for(int i = 0; i < COUNT; i++) {
    			Pos now = emptyPos.get(number[i]); 
    			tempMap[now.r][now.c] = 1; //벽 세우기
    		}
    		solve(); //메인 로직
    		return;
    	}
    	
    	for(int i = start; i < max; i++) {
    		number[cnt] = i;
    		comb(cnt + 1, i + 1, emptyPos, max);
    	}
    }
    
    public static List<Pos> setting() throws Exception {
    	List<Pos> emptyPos = new ArrayList<>();
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	map = new int[N][M];
    	
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < M; j++) {
    			int now = Integer.parseInt(st.nextToken());
    			map[i][j] = now;
    			if(now == 0) emptyPos.add(new Pos(i, j));
    		}
    	}
    	
    	return emptyPos; //빈 공간
    }
    
    
    
    
    public static boolean cantGo(int r, int c) {
    	if(r < 0 || c < 0 || r >= N || c >= M) return true;
    	return false;
    }
}

class Pos {
	int r, c;
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}