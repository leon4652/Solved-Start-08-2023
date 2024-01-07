import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
    	int res = 0;
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken()); //접시
    	int d = Integer.parseInt(st.nextToken()); //초밥수
    	int k = Integer.parseInt(st.nextToken()); //연속접시
    	int c = Integer.parseInt(st.nextToken()); //쿠폰번호
    	
    	int dish[] = new int[N];
    	for(int i = 0; i < N; i++) dish[i] = Integer.parseInt(br.readLine());
    	
    	
    	//i번 index부터 시작
    	HashMap<Integer, Integer> takes = new HashMap<>();
    	for(int i = 0; i < k; i++) {
    		int now = dish[i];
    		if(takes.containsKey(now)) takes.put(now, takes.get(now) + 1);
    		else takes.put(now, 1); 
    	}
    	res = takes.size(); 
    	if(!takes.containsKey(c)) res++;
    	
    	
    	for(int i = 0; i < N + k; i++) {
    		int last = dish[i % N]; //이전 접시
    		int now = dish[(i + k) % N]; //새로운 접시
    		
    		//제거
    		if(takes.containsKey(last)) {
    			if(takes.get(last) == 1) takes.remove(last);
    			else takes.put(last, takes.get(last) - 1);
    		}
    		
    		//추가
    		if(takes.containsKey(now)) takes.put(now, takes.get(now) + 1);
    		else takes.put(now, 1);

    		int nowRes = takes.size();
    		if(!takes.containsKey(c)) nowRes++; //쿠폰번호
    		
    		res = Math.max(res, nowRes);
    	}
    	
    	System.out.println(res);
    }
}
