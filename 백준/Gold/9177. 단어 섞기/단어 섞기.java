import java.io.*;
import java.util.*;

/**
 * 0번째 인덱스부터 대입 시도,
 * Memorization 수행
 */

public class Main {

    static int N;
    static List<String[]> questions = new ArrayList<>();
    static boolean[][] dp;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        input();
        for (int i = 0; i < questions.size(); i++) solve(questions.get(i), (i + 1));
        bw.flush();
        bw.close();
    }

    private static void solve(String[] question, int num) {
        String w1 = question[0];     //단어1
        String w2 = question[1];     //단어2
        String target = question[2]; //목표
        dp = new boolean[w1.length() + 1][w2.length() + 1];
        dp[0][0] = true;

        //w1 체크
        for (int i = 1; i <= w1.length(); i++) {
            dp[i][0] = (dp[i - 1][0] && (w1.charAt(i - 1) == target.charAt(i - 1)));
        }

        //w2 체크
        for (int i = 1; i <= w2.length(); i++) {
            dp[0][i] = (dp[0][i - 1] && (w2.charAt(i - 1) == target.charAt(i - 1)));
        }

        //w1 + w2 체크 : Target 단어의 N번째 자리는 항상 w1 글자 개수 + w2 글자 개수 - 1의 자리수이다.
        for (int i = 1; i <= w1.length(); i++) {
            for (int j = 1; j <= w2.length(); j++) {
                char t = target.charAt(i + j - 1);
                dp[i][j] = (dp[i - 1][j] && w1.charAt(i - 1) == t) || (dp[i][j -1] && w2.charAt(j - 1) == t);
            }
        }
        try {
            bw.write(output(num, dp[w1.length()][w2.length()]) + "\n");
        } catch (IOException e) {}
    }


    public static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            questions.add(br.readLine().split(" "));
        }
    }

    public static String output(int no, boolean isTrue) {
        return "Data set " + no + ": " + (isTrue ? "yes" : "no");
    }

}
