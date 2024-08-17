import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static char[][] arr1;
    public static boolean[][] vi;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static Queue<node> qu = new ArrayDeque<>();

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void throwArrow(int height, boolean isLeft) {
        int y = N - height;

        if (isLeft) {
            for (int i = 0; i < M; i++) {

                if (arr1[y][i] == 'x') {
                    arr1[y][i] = '.';
                    for (int j = 0; j < 4; j++) {
                        vi = new boolean[N][M];
                        int nx = i + dx[j];
                        int ny = y + dy[j];
                        if (isRange(nx, ny) && arr1[ny][nx] =='x') {
                            vi[ny][nx] = true;
                            qu.offer(new node(nx, ny));
                            bfs();
                        }
                    }
                    break;
                }

            }

        }else{

            for (int i = M-1; i >= 0; i--) {

                if (arr1[y][i] == 'x') {
                    arr1[y][i] = '.';
                    for (int j = 0; j < 4; j++) {
                        vi = new boolean[N][M];
                        int nx = i + dx[j];
                        int ny = y + dy[j];
                        if (isRange(nx, ny) && arr1[ny][nx] =='x') {
                            vi[ny][nx] = true;
                            qu.offer(new node(nx, ny));
                            bfs();
                        }
                    }
                    break;
                }
            }
        }
    }

    public static void bfs(){
        boolean isPossible = true;

        while (!qu.isEmpty()) {

            node nd = qu.poll();
            if(nd.y == N-1){
                isPossible = false;
            }

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (isRange(nx, ny) && !vi[ny][nx] && arr1[ny][nx] == 'x') {
                    vi[ny][nx] = true;
                    qu.offer(new node(nx, ny));
                }
            }
        }

        if(isPossible) {
            dropStone();
        }
    }

    public static void dropStone(){

        int value = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (vi[i][j]) {
                    boolean isUpdate = false;
                    int cnt = 0;

                    for (int k = i+1; k < N; k++) {
                        if(arr1[k][j] == '.'){
                            isUpdate = true;
                            cnt++;
                        }else{
                            if(vi[k][j]){
                                isUpdate = false;
                            }
                            break;
                        }
                    }
                    if(isUpdate){
                        value = Math.min(value, cnt);
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] == 'x' && vi[i][j]) {
                    arr1[i][j] = '.';
                }
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (vi[i][j]) {
                    arr1[i + value][j] = 'x';
                }
            }
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new char[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = input.charAt(j);
            }
        }

        int round = Integer.parseInt(br.readLine());
        boolean isLeft = true;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < round; i++) {
            int height = Integer.parseInt(st.nextToken());
            throwArrow(height, isLeft);
            isLeft = !isLeft;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr1[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}