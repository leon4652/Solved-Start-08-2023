import java.util.*;

class Solution {
    static HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
    
    public int solution(String word) {
        String str = "AEIOU";
        
        hm.put('A', 0);
        hm.put('E', 1);
        hm.put('I', 2);
        hm.put('O', 3);
        hm.put('U', 4);
        
        int[] x = {781, 156, 31, 6, 1};
        
        int res = word.length();
        int val = 0;

        for (int i = 0; i < word.length(); i++) {
            val = hm.get(word.charAt(i));
            res += x[i] * val;
        }
        
        return res;
    }
}
