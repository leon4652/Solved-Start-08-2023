//bandage는 [시전 시간, 초당 회복량, 추가 회복량] 형태의 길이가 3인 정수 배열입니다
//attacks[i]는 [공격 시간, 피해량] 형태의 길이가 2인 정수 배열입니다.

//기술을 쓰는 도중 몬스터에게 공격을 당하면 기술이 취소되고, 공격을 당하는 순간에는 체력을 회복할 수 없습니다. 몬스터에게 공격당해 기술이 취소당하거나 기술이 끝나면 그 즉시 붕대 감기를 다시 사용하며, 연속 성공 시간이 0으로 초기화됩니다.

//공격 x일 경우 시전 시간 증가

import java.util.*;

class Solution {
    static int T;
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        //1. 마지막 시간 알기
        T = attacks[attacks.length - 1][0];
        int nowHealth = health; //현재 체력
        int atkIdx = 0; //공격 인덱스
        int combo = 0; //연속 시전
        
        //T초까지 반복
        for(int i = 0; i <= T; i++) {
            //공격받을 경우
            if(attacks[atkIdx][0] == i) {
                nowHealth -= attacks[atkIdx][1]; 
                if(nowHealth <= 0) return -1; //게임 오버
                combo = 0; //콤보 초기화
                
                if(atkIdx != (attacks.length - 1)) atkIdx++; //다음 인덱스 확인 (최대 인덱스 넘김 방지)
            } else
            {
                //회복의 경우
                nowHealth += bandage[1];
                combo++;
                if(combo == bandage[0]) {
                    combo = 0;
                    nowHealth += bandage[2];
                }
                
                //최대 크기 방지
                nowHealth = Math.min(nowHealth, health);
            }
        }
        
        //2. 시간대별 정보 확인
        return nowHealth;
    }
}