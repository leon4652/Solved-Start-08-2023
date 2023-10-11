package programmers.lv2;


import java.util.Arrays;
import java.util.Collections;

public class 구명보트2 {


    public static void main(String[] args) {
        int[] input = new int[] {70, 80, 50};
        System.out.println(solution(input, 100));
    }

    public static int solution(int[] people, int limit) {
        int boat = 0;

        //1. 정렬 및 탈출 확인용 배열 생성
        Arrays.sort(people);
        int len = people.length;
        boolean[] isExited = new boolean[len];
        for (int i = 0; i < len; i++) {
            isExited[i] = false;
        }

        //2. 최대크기부터 탈출 진행
        for (int i = len - 1; i >= 0; i--) {
            if(isExited[i]) continue; //탑승 고객 생략
            checkTakeBoatPeople(people, isExited, limit, i);
            boat++;
        }

        return boat;
    }

    private static void checkTakeBoatPeople(int[] people, boolean[] isExited, int limit, int idx) {
        int len = people.length;
        int remain = limit - people[idx]; //보트 무게 잔여분
        isExited[idx] = true;
        if(remain < people[0]) return; //어차피 승객을 태울 수 없는 경우

        for (int i = idx - 1; i >= 0; i--) {
            if(isExited[i]) continue; //탑승 고객 생략
            if(remain >= people[i]) { //잔여석에 탈 수 있는 가장 무거운 사람 확인
                isExited[i] = true;
                break;
            }
        }
    }


}
