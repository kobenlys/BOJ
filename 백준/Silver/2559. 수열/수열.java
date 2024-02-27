import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int min = Integer.MIN_VALUE;
        int[] arr1 = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr1[i] = arr1[i - 1] + Integer.parseInt(st.nextToken());
        }

        for (int i = K; i <= N; i++) {
            min = Math.max(min, arr1[i] - arr1[i - K]);
        }

        System.out.print(min);
    }
}