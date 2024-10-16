import java.util.*;
import java.io.*;
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //수빈이 위치
        int k = Integer.parseInt(st.nextToken()); //목표

        //메모리 초과 해결 위함 : 중복된 좌표를 동일 반복 가능하기에 체크 표시 필요
        boolean[] visited = new boolean[100000 + 1];

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.add(new Pos(n, 0)); //현재 수빈이 위치 기준



        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            if(visited[now.x]) continue;
            visited[now.x] = true;

            if(now.x == k) { //목표 달성
                System.out.println(now.time);
                return;
            }

            if(now.x > 0 && !visited[now.x - 1]) {
                pq.add(new Pos(now.x - 1, now.time + 1));
            }
            if(now.x < 100000 && !visited[now.x + 1]) {
                pq.add(new Pos(now.x + 1, now.time + 1));
            }
            if(now.x * 2 <= 100000 && now.x > 0 && !visited[now.x * 2]) {
                pq.add(new Pos(now.x * 2, now.time));
            }
        }

    }
}
class Pos implements Comparable<Pos> {
    int x; //위치
    int time; //걸린 시간

    public Pos(int x, int time) {
        this.x = x;
        this.time = time;
    }

    @Override
    public int compareTo(Pos p) {
        return Integer.compare(this.time, p.time); //시간 적은 순서대로
    }
}
