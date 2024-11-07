import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_VALUE = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<Integer> temp = new ArrayList<>();

        while (st.hasMoreElements()) {
            temp.add(Integer.parseInt(st.nextToken()));
        }

        int max = 0;
        for (int i = 0; i < k; i++) max += temp.get(i);
        int now = max;

               int idx = 0;
        for (int i = k; i < n; i++) {
            int last = temp.get(idx++);
            int next = temp.get(i);
            now += next;
            now -= last;
            max = Math.max(now, max);
        }
        System.out.println(max);

    }

}
