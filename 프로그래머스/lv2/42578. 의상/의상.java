import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> clothesMap = new HashMap<>();

        // 의상 종류별로 갯수를 세어 저장
        for (String[] cloth : clothes) {
            String type = cloth[1];
            clothesMap.put(type, clothesMap.getOrDefault(type, 0) + 1);
        }

        // 각 의상 종류별로 (의상 갯수 + 1)을 곱하여 조합 계산
        for (String key : clothesMap.keySet()) {
            answer *= (clothesMap.get(key) + 1);
        }

        // 모든 의상을 입지 않는 경우를 제외
        answer--;

        return answer;
    }
}
