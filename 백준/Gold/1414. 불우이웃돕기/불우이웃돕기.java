import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;

    public static class node implements Comparable<node> {
        int x, y, val;

        public node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
        @Override
        public int compareTo(node o) {
            return val - o.val;
        }
    }
    
    // 부모 찾기.
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    // 집합 합치기
    public static boolean union(int x, int y) {
        int from = find(x);
        int to = find(y);
        if (from != to) {
            parent[to] = from;
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<node> pq = new PriorityQueue<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        int cnt = 1;

        parent = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                int val = 0;
                if (str.charAt(j) == '0') continue;
                // 대소문자 구별 가중치 계산.
                if (Character.isUpperCase(str.charAt(j))) {
                    val = str.charAt(j) - 'A' + 27;
                } else {
                    val = str.charAt(j) - 'a' + 1;
                }
                pq.offer(new node(i, j, val));
                answer += val;
            }
        }

        // 크루스칼 알고리즘.
        while (!pq.isEmpty()) {
            node now = pq.poll();

            if (!union(now.x, now.y)) {
                cnt++;
                answer -= now.val;
            }
        }

        System.out.println(N == cnt ? answer : -1);
    }
}