import java.util.*;
import java.io.*;

class Solution {
    static Map<String, Integer> nameMap = new HashMap<>();
    static Map<Integer, String> keyMap = new HashMap<>();
    public String[] solution(String[] players, String[] callings) {
        for(int i = 0; i < players.length; i++) {
            keyMap.put(i, players[i]);
            nameMap.put(players[i], i);
        }
        
        for(int i = 0; i < callings.length; i++) { 
            //역전하는 사람
            String callName = callings[i];
            int rank = nameMap.get(callName);
            nameMap.put(callName, rank - 1); //역전
            
            //역전당하는 사람
            String loseName = keyMap.get(rank - 1);
            nameMap.put(loseName, rank); // -1등
            
            //keyMap Put
            keyMap.put(rank - 1, callName);
            keyMap.put(rank, loseName);
        }
        
        String[] answer = new String[players.length];
        
        for(int key : keyMap.keySet()) {
            answer[key] = keyMap.get(key);
        }
        
        return answer;
    }
}