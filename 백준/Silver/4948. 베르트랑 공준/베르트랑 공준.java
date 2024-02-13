import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static boolean[] arr1 = new boolean[246_913];

    public static void E_Sieve() {
        for (int i = 2; i <= 246912; i++) {
            if (!arr1[i]) {
                for (int j = i + i; j <= 246912; j += i) {
                    arr1[j] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = 0;
        E_Sieve(); // 에라토스테네스의 체

        while ((N = Integer.parseInt(br.readLine())) != 0) {
            int cnt = 0;
            for (int i = N + 1; i <= N * 2; i++) {
                if (!arr1[i] && i != 1) {
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}