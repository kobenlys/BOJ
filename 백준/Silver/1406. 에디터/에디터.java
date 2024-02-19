import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stk1 = new Stack<>();
        Stack<Character> stk2 = new Stack<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < str.length(); i++) {
            stk1.push(str.charAt(i));
        }

        while (N-- > 0) {

            st = new StringTokenizer(br.readLine(), " ");
            String flag = st.nextToken();
            char key;

            if (flag.equals("L")) {

                if (stk1.isEmpty()) continue;
                stk2.push(stk1.pop());

            } else if (flag.equals("D")) {
                if (stk2.isEmpty()) continue;
                stk1.push(stk2.pop());
            } else if (flag.equals("B")) {
                if (stk1.isEmpty()) continue;
                stk1.pop();
            } else {
                key = st.nextToken().charAt(0);
                stk1.push(key);
            }
        }

        while (!stk2.isEmpty()) {
            stk1.push(stk2.pop());
        }

        while (!stk1.isEmpty()) {
            sb.append(stk1.pop());
        }
        System.out.println(sb.reverse());
    }
}