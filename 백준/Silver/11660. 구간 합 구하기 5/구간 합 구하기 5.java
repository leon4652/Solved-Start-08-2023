import java.util.*;
import java.io.*;

class Main {
    static int M, N;
    static int[][] map;
    static int[][] lists;
    static int[][] sumArr; //누적합
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        input();
        scan(); //누적합
        for (int i = 0; i < M; i++) {
            solve(i);
        }
        bw.close();
    }

    public static void scan() {
        sumArr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if (j == 0) sumArr[i][j] = map[i][j];
                else sumArr[i][j] = map[i][j] + sumArr[i][j - 1];
            }
        }
    }

    public static void solve(int m) throws IOException {
        int[] list = lists[m];

        int x1 = list[0] - 1;
        int y1 = list[1] - 1;
        int x2 = list[2] - 1;
        int y2 = list[3] - 1;

        int sum = 0;
        for (int i = x1; i <= x2; i++) {
            if (y1 == 0) sum += sumArr[i][y2];
            else sum += sumArr[i][y2] - sumArr[i][y1 - 1]; //해당 줄의 누적합을 사용한 전체 합 구하기
        }
        bw.write(sum+"\n");
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            lists = new int[M][4];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                lists[i][0] = Integer.parseInt(st.nextToken());
                lists[i][1] = Integer.parseInt(st.nextToken());
                lists[i][2] = Integer.parseInt(st.nextToken());
                lists[i][3] = Integer.parseInt(st.nextToken());
            }
        } catch (Exception e) {

        }
    }


}
