
//https://www.acmicpc.net/problem/1966
//프린터 큐 S3
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCaseCount = sc.nextInt();  // 테스트 케이스의 수를 입력받는다.

        for (int t = 0; t < testCaseCount; t++) {
            int N = sc.nextInt();  // 문서의 수
            int M = sc.nextInt();  // 궁금한 문서의 위치

            LinkedList<int[]> queue = new LinkedList<>();  // 중요도와 인덱스를 저장할 큐
            for (int i = 0; i < N; i++) {
                // 각 문서의 중요도 입력받고 큐에 (중요도, 인덱스) 형태로 추가
                queue.offer(new int[]{sc.nextInt(), i});
            }

            int printCount = 0;  // 인쇄된 문서의 수

            while (!queue.isEmpty()) {
                int[] current = queue.poll();  // 큐의 첫 문서를 꺼낸다
                boolean isPrintable = true;

                // 큐 내 다른 문서들과 중요도를 비교
                for (int[] doc : queue) {
                    if (doc[0] > current[0]) {  // 더 높은 중요도의 문서가 존재하는 경우
                        isPrintable = false;
                        break;
                    }
                }

                if (isPrintable) {  // 인쇄 가능한 경우
                    printCount++;
                    if (current[1] == M) {  // 궁금한 문서가 인쇄되는 경우
                        System.out.println(printCount);
                        break;
                    }
                } else {  // 인쇄 불가능한 경우, 다시 큐의 끝으로 보낸다
                    queue.offer(current);
                }
            }
        }
        sc.close();
    }
}
