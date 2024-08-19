import java.util.*;
import java.io.*;

/*
https://www.acmicpc.net/problem/2891
1. 자신의 바로 다음이나 전에 경기하는 팀에게만 카약을 대여
2. 다른 팀에게서 받은 카약은 또 다른 팀에게 빌려줄 수 없다
3. 카약을 하나 더 가져온 팀의 카약이 손상되었다면, 여분의 카약으로 경기에 출전 / 이것은 대여 불가
 */

class Main {
    static int N, S, R;
    static Set<Integer> brokenTeams;
    static Set<Integer> redundancyTeams;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) {
        input();
        solve();
    }

    public static void solve() {
        for (Integer num : redundancyTeams) {
            if(brokenTeams.contains(num - 1)) {
                brokenTeams.remove(num - 1);
            } else if (brokenTeams.contains(num + 1)) {
                brokenTeams.remove(num + 1);
            }
        }

        System.out.println(brokenTeams.size());
    }

    public static void input() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            brokenTeams = new HashSet<>();
            redundancyTeams = new TreeSet<>();
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreElements()) brokenTeams.add(Integer.parseInt(st.nextToken()));

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreElements()) {
                int num = Integer.parseInt(st.nextToken());
                if(brokenTeams.contains(num)) brokenTeams.remove(num);
                else redundancyTeams.add(num);
            }

        } catch (Exception e) {}
    }

}
