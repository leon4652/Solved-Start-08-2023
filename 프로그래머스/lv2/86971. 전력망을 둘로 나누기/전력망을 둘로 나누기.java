import java.util.*;

//그래프 구성 후 BFS 사용하여 개수 파악

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = n;
        
        //모든 인덱스 경우의 수 탐색
        for (int idx = 0; idx < n - 1; idx++) {
            List<List<Integer>> songJun = new ArrayList<>();
            
            for(int i = 0; i <= n; i++) songJun.add(new ArrayList<>());
            
            for(int i = 0; i < wires.length; i++) {
                if(i == idx) continue;
                songJun.get(wires[i][0]).add(wires[i][1]);
                songJun.get(wires[i][1]).add(wires[i][0]);
            }
            
            //BFS 로직 진행
            boolean[] visited = new boolean[n+1];
            Queue<Integer> queue = new LinkedList<>();
            
            queue.add(1);
            visited[1] = true;
            int count = 1;
            
            while(!queue.isEmpty()) {
                int curr = queue.poll();
                for(int next : songJun.get(curr)) {
                    if(!visited[next]) {
                        visited[next] = true;
                        queue.add(next);
                        count++;
                    }
                }
            }
            
            //결과값 계산
            answer = Math.min(answer, Math.abs(n - count * 2));
        }

        return answer;
    }
}