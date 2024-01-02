import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static String input;
    public static char[][] arr1;
    public static int maxX=52, maxY=52, minX=52, minY=52;

    public static void algorithm() { // 구현
        // 남, 서, 북, 동
        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};
        // 초기 좌표, 초기자리 표시
        int x = 52, y = 52;
        arr1[y][x] = '.';
        int dir = 0;

        for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) == 'R') { // 오른쪽 회전 남-> 서 -> 북 -> 동
                dir++;
                if (dir > 3) dir = 0;

            } else if (input.charAt(i) == 'L') { // 왼쪽 회전 남 -> 동 -> 북 -> 서
                dir--;
                if (dir < 0) dir = 3;

            } else if (input.charAt(i) == 'F') {
                // 해당 방향으로 전진, 좌표 저장
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                arr1[ny][nx] = '.';
                y = ny;
                x = nx;
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        input = br.readLine();
        arr1 = new char[104][104];

        for (int i = 0; i < 104; i++) {
            Arrays.fill(arr1[i], '#');
        }
        
        algorithm();

        for (int i = minY; i <= maxY; i++) {
            for (int j = minX; j <= maxX; j++) {
                sb.append(arr1[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}