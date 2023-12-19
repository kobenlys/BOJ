import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int max = 0;
        int[] arr1 = new int[10001];
        // 계수정렬 사용
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            max = Math.max(max, num);
            arr1[num] += 1;
        }

        for (int i = 1; i <= max; i++) {
            if (arr1[i] != 0) {
                for (int j = 0; j < arr1[i]; j++) {
                    sb.append(i).append("\n");
                }
            }
        }
        System.out.print(sb);
    }
}