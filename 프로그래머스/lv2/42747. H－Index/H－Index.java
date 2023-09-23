import java.util.*;

/*
arr n개를 정렬 후 안되는 수 - 되는 경우의 수 함
*/

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int answer = 0;
        
        int len = citations.length;
        for (int i = 0; i < len; i++) {
            if(citations[i] >= len - i) {
                answer = len - i;
                break;
            }
        }
        return answer;
    }
}