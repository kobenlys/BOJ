import java.io.*;
import java.util.*;

public class Main {

    public static int[] countDigit(int N) {
        int[] res = new int[10];
        int cur = 1;

        // cur = 1;
        // 19
        // 19 - ( 19 / 1) * 1 -> 0
        // (19/1) % 10 -> 9
        // 19 / (cur * 10)  -> 1

        while (N / cur > 0) {

            int low = N - (N / cur) * cur; // 현재 자릿수 하위 숫자들
            int now = (N / cur) % 10; // 현재자리 수
            int high = N / (cur * 10); // 현재 자리의 상위 숫자들

            // 상위 숫자에 따라 발생 빈도 계산
            for (int i = 0; i < 10; i++) {
                res[i] += high * cur;
            }

            // 현재 자리수에 따라 발생 빈도 계산
            for (int i = 0; i < now; i++) {
                res[i] += cur;
            }

            res[now] += low + 1; // 현재 숫자에 대해 계산
            res[0] -= cur;
            cur *= 10;
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