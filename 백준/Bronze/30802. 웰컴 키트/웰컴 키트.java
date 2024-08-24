import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N, T, P;
    static List<Integer> people = new ArrayList<>();

    public static void main(String[] args) {
        input();
        int tNum = 0;
        for (Integer p : people) {
            if(p == 0) continue;
            tNum += (p / T);
            if(p % T != 0) tNum += 1;
        }
        System.out.println(tNum);
        System.out.println((N / P) + " " + (N % P));
    }

    static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreElements()) people.add(Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
        } catch (Exception e) {}
    }
}