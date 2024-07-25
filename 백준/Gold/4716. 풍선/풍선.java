import java.io.*;
import java.util.*;

public class Main {
    public static int storageA, storageB;
    private static PriorityQueue<node> pq = new PriorityQueue<>();

    public static class node implements Comparable<node> {
        int numA, numB, quantity;

        public node(int numA, int numB, int quantity) {
            this.numA = numA;
            this.numB = numB;
            this.quantity = quantity;
        }

        @Override
        public int compareTo(node o) {
            return Math.abs(o.numA - o.numB) - Math.abs(numA - numB);
        }
    }

    public static void findMinPath() {
        int answer = 0;

        while (!pq.isEmpty()) {

            node tmp = pq.poll();

            if (tmp.numA < tmp.numB) {

                if (storageA >= tmp.quantity) {
                    storageA -= tmp.quantity;
                    answer += tmp.numA * tmp.quantity;
                } else {
                    int num = tmp.quantity - storageA;
                    storageB -= num;
                    answer += tmp.numA * storageA;
                    answer += tmp.numB * num;
                    storageA = 0;
                }

            } else if (tmp.numA > tmp.numB) {

                if (storageB >= tmp.quantity) {
                    storageB -= tmp.quantity;
                    answer += tmp.numB * tmp.quantity;
                } else {
                    int num = tmp.quantity - storageB;
                    storageA -= num;
                    answer += tmp.numB * storageB;
                    answer += tmp.numA * num;
                    storageB = 0;
                }
            } else {

                answer += tmp.numB * tmp.quantity;

            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            storageA = Integer.parseInt(st.nextToken());
            storageB = Integer.parseInt(st.nextToken());
            if (N == 0 && storageA == 0 && storageB == 0) break;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int quan = Integer.parseInt(st.nextToken());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                pq.offer(new node(A, B, quan));
            }
            findMinPath();
        }
    }
}