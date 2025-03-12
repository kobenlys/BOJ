import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String[] arr1 = new String[N + 1];
        int[] prev = new int[N + 1];
        int[] tail = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr1[i] = br.readLine();
            prev[i] = i;
            tail[i] = i;
        }

        int cur = -1;

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            prev[tail[A]] = B;
            tail[A] = tail[B];
            cur = A;
        }

        for (int i = 1; i <= N; i++) {
            sb.append(arr1[cur]);
            cur = prev[cur];
        }

        System.out.println(sb);
    }
}