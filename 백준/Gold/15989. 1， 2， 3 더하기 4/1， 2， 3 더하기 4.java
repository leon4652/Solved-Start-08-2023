//https://www.acmicpc.net/problem/15989
/*

1, 2, 3만 사용 가능
1부터 N까지 시작
 */
import java.util.*;
import java.io.*;
public class Main {
    static List<Integer> testcase;
    static int[] dp;

    public static void main(String[] args) {
        input();
        for(int num : testcase) solve(num);
    }

    private static void solve(int num) {
        if(num <= 3) {
            System.out.println(num);
            return;
        }
        dp = new int[num + 1];
        // 초기값 설정
        dp[1] = 1; //1
        dp[2] = 2; //1+1, 2
        dp[3] = 3; //1+1+1, 2+1, 3

        for (int i = 4; i <= num; i++) {
            dp[i] = dp[i - 3] + (i / 2) + 1;
        }

        System.out.println(dp[num]);
    }

    static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int t = Integer.parseInt(br.readLine());
            testcase = new ArrayList<>();
            while (t-- > 0) {
                testcase.add(Integer.parseInt(br.readLine()));
            }
        } catch (Exception e) {}
    }
}
