import java.io.*;
import java.util.*;

public class Main {
    public static int[] segment;

    public static void update(int start, int end, int idx, int id, int val) {
        if (id < start || end < id) return;

        if (start == end) {
            segment[idx] = val;
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, idx * 2, id, val);
        update(mid + 1, end, idx * 2 + 1, id, val);
        segment[idx] = segment[idx * 2] + segment[idx * 2 + 1];
    }

    public static int getEvenCount(int start, int end, int idx, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start & right >= end) return segment[idx];

        int mid = (start + end) / 2;

        return getEvenCount(start, mid, idx * 2, left, right)
                + getEvenCount(mid + 1, end, idx * 2 + 1, left, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        segment = new int[(N + 1) * 4];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num % 2 == 0) {
                update(1, N, 1, i, 1);
            }
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (dir == 1) {
                if (B % 2 == 0) {
                    update(1, N, 1, A,  1);
                }else{
                    update(1, N, 1, A, 0);
                }

            } else if (dir == 2) {
                int even = getEvenCount(1, N, 1, A, B);
                sb.append(even).append("\n");
            } else {
                int odd = getEvenCount(1, N, 1, A, B);
                odd = Math.abs(odd - (B - A + 1));
                sb.append(odd).append("\n");
            }
        }
        System.out.println(sb);
    }
}