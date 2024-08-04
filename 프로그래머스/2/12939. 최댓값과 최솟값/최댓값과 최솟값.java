import java.util.*;

class Solution {
    public String solution(String s) {
        String[] arrs = s.split(" ");
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for(String arr : arrs) {
            int now = Integer.parseInt(arr);  // 문자열을 정수로 변환
            min = Math.min(now, min);  // 최소값 업데이트
            max = Math.max(now, max);  // 최대값 업데이트
        }
        return min + " " + max;  // 결과 반환
    }
}
