import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stk = new Stack<>();
        Stack<Character> tmp = new Stack<>();

        String str = br.readLine();
        String C4 = br.readLine();
        int flag;

        for (int i = str.length()-1; i >= 0 ; i--) {
            flag=0;
            while (!stk.isEmpty() && stk.peek() == C4.charAt(flag)) {
                tmp.push(stk.pop());
                flag++;
                if (flag == C4.length()) {
                    break;
                }
            }

            if (tmp.size() != C4.length()) {
                while (!tmp.isEmpty()) {
                    stk.push(tmp.pop());
                }
            }
            stk.push(str.charAt(i));
            tmp.clear();
        }
        // 한번더 실행하여 스택내부 남은 문자열을 검증해준다.
        flag = 0;
        while (!stk.isEmpty() && stk.peek() == C4.charAt(flag)) {
            tmp.push(stk.pop());
            flag++;
            if (flag == C4.length()) {
                break;
            }
        }
        // tmp != C4.length, 즉 문자열 폭발 불가능 제거한 문자 다시 넣기.
        if (tmp.size() != C4.length()) {
            while (!tmp.isEmpty()) {
                stk.push(tmp.pop());
            }
        }

        if (!stk.isEmpty()) {
            List<Character> list = new ArrayList<>(stk);
            for (int i = list.size()-1; i >= 0; i--) {
                sb.append(list.get(i));
            }
        } else {
            sb.append("FRULA");
        }
        System.out.print(sb);
    }
}