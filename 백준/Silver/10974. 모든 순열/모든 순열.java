

import java.util.*;
import java.io.*;

public class Main {
	static int[] numbers;
	static int[] inputs;
	static boolean[] visits;
	static int N;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		numbers = new int[N];
		inputs = new int[N];
		visits = new boolean[N];
		for(int i = 0; i < N; i++) {
			inputs[i] = i + 1;
		}
		
		
		perm(0);
		bw.close();
	}
	
	static void perm(int cnt) throws Exception {
		if(cnt == N) {
			for(int i = 0; i < N; i++) bw.write(numbers[i] + " ");
			bw.write("\n");
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visits[i]) continue;
			visits[i] = true;
			numbers[cnt] = inputs[i];
			perm(cnt + 1);
			visits[i] = false;
		}
	}
}
