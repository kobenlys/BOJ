import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int Y, X, cnt;
    public static char[][] arr1;
    public static HashSet<Character> key;

    public static class node {
        int x, y;
        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(int x, int y) {
        Queue<node> qu = new LinkedList<>();
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        boolean[][] vi = new boolean[Y + 2][X + 2];
        qu.offer(new node(x, y));

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= X + 2 || ny >= Y + 2) continue;

                char k = arr1[ny][nx];
                // 열쇠를 발견시
                if (k != '.' && Character.isLowerCase(k)) {
                    // key에 해당 키 입력 후 방문배열 초기화. -> 열 수 있는 문이 갱신되었기 때문.
                    key.add(Character.toUpperCase(k));
                    arr1[ny][nx] = '.'; // 배열에 키 없애기.
                    qu.clear();
                    vi = new boolean[Y + 2][X + 2];
                }

                if (!vi[ny][nx]) {
                    // 빈공간 일때
                    if (arr1[ny][nx] == '.') {
                        vi[ny][nx] = true;
                        qu.offer(new node(nx, ny));
                    } else {
                        // 문서가 있을때 또는 문이 있을때
                        // 문을 열수 있거나 or 문서가있는경우 수행한다.
                        if (arr1[ny][nx] == '$' || key.contains(arr1[ny][nx])) {
                            if (arr1[ny][nx] == '$') cnt++;

                            vi[ny][nx] = true;
                            arr1[ny][nx] = '.';
                            qu.offer(new node(nx, ny));
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        // 입출력
        while (T-- > 0) {
            key = new HashSet<>(); // 가지고 있는 키 입력
            cnt = 0;

            st = new StringTokenizer(br.readLine(), " ");
            Y = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            arr1 = new char[Y + 2][X + 2]; // 빌딩 안팎으로 이동가능.
            // 빌딩 바깥쪽 설정.
            for (int i = 0; i < Y + 2; i++) {
                Arrays.fill(arr1[i], '.');
            }

            for (int i = 1; i <= Y; i++) {
                String input = br.readLine();
                for (int j = 1; j <= X; j++) {
                    arr1[i][j] = input.charAt(j - 1);
                }
            }

            String keys = br.readLine(); // 가지고 있는 키 입력

            if (!(keys.equals("0"))) {
                for (int i = 0; i < keys.length(); i++) {
                    key.add(Character.toUpperCase(keys.charAt(i)));
                }
            }

            bfs(0, 0);
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}