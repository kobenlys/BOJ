import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stk = new Stack<>();
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            // 스택에 배열자리를 저장하고
            // 저장된 배열자리에 있는 원소보다 현재 배열원소가 더 크다면
            // 반복적으로 조건에 맞는 원소를 초기화 한다.
            while (!stk.isEmpty() && arr1[stk.peek()] < arr1[i]) {
                arr1[stk.pop()] = arr1[i];
            }

            stk.push(i);
        }

        while (!stk.isEmpty()) {
            // 마지막까지 남은 원소들은 오큰수가 없다
            arr1[stk.pop()] = -1;
        }

        for (int e : arr1) {
            sb.append(e).append(" ");
        }

        System.out.println(sb);
    }
}