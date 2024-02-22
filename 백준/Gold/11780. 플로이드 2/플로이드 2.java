import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static long INF = 1000000001;
    public static long[][] arr1;
    public static int[][] trace;
    public static StringBuilder sb = new StringBuilder();

    public static void floydWarshall() {
        for (int fw = 0; fw < N; fw++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;
                    if (arr1[i][j] > arr1[i][fw] + arr1[fw][j]) {
                        arr1[i][j] = arr1[i][fw] + arr1[fw][j];
                        trace[i][j] = trace[fw][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stk = new Stack<>();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr1 = new long[N][N];
        trace = new int[N][N];


        for (int i = 0; i < N; i++) {
            Arrays.fill(arr1[i], INF);
            Arrays.fill(trace[i], -1);
            arr1[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int p = Integer.parseInt(st.nextToken());

            if (arr1[s][e] > p) {
                arr1[s][e] = p;
                trace[s][e] = s;
            }
        }

        floydWarshall();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arr1[i][j] != INF ? arr1[i][j] + " " : "0 ");
            }
            System.out.println();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (i == j || trace[i][j] == -1) {
                    sb.append("0 ").append("\n");
                    continue;
                }

                int tmp = j;
                stk.push(j);
                while (i != trace[i][tmp]) {
                    stk.push(trace[i][tmp]);
                    tmp = trace[i][tmp];
                }

                sb.append(stk.size() + 1).append(" ").append(i + 1).append(" ");

                while (!stk.isEmpty()) {
                    sb.append(stk.pop() + 1).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}