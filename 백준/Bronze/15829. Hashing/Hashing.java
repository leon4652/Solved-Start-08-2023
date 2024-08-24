
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        int r = 31;
	        int m = 1234567891;
	        int num = sc.nextInt();
	        String hash = sc.next();
	        char[] hasharr = hash.toCharArray();
	        int sum = 0;
	        for (int i = 0; i < hasharr.length; i++) {
	            sum += (hasharr[i] - '0' - 48) * (int)(Math.pow(r, i));
	        }
	        System.out.println(sum % m);
	        //a = 49

	}

}
