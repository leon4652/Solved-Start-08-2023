import java.util.*;

class Solution {
    static Queue<Integer> proq = new ArrayDeque<>();
    static Queue<Integer> speedq = new ArrayDeque<>();
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answerList = new ArrayList<>(); 
        for(int i = 0; i < progresses.length; i++) {
            proq.add(progresses[i]);
            speedq.add(speeds[i]);
        }
        
        int day = 1;
        while(!proq.isEmpty()){
            if(proq.peek() + (day * speedq.peek()) >= 100) {
                int res = cal(day, 0);
                answerList.add(res);
            }
            day++;
        }
        
        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) answer[i] = answerList.get(i);
        return answer;
    }
    
    public static int cal(int day, int cnt) {
        if(proq.isEmpty() || proq.peek() + (day * speedq.peek()) < 100) return cnt;
        else {
            proq.poll(); speedq.poll();
            cnt = cal(day, ++cnt);
        }
            
        return cnt;
    }
}