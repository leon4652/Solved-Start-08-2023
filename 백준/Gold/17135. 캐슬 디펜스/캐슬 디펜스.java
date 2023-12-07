import java.util.*;
import java.io.*;
import java.io.*;
class Main {
	static final int ARCHERNUM = 3; //궁수 숫자
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int DR[] = {0, -1, 0, 1}; //LURD, 가장 왼쪽 우선
    static final int DC[] = {-1, 0, 1, 0}; //LURD, 가장 왼쪽 우선
    
    static int N, M, D, nowResult, maxResult;
    static int enemyNum; //전체 적 수
    static int[][] map, nowMap;
    static int[] number = new int[ARCHERNUM];
    static List<Pos[]> archerList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
    	setting(); //입력받기
    	comb(0, 0); //궁수 모든 경우의 수 판정(조합)
    	for(int i = 0; i < archerList.size(); i++) {
    		if(enemyNum == maxResult) break; //이미 최대 숫자를 처치했으니 반복할 필요 없음
    		nowResult = 0;
    		nowMapErase(); //보드 초기화
    		solve(archerList.get(i)); //메인 로직
    		maxResult = Math.max(nowResult, maxResult); //최대값 갱신
    	}
    	System.out.println(maxResult);
    }
    
    static void nowMapErase() {
    	nowMap = new int[N][M];
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			nowMap[i][j] = map[i][j];
    		}
    	}
    }
    
    static void solve(Pos[] archers) {
    	Queue<Pos> targetPos = new ArrayDeque<>();
    	int cnt = N;
    	while(cnt-- > 0) {
    		//궁수 공격 위치 파악
    		for(int i = 0; i < archers.length; i++) {
    			targetPos.add(attack(archers[i])); //각 궁수 공격 위치 저장
    		}
    		//공격 위치 사격
    		while(!targetPos.isEmpty()) {
    			Pos now = targetPos.poll();
    			if(now.r == -1 && now.c == -1) continue; //적을 찾지 못함
    			if(nowMap[now.r][now.c] == 1) { //적 제거
    				nowMap[now.r][now.c] = 0;
    				nowResult++;
    			}
    		}
        	moveEnemy(); //적 이동
    	}
    }
    
    static void moveEnemy() {
    	Queue<Pos> saveQ = new ArrayDeque<>();
    	//가장 아래의 열은 제외하고 r + 1 하여 저장
    	for(int i = 0; i < (N - 1); i++) {
    		for(int j = 0; j < M; j++) {
    			if(nowMap[i][j] == 1) {
    				nowMap[i][j] = 0;
    				saveQ.add(new Pos(i + 1, j)); //한칸 내려서 저장
    			}
    		}
    	}	
    	//다시 맵에 흩뿌리기
    	while(!saveQ.isEmpty()) {
    		Pos now = saveQ.poll();
    		nowMap[now.r][now.c] = 1;
    	}
    }
    
    static Pos attack(Pos archer) {
    	//bfs를 통해 가장 가까운 좌표 찾아 제거
    	boolean[][] visit = new boolean[N][M];
    	Pos res = new Pos(-1, -1);
    	int distance = Integer.MAX_VALUE;

    	Queue<int[]> bfsq = new ArrayDeque<>();
    	bfsq.add(new int[] {archer.r - 1, archer.c, 1});
    	
    	while(!bfsq.isEmpty()) {
    		int[] now = bfsq.poll();
    		int r = now[0];
    		int c = now[1];
    		int nowDist = now[2];
    		
    		if(visit[r][c]) continue;		 //방문했음
    		if(nowDist > distance) continue; //최단거리 아님
    		visit[r][c] = true; //visit 처리
    		if(outOfRange(new Pos(r, c), archer)) continue; //사정거리 밖
    		
    		//최단거리 적 갱신
    		if(nowMap[r][c] == 1) {
    			if(nowDist < distance || (nowDist == distance && res.c > c)) {
    				res.r = r;
    				res.c = c;
    				distance = nowDist;
    			}
    			continue;
    		}
    		
    		for(int i = 0; i < 4; i++) {
    			int nr = r + DR[i];
    			int nc = c + DC[i];
    			
    			if(cantGo(nr, nc) || visit[nr][nc]) continue;
    			bfsq.add(new int[] {nr, nc, nowDist + 1});
    		}
    	}
    	
    	return res; //결과
    }
    
    static boolean outOfRange(Pos now, Pos archer) {
    	if(Math.abs(now.r - archer.r) + Math.abs(now.c - archer.c) > D) return true;
    	return false;
    }
    
    
    static void comb(int cnt, int start) {
    	if(cnt == ARCHERNUM) { //궁수 경우의 수 추가
    		archerList.add(
    				new Pos[] {
    						new Pos(N, number[0]),
    						new Pos(N, number[1]),
    						new Pos(N, number[2]),
    						}
    				);
    		return;
    	}
    	
    	for(int i = start; i < M; i++) {
    		number[cnt] = i;
    		comb(cnt + 1, i + 1);
    	}
    }
    static void setting() throws Exception {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	D = Integer.parseInt(st.nextToken());
    	
    	map = new int[N][M];
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < M; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			if(map[i][j] == 1) enemyNum++;
    		}
    	}
    }

    static boolean cantGo(int r, int c) {
    	if(r < 0 || c < 0 || r >= N || c >= M) return true;
    	return false;
    }
}

class Pos {
	int r, c;
	Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
