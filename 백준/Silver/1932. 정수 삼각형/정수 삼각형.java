//https://www.acmicpc.net/problem/1238

import java.io.*;
import java.util.*;

/*
2차월 배열 n*n 역시 사용가능하지만(N < 500),
그래프로 직접 해결하였다.
 */

public class Main {
    static int N;
    static int[] dp;
    static List<List<Integer>> pyramid = new ArrayList<>(); //피라미드
    public static void main(String[] args) throws Exception {
        input();
        int roofValue = pyramid.get(0).get(0); //최상위 값
        if(N == 1) { //한 층만 존재
            System.out.println(roofValue);
            return;
        }
        //바닥 층수부터 정상 - 1 칸까지 진행
        for(int i = 0; i < N - 1; i++) {
            check(N - (i + 1));
        }

        int maxValue = Math.max(pyramid.get(1).get(0), pyramid.get(1).get(1)); //바닥부터 N - 1층까지 쌓인 최대 값
        System.out.println(roofValue + maxValue);
    }
    static void check(int level) {
        List<Integer> now = pyramid.get(level);      //현재 층
        List<Integer> next = pyramid.get(level - 1); //윗 층
        int L = now.size();

        for(int i = 0; i < L - 1; i++) {
            next.set(i, next.get(i) + Math.max(now.get(i), now.get(i + 1)));
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            List<Integer> nowLevel = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreElements()) nowLevel.add(Integer.parseInt(st.nextToken()));
            pyramid.add(nowLevel);
        }
    }
}