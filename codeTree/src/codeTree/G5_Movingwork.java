//package codeTree;
//
////불안한 무빙워크
////https://www.codetree.ai/training-field/frequent-problems/problems/unstable-moving-walk/description?page=2&pageSize=20
//
//import java.util.*;
//import java.io.*;
//public class G5_Movingwork {
//	static int N, K, res;
//	static ArrayDeque<Block> upStair = new ArrayDeque<>();
//	static ArrayDeque<Block> downStair = new ArrayDeque<>();
//	
//	public static void main(String[] args) throws Exception {
//		setting();
//		rotate(); //무빙워크 회전 및 사람 회전
//		//사람 한칸 이동
//	}
//	
//	static void rotate() {
//		Block block = upStair.pollLast();
//		if(block.people) block.people = false; //사람 내림
//		downStair.addLast(block);
//		
//		block = downStair.poll();
//		upStair.addFirst(block);
//	}
//	
//	static void setting() throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		N = Integer.parseInt(st.nextToken());
//		K = Integer.parseInt(st.nextToken());
//		st = new StringTokenizer(br.readLine());
//		for(int i = 0; i < N; i++) {
//			upStair.add(new Block(Integer.parseInt(st.nextToken()), false));
//		}
//		for(int i = 0; i < N; i++) {
//			downStair.addFirst(new Block(Integer.parseInt(st.nextToken()), false));
//		}
//	}
//}
//
//class Block {
//	int weak;
//	boolean people;
//	
//	Block(int weak, boolean people) {
//		this.weak = weak;
//		this.people = people;
//	}
//}
