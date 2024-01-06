import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] houses = new int[N];

        for(int i = 0; i < N; i++) houses[i] = sc.nextInt();

        Arrays.sort(houses); 
        int answer = houses[(N - 1) / 2];
        
        System.out.println(answer);
    }
}
