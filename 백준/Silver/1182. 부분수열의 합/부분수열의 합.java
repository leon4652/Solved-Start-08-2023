import java.io.*;
import java.util.*;

public class Main {
    static int N, S;
    static int[] nums;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        findSubsequence(0, 0);
        if (S == 0) { // 빈 부분수열의 경우 제외
            count--;
        }
        System.out.println(count);
    }

    static void findSubsequence(int index, int sum) {
        if (index == N) {
            if (sum == S) {
                count++;
            }
            return;
        }

        findSubsequence(index + 1, sum); // 현재 원소를 포함하지 않는 경우
        findSubsequence(index + 1, sum + nums[index]); // 현재 원소를 포함하는 경우
    }
}
