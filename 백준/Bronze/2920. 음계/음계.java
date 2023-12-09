import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        boolean isAsc = true;
        boolean isDes = true;
        int temp = 0;

        for (int i = 0; i < 8; i++) {
            int N = Integer.parseInt(st.nextToken());

            if (i != 0) { // 가장 처음값은 스킵한다
                if (isAsc && temp > N) { // 이전 값 보다 현재 값이 작다면 -> 오름차순 제외
                    isAsc = false;
                } else if (isDes && temp < N) { // 이전 값 보다 현재 값이 크다면 -> 내림차순 제외
                    isDes = false;
                }
            }

            temp = N;
        }

        if (isAsc && !isDes) {
            System.out.println("ascending");
        } else if (!isAsc && isDes) {
            System.out.println("descending");
        } else {
            // 오름차순, 내림차순 둘다 false 라면 Mixed
            System.out.println("mixed");
        }
    }
}


