import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        //target 오름차순 정렬
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));

        int result = 0; 
        double lastEnd = -1.0; //마지막 요격 위치

        for (int[] target : targets) {
            // 현재 구간의 시작점이 이전에 요격한 미사일의 위치보다 뒤에 있는 경우
            if (target[0] > lastEnd) {
                lastEnd = target[1] - 0.1; // 끝점 바로 앞에서 요격
                result++; 
            }
        }

        return result;
    }
}
