

import java.io.*;
import java.util.*;

public class Main {
    static long N, M;
    static int result = -1;
    public static void main(String[] args) throws Exception {
        input();
        if(N == M) {
            System.out.println(1);
            return;
        }

        PriorityQueue<Long[]> pq = new PriorityQueue<>((l1, l2) -> -l1[0].compareTo(l2[0]));
        pq.add(new Long[] {(N * 2), 2L});
        pq.add(new Long[] {(N * 10 + 1), 2L});
        while(!pq.isEmpty()) {
            Long[] now = pq.poll();
            long value = now[0];
            int count = (int) (long) now[1];
            if(value == M) {
                result = count;
                break;
            }
            if(value < M) {
                pq.add(new Long[] {(value * 2), (long)(count + 1)});
                pq.add(new Long[] {(value * 10 + 1), (long)(count + 1)});
            }
        }

        if(result != -1) System.out.println(result);
        else System.out.println(-1);
}

static void input() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Long.parseLong(st.nextToken());
    M = Long.parseLong(st.nextToken());
}
}
