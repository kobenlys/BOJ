import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        if (m < 45) { // m 보다 45가 작다면
            // 시간
            h = h - 1;
            // 분
            m = 60- (45 - m);
            if (h < 0) { // 분 예외 처리 0시에서 23시가 될때
                h = 23;
            }
        }else{
            m = m - 45;
        }

        System.out.println(h + " " + m);
    }
}
