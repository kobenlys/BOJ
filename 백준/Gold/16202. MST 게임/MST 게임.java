import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;
        
    public static class node {
        int x, y, v;
        public node(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }
    // 부모 찾기.
    public static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    // 집합 합치기.
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
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<node> arr1 = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            arr1.add(new node(s, e, i));
        }

        while (K-- > 0) {

            parent = new int[N];
            int answer = 0;
            int cntNode = 0;

            // 초기화
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            // 크루스칼 알고리즘.
            for (node nd : arr1) {
                if (!union(nd.x, nd.y)) {
                    answer += nd.v;
                    cntNode ++;
                }
            }

            // 최소 스패닝 트리 만들어졌는지 확인.
            if (cntNode == N - 1) {
                sb.append(answer).append(" ");
            } else {
                sb.append(0).append(" ");
            }

            // 가장 작은 가중치의 간선 제거.
            arr1.remove(0);
        }

        System.out.println(sb);
    }
}