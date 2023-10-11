//package programmers.lv3;
//
////https://school.programmers.co.kr/learn/courses/30/lessons/49191
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class 그래프 {
//
//    public static void main(String[] args) throws IOException {
////        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        System.out.println(solution(5, new int[][] {{4,3},{4,2},{3,2},{1,2},{2,5}}));
//    }
//
//    public static int solution(int n, int[][] results) {
//        int answer = 0;
//        HashMap<Integer, Man> mans = new HashMap<>();
//        //N개의 객체 생성
//        for (int i = 0; i < n; i++) {
//            int player = results[i][0];
//            int rival = results[i][1];
//            //플레이어의 전적이 있다면 갱신하고, 없다면 추가한다.
//            if (mans.containsKey(player)) mans.get(player).down.add(rival);
//            else {
//                mans.put(player, new Man(player));
//                mans.get(player).down.add(rival);
//            }
//
//            //라이벌(패배)의 전적이 있다면 갱신하고, 없다면 추가한다.
//            if (mans.containsKey(rival)) mans.get(rival).up.add(player);
//            else {
//                mans.put(rival, new Man(rival));
//                mans.get(rival).up.add(player);
//            }
//        }
//
//        //D
//        for (int i = 0; i < n; i++) {
//            System.out.println(mans.get(i + 1));
//        }
//
//        //각 플레이어 결과 조회
//        for (int i = 0; i < n; i++) {
//            int upRank = getUpRank(i, mans);//나보다 높은 사람들 조회
//            int downRank = 0;//나보다 낮은 사람들 조회
//            if(upRank + downRank - 1 == n) answer++; //순위를 정확하게 측정할 수 있는 경우
//        }
//
//        return 0;
//    }
//
//    private static int getUpRank(int player, HashMap<Integer, Man> mans) {
//        //나보다 높은 사람들 랭킹 조회
//        int res = 0;
//        while (true) {
//            Man nextMan = mans.get(player);
//
//            if() break;
//        }
//
//        return res;
//    }
//
//    public static class Man {
//        int number; //내 번호
//        List<Integer> up = new ArrayList<>(); //나보다 위의 사람
//        List<Integer> down = new ArrayList<>(); //나보다 아래의 사람
//
//        public Man(int player) {
//            this.number = player;
//        }
//    }
//
//}
