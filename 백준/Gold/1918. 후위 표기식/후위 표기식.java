import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> map = new HashMap<>();
        Stack<Character> stk = new Stack<>();

        map.put('-', 1); map.put('+', 1);
        map.put('*', 2); map.put('/', 2);
        map.put('(', 0); map.put(')', 0);

        String str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            char num = str.charAt(i);

            if (num >= 'A') {
                sb.append(num);
                continue;
            }

            if (num == '(') {
                stk.push(num);
                continue;
            }

            if (num == ')') {
                while (stk.peek() != '(') {
                    sb.append(stk.pop());
                }
                stk.pop();
                continue;
            }
            while (!stk.isEmpty() && map.get(stk.peek()) >= map.get(num)) {
                sb.append(stk.pop());
            }
            stk.push(num);
        }
        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        System.out.print(sb);
    }
}