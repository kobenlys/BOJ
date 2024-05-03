import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            st = new StringTokenizer(br.readLine(), " ");
            int L = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int min = 0, max = 0;

            int[] arr1 = new int[N];
            int[] arr2 = new int[N];

            for (int i = 0; i < N; i++) {
                arr1[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(arr1);
            max = Math.max(L - arr1[0], arr1[N - 1]);
            

            for (int i = 0; i < N; i++) {
                arr2[i] = Math.min(arr1[i], L - arr1[i]);
            }
            Arrays.sort(arr2);
            min = arr2[N - 1];

            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.print(sb);
    }
}