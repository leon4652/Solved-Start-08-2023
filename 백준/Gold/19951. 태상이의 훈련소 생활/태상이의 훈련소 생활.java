import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_VALUE = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<Integer> heights = new ArrayList<>();

        while (st.hasMoreElements()) {
            heights.add(Integer.parseInt(st.nextToken()));
        }

        int[] work = new int[heights.size()];

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for (int i = s; i < e; i++) {
                work[i] += k;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < heights.size(); i++) {
            int res = work[i] + heights.get(i);
            bw.write(res + " ");
        }

        bw.close();

    }

}
