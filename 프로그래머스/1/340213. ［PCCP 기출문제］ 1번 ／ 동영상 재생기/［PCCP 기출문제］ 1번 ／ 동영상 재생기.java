import java.io.*;
import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands)     {
        int posTime = getTime(pos);
        int opStart = getTime(op_start);
        int opEnd = getTime(op_end);
        int videoLen = getTime(video_len);
        
        for(int i = 0; i < commands.length; i++) {
            //오프닝 건너뛰기
            if(opStart <= posTime && posTime < opEnd) posTime = opEnd;
            
            if(commands[i].startsWith("n")) { //next
                posTime = Math.min(posTime + 10, videoLen);
            } else { //prev
                posTime = Math.max(posTime - 10, 0);
            }
            //오프닝 건너뛰기
            if(opStart <= posTime && posTime < opEnd) posTime = opEnd;
        }
        
        
        
        String answer = getStrTime(posTime);
        return answer;
    }
    
    
    public int getTime(String str) {
        String[] timeStr = str.split(":");
        int res = Integer.parseInt(timeStr[0]) * 60 + Integer.parseInt(timeStr[1]);
        return res;
    }
    
    public String getStrTime(int intTime) {
        int h = intTime / 60;
        int m = intTime % 60;
        
        String hour = String.valueOf(h);
        if(hour.length() == 1) hour = "0" + hour;
        String minute = String.valueOf(m);
        if(minute.length() == 1) minute = "0" + minute;
        
        return hour + ":" + minute;
    }
    
}