import java.io.*;
import java.util.*;

public class Main {
    public static long[] arr1;
    public static long[] segment;

    public static long init(int s, int e, int idx) {

        if (s == e) return segment[idx] = arr1[s];
        int mid = (s + e) / 2;
        return segment[idx] = init(s, mid, idx * 2) + init(mid + 1, e, idx * 2 + 1);
    }

    public static void update(int s, int e, int idx, int id, int val) {

        if (id < s || id > e) return;
        if (s == e) {
            segment[idx] = val;
            return;
        }

        int mid = (s + e) / 2;
        update(s, mid, idx * 2, id, val);
        update(mid + 1, e, idx * 2 + 1, id, val);
        segment[idx] = segment[idx * 2] + segment[idx * 2 + 1];
    }

    public static long getSum(int s, int e, int idx, int left, int right) {
        if (right < s || left > e) return 0;
        if (left <= s && right >= e) return segment[idx];

        int mid = (s + e) / 2;
        return getSum(s, mid, idx * 2, left, right) + getSum(mid + 1, e, idx * 2 + 1, left, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr1 = new long[N + 1];
        segment = new long[(N + 1) * 4];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        init(1, N, 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(x > y){
                int tmp = x;
                x = y;
                y = tmp;
            }
            sb.append(getSum(1, N, 1, x, y)).append("\n");
            update(1, N, 1, a, b);
            arr1[a] = b;
        }
        System.out.println(sb);
    }
}