
import java.io.*;
import java.util.*;
/*
1. 먼저 M개에 맞는 채널 개수만큼 입력
2. 해당 채널이 결과값과 얼마나 차이나는지 확인
3. 백트래킹 사용

최대 N = 500000 .. 6자리
경우의 수 : 10^6 = 1000000
5자리, 4자리 .. 가 더욱 효율적일 수도 있다. 결국 다 구해봐야 한다.
*/

class Main {
  static final int CHANNAL = 100;
  static int N;
  static int M;
  static int remote[];
  static int result; //결과
  public static void main(String[] args) throws Exception{
    //입력
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());
    HashSet<Integer> broken = new HashSet<Integer>();
    
    //고장난 버튼이 있다면 추가하기
    if(M > 0) {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < M; i++) broken.add(Integer.parseInt(st.nextToken()));
    }

    //리모컨 버튼 누를 수 있는 경우를 담는 배열
    remote = new int[10 - M];
    int idx = 0;
    for(int i = 0; i < 10; i++) {
      if(broken.contains(i)) continue;
      remote[idx++] = i;
    }

    // +1 -1만 했을 때 나오는 결과
    result = Math.abs(N - CHANNAL); 
    
    //리모컨 누르는 회수(1자리 ~ 6자리)
    for(int i = 1; i <= 6; i++) {
      solve(i, 0, 0);
    }

    System.out.println(result);
  }

  static void solve(int maxButtonCount, int nowButtonCount, int totalCount) {
	  if(maxButtonCount == nowButtonCount) { //버튼 총 사용량 체크
		  result = Math.min(result, Math.abs(totalCount - N) + maxButtonCount);
      return;  
    }

    totalCount *= 10; //자리수 추가
    
    for(int i = 0; i < remote.length; i++) {
      solve(maxButtonCount, nowButtonCount + 1, totalCount + remote[i]);
    }
  }
}