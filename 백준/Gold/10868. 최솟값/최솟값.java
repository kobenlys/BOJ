import java.io.*;
import java.util.*;

public class Main {
    public static int[] arr1;
    public static int[] segment;

    public static int getMid(int s, int e) {
        return (s + e) / 2;
    }

    public static int init(int start, int end, int idx) {

        if (start == end) return segment[idx] = arr1[start];
        int mid = getMid(start, end);
        return segment[idx] = Math.min(init(start, mid, idx * 2), init(mid + 1, end, idx * 2 + 1));
    }

    public static int getMin(int start, int end, int idx, int left, int right) {
        if(right < start || left > end) return Integer.MAX_VALUE;
        if(left <= start && right >= end) return segment[idx];

        int mid = getMid(start, end);
        return Math.min(getMin(start, mid, idx * 2, left, right),
                getMin(mid + 1, end, idx * 2 + 1, left, right));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr1 = new int[N + 1];
        segment = new int[(N + 1) * 4];

        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }
        init(1, N, 1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(getMin(1, N, 1, s, e)).append("\n");
        }
        System.out.println(sb);
    }
}