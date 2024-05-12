/*
1. 입력된 행렬 A와 B를 문자열 배열로 받는다.
2. 행렬 A를 순차적으로 검사하며, A와 B가 다른 위치를 발견할 때 해당 위치를 포함하는 3x3 크기의 부분 행렬을 뒤집는다.
3. 만약 행렬의 크기가 3x3보다 작은 부분에서 뒤집기가 필요할 경우, 뒤집을 수 없으므로 바로 -1을 반환한다.
4. 모든 가능한 부분에서 뒤집기를 수행한 후 A와 B가 동일한지 최종 확인한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sizes = br.readLine().split(" ");
        int N = Integer.parseInt(sizes[0]);
        int M = Integer.parseInt(sizes[1]);

        char[][] A = new char[N][M];
        char[][] B = new char[N][M];

        for (int i = 0; i < N; i++) {
            A[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < N; i++) {
            B[i] = br.readLine().toCharArray();
        }

        int flipCount = 0;
        for (int i = 0; i <= N - 3; i++) {
            for (int j = 0; j <= M - 3; j++) {
                if (A[i][j] != B[i][j]) {
                    flip(A, i, j);
                    flipCount++;
                }
            }
        }

        if (checkEqual(A, B)) {
            System.out.println(flipCount);
        } else {
            System.out.println(-1);
        }
    }

    private static void flip(char[][] matrix, int row, int col) {
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                matrix[i][j] = matrix[i][j] == '0' ? '1' : '0';
            }
        }
    }

    private static boolean checkEqual(char[][] A, char[][] B) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != B[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
