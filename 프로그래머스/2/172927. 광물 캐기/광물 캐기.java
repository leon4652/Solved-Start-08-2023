
/*

picks = [dia, iron, stone] 순서, 각각 곡괭이의 개수를 의미, 각 곡괭이는 5개의 광물을 캐면 사용 불가
minerals = 광물의 순서
*/

import java.util.*;
class Solution {
    static int maxIdx;
    static int minRes = Integer.MAX_VALUE; //결과
    static int[][] chart = 
    {
        {1, 1, 1}, //다이아 곡괭이 - 다이아, 철, 돌
        {5, 1, 1}, //철 곡괭이 - 다이아, 철, 돌
        {25, 5, 1}, //돌 곡괭이 - 다이아, 철, 돌
    };
    public int solution(int[] picks, String[] minerals) {
        maxIdx = minerals.length - 1; //가능한 최대 인덱스
        
        solve(picks[0], picks[1], picks[2], minerals, 0, 0, 0); //다이아 곡괭이 사용
        solve(picks[0], picks[1], picks[2], minerals, 0, 0, 1); //철 곡괭이 사용
        solve(picks[0], picks[1], picks[2], minerals, 0, 0, 2); //돌 곡괭이 사용
        return minRes;
    }
    
    
    public void solve(int diaPix, int ironPix, int stonePix, String minerals[], int fatigue, int idx, int nowUse) {
        
        if(fatigue >= minRes) return; //최대 피로도 넘김
        if(diaPix == 0 && ironPix == 0 && stonePix == 0) { //모든 곡괭이 사용
            minRes = Math.min(minRes, fatigue); //정산
            System.out.println("정산 : " + fatigue);
            return; 
        }
        if(nowUse == 0 && diaPix == 0 || nowUse == 1 && ironPix == 0 || nowUse == 2 && stonePix == 0) {
            return; //해당 곡괭이 사용 불가
        }


        //곡괭이 소모
        if(nowUse == 0) diaPix--;
        else if(nowUse == 1) ironPix--;
        else stonePix--;
        
        int count = 5; //한 곡괭이에 최대 5번 사용
        while(count-- > 0) {
            
            //광물 번호로 치환
            int mineralNum; 
            if(minerals[idx].equals("diamond")) mineralNum = 0;
            else if(minerals[idx].equals("iron")) mineralNum = 1;
            else mineralNum = 2;
            
            //자원 캐기(피로도 증가)
            fatigue += chart[nowUse][mineralNum];
            
            //인덱스 증가 및 전부 캤는지 확인
            idx++;
            if(idx > maxIdx) {
                minRes = Math.min(minRes, fatigue);
                return;
            }
        }

        //다음 재귀 시행
        solve(diaPix, ironPix, stonePix, minerals, fatigue, idx, 0);
        solve(diaPix, ironPix, stonePix, minerals, fatigue, idx, 1);
        solve(diaPix, ironPix, stonePix, minerals, fatigue, idx, 2);

        
    }
}