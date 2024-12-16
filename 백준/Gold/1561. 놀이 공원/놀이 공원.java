import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[] arr1;

    public static long findMinTime() {
        long left = 0, right = N * 30L;
        long result = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            long people = 0;

            for (int i = 0; i < M; i++) {
                people += mid / arr1[i];
            }
            people += M;

            if (people >= N) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        long time = findMinTime();

        if(time == 0){
            System.out.println(N);
            return;
        }
        long before = M;

        for (int i = 0; i < M; i++) {
            before += (time - 1) / arr1[i];
        }

        // 10
        // 10 + 5 + 3 + 2 + 2 = 22
        // 9
        // 9 + 4 + 3 + 2 + 1 = 19
        for (int i = 0; i < M; i++) {
            if (time % arr1[i] == 0) {
                before++;
                if (N == before) {
                    System.out.println(i + 1);
                    break;
                }
            }
        }
    }
}