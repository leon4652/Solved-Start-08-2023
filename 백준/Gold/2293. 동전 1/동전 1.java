import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, K;
    static int[] coins, memo;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N];
        memo = new int[K + 1];

        // 동전 가치 입력받기
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        memo[0] = 1;

        // 모든 동전에 대해 반복
        for (int coin : coins) {
            for (int j = coin; j <= K; j++) {
                memo[j] += memo[j - coin];
            }
        }

        // 결과 출력
        bw.write(memo[K] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
