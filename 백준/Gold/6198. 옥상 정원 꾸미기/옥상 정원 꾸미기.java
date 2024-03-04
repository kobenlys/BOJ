import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stk1 = new Stack<>();
        Stack<Integer> stk2 = new Stack<>();

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N];
        int[] arr1 = new int[N];

        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(br.readLine()); // 빌딩 높이 저장.
            stk1.push(i); // 인덱스 번호 저장
        }
        
        while (!stk1.isEmpty()) {
            // stk1.peek 가 stk2.peek의 참조 arr1배열이 크다면 dp[stk2.pop]+1
            // 메모이제이션을 사용하여 지나간 빌딩을 다시 탐색할 필요없음.
            while (!stk2.isEmpty() && arr1[stk1.peek()] > arr1[stk2.peek()]) {
                dp[stk1.peek()] += dp[stk2.pop()]+1;
            }
            stk2.push(stk1.pop());
        }
        // 등차 수열 합 = N/2*(1+N) , N == 80000 이면 cnt 의 최댓값은 int형 범위를 넘어간다.
        long cnt = 0;
        for (int i = 0; i < N; i++) {
            cnt += dp[i];
        }
        System.out.print(cnt);
    }
}