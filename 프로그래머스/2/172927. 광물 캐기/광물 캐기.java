import java.io.*;
import java.util.*;
class Solution {
    static int[] minArr;
    static int answer = Integer.MAX_VALUE;
    static int[][] breaking = {
        {1, 1, 1}, //dia
        {5, 1, 1},
        {25, 5, 1}
    };
    
    public int solution(int[] picks, String[] minerals) {
        minArr = new int[minerals.length];
        for(int i = 0 ; i < minerals.length; i++) {
            if(minerals[i].startsWith("d")) minArr[i] = 0;
            else if(minerals[i].startsWith("i")) minArr[i] = 1;
            else if(minerals[i].startsWith("s")) minArr[i] = 2;
            else throw new RuntimeException("str -> int fail");
        }
        
        backTracking(0, 0, 0, 0, picks[0], picks[1], picks[2]);
        return answer;
    }
    
    public void backTracking(int cnt, int idx, int nowUseType, int pixRemain, int diaPix, int ironPix, int stonePix) {
        
        //캘 수 있을 경우
        while(pixRemain > 0 && idx < minArr.length) {
            int minType = minArr[idx]; //광물
            cnt += breaking[nowUseType][minType];
            pixRemain--; //내구도 감소
            idx++; //다음 광물
        }
        
        
        //다음 곡괭이 사용
        if(diaPix > 0) backTracking(cnt, idx, 0, 5, diaPix - 1, ironPix, stonePix);    
        if(ironPix > 0) backTracking(cnt, idx, 1, 5, diaPix, ironPix - 1, stonePix);
        if(stonePix > 0) backTracking(cnt, idx, 2, 5, diaPix, ironPix, stonePix - 1);
        
        //전부 캤거나 전부 소모
        if(idx >= minArr.length || (diaPix == 0 && ironPix == 0 && stonePix == 0 && pixRemain == 0)) { 
            
            answer = Math.min(cnt, answer);
            return;
        }        
    }
}