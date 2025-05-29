import java.io.*;
import java.util.*;

public class Main {

    public static int[] segment;

    public static void update(int start, int end, int idx, int id) {
        if (id < start || id > end) {
            return;
        }

        int mid = (start + end) / 2;
        segment[idx] += 1;
        if (start == end) {
            return;
        }

        update(start, mid, mid * 2, id);
        update(mid + 1, end, mid * 2 + 1, id);
    }

    public static int getInversion(int start, int end, int idx, int left, int right) {
        if (right < start || left > end) {
            return 0;
        }

        if (left <= start && right >= end) {
            return segment[idx];
        }
        int mid = (start + end) / 2;

        return getInversion(start, mid, mid * 2, left, right) + getInversion(mid + 1, end,
            mid * 2 + 1, left, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        segment = new int[(N + 1) * 4];
        long answer = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int target = Integer.parseInt(st.nextToken());
            answer += getInversion(1, N, 1, target, N);
            update(1, N, 1, target);
        }

        System.out.println(answer);
    }
}