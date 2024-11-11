import java.io.*;
import java.util.*;

public class Main {
    public static int[] segment;
    public static List<Node> list;

    public static class Node {
        int num, idx, score, comp;

        public Node(int num, int idx, int score, int comp) {
            this.num = num;
            this.idx = idx;
            this.score = score;
            this.comp = comp;
        }
    }

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

    public static int getRankScore(int start, int end, int idx, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && right >= end) {
            return segment[idx];
        }

        int mid = (start + end) / 2;
        return getRankScore(start, mid, idx * 2, left, right)
                + getRankScore(mid + 1, end, idx * 2 + 1, left, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int cnt = 1;
        list = new ArrayList<>();
        segment = new int[(N + 1) * 4];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            list.add(new Node(num, i + 1, 0, 0));
        }

        Collections.sort(list, Comparator.comparingInt(value -> value.num));

        for (int i = 0; i < list.size(); i++) {
            list.get(i).comp = cnt++;
        }

        Collections.sort(list, Comparator.comparingInt(value -> value.idx));

        for (int i = 0; i < N; i++) {
            Node nd = list.get(i);

            int rank = getRankScore(1, N, 1, nd.comp + 1, N) + 1;
            sb.append(rank).append("\n");
            update(1, N, 1, nd.comp);
        }

        System.out.println(sb);
    }
}