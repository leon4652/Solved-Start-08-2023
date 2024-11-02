import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int[] input = new int[n];
            for (int i = 0; i < n; i++) {
                input[i] = Integer.parseInt(st.nextToken());
            }
            solve(n, input);
        }
    }

    //후열 순회
    static void solve(int n, int[] input) {
        long earn = 0;

        int lastPrice = input[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            int nowPrice = input[i];
            if(nowPrice < lastPrice) earn += (lastPrice - nowPrice);
            else if(nowPrice > lastPrice) lastPrice = nowPrice;
        }
        System.out.println(earn);
    }

}