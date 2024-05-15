import java.io.*;
import java.util.*;

public class Main {
    public static int[] arr1;

    public static long calcTime(long time) {
        long cnt = 0;

        for (int j : arr1) {
            cnt += time / j;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr1 = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        long left = 0, right = 10_000_000_000_000L;
        long answer = 0;

        while (left <= right) {

            long mid = (left + right) / 2;

            long tmp = calcTime(mid);

            if (M <= tmp) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }
}