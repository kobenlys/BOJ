import java.io.*;
import java.util.*;

public class Main {

    public static int N, Aa, Bb, Cc, Dd, min;
    public static Food[] arr1;
    public static List<String> results;

    public static class Food {

        int A, B, C, D, price;
        String idxTrace;

        public Food(int a, int b, int c, int d, int price) {
            A = a;
            B = b;
            C = c;
            D = d;
            this.price = price;
            idxTrace = "";
        }

        public void sum(Food tmp) {
            A += tmp.A;
            B += tmp.B;
            C += tmp.C;
            D += tmp.D;
            price += tmp.price;
        }

        public void minus(Food tmp) {
            A -= tmp.A;
            B -= tmp.B;
            C -= tmp.C;
            D -= tmp.D;
            price -= tmp.price;
        }


        public boolean isTargetMet() {
            return Aa <= A && Bb <= B && Cc <= C && Dd <= D;
        }
    }

    public static void dfs(int idx, Food cache) {
        
        if (cache.isTargetMet()) {
            if (min >= cache.price) {
                if (min > cache.price) {
                    min = cache.price;
                    results.clear();
                }
                results.add(cache.idxTrace);
            }
            return;
        }

        if (idx == N || min <= cache.price) {
            return;
        }

        for (int i = idx; i < N; i++) {
            cache.sum(arr1[i]);
            String tmpTrace = cache.idxTrace;
            cache.idxTrace = cache.idxTrace + " " + (i + 1);
            dfs(i + 1, cache);
            cache.idxTrace = tmpTrace;
            cache.minus(arr1[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new Food[N];
        results = new ArrayList<>();
        min = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        Aa = Integer.parseInt(st.nextToken());
        Bb = Integer.parseInt(st.nextToken());
        Cc = Integer.parseInt(st.nextToken());
        Dd = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            arr1[i] = new Food(a, b, c, d, p);
        }

        dfs(0, new Food(0, 0, 0, 0, 0));

        if (results.isEmpty()) {
            System.out.println(-1);
            return;
        }
        Collections.sort(results);
        sb.append(min).append("\n").append(results.get(0).substring(1));
        System.out.println(sb);
    }
}