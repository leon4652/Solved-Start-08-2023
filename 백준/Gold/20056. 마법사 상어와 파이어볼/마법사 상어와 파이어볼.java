
import java.util.*;
import java.io.*;
public class Main {
	static int N, M, K;
	static Queue<Fireball> balls = new ArrayDeque<>();
	static int[] DR = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] DC = {0, 1, 1, 1, 0, -1, -1, -1};
	static Queue<Fireball>[][] map;
	
	public static void main(String[] args) {
		input();
		for(int i = 0; i < K; i++) {
			moveBall(); //모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동
			splitBall(); //2개 이상 체크 후 스플릿
		}
		sumAll(); //최종 계산
	}

	static void sumAll() {
		
		int sum = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				Queue<Fireball> now = map[i][j];
				while(!now.isEmpty()) {
					sum += now.poll().m;
				}
			}
		}
		System.out.println(sum);
	}
	
	static void splitBall() {
		if(!balls.isEmpty()) System.out.println("splitBall 버그 발생");
		//1. 두개 이상 체크
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j].size() > 1) sumBall(i, j); //합치기 시작
			}
		}
		
		//2. 다시 공들 전부 재배치
		while(!balls.isEmpty()) {
			Fireball nowBall = balls.poll();
			map[nowBall.r][nowBall.c].add(nowBall);
		}
		
	}
	
	static void sumBall(int r, int c) {
		try {
			int sumL = 0; //합쳐진 파이어볼 질량의 합 
			int sumC = 0; //합쳐진 파이어볼의 개수
			int sumS = 0; //합쳐진 파이어볼 속력
			boolean isSameDist = true; //모두 홀수이거나 짝수인지?
			boolean isOdd = (map[r][c].peek().d % 2) == 1;
			
			//합치기
			while(!map[r][c].isEmpty()) {
				Fireball fb = map[r][c].poll();
				sumL += fb.m; //질량 증가
				sumC++;
				sumS += fb.s;
				if(isSameDist) { //동일 방향 유지중일때 판정
					boolean nowDist = fb.d % 2 == 1; //현재 방향
					if(isOdd != nowDist) isSameDist = false;
				}
			}
			
			// 나누기
			sumL /= 5;
			sumS = (sumS / sumC);
			int[] dist = new int[4];
			if(isSameDist) dist = new int[] {0, 2, 4, 6};
			else dist = new int[] {1, 3, 5, 7};
			
			//질량이 0인 파이어볼은 소멸되어 없어진다.
			if(sumL == 0) return;
			
			for(int i = 0; i < 4; i++) {
				int d = dist[i];		
				balls.add(new Fireball(r, c, sumL, sumS, d));
			}
			
		} catch(Exception e) {
			System.out.println("sumBall 버그 발생");
		}
	}
	
	//-------moveBall
	static void moveBall() {
		if(!balls.isEmpty()) System.out.println("moveBall 버그 발생");
		//1. 맵에 존재하는 공들 포집
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				Queue<Fireball> nowPosQueue = map[i][j];
				while(!nowPosQueue.isEmpty()) {
					Fireball nowBall = nowPosQueue.poll(); //객체 꺼내기
					moveBall_Each(nowBall); //파이어볼 변경 로직
					balls.add(nowBall); //변환 후 담기
				}
			}
		}
		
		//2. 다시 공들 전부 재배치
		while(!balls.isEmpty()) {
			Fireball nowBall = balls.poll();
			map[nowBall.r][nowBall.c].add(nowBall);
		}
		
	}
	
	static void moveBall_Each(Fireball nowBall) {
		int d = nowBall.d;
		int s = nowBall.s;
		int nr = -1;
		int nc = -1;
		
		//격자 이동
		nr = nowBall.r + (DR[d] * s);
		nc = nowBall.c + (DC[d] * s);
		
		//나감 판정
		nr = checkIsOut(nr);
		nc = checkIsOut(nc);
			
		//변경값 적용
		nowBall.r = nr;
		nowBall.c = nc;
	}
	
	static int checkIsOut(int num) {
		num = num % N; //널뛰기 방지
		if(num < 0) {
			num = N + num;
		}
		return num;
	}
	
	
	
	static boolean cantGo(int r, int c) {
		return (r < 0 || c < 0 || r >= N || c >= N);
	}
	public static void input() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
		
			//맵 구성 및 초기화
			map = new ArrayDeque[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = new ArrayDeque<>();
				}
			}
			
			//최초 파이어볼
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				Fireball fb = new Fireball();
				fb.r = Integer.parseInt(st.nextToken()) - 1;
				fb.c = Integer.parseInt(st.nextToken()) - 1;
				fb.m = Integer.parseInt(st.nextToken());
				fb.s = Integer.parseInt(st.nextToken());
				fb.d = Integer.parseInt(st.nextToken());
				map[fb.r][fb.c].add(fb);
			}
		} catch(IOException e) {
			
		}
	}
} 

class Fireball {
	public Fireball() {}
	public Fireball(int r, int c, int m, int s, int d) {
		this.r = r;
		this.c = c;
		this.m = m; //질량
		this.s = s; //속력
		this.d = d; //방향
	}
	int r, c, m, s, d;
}


