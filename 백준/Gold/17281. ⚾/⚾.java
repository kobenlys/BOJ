import java.io.*;
import java.util.*;

public class Main {

    public static int answer;
    public static int[] player;
    public static boolean[] vi;
    public static int[][] caches;

    public static void dfs(int start) {

        if (start == 10) {
            playBaseBall();
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (!vi[i]) {
                vi[i] = true;
                player[i] = start;
                dfs(start + 1);
                vi[i] = false;
            }
        }
    }

    public static void playBaseBall() {

        boolean[] base = new boolean[4]; // index 1~3: 1루~3루
        int res = 0;
        int index = 1;

        for (int i = 0; i < caches.length; i++) {
            int outCount = 0;
            Arrays.fill(base, false); // 이닝 시작 시 베이스 초기화

            while (outCount < 3) {
                if (index == 10) {
                    index = 1;
                }
                int result = caches[i][player[index++]];

                if (result == 0) {
                    outCount++;
                } else {
                    // 주자 이동 처리 (3루부터 역순으로 이동)
                    for (int b = 3; b >= 1; b--) {
                        if (base[b]) {
                            if (b + result > 3) {
                                res++;
                            } else {
                                base[b + result] = true;
                            }
                            base[b] = false;
                        }
                    }

                    // 타자 이동
                    if (result >= 4) {
                        res++; // 홈런
                    } else {
                        base[result] = true;
                    }
                }
            }
        }

        answer = Math.max(answer, res);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        player = new int[10];
        vi = new boolean[10];
        caches = new int[N][10];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                caches[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        vi[4] = true;
        player[4] = 1;
        dfs(2);

        System.out.println(answer);
    }
}