import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        int N = sc.nextInt();

        boolean[] prime = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            prime[i] = true;
        }

        for (int i = 2; (i * i) <= N; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    prime[j] = false;
                }
            }
        }

        for (int i = M; i <= N; i++) {
            if (prime[i]) {
                System.out.println(i);
            }
        }
    }
}
