import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bfs(a, b);
        }
    }

    private static void bfs(int start, int target) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[MAX];
        int[] prev = new int[MAX];
        char[] how = new char[MAX];

        Arrays.fill(prev, -1);

        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            int D = operationD(now);
            int S = operationS(now);
            int L = operationL(now);
            int R = operationR(now);

            processNext(now, D, 'D', visited, q, prev, how);
            processNext(now, S, 'S', visited, q, prev, how);
            processNext(now, L, 'L', visited, q, prev, how);
            processNext(now, R, 'R', visited, q, prev, how);
        }

        StringBuilder sb = new StringBuilder();
        while (target != start) {
            sb.append(how[target]);
            target = prev[target];
        }

        System.out.println(sb.reverse());
    }

    private static int operationD(int n) {
        return (2 * n) % MAX;
    }

    private static int operationS(int n) {
        return n == 0 ? 9999 : n - 1;
    }

    private static int operationL(int n) {
        return (n % 1000) * 10 + n / 1000;
    }

    private static int operationR(int n) {
        return (n % 10) * 1000 + n / 10;
    }

    private static void processNext(int now, int next, char op, boolean[] visited, Queue<Integer> q, int[] prev, char[] how) {
        if (!visited[next]) {
            q.add(next);
            visited[next] = true;
            prev[next] = now;
            how[next] = op;
        }
    }
}
