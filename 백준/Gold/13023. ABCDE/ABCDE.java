import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static boolean[] check;
    static int checkCnt;
    static Node[] nodeArr;
    static boolean isRemain = false;
    public static void main(String[] args) {
        input();
        //logging();
        for(int i = 0; i < N; i++) {
            check = new boolean[N]; //초기화
            backTracking(i); //logic
            if (isRemain) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);

    }

    static void backTracking(int idx) {
        check[idx] = true;
        if (isRemain || checkLength()) { //기저조건
            isRemain = true;
            return;
        }

        for(int i = 0; i < nodeArr[idx].friends.size(); i++) {
            int next = nodeArr[idx].friends.get(i);
            if(check[next]) continue;
            backTracking(next);
            check[next] = false; //다시 false 처리
        }
    }

    static boolean checkLength() {
        int cnt = 0;
        for(boolean now : check) {
            if (now) cnt++;
            if (cnt >= 5) return true;
        }
        return false;
    }

    static void logging() {
        for(int i = 0; i < nodeArr.length; i++) {
            try{
                System.out.println(i + " : " + Arrays.toString(nodeArr[i].friends.toArray()));
            } catch (NullPointerException e) {
                System.out.println("NULL");
            }
        }
    }
    static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            check = new boolean[N];
            nodeArr = new Node[N];
            for (int i = 0; i < N; i++) { // 모든 노드를 초기화하는 과정 추가 (99%에서 NullException 발생)
                nodeArr[i] = new Node(i);
            }

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken());
                int friend = Integer.parseInt(st.nextToken());
                //내 쪽 추가
                if(nodeArr[num] == null) nodeArr[num] = new Node(num, friend);
                else nodeArr[num].friends.add(friend);

                //친구 쪽 추가
                if(nodeArr[friend] == null) nodeArr[friend] = new Node(friend, num);
                else nodeArr[friend].friends.add(num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Node {
    int num;
    List<Integer> friends = new ArrayList<>();

    public Node (int num) { this.num = num;}
    public Node (int num, int friend) {
        this.num = num;
        friends.add(friend);
    }
}