import java.io.*;
import java.util.*;

public class Main {
    public static int N, max, answer = Integer.MAX_VALUE;
    public static int[][] arr1;
    public static boolean[] vi;

    public static void dfs(int start, int cnt, int limit) {
        calcBalance();
        if (cnt == limit) {
            return;
        }

        for (int i = start; i < N; i++) {
            if (!vi[i]) {
                vi[i] = true;
                dfs(i + 1, cnt + 1, limit);
                vi[i] = false;
            }
        }
    }

    public static void calcBalance() {
        List<Integer> start = new ArrayList<>();
        List<Integer> link = new ArrayList<>();
        int startTeam = 0;
        int linkTeam = 0;

        for (int i = 0; i < N; i++) {
            if (vi[i]) {
                start.add(i);
            } else {
                link.add(i);
            }
        }

        for (int i = 0; i < start.size(); i++) {
            int pos1 = start.get(i);
            for (int j = i + 1; j < start.size(); j++) {
                int pos2 = start.get(j);
                startTeam += arr1[pos1][pos2] + arr1[pos2][pos1];
            }
        }

        for (int i = 0; i < link.size(); i++) {
            int pos1 = link.get(i);
            for (int j = i + 1; j < link.size(); j++) {
                int pos2 = link.get(j);
                linkTeam += arr1[pos1][pos2] + arr1[pos2][pos1];
            }
        }

        answer = Math.min(answer, Math.abs(startTeam - linkTeam));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        vi = new boolean[N];
        dfs(0, 0, N / 2 + 1);
        System.out.println(answer);
    }
}