import java.util.*;
class Solution {
    static int res = 0;
    static HashSet<Integer> hs = new HashSet<>();
    static int[] numbers, inputs;
    static boolean[] visit;
    public int solution(String word) {

        for(int i = 1; i <= word.length(); i++) {
            solve(i, word);
        }
        return res;
    }
    
    //세팅
    public void solve(int num, String word) {
        numbers = new int[num];
        inputs = new int[word.length()];
        visit = new boolean[word.length()];
        
        for(int i = 0; i < inputs.length; i++) {
            inputs[i] = word.charAt(i) - '0';
        }
        perm(num, 0);
    }
    
    //순열 만들기
    static void perm(int max, int cnt) {
        if(cnt == max) {
            //순열 숫자화 하기
            int result = 0;
            for(int i = 0; i < numbers.length; i++) {
                result += numbers[i] * Math.pow(10, numbers.length - i - 1);
            }
            //소수가 아니거나, 이미 있다면 스킵
            if(!isPrime(result) || hs.contains(result)) return;
            
            hs.add(result);
            res++;
            // System.out.println("res :  " + result);
            return;
        }
        
        for(int i = 0; i < inputs.length; i++) {
            if(visit[i]) continue;
            visit[i] = true;
            numbers[cnt] = inputs[i];
            perm(max, cnt + 1);
            visit[i] = false;
        }
    }
    
    static boolean isPrime(int num) {
        if(num == 0 || num == 1 || num == 4) return false; //4 이하 
        for(int i = 2; i < num; i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }
}