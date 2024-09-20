import java.util.*;
import java.io.*;

class Main {
    static int N, M, money[];

    public static void main(String[] args) {
        input();
        binarySearch(); // 가장 작은 값부터 탐색
    }

    static void binarySearch() {
        // K값 범위
        int minVal = Arrays.stream(money).max().getAsInt(); // 최소 K값은 하루 중 가장 큰 지출
        int maxVal = Arrays.stream(money).sum();            // 최대 K값은 모든 지출의 합
        int res = maxVal;

        while (minVal <= maxVal) {
            int mid = (minVal + maxVal) / 2;
            if (checkIfCan(mid)) { // 가능하다면 K값을 줄여서 탐색
                res = mid;
                maxVal = mid - 1;
            } else { // 불가능하다면 K값을 키워서 탐색
                minVal = mid + 1;
            }
        }

        System.out.println(res);
    }

    static boolean checkIfCan(int k) {
        int cnt = 1; // 인출 횟수
        int remain = k; // 인출한 금액에서 남은 금액

        for (int i = 0; i < N; i++) {
            if (money[i] > remain) { // 남은 금액으로 오늘 지출을 커버할 수 없으면
                cnt++;
                remain = k;
            }
            remain -= money[i]; // 남은 금액에서 오늘 지출만큼 차감
        }

        return cnt <= M;
    }

    static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            money = new int[N];
            for (int i = 0; i < N; i++) {
                money[i] = Integer.parseInt(br.readLine());
            }
        } catch (IOException e) {}
    }
}
