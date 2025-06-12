import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static int[] parent;

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        int from = find(x);
        int to = find(y);

        if (from != to) {
            parent[to] = from;
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

        parent = new int[N + 1];
        int[] arr1 = new int[N + 1];
        IntStream.rangeClosed(0, N).forEach(i -> parent[i] = i);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if(find(A) == find(B)){
                System.out.println(0);
                System.exit(0);
            }

            int friendA = arr1[A];
            int friendB = arr1[B];

            if (friendA == 0) {
                arr1[A] = B;
            }else{
                union(friendA, B);
            }

            if (friendB == 0) {
                arr1[B] = A;
            }else{
                union(friendB, A);
            }
        }

        System.out.println(1);
    }
}