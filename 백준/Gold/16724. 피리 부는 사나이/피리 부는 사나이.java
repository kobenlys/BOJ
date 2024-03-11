import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, cntSafeZone;
    public static char[][] arr1;
    public static int[][] vi;
    public static Queue<node> trace = new LinkedList<>();


    public static class node {
        int x, y;
        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void checkTrace(int flag) {
        // 현재 이동경로집합을 원래 존재했던 경로집합에 포함시킨다.
        while (!trace.isEmpty()) {
            node nd = trace.poll();
            vi[nd.y][nd.x] = flag;
        }
    }

    public static void findDeadLock() {
        // RL 교착상태 찾고 cntSafeZone의 숫자로 집합표시
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M - 1; j++) {
                if (arr1[i][j] == 'R') {
                    if (arr1[i][j + 1] == 'L') {
                        vi[i][j] = vi[i][j + 1] = ++cntSafeZone;
                    }
                }
            }
        }
        // DU 교착상태 찾고 cntSafeZone의 숫자로 집합표시
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] == 'U') {
                    if (arr1[i - 1][j] == 'D') {
                        vi[i][j] = vi[i - 1][j] = ++cntSafeZone;
                    }
                }
            }
        }
    }

    public static void findCircle() {
        Queue<node> qu = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (vi[i][j] == 0) {
                    trace.clear();
                    trace.offer(new node(j, i));
                    qu.offer(new node(j, i));
                    vi[i][j] = ++cntSafeZone;

                    while (!qu.isEmpty()) {

                        node nd = qu.poll();
                        char nowDir = arr1[nd.y][nd.x];
                        int dx = 0, dy = 0;
                        // 방향전환
                        switch (nowDir) {
                            case 'U':
                                dx = nd.x;
                                dy = nd.y - 1;
                                break;
                            case 'D':
                                dx = nd.x;
                                dy = nd.y + 1;
                                break;
                            case 'R':
                                dx = nd.x + 1;
                                dy = nd.y;
                                break;
                            case 'L':
                                dx = nd.x - 1;
                                dy = nd.y;
                                break;
                        }
                        // 경로상 아직 어느집합에도 포함되지 않는 경로
                        // cntSafeZone숫자로 집합 포함관계 표시.
                        if (vi[dy][dx] == 0) {
                            vi[dy][dx] = cntSafeZone;
                            qu.offer(new node(dx, dy)); // 진행
                            trace.offer(new node(dx, dy)); // 지나온 경로 저장
                        } else {
                            // 다른 집합에 포함된 경로 발견시
                            // 자기 자신이 아니라면
                            if (vi[dy][dx] != cntSafeZone) {
                                // 다른경로집합에 현재경로집합 포함하기
                                checkTrace(vi[dy][dx]);
                                cntSafeZone--;
                            }
                        }
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
        arr1 = new char[N][M];
        vi = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j);
            }
        }
        // cntSafeZone의 역할 -> 서로다른 경로집합끼리 구분
        // 결국 집합의 수 만큼 SafeZone이 결정됨
                        //               D
        findDeadLock(); // 교착상태 -> RL ,U 경우 cnt, 경로 숫자표시 
        findCircle(); // 큰 사이클에 포함되는 집합은 같은 숫자로 표시
        System.out.println(cntSafeZone);
    }
}