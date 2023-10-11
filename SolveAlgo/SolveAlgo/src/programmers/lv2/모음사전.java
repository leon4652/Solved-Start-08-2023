package programmers.lv2;

//https://school.programmers.co.kr/learn/courses/30/lessons/84512

/*

 'A', 'E', 'I', 'O', 'U 만 존재

사전에서 첫 번째 단어는 "A"이고,
그다음은 "AA", "AAA", "AAAA", "AAAAA", "AAAAE", ... 와 같습니다.
"AAAAE"는 사전에서 6번째 단어입니다.


"AAAE"는 "A", "AA", "AAA", "AAAA", "AAAAA", "AAAAE", "AAAAI", "AAAAO", "AAAAU"의 다음인 10번째 단어입니다.


AAAA = 4

AAAAA = 5
.
.
AAAAU = 9

AAAE = 10

AAAI = 16 = 10 + 5^1 + 1
AAAO = 22 = 16 + 5^1 + 1
AAAU = 28

AAE = 29 -> AAA는 3, 3 +



- - - - -
AEIOU = 01234

마지막자리는 바뀔때마다  5^0(n) + 1 = 1
4번째는 (1*5) + 1 = 6
3번째는 (6*5) + 1 = 31
2번째는 (31*5) + 1 = 156
1번째는 (156 * 5) + 1 = 781

AAAU = 1 1 1 (5*4) 5 = 28
AAE 1 1 25 + 5

 */

import java.util.HashMap;

public class 모음사전 {

    public static void main(String[] args) {
        String input = "A";
        System.out.println(solution(input));
    }

    public static int solution(String word) {
        int[] value = new int[] {1, 6, 31, 156, 781};
        HashMap hm = new HashMap<Character, Integer>();
        hm.put("A", 0);
        hm.put("E", 1);
        hm.put("I", 2);
        hm.put("O", 3);
        hm.put("U", 4);
        int len = word.length(); //문자열 길이
        int res = 0;

        int idx = 0;
        for (int i = len; i > 0; i--) {

            idx++;
        }
        return 0;
    }

}
