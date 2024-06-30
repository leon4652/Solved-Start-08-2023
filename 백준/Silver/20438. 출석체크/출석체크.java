import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<Integer> sleepingStudents = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            sleepingStudents.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> codeReceivers = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            codeReceivers.add(Integer.parseInt(st.nextToken()));
        }

        boolean[] attended = new boolean[N + 3]; // 학생들의 출석 상태를 저장하는 배열

        for (int receiver : codeReceivers) {
            if (sleepingStudents.contains(receiver)) {
                continue; // 졸고 있는 학생이면 패스
            }
            for (int j = receiver; j <= N + 2; j += receiver) {
                if (!sleepingStudents.contains(j)) {
                    attended[j] = true; // 출석 상태 업데이트
                }
            }
        }

        // 누적 합 계산
        int[] prefixSum = new int[N + 3];
        for (int i = 3; i <= N + 2; i++) {
            prefixSum[i] = prefixSum[i - 1] + (attended[i] ? 1 : 0);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int notAttendedCount = (E - S + 1) - (prefixSum[E] - prefixSum[S - 1]);
            sb.append(notAttendedCount).append("\n");
        }

        // 결과 출력
        System.out.print(sb.toString());
    }
}
