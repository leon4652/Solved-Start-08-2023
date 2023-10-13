//package codeTree;
//
///*
// https://www.codetree.ai/training-field/frequent-problems/problems/battle-ground/description?page=1&pageSize=20
// G1 싸움땅
// 
//dr dc 중 에러나서 디버깅에 30분 썼음
//총 2시간 50분 
//+ 20분, 또 문제 제대로 안 읽어서 히든테케 틀렸다. 진짜 지문 제대로 읽어야 할 듯
// */
//
//import java.util.*;
//import java.io.*;
//
//public class G1_BattleField {
//	static int N, M, K;
//	static Field[][] map;
//	
//	static List<Player> playerList = new ArrayList<>(); //플레이어 리스트 조회
//	
//	static final int[] DR = {-1, 0, 1, 0}; //U R D L
//	static final int[] DC = {0, 1, 0, -1}; //0 1 2 3 .. +2 % 4하면 될듯
//	
//	public static void main(String[] args) throws Exception {
//		setting(); //입력 받기
//		
//		while(K > 0) {
//			move(); //플레이어 순차 이동
//			K--;
//			
////			for(int i = 0; i < playerList.size(); i++) {
////				Player now = playerList.get(i);
////				System.out.println(now.no + "번 : " + now.r + " " + now.c);
////			}
////			System.out.println("---------");
//		}
//		
//		print(); //출력
//	}
//
//	private static void print() {
//		for(int i = 0; i < playerList.size(); i++) {
//			System.out.print(playerList.get(i).point + " ");
//		}
//		
//	}
//
//	static void move() {
//		for(int i = 0; i < playerList.size(); i++) {
//			move_pickOne(i); //플레이어 순차 이동 
//		}
//	}
//	
//	static void move_pickOne(int no) {
//		//해당 플레이어 이동(D)
//		Player now = playerList.get(no);
//		int nr = now.r + DR[now.d];
//		int nc = now.c + DC[now.d];
//		
//		//격자 벗어날 경우 정반대 방향으로 변경
//		if(cantGo(nr, nc)) {
//			now.d = (now.d + 2) % 4;
//			nr = now.r + DR[now.d];
//			nc = now.c + DC[now.d];
//		}
//		//아닐 경우 해당 방향으로 이동
////		System.out.println(no + " 이동 : " + now.r + " " + now.c + " 에서 -> " + nr + nc);
//		now.r = nr;
//		now.c = nc;
//		
//		//해당 플레이어가 있는지 확인
//		if(checkThisPlace(nr, nc, no)) {
////			System.out.println(" 배틀 : " +now.no+"가 " + now.r + " " + now.c + "로 이동하며 배틀 진행");
//			battle(now); //있다면 배틀
//		}
//		else {
//			getGun(now); //없다면 총 주움(교체)
//		}
//		
//		
//	}
//	
//	static void battle(Player now) {
//		//라이벌 찾기
//		Player rival = new Player(); //라이벌
//		for(int i = 0; i < playerList.size(); i++) {
//			Player p = playerList.get(i);
//			if(p.r == now.r && p.c == now.c && p.no != now.no) {
//				rival = p;
//				break;
//			}
//		}
//		//배틀
//		int nowPower = now.power + now.gun;
//		int rivalPower = rival.power + rival.gun;
//		int reward = Math.abs(nowPower - rivalPower);
//		
//		//내가 이김
//		if(nowPower > rivalPower || (nowPower == rivalPower && now.power > rival.power)) {
//			now.point = now.point + reward;
//			loseAction(rival);
//			getGun(now);
//		}
//		else { //라이벌이 이김
//			rival.point = rival.point + reward;
//			loseAction(now);
//			getGun(rival);
//		}
//	}
//
//	
//	static void loseAction(Player loser) {
//		int r = loser.r;
//		int c = loser.c;
//		
//		//총이 있다면 버리기
//		if(loser.gun != 0) {
//			map[r][c].guns.add(loser.gun); //넣기
//			loser.gun = 0; //버리기
//		}
//		
//		//이동
//
//		while(true) {
//			int nr = r + DR[loser.d];
//			int nc = c + DC[loser.d];
//			
//			if(cantGo(nr, nc) || checkThisPlace(nr, nc, loser.no)) { //격자 밖이거나 사람이 있음
//				int d = loser.d;
//				d++;
//				d %= 4; //나누기
//				loser.d = d; //갱신
//				continue; //다음 방향
//			}
//			//괜찮다면 이동
//			loser.r = nr; 
//			loser.c = nc;
//			
//			//이동한 곳에서 총 줍기
//			getGun(loser);
//			return;
//		}
//	}
//	
//	static void getGun(Player now) {
//		int r = now.r;
//		int c = now.c;
//		//총이 있다면 확인한다
//		if(map[r][c].guns.size() > 0) {
//			int gun = map[r][c].guns.poll(); //바닥에 있는 총
//			
//			//총 없으면 줍는다
//			if(now.gun == 0) {
//				now.gun = gun;
//			}
//			else { //있다면 비교한다
//				if(now.gun >= gun) map[r][c].guns.add(gun); //다시 버리고 감
//				else {
//					map[r][c].guns.add(now.gun); //내 총 두고감
//					now.gun = gun; //주움
//				}
//			}
//		}
//	}
//	
//	static boolean checkThisPlace(int r, int c, int no) {
//		for(int i = 0; i < playerList.size(); i++) {
//			Player now = playerList.get(i);
//			if(r == now.r && c == now.c && no != now.no) return true;
//		}
//		return false;
//	}
//	
//	
//	static void log() {
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(map[i][j].guns.peek() + "\t");
//			}
//			System.out.println();
//		}
//		System.out.println("=============================");
//	}
//	
//	static void setting() throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//		K = Integer.parseInt(st.nextToken());
//		
//		//초기화
//		map = new Field[N][N];
//		for(int i = 0; i < N; i++) {
//		    for(int j = 0; j < N; j++) {
//		        map[i][j] = new Field();
//		    }
//		}
//		
//		//총 넣기
//		for(int i = 0; i < N; i++) {
//			st = new StringTokenizer(br.readLine());
//			for(int j = 0; j < N; j++) {						
//				int val = Integer.parseInt(st.nextToken());
//				if(val == 0) continue; //총 없음
//				map[i][j].guns.add(val);
//			}
//		}
//		
//		//플레이어 넣기 + 리스트에 추가하기
//		for(int i = 0; i < M; i++) {
//			st = new StringTokenizer(br.readLine());
//			int r = Integer.parseInt(st.nextToken()) - 1;
//			int c = Integer.parseInt(st.nextToken()) - 1;
//			int d = Integer.parseInt(st.nextToken());
//			int power = Integer.parseInt(st.nextToken());
//			playerList.add(new Player(i, r, c, d, power)); //리스트에 추가
//		}
//	}
//	
//	static boolean cantGo(int r, int c) {
//		if(r < 0 || c < 0 || r >= N || c >= N) return true;
//		return false;
//	}
//}
//
//class Field {
//	PriorityQueue<Integer> guns = new PriorityQueue<>(new Comparator<Integer>() {
//		@Override
//		public int compare(Integer o1, Integer o2) {
//			return o2 - o1;
//		}
//	});
//}
//
//class Player {
//	int r, c, d, power, point, no;
//	int gun;
//	Player(int no, int r, int c, int d, int power) {
//		this.r = r;
//		this.c = c;
//		this.d = d;
//		this.power = power;
//		this.no = no;
//	}
//	public Player() {
//		// TODO Auto-generated constructor stub
//	}
//}
//
