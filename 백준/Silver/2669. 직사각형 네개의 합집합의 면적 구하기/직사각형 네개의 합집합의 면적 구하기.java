import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        boolean[][] arr1 = new boolean[100][100];
        int cnt = 0;

        for (int tc = 0; tc < 4; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken()) -1;
            int y1 = Integer.parseInt(st.nextToken()) -1;
            int x2 = Integer.parseInt(st.nextToken()) -1;
            int y2 = Integer.parseInt(st.nextToken()) -1;
            
            // 방문한 자리는 재방문 하지 않는다
            for (int i = y1; i < y2; i++) {
                for (int j = x1; j < x2; j++) {
                    if (!arr1[i][j]) {
                        arr1[i][j] = true;
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
