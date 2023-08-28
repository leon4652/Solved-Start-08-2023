import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
 int answer = 0;
      int idx = location; //목표로 하는 값의 인덱스

      //1.PQ에 가장 높은 순위로 배당, 큐에 값 저장
      PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
      Queue<Integer> queue = new ArrayDeque<>();
      for (int i = 0; i <priorities.length; i++) {
          pq.offer(priorities[i]);
          queue.offer(priorities[i]);
      }

      //2. 인덱싱
      while (true) {
          int priorityValue = pq.peek(); 

          //값 확인 : 최대값일 경우(우선순위일 경우) 일단 빼냄)
          if(priorityValue == queue.peek()) {
              pq.poll();
              queue.poll();
              answer++; 

              if(idx == 0) break; //정답일 경우
              else idx--; //아닐 경우 정답 인덱스 한칸 앞으로
          }
          else {
              
              int getQueueValue = queue.poll();
              queue.offer(getQueueValue);

              //만약 지금 비교한 값이 목표 값이라면 뒤로 보냈으니까 인덱스를 다시 맨 뒤로 보낸다.
              if(idx == 0) idx = queue.size() - 1; //인덱스 맨 뒤로
              else idx--; //아닐 경우 정답 인덱스 한칸 앞으로
          }
      }
      return answer;
    }
}