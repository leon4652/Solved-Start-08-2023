import java.util.Arrays;

class Solution {
public static int solution(int[] people, int limit) {
        int boat = 0;
        int left = 0;
        int right = people.length - 1;

        Arrays.sort(people);

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            boat++;
        }

        return boat;
    }
}