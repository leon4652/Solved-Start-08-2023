import java.util.*;
import java.io.*;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    public static void main(String[] args) throws Exception {
    	HashMap<Integer, Integer> hm = new HashMap<>(); 
    	
    	N = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	while(st.hasMoreTokens()) {
    		int now = Integer.parseInt(st.nextToken());
    		if(hm.containsKey(now)) hm.put(now, hm.get(now) + 1);
    		else hm.put(now, 1);
    	}
    	
    	
    	M = Integer.parseInt(br.readLine());
    	st = new StringTokenizer(br.readLine());
    	while(st.hasMoreTokens()) {
    		int now = Integer.parseInt(st.nextToken());
    		
    		if(hm.containsKey(now)) bw.write(hm.get(now) + " ");
    		else bw.write("0 ");
    	}
    	
    	bw.close();
    }

} //EOC
