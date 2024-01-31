import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, K;
    public static int[] bags;
    public static node[] arr1;

    public static class node {
        int w, v;
        public node(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    public static void greedy() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long ans = 0;
        int j = 0;
        for (int i = 0; i < K; i++) {
            while (j < N) {
                if (arr1[j].w <= bags[i]) {
                    pq.offer(arr1[j++].v);
                } else {
                    break;
                }
            }
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr1 = new node[N];
        bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr1[i] = new node(w, v);
        }

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr1, (g1, g2) -> {
            if(g1.w == g2.w) return g2.v - g1.v;
            return g1.w - g2.w;
        });
        Arrays.sort(bags);

        greedy();
    }
}
