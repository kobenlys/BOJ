import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, ans, front;
    public static ArrayList<node> set = new ArrayList<>();
    public static Deque<node> dq = new ArrayDeque<>();
    public static HashMap<Integer,Character> map = new HashMap<>();

    public static class node {
        int x, y;
        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false;
            node other = (node) obj;
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void snakeMove() {

        int[] dx = {1,0,-1,0};
        int[] dy = {0,-1,0,1};

        while (true) {
            ans++;
            node nd = dq.peekLast();

            int nx = nd.x + dx[front];
            int ny = nd.y + dy[front];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                System.out.println(ans);
                System.exit(0);
            }

            if (dq.contains(new node(nx, ny))) {
                System.out.println(ans);
                System.exit(0);
            }

            if (set.contains(new node(nx, ny))) {

                set.remove(new node(nx, ny));
                dq.offerLast(new node(nx, ny));
            } else {
                dq.removeFirst();
                dq.offerLast(new node(nx, ny));
            }

            //System.out.println(ny+" "+nx+" "+dq.size());

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
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            set.add(new node(x, y));
        }


        int L = Integer.parseInt(br.readLine());
        front = 0;
        dq.offer(new node(0, 0));
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            map.put(X, C);
        }
        snakeMove();
    }
}
