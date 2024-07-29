import java.io.*;
import java.util.*;
public class Main {
    static int R, C, day=0;
    static boolean[][] visited;
    static char[][] arr;
    static Queue<Node> waterQ, q;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        C = Integer.parseInt(s[0]);
        R = Integer.parseInt(s[1]);
        arr = new char[C][R];
        visited = new boolean[C][R];
        waterQ = new LinkedList<>();
        q = new LinkedList<>();
        ArrayList<Node> swan = new ArrayList<>();

        for (int i = 0; i < C; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < R; j++) {
                arr[i][j] = c[j];
                if (c[j] == 'L') {
                    swan.add(new Node(i, j));
                }
                if(c[j] != 'X') {
                    waterQ.add(new Node(i, j));
                }
            }
        }

        q.add(swan.get(0)); // 처음 백조 출발점.
        while(true) {
            if (findSwan(swan.get(1))) {
                System.out.println(day);
                break;
            }
            breakIce();
            day++;
        }

    }
    public static void breakIce() {
        int size = waterQ.size();
        for (int k = 0; k < size; k++) {
            Node n = waterQ.poll();
            for (int i = 0; i < 4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= C || ny >= R)
                    continue;
                if (arr[nx][ny] == 'X') {
                    arr[nx][ny] = '.';
                    waterQ.add(new Node(nx, ny));
                }
            }
        }

    }
    public static boolean findSwan(Node end) {
        Queue<Node> swanQ = new LinkedList<>();
        while(!q.isEmpty()) {
            Node n = q.poll();
            if (n.x == end.x && n.y == end.y) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= C || ny >= R || visited[nx][ny])
                    continue;
                visited[nx][ny] = true;
                if (arr[nx][ny] == 'X') {
                    swanQ.add(new Node(nx, ny));
                    continue; // 얼음 벽은 갱신용이기 때문에 굳이 큐(q)에 추가할 필요X.
                }
                q.add(new Node(nx, ny));
            }
        }
        q = swanQ; // 백조가 만난 얼음벽이 다음 날 출발지점.

        return false;
    }
}
class Node {
    int x, y;
    Node (int x, int y) {
        this.x = x;
        this.y = y;
    }
}