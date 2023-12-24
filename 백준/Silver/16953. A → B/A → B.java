import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int A, B;

    public static void algorithm(int start, long sum) {
        if (sum >= B) {
            if (sum == B) {
                System.out.println(start);
                System.exit(0);
            }
            return;
        }
        // 2를 곱하는 경우
        algorithm(start + 1, sum * 2);
        // 1을 수의 가장 오른쪽에 추가하는 경우
        long num = sum * 10 + 1; // sum 이 10억 일때 *10 해주면 정수형 범위 넘어간다.
        algorithm(start + 1, num);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        algorithm(1, A);
        System.out.println(-1); // 함수 탈출 시 -1 출력
    }
}