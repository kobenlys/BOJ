import java.io.*;
import java.util.*;

public class Main {

    public static int[] countDigit(int N) {
        int[] res = new int[10];
        int target = 1;

        while (N / target > 0) {
            int lower = N - (N / target) * target;
            int now = (N / target) % 10;
            int higher = N / (target * 10);

            // 1234이면서 현재 3번 자리라면
            //  현재자리에서는 0~9번이  12*현재자리(target)번 반복됨.
            for (int i = 0; i < 10; i++) {
                res[i] += higher * target;
            }

            // 1234이면서 현재 3번 자리라면
            // 현재자리는 적어도 0, 1, 2 자릿수만큼 반복됨.
            for (int i = 0; i < now; i++) {
                res[i] += target;
            }

            // 내 현재자리 3번은 아래 숫자 + 1번 반복됨
            // 12(3)4
            // 30, 31, 32, 33, 34 -> 5번 반복됨.
            // 즉 숫자 3은 5번 반복됨.
            res[now] += lower + 1;
            res[0] -= target; // 아까했던 연산중 숫자 0을 포함했지만 0으로 시작되는 수가없음 제거한다.
            target *= 10;
        }
        
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = countDigit(N);

        for (int i = 0; i < 10; i++) {
            System.out.print(arr1[i] + " ");
        }
    }
}