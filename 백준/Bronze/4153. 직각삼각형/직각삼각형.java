import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { // 값 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] arr1 = new int[3];

        while (true) {

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < 3; i++) {
                arr1[i] = Integer.parseInt(st.nextToken());
            }
            // 입력되는 가장 큰 변이 c 가 되어야 한다.
            // 즉 이 문제는 입력데이터의 자리가 랜덤이다.
            Arrays.sort(arr1);

            if (arr1[0] == 0) {
                break;
            }
            // 피타고라스 정리 a^2+b^2 == c^2 -> 직각삼각형이다.
            System.out.println(arr1[0] * arr1[0] + arr1[1] * arr1[1] == arr1[2] * arr1[2] ? "right" : "wrong");
        }
    }
}