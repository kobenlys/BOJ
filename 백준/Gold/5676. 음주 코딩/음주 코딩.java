import java.io.*;
import java.util.*;

public class Main {
    public static int[] arr1;
    public static int[] segment;

    public static int init(int start, int end, int idx) {

        if (start == end) return segment[idx] = arr1[start];

        int mid = (start + end) / 2;
        return segment[idx] = init(start, mid, idx * 2)
                * init(mid + 1, end, idx * 2 + 1);
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
        segment[idx] = segment[idx * 2] * segment[idx * 2 + 1];
    }

    public static int getMulti(int start, int end, int idx, int left, int right) {

        if (right < start || left > end) return 1;
        if (left <= start && right >= end) return segment[idx];

        int mid = (start + end) / 2;

        return getMulti(start, mid, idx * 2, left, right)
                * getMulti(mid + 1, end, idx * 2 + 1, left, right);
    }

    public static int isPositive(int num) {

        if (num < 0) {
            return -1;
        } else if (num > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {

            String str = br.readLine();
            if (Objects.isNull(str)) break;

            st = new StringTokenizer(str);
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            arr1 = new int[N + 1];
            segment = new int[(N + 1) * 4];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr1[i] = isPositive(Integer.parseInt(st.nextToken()));
            }

            init(1, N, 1);

            while (M-- > 0) {

                st = new StringTokenizer(br.readLine());

                String ctrl = st.nextToken();
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                if (ctrl.equals("C")) {
                    int num = isPositive(B);
                    update(1, N, 1, A, num);
                    arr1[A] = num;
                } else {

                    int res = getMulti(1, N, 1, A, B);

                    if (res == 1) {
                        sb.append("+");
                    } else if (res == -1) {
                        sb.append("-");
                    } else {
                        sb.append("0");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}