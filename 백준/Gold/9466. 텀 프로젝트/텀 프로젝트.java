import java.io.*;
import java.util.*;

public class Main {
    public static int N, sum;
    public static int[] arr1;
    public static boolean[] vi, fi;

    public static void dfs(int start) {

        if(fi[start]) return;
        if (vi[start]) {
            fi[start] = true;
            sum++;
        }

        vi[start] = true;
        dfs(arr1[start]);
        fi[start] = true;
        vi[start] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            N = Integer.parseInt(br.readLine());
            sum = 0;
            arr1 = new int[N];
            fi = new boolean[N];
            vi = new boolean[N];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr1[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            for (int i = 0; i < N; i++) {
                if (!fi[i]) {
                    dfs(i);
                }
            }
            sb.append(N - sum).append("\n");
        }
        System.out.print(sb);
    }
}