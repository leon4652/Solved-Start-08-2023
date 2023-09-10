import java.util.*;
import java.io.*;

class Solution {
    static int result = 0; //결과
	static int[][] dungeons; //입력값 전역변수화
	static boolean visit[];  //방문 체크
    
	public static int solution(int k, int[][] localDungeons) {
		
		dungeons = Arrays.copyOf(localDungeons, localDungeons.length); 	 //전역변수로 얕은복사
		visit = new boolean[dungeons.length];
	
		
		perm(0, k);
		System.out.println(result);

		return result;
	}
    
    
	static void perm(int cnt, int perodo) {
		//피로도가 0이거나, 미만일 경우 지금까지 클리어한 던전 계산
		if(perodo <= 0 || cnt == dungeons.length) {
			if(perodo < 0) result = Math.max(result, cnt - 1); //마이너스라면 이전 계산값 빼버림(-1)
			else result = Math.max(result, cnt); //아니라면 여기까지 계산
			return;
		}
		
		for(int i = 0; i < dungeons.length; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			
			if(dungeons[i][0] > perodo) perm(cnt + 1, -1); //필요 피로도가 부족할 경우 끝(마이너스 처리)
			else perm(cnt + 1, perodo - dungeons[i][1]); //충분할 경우 지금 던전을 돈다.
			
			visit[i] = false;
		}
	}
}