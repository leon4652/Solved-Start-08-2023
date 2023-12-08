import java.util.*;
import java.io.*;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int K, N, arr[];
    public static void main(String[] args) throws Exception {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	K = Integer.parseInt(st.nextToken());
    	N = Integer.parseInt(st.nextToken());
    	arr = new int[K];
    	for(int i = 0; i < K; i++) arr[i] = Integer.parseInt(br.readLine());
    	
    	binarySearch(1, Integer.MAX_VALUE);
    }
    
    static void binarySearch(long low, long high) {
    	while(low <= high) {
    		long mid = low + (high - low) / 2;
    		
    		int sum = 0;
    		for(int i = 0; i < K; i++) {
    			sum += (arr[i] / mid);
    		}
    		
    		if(sum < N) { //개수를 늘려야(mid를 줄여야) 함
    			high = mid - 1;
    		} else { //가능하니 mid를 늘려야 함
    			low = mid + 1;
    		}
    	}
    	
    	System.out.println(high);
    }

} //EOC
