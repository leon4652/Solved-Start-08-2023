package programmers.lv2;

//https://school.programmers.co.kr/learn/courses/30/lessons/42587

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

//PQ 쓰면 되는데, 동일한 게 여러 개 있을 경우 몇번째인지를 분간하는 로직이 필요

public class 프로세스 {

    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) {
        int[] input = new int[] {1, 1, 9, 1, 1, 1};
        System.out.println(solution(input, 0));
    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        

        for (int i = 0; i < priorities.length; i++) {

        }

        return answer;
    }
}
