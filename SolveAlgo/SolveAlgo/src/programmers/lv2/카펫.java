package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 카펫 {
    public static void main(String[] args) {
        int n = 9;
        int[][] wires = new int[][] {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        System.out.println(solution(n, wires));
    }

    private static int solution(int n, int[][] wires) {
        int answer = -1;
        n-= 1;
        int idx = 0;
        while(idx < n) {
            List<int[]> splitTowerL = new ArrayList<>();
            List<int[]> splitTowerR = new ArrayList<>();

            for (int i = 0; i < idx; i++) splitTowerL.add(wires[i]);
            for (int i = idx; i < n; i++) splitTowerR.add(wires[i]);
            
            answer = Math.max(answer, checkDifference(splitTowerL, splitTowerR)); //송전탑 개수 차이값
            idx++;
        }
        return answer;
    }

    private static int checkDifference(List<int[]> splitTowerL, List<int[]> splitTowerR) {
    }


}
