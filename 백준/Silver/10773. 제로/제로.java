import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stk = new Stack<>();

        int K = Integer.parseInt(br.readLine());
        int sum = 0;
        // 본 문제는 스택의 자료구조와 같다.
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                stk.pop();
                continue;
            }
            stk.push(num);

        }
        for (int e : stk) {
            sum += e;
        }

        System.out.println(sum);
    }
}