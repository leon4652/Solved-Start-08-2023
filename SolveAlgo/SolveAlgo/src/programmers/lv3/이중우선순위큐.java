package programmers.lv3;


//https://school.programmers.co.kr/learn/courses/30/lessons/42628

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class 이중우선순위큐 {
    static PriorityQueue<Integer> minPQ = new PriorityQueue<>();
    static PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = new String[] {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        int[] res = solution(input);
        System.out.println(res[0] + " " + res[1]);
    }

    static int[] solution(String[] operations) {
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
