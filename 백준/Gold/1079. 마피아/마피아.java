import java.io.*;
import java.util.*;


public class Main {
    public static int N, eunjin, answer;
    public static int[][] arr1;
    public static int[] player;
    public static boolean[] isDead;

    public static void updateScore(int deadIdx) {
        for (int i = 0; i < N; i++) {
            if (!isDead[i]) {
                player[i] += arr1[deadIdx][i];
            }
        }
    }

    public static void revokeScore(int deadIdx) {
        for (int i = 0; i < N; i++) {
            if (!isDead[i]) {
                player[i] -= arr1[deadIdx][i];
            }
        }
    }

    public static void dfs(int start, int people) {

        if (isDead[eunjin] || people == 1) {
            answer = Math.max(answer, start);
            return;
        }

        if (people % 2 == 0) {
            // 밤, 마피아가 선택해서 죽임.
            for (int i = 0; i < N; i++) {
                if (!isDead[i] && i != eunjin) {
                    isDead[i] = true;
                    updateScore(i);
                    dfs(start + 1, people - 1);
                    revokeScore(i);
                    isDead[i] = false;
                }
            }

        } else {
            // 낯
            int max = 0;
            int idx = 0;
            for (int i = 0; i < N; i++) {
                if (!isDead[i] && max < player[i]) {
                    idx = i;
                    max = player[i];
                }
            }

            isDead[idx] = true;
            dfs(start, people - 1);
            isDead[idx] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N][N];
        player = new int[N];
        isDead = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            player[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        eunjin = Integer.parseInt(br.readLine());

        dfs(0, N);
        System.out.println(answer);
    }
}