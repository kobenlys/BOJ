import java.io.*;
import java.util.*;

public class Main {
    public static int[] segment;

    public static void update(int start, int end, int idx, int id) {
        if (id < start || id > end) return;

        segment[idx] += 1;

        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, idx * 2, id);
        update(mid + 1, end, idx * 2 + 1, id);
    }

    public static int getInversion(int start, int end, int idx, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && right >= end) return segment[idx];

        int mid = (start + end) / 2;

        return getInversion(start, mid, idx * 2, left, right)
                + getInversion(mid + 1, end, idx * 2 + 1, left, right);

    }
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        StringTokenizer st;

        //st = new StringTokenizer(br.readLine());
        int N = read();
        int M = read();
        long answer = 0;

        List<List<Integer>> list = new ArrayList<>();
        segment = new int[(N + 1) * 4];

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            //st = new StringTokenizer(br.readLine());
            int from = read();
            int to = read();
            list.get(from).add(to);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(list.get(i));

            for (int to : list.get(i)) {
                answer += getInversion(1, N, 1, to + 1, N);
                update(1, N, 1, to);
            }

        }
        System.out.println(answer);
    }
}