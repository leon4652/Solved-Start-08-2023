//새로운 과제 시작 시간 있다면 진행중이던거 멈춤
//과제 끝나면 멈춰둔거 이어서 (시간 겹치면 새로 시작하는 것부터)
//멈춘건 가장 최신 순으로

import java.util.*;
import java.io.*;
class Solution {
    static String[] answer;
    static int idx = 0;
    static int order = 1;
    public String[] solution(String[][] plans) {
        TreeMap<Integer, Work> map = new TreeMap<>();
        PriorityQueue<Work> pq = new PriorityQueue<>();
        answer = new String[plans.length];
        
        
        for(int i = 0; i < plans.length; i++) {
            String[] plan = plans[i];
            Work w = new Work(plan[0], plan[1], plan[2]);
            map.put(w.startTime, w);
        }
        
        Work working = null;
        for(int i = 0; i <= (1440 * 100); i++) {
            if(map.containsKey(i)) {//1. 해당 시간에 존재하는 과제 있나 확인
                if(working != null) { //진행중인 과제가 있다면 세이브
                    working.order = order++;
                    pq.add(working); //큐에 저장
                    working = null;
                }
                working = map.get(i); //작업중인 과제 교체
            } else { //현재 시간에 과제가 없다면 남아있는 과제 
                if(working == null && !pq.isEmpty()) {
                    working = pq.poll();
                }
            }
            
            //1분 작업
            if(working != null) {
                working.remain -= 1;     //남은 기간
                working.processTime = i; //최근 작업 시간
                if(working.checkIsEnd()) {
                    answer[idx++] = working.name;
                    working = null;
                }
            }
        }
        

        return answer;
    }
}

class Work implements Comparable<Work> {
    String name;
    Integer startTime; //시작 시간
    Integer processTime; //마지막 진행 시간
    Integer remain;
    Integer order = 0;
    
    @Override
    public int compareTo(Work w) {
        return Integer.compare(w.order, this.order);
    }
    
    boolean checkIsEnd() {
        return remain == 0;
    }
    
    public Work(String name, String start, String playtime) {
        this.name = name;
        String[] splited = start.split(":");
        this.startTime = Integer.parseInt(splited[0]) * 60 + Integer.parseInt(splited[1]);
        this.processTime = this.startTime;
        this.remain = Integer.parseInt(playtime);
    }
}