import java.io.*;
import java.util.*;

public class Main {
    public static int max;
    public static int x1, x2, y1, y2;

    public static void makeSpiralArray(int[][] arr1) {

        int num = 1;
        int cnt = 0;
        int dir = 0;
        int size = 1;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};
        int x = 0;
        int y = 0;

        while (true) {


            if(arr1[0][0] != 0 && arr1[0][x2 - x1] != 0 &&
                    arr1[y2 - y1][0] != 0 && arr1[y2 - y1][x2 - x1] != 0){
                break;
            }

            if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
                arr1[y - y1][x - x1] = num;
            }

            num++;
            cnt++;
            x += dx[dir];
            y += dy[dir];

            if (cnt == size) {
                cnt = 0;
                if (dir == 1 || dir == 3) {
                    size++;
                }
                dir = (dir + 1) % 4;
            }
        }
        max = num - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        y1 = Integer.parseInt(st.nextToken());
        x1 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());
        x2 = Integer.parseInt(st.nextToken());

        int[][] arr1 = new int[y2 - y1 + 1][x2 - x1 + 1];
        makeSpiralArray(arr1);

        max = (int) Math.log10(max);

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {

                int len = (int) Math.log10(arr1[i][j]);
                len = max - len;
                for (int k = 0; k < len; k++) {
                    sb.append(" ");
                }
                sb.append(arr1[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}