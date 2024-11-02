import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long res = 1;

        long minusTwo = 1; //arr[n - 2]
        long minusOne = 1; //arr[n - 1]
        for (int i = 2; i < n; i++) {
            res = minusOne + minusTwo; //arr[n]
            minusTwo = minusOne;
            minusOne = res;
        }
        System.out.println(res);
    }
}