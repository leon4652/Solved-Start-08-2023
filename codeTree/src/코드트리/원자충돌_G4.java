//package 코드트리;
//
//import java.io.*;
//import java.util.*;
//
//public class 원자충돌_G4 {
//	static int N, M, K, res;
//	static Field[][] map;
//	static final int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
//	static final int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
//	static ArrayDeque<Atom> tempQueue = new ArrayDeque<>();
//	
//	public static void main(String[] args) throws Exception {
//		//�Է�
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//		K = Integer.parseInt(st.nextToken());
//		map = new Field[N][N];
//		// Field �ν��Ͻ� �ʱ�ȭ
//		for(int r = 0; r < N; r++) {
//		    for(int c = 0; c < N; c++) {
//		        map[r][c] = new Field();
//		    }
//		}
//		
//		for(int i = 0; i < M; i++) {
//			st = new StringTokenizer(br.readLine());
//			int r = Integer.parseInt(st.nextToken()) - 1;
//			int c = Integer.parseInt(st.nextToken()) - 1;
//			int m = Integer.parseInt(st.nextToken());
//			int s = Integer.parseInt(st.nextToken());
//			int d = Integer.parseInt(st.nextToken());
//			map[r][c].atoms.add(new Atom(r, c, m, s, d)); //�ش� ���� �ʵ� ����Ʈ�� ���� �߰�
//		}
//		
//		solve(); //���� ����
//		System.out.println(res); //��� ���
//	}
//	
//	static void solve() {
//		//K�ð���ŭ �ݺ�
//		for(int i = 0; i < K; i++) {
//			moveAllAtoms(); //��� ���� �̵�, tempQueue�� ���� ���
//			setAllAtoms(); //tempQueue�� ���� ��� ���� �ٽ� map�� ����
//			makeAtoms(); //�ʿ� �ΰ� �̻� �پ��ִ� ���� �ռ� ����
//			setAllAtoms(); //tempQueue�� ���� ��� ���� �ٽ� map�� ����
//		}
//
//		//���
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				if(map[i][j].atoms.size() > 0) {
//					calResult(i, j);
//				}
//			}
//		}
//	}
//	
//	static void calResult(int r, int c) {
//		int size = map[r][c].atoms.size();
//		for(int i = 0; i < size; i++) {
//			res += map[r][c].atoms.poll().m;
//		}
//	}
//	
//	static void makeAtoms_unionAndSplit_make4Atoms(boolean cross, int r, int c, int m, int s) {
//		if(cross) { //�밢�� ����
//			tempQueue.offer(new Atom(r, c, m, s, 1));
//			tempQueue.offer(new Atom(r, c, m, s, 3));
//			tempQueue.offer(new Atom(r, c, m, s, 5));
//			tempQueue.offer(new Atom(r, c, m, s, 7));
//		}
//		else { //�����¿� ����
//			tempQueue.offer(new Atom(r, c, m, s, 0));
//			tempQueue.offer(new Atom(r, c, m, s, 2));
//			tempQueue.offer(new Atom(r, c, m, s, 4));
//			tempQueue.offer(new Atom(r, c, m, s, 6));
//		}
//	}
//	
//	static void makeAtoms_unionAndSplit(int r, int c) {
//		//a. ���� ĭ�� �ִ� ���ڵ��� ������ ������ �ӷ��� ��� ���� �ϳ��� ���ڷ� �������ϴ�.
//		int cross = 0; //�밢
//		int udlr = 0; //�����¿�
//		int m = 0; //����
//		int s = 0; //�ӷ�
//		int size = map[r][c].atoms.size();
//		for(int i = 0; i < size; i++) {
//			Atom now = map[r][c].atoms.poll();
//			m += now.m;
//			s += now.s;
//			if(now.d % 2 == 0) udlr++;
//			else cross++;
//		}
//		
//		//b. ���� ������ ���ڴ� 4���� ���ڷ� �������ϴ�.
//		if(m < 5) return; //������ 0���� ���Ǵ� ����
//		m = m / 5; //���� 
//		s = s / size; //�ӷ�
//
//		//���� ����
//		if(cross > 0 && udlr > 0) makeAtoms_unionAndSplit_make4Atoms(true, r, c, m, s);
//		else makeAtoms_unionAndSplit_make4Atoms(false, r, c, m, s);
//	}
//	
//	static void makeAtoms() {
//		//�ΰ��̻� ���� ã�� ����
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				if(map[i][j].atoms.size() >= 2) makeAtoms_unionAndSplit(i, j);
//			}
//		}
//	}
//	
//	static void setAllAtoms() {
//		while(!tempQueue.isEmpty()) {
//			Atom now = tempQueue.poll();
//			map[now.r][now.c].atoms.offer(now);
//		}
//	}
//	
//	static void moveAllAtoms() {
//		//���� �ӷ� �̵�, size() 1�� �̻� ���� ������ �̵� ��ġ �� �ӽ� ť�� ��Ƴ���
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				if(map[i][j].atoms.size() > 0) { //���� �ش� ��ġ�� ���ڵ��� �����Ѵٸ�
//					moveAllAtoms_putAndMove(i, j); //�ش� ���� ������ �̵�
//				}
//			}
//		}
//	}
//	
//	static void moveAllAtoms_putAndMove(int r, int c) {
//		//��� ������ tempQueue�� �̰��ϴ� �۾� ����, �� �������� �̵� ��ó��	
//		int size = map[r][c].atoms.size(); //���� ������ŭ �ݺ�
//		for(int i = 0; i < size; i++) {
//			Atom now = map[r][c].atoms.poll(); //������
//			//�̵��ϱ�
//			now.r = now.r + (now.s * dr[now.d]);
//			now.c = now.c + (now.s * dc[now.d]);
//			//�� �� �ε��� �Ѿ�°� ó���ϱ�
//			now.r = convertOutOfMap(now.r);
//			now.c = convertOutOfMap(now.c);
//			//tempQueue�� ����
//			tempQueue.offer(now);
//		}
//
//	}
//
//	static int convertOutOfMap(int r) {
//		if(r < 0) {
//			r = r % N; //N���� Ŭ ��� ����, ����� ����
//			r = N + r; //���� ����ŭ �������� ����Ʈ
//			if(r == N) r = 0; //r�� 0�� ��� ��ȯ
//		}
//		else if(r >= N) {
//			r = r % N;
//		}
//		return r;
//	}
//	
//	static boolean cantGo(int r, int c) {
//		if(r < 0 || c < 0 || r >= N || c >= N) return true;
//		return false;
//	}
//}
//
//class Atom {
//	int r, c, m, s, d;
//	Atom(int r, int c, int m, int s, int d) {
//		this.r = r;
//		this.c = c;
//		this.m = m;
//		this.s = s;
//		this.d = d;
//	}
//}
//
//class Pos {
//	int r;
//	int c;
//	Pos(int r, int c) {
//		this.r = r;
//		this.c = c;
//	}
//}
//
//class Field {
//	Queue<Atom> atoms = new ArrayDeque<>();
//}
