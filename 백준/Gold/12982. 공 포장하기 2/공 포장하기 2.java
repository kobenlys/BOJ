import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());
        int cnt = 0;
        int answer = 0;
        int[] arr1 = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) {
            if (arr1[i] >= K) {
                cnt += arr1[i] / K;
                arr1[i] %= K;
            }
        }

        Arrays.sort(arr1);
        int tmp = 0, max = 0;
        for (int i = 0; i < K; i++) {
            if (arr1[i] == 0) continue;

            tmp++;

            int tmpMax = max;
            max = Math.max(max, arr1[i]);

            if (1 < max - tmpMax) max -= max - tmpMax-1;
        }

        answer = cnt + Math.min(max, tmp);
        System.out.println(answer);
    }
}