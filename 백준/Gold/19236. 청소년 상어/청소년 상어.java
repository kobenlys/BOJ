import java.io.*;
import java.util.*;

public class Main {
    public static int answer = 0;
    public static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    public static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static class node {
        int dir, value;

        public node(int dir, int value) {
            this.dir = dir;
            this.value = value;
        }
    }

    public static class tmpNode {
        node[][] tmpArr;
        int sX, sY, sDir, sum;

        public tmpNode(node[][] tmpArr, int sX, int sY, int sDir, int sum) {
            this.tmpArr = tmpArr;
            this.sX = sX;
            this.sY = sY;
            this.sDir = sDir;
            this.sum = sum;
        }
    }

    public static node[][] moveFish(node[][] tmp) {


        for (int i = 1; i <= 16; i++) {
            st:
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (tmp[j][k].value == i) {

                        for (int idx = 0; idx < 8; idx++) {
                            int nDir = (tmp[j][k].dir + idx) % 8;
                            int nx = k + dx[nDir];
                            int ny = j + dy[nDir];

                            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;

                            if (tmp[ny][nx].value != -1) {
                                int tmpV = tmp[j][k].value;
                                tmp[j][k] = new node(tmp[ny][nx].dir, tmp[ny][nx].value);
                                tmp[ny][nx] = new node(nDir, tmpV);
                                break st;
                            }
                        }

                    }
                }
            }
        }

        return tmp;
    }

    public static void bfs(node[][] start) {
        Queue<tmpNode> qu = new LinkedList<>();
        int val = start[0][0].value;
        int dir = start[0][0].dir;
        start[0][0] = new node(-1, -1);
        qu.offer(new tmpNode(deepCopy(start), 0, 0, dir, val));


        while (!qu.isEmpty()) {

            tmpNode now = qu.poll();
            node[][] tmp = moveFish(deepCopy(now.tmpArr));

            answer = Math.max(answer, now.sum);


            for (int i = 1; i < 4; i++) {
                int nx = now.sX + (dx[now.sDir] * i);
                int ny = now.sY + (dy[now.sDir] * i);

                if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;

                if (tmp[ny][nx].value != 0) {
                    int s = tmp[ny][nx].value;
                    int d = tmp[ny][nx].dir;
                    tmp[now.sY][now.sX] = new node(0, 0);
                    tmp[ny][nx] = new node(-1, -1);

                    qu.offer(new tmpNode(deepCopy(tmp), nx, ny, d, now.sum + s));

                    tmp[now.sY][now.sX] = new node(-1, -1);
                    tmp[ny][nx] = new node(d, s);
                }
            }
        }
    }

    public static node[][] deepCopy(node[][] origin) {
        node[][] copy = new node[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copy[i][j] = new node(origin[i][j].dir, origin[i][j].value);
            }
        }
        return copy;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        node[][] arr1 = new node[4][4];
        // 상어 -1, 빈공간 = 0;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int v = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) - 1;
                arr1[i][j] = new node(d, v);
            }
        }

        bfs(arr1);
        System.out.println(answer);
    }
}