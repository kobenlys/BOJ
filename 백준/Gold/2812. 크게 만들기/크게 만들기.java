import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr1 = new int[10];
        String str = br.readLine();

        for (int i = 0; i < N; i++) {
            int n = Character.getNumericValue(str.charAt(i));
            arr1[n]++;
            while (!stk.isEmpty() && stk.peek() < n) {
                if(K == 0) break;
                arr1[stk.pop()]--;
                K--;
            }
            stk.push(n);
        }


        while (!stk.isEmpty()) {
            if (K > 0) {
                stk.pop();
                K--;
                continue;
            }
            sb.append(stk.pop());
        }
        if (sb.length() == 0) {
            System.out.print(0);
        } else {
            System.out.print(sb.reverse());
        }
    }
}