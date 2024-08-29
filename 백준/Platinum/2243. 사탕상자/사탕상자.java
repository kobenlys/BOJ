import java.io.*;
import java.util.*;

public class Main {
    public static int[] segment;

    public static void update(int start, int end, int idx, int id, int val) {
        if (id < start || id > end) return;

        segment[idx] += val;
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, idx * 2, id, val);
        update(mid + 1, end, idx * 2 + 1, id, val);
        //segment[idx] = segment[idx * 2] + segment[idx * 2 + 1];
    }

    public static int getCandy(int start, int end, int idx, int target) {
        if (start == end) return start;
        int mid = (start + end) / 2;

        if (target <= segment[idx * 2]) {
            return getCandy(start, mid, idx * 2, target);
        } else {
            return getCandy(mid + 1, end, idx * 2 + 1, target - segment[idx * 2]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        segment = new int[1_000_001 * 4];

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (oper == 1) {
                int res = getCandy(1, 1_000_000, 1, B);
                sb.append(res).append("\n");
                update(1, 1_000_000, 1, res, -1);
            } else {
                int C = Integer.parseInt(st.nextToken());
                update(1, 1_000_000, 1, B, C);
            }

           
        }
        System.out.print(sb);
    }
}