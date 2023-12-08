import java.util.*;
import java.io.*;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int M;
    static HashSet<Integer> hs = new HashSet<>();
    public static void main(String[] args) throws Exception {
    	M = Integer.parseInt(br.readLine());
    	while(M-- > 0) {
    		solve(br.readLine());
    	}
    	
    	bw.close();
	}
    
    static void solve(String input) throws Exception {
		StringTokenizer st = new StringTokenizer(input);
		String command = st.nextToken();
		int value = 0;
		if(st.hasMoreTokens()) value = Integer.parseInt(st.nextToken());
		
    	if(command.equals("add")) hs.add(value);
    	else if(command.equals("remove")) hs.remove(value);
    	else if(command.equals("check")) {
    		if(hs.contains(value)) bw.write("1\n");
    		else bw.write("0\n");
    	}
    	else if(command.equals("toggle")) {
    		if(hs.contains(value)) hs.remove(value);
    		else hs.add(value);
    	}
    	else if(command.equals("all")) {
    		hs = new HashSet<>();
    		for(int i = 1; i < 21; i++) hs.add(i);
    	}
    	else hs = new HashSet<>();
    }
    
}
