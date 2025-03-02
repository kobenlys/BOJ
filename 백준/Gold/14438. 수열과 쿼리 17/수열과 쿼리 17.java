import java.io.*;
import java.util.*;

public class Main {
    public static long[] segment;
    public static long[] arr1;

    public static long init(int start, int end, int idx) {
        if (start == end) return segment[idx] = arr1[start];
        int mid = (start + end) / 2;
        return segment[idx] = Math.min(init(start, mid, idx * 2), init(mid + 1, end, idx * 2 + 1));
    }

    public static void update(int start, int end, int idx, int id, int val) {
        if (id < start || id > end) return;
        if (start == end) {
            segment[idx] = val;
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, idx * 2, id, val);
        update(mid + 1, end, idx * 2 + 1, id, val);
        segment[idx] = Math.min(segment[idx * 2], segment[idx * 2 + 1]);
    }

    public static long getMinNumber(int start, int end, int idx, int left, int right) {
        if (right < start || left > end) return Integer.MAX_VALUE;
        if (left <= start && right >= end) return segment[idx];
        int mid = (start + end) / 2;
        return Math.min(getMinNumber(start, mid, idx * 2, left, right),
                getMinNumber(mid + 1, end, idx * 2 + 1, left, right));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        segment = new long[(N + 1) * 4];
        arr1 = new long[N + 1];
        Arrays.fill(segment, Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        init(1, N, 1);

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int ctrl = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (ctrl == 1) {
                update(1, N, 1, A, B);
            } else {
                sb.append(getMinNumber(1, N, 1, A, B)).append("\n");
            }
        }
        System.out.println(sb);
    }
}