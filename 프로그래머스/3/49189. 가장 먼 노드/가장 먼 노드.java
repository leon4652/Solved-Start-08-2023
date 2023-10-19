import java.util.*;

public class Solution {
    public int solution(int n, int[][] edge) {
        boolean[] visited = new boolean[n+1];
        List<List<Integer>> list = new ArrayList<>();
        
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int[] e : edge) {
            list.get(e[0]).add(e[1]);
            list.get(e[1]).add(e[0]);
        }
        
        // BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        
        int count = 0;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            count = size;
            
            for(int i=0; i<size; i++) {
                int current = queue.poll();
                for(int next : list.get(current)) {
                    if(!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }
        
        return count;
    }
}
