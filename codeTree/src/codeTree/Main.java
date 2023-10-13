//package codeTree;
//
//import java.io.*;
//import java.util.*;
//public class Main {
//	static int T, x, y;
//	static int res; 
//	static HashSet<Integer> spot;
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		T = Integer.parseInt(br.readLine());
//		while(T > 0) {
//			res = Integer.MAX_VALUE;
//			spot = new HashSet<>();
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			solve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
//			bw.write(res + "\n");
//			T--;
//		}
//		bw.close();
//	}
//	
//	static int solve(int x, int y) {
//				
//		backTracking(x, y, 0, 0); //0 
//		backTracking(x, y, 1, 0); // 1
//		return 1;
//	}
//	
//	static void backTracking(int x, int y, int move, int cnt) {
//		if(x >= y || cnt >= res || x < 0) {
//			if(x == y) res = Math.min(res, cnt);
//			return;
//		}
//		if(move == 0) return; //재귀
//		
//		x += move; //이동
//		cnt++; //카운트업
//		
//		if()
//		
//		back//K - 1 
//		if(move - 1 != 0) backTracking(x + move - 1, y, move - 1, cnt + 1); //-1
//		if(move != 0) backTracking(x + move, y, move, cnt + 1); //0
//		if(move + 1 != 0) backTracking(x + move + 1, y, move + 1, cnt + 1); //1
//	}
//	
//}
