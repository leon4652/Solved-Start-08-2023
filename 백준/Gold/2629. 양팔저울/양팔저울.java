import java.util.*;
import java.io.*;
class Main {
    static int W, T, sumOfWeight;
    static int[] weights;
    static boolean[] isUsed; //backtracking 체크용
    static int[] marbles;
    static boolean[] dp; //경우의 수
    static Queue<Boolean> answer = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        input();
        setting();
        for (int i = 0; i < T; i++) solve(i);
        output();
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (!answer.isEmpty()) {
            if (answer.poll()) bw.write("Y ");
            else bw.write("N ");
        }
//        System.out.println(Arrays.toString(dp));
        bw.close();
    }

    static void solve(int idx) {
        int marble = marbles[idx];
        if(marble <= sumOfWeight && dp[marble]) answer.add(true);
        else answer.add(false);
    }

    //경우의 수 DP를 통해 구하기
    static void setting() {
//        // dp 배열 초기화
//        dp = new boolean[sumOfWeight+1][sumOfWeight + 1];
//        dp[0][0] = true; // 아무 추도 사용하지 않았을 때 0은 항상 만들 수 있음
//
//        // 모든 추에 대해 반복
//        for (int i = 1; i <= W; i++) {
//            int weight = weights[i-1]; // i번째 추의 무게
//            for (int j = 0; j <= sumOfWeight; j++) {
//                dp[weight][weight] = true; //현재 무게
//                if (dp[i-1][j]) { // 이전 추까지 고려한 결과가 true인 경우
//                    dp[i][j] = true; // 현재 추를 사용하지 않는 경우 (이전 추의 값 그대로 받아옴)
//                    if (j + weight <= sumOfWeight) dp[i][j + weight] = true; // 현재 추를 더하는 경우
//                    if(j >= weight) { //구슬 위치(왼쪽)에 추를 더하는 경우
//                        dp[i][j - weight] = true;
//                    }
//                }
//            }
//        }

        dp = new boolean[sumOfWeight + 1];
        dp[0] = true;
        // 각 추에 대해 반복
        Queue<Integer> tempQueue = new ArrayDeque<>();
        for (int i = W - 1; i >= 0; i--) {
            int weight = weights[i];
            for (int j = dp.length - 1; j >= 0; j--) { //큰 수부터 하향식으로 진행 (아래 반복문에 중첩 발생 방지)
                if(dp[j]) { //이전값 고려, 추를 더하거나 빼거나
                    int newWeightPlus = j + weight;
                    int newWeightMinus = Math.abs(j - weight); //양팔 저울은 음수 값도 고려할 수 있어야 함.

                    //범위 안에서의 더하기, 중복 고려
                    if (newWeightPlus <= sumOfWeight && !dp[newWeightPlus]) {
                        tempQueue.add(newWeightPlus);
                    }
                    //반대로 추 빼기
                    if (newWeightMinus >= 0 && !dp[newWeightMinus]) {
                        tempQueue.add(newWeightMinus);
                    }
                }
            }
            while (!tempQueue.isEmpty()) {
                dp[tempQueue.poll()] = true; //적용
            }
        }
    }

    //시간 초과 발생..
//    static void backTracking(int nowWeight, int idx, boolean use) {
//        if(idx == weights.length) return;
//        isUsed[idx] = true;
//        if(use) nowWeight += weights[idx];
//        dp[nowWeight] = true;
//
//        backTracking(nowWeight, idx + 1, true);
//        backTracking(nowWeight, idx + 1, false);
//        isUsed[idx] = false;
//    }


    //입력받기
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        W = Integer.parseInt(br.readLine()); //저울추 개수
        weights = new int[W]; //추의 무게
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < W; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
            sumOfWeight += weights[i];
        }
        T = Integer.parseInt(br.readLine()); //테스트 개수
        marbles = new int[T]; //구슬들의 무게
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < T; i++) {
            marbles[i] = Integer.parseInt(st.nextToken());
        }
    }
}