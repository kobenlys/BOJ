import java.io.*;
import java.util.*;

public class Main {

    public static int N, M, answer;
    public static int[] arr1;

    public static void dfs(int index, int size, int time) {

        if (index == N || time == M) {
            answer = Math.max(answer, size);
            return;
        }

        dfs(index + 1, size + arr1[index + 1], time + 1);
        if (index + 2 > N) {
            return;
        }
        dfs(index + 2, size / 2 + arr1[index + 2], time + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 1, 0);
        System.out.println(answer);
    }
}