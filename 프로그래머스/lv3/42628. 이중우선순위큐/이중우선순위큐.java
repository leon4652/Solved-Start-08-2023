import java.util.*;
import java.io.*;


class Solution {
    static PriorityQueue<Integer> minPQ = new PriorityQueue<>();
    static PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

    public int[] solution(String[] operations) {
        int cnt = 0; //input output 카운팅

        for(String s : operations) {
            StringTokenizer st = new StringTokenizer(s);

            //입력 파라미터 I / D 구분
            if(st.nextToken().equals("I")) {
                cnt++;
                insertQueue(Integer.parseInt(st.nextToken()));
            }
            else { //D
                if(cnt == 0) continue;
                cnt--;
                if(st.nextToken().equals("1")) deleteMaxValue();
                else deleteMinValue();
            }
        }

        if(cnt == 0) return new int[] {0, 0};
        else return new int[] {maxPQ.peek(), minPQ.peek()};
    }
    
        private static void deleteMinValue() {
        int min = minPQ.poll();
        maxPQ.remove(min);
    }

    private static void deleteMaxValue() {
        int max = maxPQ.poll();
        minPQ.remove(max);
    }

    static void insertQueue(int value) {
        maxPQ.offer(value);
        minPQ.offer(value);
    }
}