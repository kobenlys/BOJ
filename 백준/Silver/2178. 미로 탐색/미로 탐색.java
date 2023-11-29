import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] arr1;
    public static boolean[][] vi;
    // queue  node객체 삽입
    public static Queue<node> qu = new LinkedList<>();
    public static int[] dx = {0,0,-1,1}; // 상하좌우 체크!
    public static int[] dy = {-1,1,0,0};


    // x, y 좌표를 저장하는 객체 , 활용하면 여러가지 데이터를 한 큐에 넣기 가능
    public static class node{ 
        int x;
        int y;
        public node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(int x, int y) {
        qu.offer(new node(x, y)); // x,y 좌표 qu에 넣기

        while (!qu.isEmpty()) {
            node nd = qu.poll(); // qu에서 꺼내서 node인스턴스에 넣기

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i]; //상 하 좌 우 갈 수 있는 경로 찾기
                int ny = nd.y + dy[i];
                
                // nx, ny 가 범위내에 있으며 한번도 안가보고(vi=false) 갈 수 있는 길 있으면
                if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                    if (!vi[ny][nx] && arr1[ny][nx] == 1) {
                        qu.offer(new node(nx, ny)); // nx, ny로 이동 = 큐에 넣기
                        vi[ny][nx] = true; // 방문 체크
                        arr1[ny][nx] = arr1[nd.y][nd.x] + 1; // 움직임 횟수 증가

                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException { //조건 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new int[N][M];
        vi = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++){
                arr1[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }

        vi[0][0] = true;

        bfs(0,0);
        System.out.println(arr1[N-1][M-1]);

    }

}
