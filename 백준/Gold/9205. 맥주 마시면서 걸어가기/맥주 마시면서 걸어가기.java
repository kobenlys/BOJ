import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static ArrayList<node> arr1;
    public static boolean[][] vi;
    public static int N;

    public static class node {
        int x, y;
        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int Manhattan(node p1, node p2) { // 맨하탄 거리
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    public static void floydWarshall() {
        // 플로이드 워셜 알고리즘 -> 어딘가를 거쳐가야 한다면 효과적임
        for (int fw = 0; fw < N + 2; fw++) {
            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    if (vi[i][fw] && vi[fw][j]) {
                        vi[i][j] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            vi = new boolean[N + 2][N + 2];
            arr1 = new ArrayList<>();

            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr1.add(new node(x, y));
            }

            for (int i = 0; i < N + 2; i++) {
                for (int j = i + 1; j < N + 2; j++) {
                    // 20*50 = 1000미터 갈 수 있음 즉, 맨하탄거리가 1000 이하 노드
                    // 양 방향으로 체크 해준다.
                    if (Manhattan(arr1.get(i), arr1.get(j)) <= 1000) {
                        vi[i][j] = vi[j][i] = true;
                    }
                }
            }
            floydWarshall(); // 플로이드워셜 알고리즘 사용
            sb.append(vi[0][N + 1] ? "happy" : "sad").append("\n");
        }
        System.out.print(sb);
    }
}