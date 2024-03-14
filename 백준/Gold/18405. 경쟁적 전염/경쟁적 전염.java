import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, K;
    public static int[][] arr1;
    public static PriorityQueue<node> pq = new PriorityQueue<>();

    public static class node implements Comparable<node> {
        int x, y, virus;

        public node(int x, int y, int virus) {
            this.x = x;
            this.y = y;
            this.virus = virus;
        }
        // 숫자가 낮은 바이러스 부터 증식한다.
        @Override
        public int compareTo(node o) {
            return virus - o.virus;
        }
    }

    public static void spreadVirus() {
        Stack<node> stk = new Stack<>();
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!pq.isEmpty()) {

            node nd = pq.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (arr1[ny][nx] == 0) {
                    arr1[ny][nx] = nd.virus;
                    stk.push(new node(nx, ny, nd.virus)); // 증식된 바이러스 위치, 정보를 담는다
                }
            }
        }
        // 우선순위 큐 업데이트 해준다.
        pq = new PriorityQueue<>(stk);
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr1 = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                pq.offer(new node(j, i, arr1[i][j])); // 우선순위 큐 이용한 풀이법
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int S = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken()) - 1;
        int X = Integer.parseInt(st.nextToken()) - 1;

        // S초 만큼 실행
        while (S-- > 0) {
            if (!pq.isEmpty()) {
                spreadVirus();
            } else {
                break;
            }
        }

        System.out.println(arr1[Y][X]);
    }
}