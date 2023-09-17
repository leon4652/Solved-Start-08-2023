

class Solution {

    public static int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }

    public static int dfs(int[] numbers, int target, int idx, int sum) {
        if (idx == numbers.length) {
            return sum == target ? 1 : 0;
        } else {
            return dfs(numbers, target, idx + 1, sum + numbers[idx])
                    + dfs(numbers, target, idx + 1, sum - numbers[idx]);
        }
    }
}