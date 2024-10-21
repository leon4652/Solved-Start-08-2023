import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            System.out.println(comb(m, n)); 
        }
    }

    static int comb(int m, int n) {
        if (n == 0 || n == m) return 1;  
        int res = 1;
        for (int i = 0; i < n; i++) {
            res *= (m - i);  // 분자 
            res /= (i + 1);  // 분모
        }
        return res;  
    }
}