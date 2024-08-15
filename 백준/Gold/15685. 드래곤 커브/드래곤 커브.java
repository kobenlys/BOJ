import java.io.*;
import java.util.*;

public class Main {
    public static boolean[][] arr1;
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, -1, 0, 1};

    // 드래곤 커브로 만들어진 맵 중 1x1 사각형의 네 꼭짓점이 모두 드래곤 커브에 포함된다면 카운트
    public static int findAnswer() {
        int ans = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 정사각형의 네 꼭짓점 드래곤커브에 포함되는지 판단.
                if (arr1[i][j] && arr1[i][j + 1] && arr1[i + 1][j] && arr1[i + 1][j + 1]) {
                    ans++;
                }
            }
        }
        return ans;
    }
    
    // 맵에 드래곤 커브 그리기.
    public static void dragonCurve(int x, int y, int d, int round) {

        ArrayList<Integer> list = new ArrayList<>();
        arr1[y][x] = true; // 초기 셋팅
        x = x + dx[d];
        y = y + dy[d];
        arr1[y][x] = true;

        list.add(d);

        while (round-- > 0) { // 라운드 만큼 커브 진행

            int nowSize = list.size();
            for (int i = nowSize - 1; i >= 0; i--) { // 가장 마지막에 입력된 방향부터 처리한다.
                int dir = list.get(i);
                
                // 조건1. 이미 그려온 경로의 90도 만큼 시계방향으로 회전
                dir = (dir+1) % 4;
                x = x + dx[dir];
                y = y + dy[dir];
                arr1[y][x] = true;
                list.add(dir); // 다음번 라운드에 사용할 수 있게 방향 저장
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr1 = new boolean[101][101];

        while (N-- > 0) {

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int round = Integer.parseInt(st.nextToken());

            dragonCurve(x, y, dir, round); // 드래곤커브 그리기
        }
        
        // 출력하기.
        int answer = findAnswer();
        System.out.println(answer);
    }
}