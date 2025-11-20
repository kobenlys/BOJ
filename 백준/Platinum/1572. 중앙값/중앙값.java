import java.io.*;
import java.util.*;

public class Main {

    public static int[] segment;
    public static int[] arr1;

    public static void update(int start, int end, int idx, int id, int diff) {
        if (id < start || id > end) {
            return;
        }

        segment[idx] += diff;
        if (start == end) {
            return;
        }

        int mid = (start + end) >> 1;
        update(start, mid, idx * 2, id, diff);
        update(mid + 1, end, idx * 2 + 1, id, diff);
    }

    public static int segmentBinarySearch(int start, int end, int idx, int target) {

        if (start == end) {
            return start;
        }

        int mid = (start + end) >> 1;

        if (target <= segment[idx * 2]) {
            return segmentBinarySearch(start, mid, idx * 2, target);
        } else {
            return segmentBinarySearch
                (mid + 1, end, idx * 2 + 1, target - segment[idx * 2]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long answer = 0;
        int size = 65535;
        segment = new int[(size + 1) * 4];
        int[] arr1 = new int[N];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            arr1[i] = num;
            int beforeIdx = i - K;

            update(0, size, 1, num, 1);

            if (beforeIdx > -1) {
                update(0, size, 1, arr1[beforeIdx], -1);
            }

            if (i + 1 >= K) {
                answer += segmentBinarySearch(0, size, 1, (K + 1) / 2);
            }
        }
        System.out.println(answer);
    }
}