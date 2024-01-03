import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, cnt;
    public static int[][] arr1;
    public static boolean[][] vi;
    //상 하 좌 우
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    // BFS구현은 Queue , 이웃 수 기록은 ArrayList로
    public static Queue<node> qu = new LinkedList<>();
    public static ArrayList<Integer> res = new ArrayList<>();

    // 좌표를 담는 객체
    public static class node {
        int x;
        int y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void algorithm() { // BFS 알고리즘

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (arr1[i][j] == 1 && !vi[i][j]) { // 조건 통과 시 해당 좌표부터 BFS탐색
                    qu.offer(new node(j, i));
                    vi[i][j] = true;
                    cnt = 1;

                    while (!qu.isEmpty()) {
                        node nd = qu.poll();

                        // 기준 좌표에서 상 하 좌 우 탐색
                        for (int k = 0; k < 4; k++) {
                            int nx = nd.x + dx[k];
                            int ny = nd.y + dy[k];

                            // 조건 성립시 방문 체크(true)하고 cnt++ 및 qu 에 해당 좌표 담기
                            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                                if (arr1[ny][nx] == 1 && !vi[ny][nx]) {
                                    cnt++;
                                    qu.offer(new node(nx, ny));
                                    vi[ny][nx] = true;
                                }
                            }
                        }
                    }
                    res.add(cnt);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        arr1 = new int[N][N];
        vi = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }
        cnt = 0;
        algorithm();

        //ArrayList sort는 Collections 사용, 출력
        Collections.sort(res);
        sb.append(res.size()).append("\n");
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i)).append("\n");
        }
        System.out.print(sb);
    }
}
