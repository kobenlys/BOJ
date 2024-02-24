import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stk = new Stack<>();
        StringBuilder sb = new StringBuilder();
        StringBuilder rvs = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();
        boolean isCheck = false;

        for (int i = 0; i < str.length(); i++) {


            if (!stk.isEmpty() && (str.charAt(i) == '<' || str.charAt(i) == ' ')) {
                if (isCheck && str.charAt(i) == ' ') {
                    stk.push(' ');
                    continue;
                }
                while (!stk.isEmpty()) {
                    sb.append(stk.pop());
                }
                if (str.charAt(i) == ' ') {
                    sb.append(" ");
                }
            }


            if (!stk.isEmpty() && str.charAt(i) == '>') {
                stk.push('>');
                while (!stk.isEmpty()) {
                    rvs.append(stk.pop());
                }
                sb.append(rvs.reverse());
                rvs.setLength(0);
                isCheck = false;
            }
            if(str.charAt(i) == '<') isCheck = true;
            if(str.charAt(i)==' ' || str.charAt(i)=='>') continue;
            stk.push(str.charAt(i));
        }

        if (str.charAt(str.length() - 1) != '>') {
            while (!stk.isEmpty()) {
                sb.append(stk.pop());
            }
        } else {
            while (!stk.isEmpty()) {
                rvs.append(stk.pop());
            }
            sb.append(rvs.reverse());
        }


        System.out.print(sb);
    }
}
