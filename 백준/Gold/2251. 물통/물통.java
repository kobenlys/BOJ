import java.io.*;
import java.util.*;

public class Main {
    public static int A, B, C;
    public static boolean[][] vi;
    public static HashSet<Integer> set = new HashSet<>();

    public static void dfs(int start, int a, int b, int c) {

        if (vi[a][b]) {
            return;
        }

        if (a == 0) {
            set.add(c);
        }

        vi[a][b] = true;
        int tmpA, tmpB, tmpC;

        if (B != b && c != 0) {
            tmpB = Math.min(B, b + c);
            tmpC = Math.max(c - (B - b), 0);
            dfs(start + 1, a, tmpB, tmpC);
        }

        if (C != c && b != 0) {
            tmpB = Math.max(b - (C - c), 0);
            tmpC = Math.min(C, c + b);
            dfs(start + 1, a, tmpB, tmpC);
        }

        if (A != a && b != 0) {
            tmpA = Math.min(A, a + b);
            tmpB = Math.max(b - (A - a), 0);
            dfs(start + 1, tmpA, tmpB, c);
        }

        if (B != b && a != 0) {
            tmpA = Math.max(a - (B - b), 0);
            tmpB = Math.min(B, b + a);
            dfs(start + 1, tmpA, tmpB, c);
        }

        if (A != a && c != 0) {
            tmpA = Math.min(A, a + c);
            tmpC = Math.max(c - (A - a), 0);
            dfs(start + 1, tmpA, b, tmpC);
        }

        if (C != c && A != 0) {
            tmpA = Math.max(a - (C - c), 0);
            tmpC = Math.min(C, c + a);
            dfs(start + 1, tmpA, b, tmpC);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());


        vi = new boolean[201][201];
        dfs(0, 0, 0, C);

        ArrayList<Integer> arr1 = new ArrayList<>(set);
        Collections.sort(arr1);


        for (int e : arr1) {
            sb.append(e).append(" ");
        }
        System.out.print(sb);
    }
}