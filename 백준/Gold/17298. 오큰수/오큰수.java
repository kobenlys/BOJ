import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stk = new Stack<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {

            while (!stk.isEmpty() && arr1[stk.peek()] < arr1[i]) {
                arr1[stk.pop()] = arr1[i];
            }

            stk.push(i);
        }

        while (!stk.isEmpty()) {
            arr1[stk.pop()] = -1;
        }
        for (int e : arr1) {
            sb.append(e).append(" ");
        }
        System.out.println(sb);
    }
}