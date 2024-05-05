import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int cnt = 0;

        int[] arr1 = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr1[i] = arr1[i - 1] + n;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i - 1; 0 <= j; j--) {
                if (arr1[i] - arr1[j] >= M) {
                    if (arr1[i] - arr1[j] == M) {
                        cnt++;
                    }
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}