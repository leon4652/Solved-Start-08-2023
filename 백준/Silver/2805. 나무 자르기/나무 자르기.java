import java.util.*;
import java.io.*;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, R, tree[];
    public static void main(String[] args) throws Exception {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	tree = new int[N];
    	st = new StringTokenizer(br.readLine());
    	
    	int max = 0;
    	for(int i = 0; i < N; i++) {
    		tree[i] = Integer.parseInt(st.nextToken());
    		max = Math.max(max, tree[i]);
    	}
    	
    	
        System.out.println(solve(0, max));
    }

    static long solve(int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2;
            long sum = 0;

            for (int i = 0; i < tree.length; i++) {
                if (tree[i] > mid) {
                    sum += tree[i] - mid;
                }
            }

            if (sum >= M) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }
} //EOC
