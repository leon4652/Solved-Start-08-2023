import java.util.*;
import java.io.*;

class Solution {
    static int[] time = new int[1440]; 
    public int solution(String[][] book_time) {
        trans(book_time);
        int answer = 0;
        int room = 0;
        for(int i = 0 ; i < 1440; i++) {
            room += time[i];
            answer = Math.max(answer, room);
        }
        
        
        return answer;
    }
    
    void trans(String[][] book_time) {
        for(int i = 0; i < book_time.length; i++) {
            String[] nowBook = book_time[i];
            
            //시작
            String[] startTime = nowBook[0].split(":");
            int startMin = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
            time[startMin] += 1;
            
            //끝
            String[] endTime = nowBook[1].split(":");
            int endMin = Math.min(
                1439, Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]) + 10); 
            time[endMin] -= 1;
        }
    }
}