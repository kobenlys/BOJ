import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<String> stk = new Stack<>();
        StringTokenizer st;

        String input = br.readLine();
        int sum = 0;

        for (int i = 0; i < input.length(); i++) {

            int tmp = 0;
            boolean isUpdate1 = false;
            // ')'인 경우 연산.
            while (!stk.isEmpty() && input.charAt(i) == ')') {
                String flag = stk.peek();

                if (flag.equals("[")) {
                    // 괄호 조합이 다른 경우 예외처리
                    System.out.println(0);
                    System.exit(0);
                } else if (flag.equals("(")) {
                    isUpdate1 = true;
                    stk.pop();
                    break;
                } else {
                    // 괄호 속 숫자 있다면 tmp에 더하기
                    tmp += Integer.parseInt(stk.pop());
                }
            }
            if (isUpdate1) {
                // 괄호'(' 가 닫혔다면
                // tmp = 0, 괄호가 바로 닫힌경우 -> () -> 2
                if (tmp == 0) {
                    tmp += 2;
                } else {
                    //tmp !=0, 괄호 속에 숫자가 있었던 경우
                    // -> (3) -> 6
                    tmp = tmp*2;
                }
                stk.push(String.valueOf(tmp));
            }


            tmp = 0;
            boolean isUpdate2 = false;

            while (!stk.isEmpty() && input.charAt(i) == ']') {
                String flag = stk.peek();

                if (flag.equals("(")) {
                    // 괄호 조합이 다른 경우 예외처리
                    System.out.println(0);
                    System.exit(0);
                } else if (flag.equals("[")) {
                    isUpdate2 = true;
                    stk.pop();
                    break;
                } else {
                    // 괄호 속 숫자 있다면 tmp에 더하기
                    tmp += Integer.parseInt(stk.pop());
                }
            }
            if (isUpdate2) {
                // 괄호'[' 가 닫혔다면
                // tmp = 0, 괄호가 바로 닫힌경우 -> [] -> 3
                if (tmp == 0) {
                    tmp += 3;
                } else {
                    //tmp !=0, 괄호 속에 숫자가 있었던 경우
                    // -> [3] -> 9
                    tmp = tmp*3;
                }
                stk.push(String.valueOf(tmp));
            }

            if (input.charAt(i) == '(' || input.charAt(i) == '[') {
                stk.push(String.valueOf(input.charAt(i)));
            } else if(stk.isEmpty()){
                // 스택이 비었는데 닫는 괄호 ex. ')', ']' 입력시 예외처리
                System.out.println(0);
                System.exit(0);
            }
        }
        
        // 출력
        while (!stk.isEmpty()) {
            // ((( 같은 tc방지.
            if (stk.peek().equals("(") || stk.peek().equals("[")) {
                sum = 0;
                break;
            }
            sum += Integer.parseInt(stk.pop());
        }
        System.out.println(sum);
    }
}