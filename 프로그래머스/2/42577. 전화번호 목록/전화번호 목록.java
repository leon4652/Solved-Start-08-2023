import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        HashSet<String> hs = new HashSet<>(); //전화번호 리스트
        
        //배열 길이별 분류
        Arrays.sort(phone_book, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        
        for(String phone : phone_book) {
            for(int i = 1; i < phone.length(); i++) {
                if(hs.contains(phone.substring(0, i))) {
                    return false;
                }
            }
            hs.add(phone);
        }
        
        return true;
    }
}
