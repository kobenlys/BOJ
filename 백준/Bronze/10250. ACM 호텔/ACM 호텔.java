import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tcase = Integer.parseInt(br.readLine());
        for (int i = 0; i < tcase; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x, y;

            // N번째 손님의 방 번호 구하기
            if (N % H > 0) {
                x = N / H + 1;
                y = N % H;
            } else {
                x = N / H;
                y = H;
            }
            // x가 1의자리 수 라면 0을 붙여서 출력해야 한다.
            if (String.valueOf(x).length() == 1) {
                sb.append(y).append("0").append(x).append("\n");
            }else{
                sb.append(y).append(x).append("\n");
            }
        }
        System.out.print(sb);
    }
}