import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static int[] arr1;

    public static void algorithm() {
        Stack<Integer> stk = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int[] ans = new int[N + 1];

        for (int i = N; i > 0; i--) {
            // 스텍에 arr1을 참조할 수 있는 인덱스 값을 저장한다
            while (!stk.isEmpty() && arr1[i] >= arr1[stk.peek()]) {
                // 오른쪽에서 왼쪽으로 탐색한다,
                // 크거나 같은값 발견시 스택에서 pop하면서 ans배열에 큰값 인덱스 번호 저장
                ans[stk.pop()] = i;
            }
            stk.push(i);
        }

        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        // 함수 호출
        algorithm();
    }
}
