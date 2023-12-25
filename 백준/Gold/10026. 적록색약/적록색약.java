import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static int N, cnt1,cnt2, blue=0;
    public static String[][] arr1;
    public static boolean[][] vi;
    // 상 하 좌 우 탐색 방향 설정.
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static Queue<node> qu = new LinkedList<>();
    
    // Queue에 좌표를 담기위한 객체 생성
    public static class node{
        int x;
        int y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void algorithm1() { // STEP 1. BFS 이용하여 적록색약 X 
        String flag = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!vi[i][j]) {

                    flag = arr1[i][j];
                    cnt1++;
                    qu.offer(new node(j, i));

                    if (flag.equals("B")) {
                        //적록색약인 경우 계산 할때 필요하다.
                        blue++;
                    }

                    while (!qu.isEmpty()) {

                        node nd = qu.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = nd.x + dx[k];
                            int ny = nd.y + dy[k];

                            if (range(nx, ny)) {

                                if (!vi[ny][nx] && arr1[ny][nx].equals(flag)) {

                                    qu.offer(new node(nx, ny));
                                    vi[ny][nx] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        // 다음 함수 호출 전 vi 초기화 
        vi = new boolean[N][N];
        algorithm2();
    }

    public static void algorithm2() { // STEP 1. BFS 이용하여 적록색약 O
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // blue 제외하고 빨강과 그린이 붙어있는 경우를 카운트 한다. 
                if ((!vi[i][j] && arr1[i][j].equals("G")) || (!vi[i][j] && arr1[i][j].equals("R"))) {

                    cnt2++;

                    qu.offer(new node(j, i));
                    while (!qu.isEmpty()) {

                        node nd = qu.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = nd.x + dx[k];
                            int ny = nd.y + dy[k];

                            if (range(nx, ny)) {
                                // blue 제외하고 빨강과 그린이 붙어있는 경우를 카운트 한다. 
                                if ((!vi[ny][nx] && arr1[ny][nx].equals("G")) || (!vi[ny][nx] && arr1[ny][nx].equals("R"))) {

                                    qu.offer(new node(nx, ny));
                                    vi[ny][nx] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }




    public static boolean range(int x, int y) { // 범위 체크.
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr1 = new String[N][N];
        vi = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++){
                arr1[i][j] = String.valueOf(str.charAt(j));

            }

        }

        algorithm1();
        // 출력 blue 구역 횟수 + 적록색약인 경우(빨강,녹색)합친 구역
        System.out.println(cnt1+" "+(blue+cnt2));

    }
}
