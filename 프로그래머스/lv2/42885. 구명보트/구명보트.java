import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
       int boat = 0;
        int left = 0;
        int right = people.length - 1;

        Arrays.sort(people);

        while (left <= right) {
						//가장 가벼운 승객을 탈출시킬 수 있다면 개수 증가
            if (people[left] + people[right] <= limit) {
                left++;
            }
						//매 반복문마다 보트 + 1, 가장 무거운 사람은 -1 되게 되어있음.
						//근데 여기에 가벼운 사람을 태울 수 있다면(위의 if문) 같이 태움
            right--;
            boat++;
        }

        return boat;
    }
}