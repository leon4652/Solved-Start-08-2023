package programmers.lv2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 올바른괄호 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solution(br.readLine()));
    }

    static boolean solution(String s) {
        Stack stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') stack.push(c);
            else {
                if(stack.isEmpty() || (char)stack.peek() == ')') return false;
                stack.pop();
            }
        }

        if(stack.isEmpty()) return true;
        else return false;
    }

}
