import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static final int INF = Integer.MAX_VALUE;
    static int N, E;
    static int V1, V2; //방문해야 하는 노드
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    
    static class Node implements Comparable<Node> {
    	int vertex, dist;
    	
    	public Node(int vertex, int dist) {
    		this.vertex = vertex;
    		this.dist = dist;
    	}
    	
    	@Override
    	public int compareTo(Node n) {
    		return this.dist - n.dist;
    	}
    }
    
    public static void main(String[] args) throws IOException {
    	
    	setting(); //입력받기

    	int[] firstDist = dijkstra(0); //0번째 인덱스부터 순회
        int[] distV1 = dijkstra(V1); //V1부터 연결된 모든 노드까지의 최단거리
        int[] distV2 = dijkstra(V2); //V2부터 연결된 모든 노드까지의 최단거리
        
        //최단거리 탐색
        long path1 = checkOverflow(firstDist[V1], distV1[V2], distV2[N - 1]); //시작 -> V1 -> v2 -> N
        long path2 = checkOverflow(firstDist[V2], distV2[V1], distV1[N - 1]); //시작 -> V2 -> V1 -> N
        long result = Math.min(path1, path2);
        
        if(result == INF) System.out.println(-1);
        else System.out.println(result);
    }
    
    static int checkOverflow(int a, int b, int c) {
    	int res = a + b + c;
    	if(a == INF || b == INF || c == INF || res < 0) return INF;
    	else return res;
    }
    
    static int[] dijkstra(int startNum) {
    	int dist[] = new int[N];
    	Arrays.fill(dist, INF);
    	dist[startNum] = 0; //시작 위치
    	PriorityQueue<Node> pq = new PriorityQueue<>(); //최단 거리 큐
    	
    	pq.add(new Node(startNum, dist[startNum])); //해당 위치에서 시작(시작 번호, 지금까지 dist)
    	
    	while(!pq.isEmpty()) {
            Node current = pq.poll(); //현재 노드
            int vertex = current.vertex; //이어진 노드(리스트를 불러오는 인덱스)
            
            if (dist[vertex] < current.dist) continue; //최단거리 넘어갈 경우 생략
            
            //이어진 간선들을 전부 탐색
            for (Node neighbor : graph.get(vertex)) {
                if (dist[neighbor.vertex] > dist[vertex] + neighbor.dist) {
                    dist[neighbor.vertex] = dist[vertex] + neighbor.dist;

                    // 해당 방향으로 새로운 노드 추가
                    pq.offer(new Node(neighbor.vertex, dist[neighbor.vertex]));
                }
            }
    	}
    	
    	return dist;
    }
    
    static void setting() throws IOException {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	E = Integer.parseInt(st.nextToken());
    	
    	for(int i = 0; i < N; i++) {
    		graph.add(new ArrayList<Node>()); //그래프 추가
    	}
    	
    	for(int i = 0; i < E; i++) {
    		st = new StringTokenizer(br.readLine());
    		int start = Integer.parseInt(st.nextToken()) - 1;
    		int end = Integer.parseInt(st.nextToken()) - 1;
    		int dist = Integer.parseInt(st.nextToken());
    		graph.get(start).add(new Node(end, dist)); //시작 노드 추가
    		graph.get(end).add(new Node(start, dist)); //끝 노드 추가
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	V1 = Integer.parseInt(st.nextToken()) - 1;
    	V2 = Integer.parseInt(st.nextToken()) - 1;
    }
    
}
