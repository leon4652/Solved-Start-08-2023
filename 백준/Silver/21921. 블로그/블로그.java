import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int result = 0;
        int cnt = 1;
        int maxResult = 0;  // 가장 큰 구간합 저장
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] day = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            day[i] = Integer.parseInt(st.nextToken());
        }

        //최초 result값
        for (int i = 0; i < x; i++) {
            result += day[i];
        }
        maxResult = result;

        //슬라이딩 윈도우
        for (int i = x; i < n; i++) {
            result = result - day[i - x] + day[i];  // 윈도우 이동
            if (result > maxResult) {
                maxResult = result;
                cnt = 1;
            } else if (maxResult == result) cnt++;
        }

        if (maxResult == 0) System.out.println("SAD");
        else {
            System.out.println(maxResult);
            System.out.println(cnt);
        }
    }

}
