import java.util.*;
class Solution {
    static Map<String, Integer> pointMap = new HashMap<>();
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int len = name.length;
        for(int i = 0; i < len; i++) {
            pointMap.put(name[i], yearning[i]);
        }
         
        int[] answer = new int[photo.length];
        
        for(int i = 0; i < photo.length; i++) {
            int res = 0;
            String[] nowPhoto = photo[i];
            for(int j = 0; j < nowPhoto.length; j++) {
                if(!pointMap.containsKey(nowPhoto[j])) continue;
                res += pointMap.get(nowPhoto[j]);
            }
            
            answer[i] = res;
        }
        return answer;
    }
}