import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

class Main {
    static int N, M, X;
    static int[] numbers;
    static int[] inputs;
    static boolean[] visits;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static List<List<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M];
        inputs = new int[N];
        visits = new boolean[N];
        for(int i = 0; i < N; i++) inputs[i] = i + 1;
//        comp(0, 0);
//        input();
        perm(0);
        bw.close();
    }

    static void perm(int cnt) throws IOException {
        if(cnt == M) {
            bw.write(numbers[0] + "");
            for(int i = 1; i < M; i++) bw.write(" " + numbers[i]);
            bw.write("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            numbers[cnt] = inputs[i];
            perm(cnt + 1);
        }
    }

    static void comp(int start, int cnt) throws IOException {
        if(cnt == M) {
            bw.write(numbers[0] + "");
            for(int i = 1; i < M; i++) bw.write(" " + numbers[i]);
            bw.write("\n");
            return;
        }

        for(int i = start; i < N; i++) {
            numbers[cnt] = inputs[i];
            comp(i + 1, cnt + 1);
        }
    }
    static void solve() {
        //최대값 저장

    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++) graph.add(new ArrayList<Node>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, cost));
        }
    }
}

class Node implements Comparable<Node> {
    int edge;
    int cost;

    public Node(int edge, int cost) {
        this.edge = edge;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node n) {
        return this.cost - n.cost;
    }
}

