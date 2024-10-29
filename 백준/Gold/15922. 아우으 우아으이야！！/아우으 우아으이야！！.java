import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_VALUE = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 이벤트 리스트 생성 (시작과 끝을 각각 기록)
        List<int[]> events = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = MAX_VALUE + Integer.parseInt(st.nextToken());
            int e = MAX_VALUE + Integer.parseInt(st.nextToken());

            // 시작점은 +1로, 끝점은 -1로 기록
            events.add(new int[]{s, 1});
            events.add(new int[]{e, -1});
        }

        // 정렬: 좌표 기준으로 오름차순, 같은 좌표일 경우 끝점(-1)을 우선
        Collections.sort(events, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return a[0] - b[0]; // 좌표 기준 정렬
                }
                return a[1] - b[1]; // 동일 좌표일 경우, 끝점 우선
            }
        });

        int res = 0;
        int activeSegments = 0;
        int lastX = -1;

        // 스위핑 시작
        for (int[] event : events) {
            int x = event[0];
            int type = event[1];

            // 선분이 활성화된 동안 길이를 누적
            if (activeSegments > 0 && lastX != -1) {
                res += x - lastX;
            }

            // 현재 이벤트를 반영하여 activeSegments 조정
            activeSegments += type;
            lastX = x;
        }

        System.out.println(res);
    }
}
