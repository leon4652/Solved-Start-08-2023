//package codeTree;
//
////G3 예술성 : https://www.codetree.ai/training-field/frequent-problems/problems/artistry/description?page=1&pageSize=20
//
//import java.util.*;
//import java.io.*;
//
//public class G3_Artistry {
//	static int N, result, groupNo;
//	static Group[][] map;
//	static boolean[][] visit;
//
//	static final int[] DR = {-1, 1, 0, 0}; //상하좌우
//	static final int[] DC = {0, 0, -1, 1};
//	
//	static List<Pos> groupNoList = new ArrayList<>(); //첫 그룹 좌표
//	static HashMap<Integer, Integer> groupKanCount = new HashMap<>(); //그룹No별 칸 점유 개수
//	
//	public static void main(String[] args) throws Exception {
//		input(); //최초 입력
//		
//		int repeat = 3; //3회 반복
//		while(repeat > 0) {
//			group(); //그룹 만들기
//			getScore(); //예술 점수 확인
////			rotateCross(); //십자 돌리기
////			rotateSquare(); //사각형 네번 벌로기
//			log();
//			//값 초기화 : 맵의 그룹넘버, visit, groupNo, groupNoList, groupKanCount
//			repeat--;
//		}
//		
//		
//
//	}
//	
//	static void getScore() {
//		int size = groupNoList.size(); //그룹 개수
//		int score = 0;
//		for(int i = 0; i < size; i++) { //각 그룹별로 인접한 행렬 찾아 점수 계산하기
//			Pos now = groupNoList.get(i);
//			score += getScore_calculate(now.r, now.c, map[now.r][now.c].val);
//		}
//		score /= 2; //나누기 2 해야함, 중복 때문
//		System.out.println("score : " + score);
//		result += score;
//	}
//	
//	static int getScore_calculate(int r, int c, int thisGroupVal) {
//		int score = 0;
//		//현재 특정 그룹 배열의 좌표를 가져왔음. 이 좌표와 맞닿은 그룹의 변의 개수와 해당 그룹 val을 얻어 계산해야 함
//		
//		HashMap<Integer, Integer> hashMap = new HashMap<>(); //맞닿은 배열의 변 개수 저장, groupNo와 변 개수
//		visit = new boolean[N][N]; //방문 배열 초기화
//		
//		Queue<Pos> bfs = new ArrayDeque<>();
//		bfs.add(new Pos(r, c));
//		while(!bfs.isEmpty()) {
//			Pos pos = bfs.poll();
//			if(visit[pos.r][pos.c]) continue;
//			visit[pos.r][pos.c] = true; //방문 처리
//			
//			for(int i = 0; i < 4; i++) {
//				int nr = pos.r + DR[i];
//				int nc = pos.c + DC[i];
//				if(cantGo(nr, nc) || visit[nr][nc]) continue; //맵밖, 방문했던 곳이라면
//				if(map[nr][nc].val != thisGroupVal) { //변경지역
//					int nowGroupNo = map[nr][nc].groupNo;
//					//변경지역 정보 수집
//					if(hashMap.containsKey(nowGroupNo)) { //있다면 기존 값에 1 추가
//						int val = hashMap.get(nowGroupNo);
//						hashMap.put(nowGroupNo, val + 1);
//					} 
//					else hashMap.put(nowGroupNo, 1); //없다면 새로 추가
//					continue; //더 넘어가면 안됨
//				}
//			}
//		}
//
//		//debug
//		System.out.println("TGN : " + thisGroupVal);
//		for(Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
//		    System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//		}
//
//		return score;
//	}
//
//	static void group() {
//		//BFS와 visit 사용하여 그룹핑하고, 넘버링
//		groupNo = 0; //그룹넘버
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				if(visit[i][j]) continue; //이미 그룹핑
//				groupNoList.add(new Pos (i, j));
//				group_grouping(i, j, groupNo++, map[i][j].val); //해당 지역 그룹화 진행
//			}
//		}	
//	}
//	
//	private static void group_grouping(int i, int j, int groupNo, int val) {
//
//		Queue<Pos> bfs = new ArrayDeque<>();
//		bfs.add(new Pos(i, j));
//		
//		int kan = 0; //칸 점유
//		while(!bfs.isEmpty()) {
//			Pos pos = bfs.poll();
//			if(visit[pos.r][pos.c] || visit[pos.r][pos.c] || map[pos.r][pos.c].val != val) continue; //방문 시, 혹은 다른 곳일 경우
//			
//			visit[pos.r][pos.c] = true; //방문 처리
//			map[pos.r][pos.c].groupNo = groupNo; //해당 그룹핑 넘버로 설정
//			kan++; //칸 개수 추가
//			
//			for(int k = 0; k < 4; k++) {
//				int nr = pos.r + DR[k];
//				int nc = pos.c + DC[k];
//				if(cantGo(nr, nc) || visit[nr][nc] || map[nr][nc].val != val) continue; //다른 지역
//				bfs.add(new Pos(nr, nc));
//			}
//		}
//		
//		//그룹칸을 측정하는 해시맵에 추가 groupNo가 있다면
//		groupKanCount.put(groupNo, kan);
//	}
//
//	static int countJowha(int aKan, int bKan, int aVal, int bVal, int between) {
//		//그룹 a와 b의 조화로움값 
//		int count = 0;
//		count = (aKan + bKan) * aVal * bVal * between;
//		return count;
//	}
//	
//	static void input() throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		N = Integer.parseInt(st.nextToken());
//		map = new Group[N][N];
//		visit = new boolean[N][N];
//		for(int i = 0; i < N; i++) {
//			st = new StringTokenizer(br.readLine());
//			for(int j = 0; j < N; j++) {
//				map[i][j] = new Group();
//				map[i][j].val = Integer.parseInt(st.nextToken());
//			}
//		}
//	}
//	
//	static void log() {
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(map[i][j].groupNo + "\t");
//			}
//			System.out.println();
//		}
//		System.out.println("---------------------------------------");
//	}
//	
//	static boolean cantGo(int r, int c) {
//		if(r < 0 || c < 0 || r >= N || c >= N) return true;
//		return false;
//	}
//}
//
//class Group {
//	int val;
//	int groupNo;
//}
//
//class Pos {
//	int r, c;
//	Pos(int r, int c) {
//		this.r = r;
//		this.c = c;
//	}
//}