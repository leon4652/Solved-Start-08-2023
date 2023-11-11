import java.io.*;
import java.util.*;


class Main {
  static int N, M;
  static List<List<Integer>> networks = new ArrayList<List<Integer>>();
  static HashSet<Integer> checked = new HashSet<>();
  public static void main(String[] args) throws Exception{
    //입력
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());
    
    for(int i = 0; i < N; i++) networks.add(new ArrayList<>());
    for(int i = 0; i < M; i++) {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken()) - 1;
    	int m = Integer.parseInt(st.nextToken()) - 1;
    	networks.get(n).add(m);
    	networks.get(m).add(n);
    }
    
    check(0);
    
    System.out.println(checked.size() - 1);
  }

  static void check(int idx) {
	  if(checked.contains(idx)) return;
	  checked.add(idx);
	  
	  List<Integer> connected = networks.get(idx);
	  for(int i = 0; i < connected.size(); i++) {
		  check(connected.get(i));
	  }
  }
  
}