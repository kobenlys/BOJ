import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static long[] arr1;

    public static boolean isPossible(int limit) {

        int limitCnt = 1;
        long tmp = 0;
        for (int i = 0; i < N; i++) {

            if (arr1[i] > limit) {
                return false;
            }
            tmp += arr1[i];

            if (tmp > limit) {
                tmp = 0;
                tmp += arr1[i];
                limitCnt++;
            }

            if (limitCnt > M) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 1_000_000_000;
        long answer = Long.MAX_VALUE;
        while (left <= right) {

            int mid = (left + right) >> 1;
            if (isPossible(mid)) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}