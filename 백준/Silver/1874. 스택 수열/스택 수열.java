
import java.io.*;
import java.util.*;

class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int N;
  
  public static void main(String[] args) throws Exception {
    N = Integer.parseInt(br.readLine());
    Stack<Integer> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();
    
    int nowNum = 1; // 넣어야 하는 숫자

    while (N-- > 0) {
      int targetNum = Integer.parseInt(br.readLine()); // 뽑아야 하는 숫자

      while (nowNum <= targetNum) { // 목표 값이 될 때까지 숫자 넣기
        stack.push(nowNum);
        sb.append("+\n");
        nowNum++;
      }

      if (stack.peek() == targetNum) { // 목표 값 빼기
        stack.pop();
        sb.append("-\n");
      } else { // targetNum < stack.peek()일 경우 수열을 만들 수 없음
        System.out.println("NO");
        return;
      }
    }

    System.out.println(sb.toString());
  }
}
