import java.util.*;
class Solution {
    static int answer = -1;
    public int solution(int[] mats, String[][] park) {
        int r = park.length;
        int c = park[0].length;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(park[i][j].equals("-1")) {
                    for(int k = 0; k < mats.length; k++) check(i, j, park, mats[k], r, c);
                }
            }
        }
        
        return answer;
    }
    
    public void check(int r, int c, String[][] park, int l, int mr, int mc) {
        for(int i = r; i < r + l; i++) {
            for(int j = c; j < c + l; j++) {
                if(i >= mr || j >= mc || !park[i][j].equals("-1")) return;
            }
        }
        answer = Math.max(l, answer);
    }
}