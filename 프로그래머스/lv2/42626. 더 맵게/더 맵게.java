import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            pq.offer(scoville[i]);
        }

        while (pq.size() > 1 && pq.peek() < K) {
            pq.offer(pq.poll() + 2 * pq.poll());
            answer += 1;
        }

        if(pq.poll() >= K) return answer;
        return -1;
    }
}