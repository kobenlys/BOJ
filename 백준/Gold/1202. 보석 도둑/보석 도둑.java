import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, K;
    public static node[] arr1;
    public static int[] bags;

    public static class node{
        int weight, value;

        public node(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void greedy() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long ans = 0;
        int j =0;
        for (int i = 0; i < K; i++) {
            while (j < N) {
                if (arr1[j].weight <= bags[i]) {
                    pq.offer(arr1[j++].value);
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
        Arrays.sort(arr1, (g1, g2) -> {
            if(g1.weight == g2.weight) return g2.value - g1.value;
            return g1.weight - g2.weight;
        });

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine()," ");
            bags[i] = Integer.parseInt(st.nextToken());
        }


        Arrays.sort(bags);
        greedy();
    }
}