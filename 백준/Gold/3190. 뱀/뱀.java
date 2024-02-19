import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, ans, front;
    public static boolean[][] arr1;
    public static Deque<node> dq = new ArrayDeque<>();
    public static HashMap<Integer,Character> map = new HashMap<>();

    public static class node {
        int x, y;
        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void snakeMove() { // 구현
        int[] dx = {1,0,-1,0};
        int[] dy = {0,-1,0,1};

        while (true) {
            ans++;
            // 머리를 기준으로 나아간다.
            node nd = dq.peekLast();

            int nx = nd.x + dx[front];
            int ny = nd.y + dy[front];
            // 종료조건 1. 뱀이 벽에 부딪힌 경우
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                System.out.println(ans);
                System.exit(0);
            }
            // 종료조건 2. 뱀이 자신과 부딪힌 경우
            for (node e : dq) {
                if (e.x == nx && e.y == ny) {
                    System.out.println(ans);
                    System.exit(0);
                }
            }
            // 사과를 먹었는지 안먹었는지 판단.
            if (arr1[ny][nx]) {
                //먹었다면 길이++, 사과제거
                arr1[ny][nx] = false;
                dq.offerLast(new node(nx, ny));
            } else {
                // 못먹었다면 꼬리--(이동할때 길어졌으니);
                dq.removeFirst();
                dq.offerLast(new node(nx, ny));
            }
            // 시간에 맞춰 방향 변경.
            if (map.containsKey(ans)) {
                char dir = map.get(ans);
                if(dir == 'L') front = (front+1)%4;
                if(dir == 'D') front = (front-1 +4)%4;
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new boolean[N][N];
        int K = Integer.parseInt(br.readLine());
        
        // 맵에 사과 표시.
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            arr1[y][x] = true;
        }

        // 초기 세팅, HashMap에 시간 + 방향 입력
        int L = Integer.parseInt(br.readLine());
        front = 0;
        dq.offer(new node(0, 0));
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            map.put(X, C);
        }
        snakeMove(); // 함수 호출
    }
}
