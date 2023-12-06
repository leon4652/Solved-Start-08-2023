import java.util.*;
import java.io.*;
import java.io.*;
class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args) throws IOException {
    	int N = Integer.parseInt(br.readLine());
    	while(N-- > 0) {
    		solve(); //메인 로직
    	}
    	bw.close();
    }
    
    public static void solve() throws IOException {
        String inputs = br.readLine();
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < inputs.length(); i++) {
            char c = inputs.charAt(i);

            if(c == '(') {
                stack.push(c);
            } else if(c == ')') {
                if(stack.isEmpty() || stack.peek() != '(') {
                    bw.write("NO\n");
                    return;
                }
                stack.pop();
            }
        }

        if(stack.isEmpty()) {
            bw.write("YES\n");
        } else {
            bw.write("NO\n");
        }
    }

}
