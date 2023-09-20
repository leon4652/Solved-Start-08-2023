import java.util.*;
//Stack에 문자열 저장하고 꺼내며 현재 최고 문자열과 비교
class Solution {
 public static String solution(String number, int k) {
        char[] result = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        // k가 0보다 큰 경우, 남은 k만큼 스택에서 제거
        for (int i = 0; i < k; i++) stack.pop();

        // 스택에 남은 문자를 결과에 저장
        for (int i = 0; i < result.length; i++) result[i] = stack.get(i);

        return new String(result);
    }
}