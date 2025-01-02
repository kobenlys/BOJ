import java.io.*;
import java.util.*;

public class Main {
    public static long[] segment;
    public static long[] arr1;

    public static long init(int start, int end, int idx) {
        if (start == end) return segment[idx] = arr1[start];
        int mid = (start + end) / 2;
        return segment[idx] = init(start, mid, idx * 2) + init(mid + 1, end, idx * 2 + 1);
    }

    public static void update(int start, int end, int idx, int id, int val) {
        if (id < start || id > end) return;

        int mid = (start + end) / 2;
        segment[idx] += val;
        if (start == end) return;

        update(start, mid, idx * 2, id, val);
        update(mid + 1, end, idx * 2 + 1, id, val);
    }

    public static int binarySearch(int start, int end, int idx, long val) {
        if (start == end) return start;
        int mid = (start + end) / 2;

        if (val <= segment[idx * 2]) {
            return binarySearch(start, mid, idx * 2, val);
        } else {
            return binarySearch(mid + 1, end, idx * 2 + 1, val - segment[idx * 2]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr1 = new long[N + 1];
        segment = new long[(N + 1) * 4];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        init(1, N, 1);

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());

            if (dir == 1) {
                int base = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());
                update(1, N, 1, base, val);

            } else {
                int soldierIdx = Integer.parseInt(st.nextToken());
                int base = binarySearch(1, N, 1, soldierIdx);
                sb.append(base).append("\n");
            }
        }
        System.out.println(sb);
    }
}