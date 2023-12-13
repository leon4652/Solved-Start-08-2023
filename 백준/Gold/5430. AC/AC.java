
import java.io.*;
import java.util.*;

//함수 R은 배열에 있는 수의 순서를 뒤집는 함수이고, 
//D는 첫 번째 수를 버리는 함수이다. 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int T;
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringBuilder command = new StringBuilder(br.readLine());
			int num = Integer.parseInt(br.readLine());
			StringBuilder arr = new StringBuilder(br.readLine());
			solve(command, arr);
		}
		
		bw.close();
	}
	
	static void solve(StringBuilder command, StringBuilder arr) throws IOException {
		boolean reverse = false;
		ArrayDeque<Integer> nowQueue = new ArrayDeque<>();
		int nowNum = 0; //현재 배열 숫자
		
		//1. Array 파싱
		for(int i = 0; i < arr.length(); i++) {
			char now = arr.charAt(i);
			if(now < 48 || now > 57) {
				if(nowNum != 0) { //저장숫자가 있다면 저장
					nowQueue.add(nowNum);
					nowNum = 0;
				}
				continue; //괄호, ',' 배제 및 큐 추가
			}		
			nowNum *= 10; //자리수 증가
			nowNum += (now - '0'); //현재 숫자 추가
		}

		
		//2. 명령어 수행
		for(int i = 0; i < command.length(); i++) {
			char now = command.charAt(i);
			if(now == 'R') reverse = !reverse; //반전
			else { //D의 경우 
				//배열이 비었는데 D일시 에러
				if(nowQueue.isEmpty()) {
					bw.write("error\n");
					return;
				}
				
				if(reverse) nowQueue.pollLast(); //마지막 꺼내기
				else nowQueue.poll();
			}
		}
		
		//3. 출력
		bw.write("[");
		if(!nowQueue.isEmpty()) {
			if(reverse) {
				bw.write("" + nowQueue.pollLast());
				while(!nowQueue.isEmpty()) bw.write("," + nowQueue.pollLast());
			} else {
				bw.write("" + nowQueue.poll());
				while(!nowQueue.isEmpty()) bw.write("," + nowQueue.poll());			
			}
		}
		bw.write("]\n");
	}
	

}


