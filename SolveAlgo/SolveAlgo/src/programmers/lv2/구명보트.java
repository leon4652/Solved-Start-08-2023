package programmers.lv2;

import java.util.Arrays;
import java.util.HashMap;

//https://school.programmers.co.kr/learn/courses/30/lessons/42885

/*
1. 일단 첫번째로 sort를 통해 사람을 오름차순으로 정렬한다.

2. 현재 탈출시키는 가장 가벼운 사람에게 '가장 무거운 사람부터 내림차순으로 매칭'시킨다.
10 20 30 ... 80 90 100 이라고 생각해보자
구명보트의 탈출 순서는 people 인덱스 0부터 가벼운 순대로 체크한다.
만약 limit가 100 근처(+-5)라고 한다면,
10 + 100 X
10 + 90 X
..
10 + 80 (fit) 으로 가장 가벼운 사람을 기준으로 하고, 가장 무거운 순에서 그 다음 가벼운 순으로 인덱스 뒤쪽 내림차순으로 맞춘다.

3. 무거운 사람의 무게가 고정되었을 경우 가벼운 사람 중 '더 무거운 사람'이 탈 수 있는지 판단한다.
10 + 80 (fit)
20 + 80 (fit)
.
.
30 + 80 X
이런 식으로 최대로 무거운 사람을 판단한다.
만약 모든 배열을 순회했을 때 한 명밖에 못 탄다면 해당 인원만 적용한다.

4. 탈출시킨 사람은 추가적으로 boolean 타입 배열을 사용하여 T/F 처리한다.

 */

public class 구명보트 {

    public static void main(String[] args) {
        int[] input = new int[] {70, 80, 50};
        System.out.println(solution(input, 100));
    }

    public static int solution(int[] people, int limit) {
        int answer = 0;
        
        //1. 정렬 및 탈출 확인용 배열 생성
        Arrays.sort(people);
        int len = people.length;
        boolean[] isExited = new boolean[len];
        for (int i = 0; i < len; i++) {
            isExited[i] = false;
        }

        //2. 현재 탈출시키는 가장 가벼운 사람에게 '가장 무거운 사람부터 내림차순으로 매칭'
        for (int i = 0; i < len; i++) {
            if(!isExited[i]) { //가장 가벼운 사람 찾기
                findHeavyForLight(people, isExited, i ,limit);
                answer++; //보트 1대 소모
            }
        }

        System.out.println(Arrays.toString(people));
        return answer;
    }

    private static void findHeavyForLight(int[] people, boolean[] isExited, int lightIdx ,int limit) {
        //보트에 탈 수 있는 가장 무거운 사람을 찾는다.
        int len = people.length;
        int heavyIdx = -1; //가장 무거운 사람의 인덱스

        //가장 무거운 순대로 내려가서 매칭했는데, 제한 조건에 수립된다면 가장 무거운 사람이다.
        for (int i = len - 1; i > lightIdx; i--) {
            if(isExited[i]) continue; //이미 탈출한 사람은 제외
            if(limit >= people[lightIdx] + people[i]) {
                heavyIdx = i; //무거운 사람의 인덱스 갱신
                break;
            }
        }

        //2명을 못 타는 경우 : 무거운 사람이 없다.
        if(heavyIdx == -1) {
            isExited[lightIdx] = true; //탈출
            return;
        }

        //무거운 사람에 맞추어 가벼운 사람을 다시 매칭한다. 현재 lightIdx보다 높다면 이를 갱신한다.
        for (int i = lightIdx + 1; i < heavyIdx; i++) {
            if(isExited[i]) continue; //탈출했다면 스킵
            if(limit < people[i] + people[heavyIdx]) break; //제한사항을 넘긴다면 스킵
            //아니라면 최소 가벼운 사람 갱신
            lightIdx = i;
        }

        //가장 적절한 사람 두명 탈출
        isExited[lightIdx] = true;
        isExited[heavyIdx] = true;
        System.out.println(people[lightIdx] +" " + people[heavyIdx]);
    }
}
