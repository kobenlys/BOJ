import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr1 = new int[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            arr1[i] = i + 1;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken())-1; // 시작
            int b = Integer.parseInt(st.nextToken())-1; // 목표
            // arr1배열 의 특정 구간 temp에 입력
            for (int j = a; j <= b; j++) {
                temp[j] = arr1[j];
            }
            // arr1에 temp를 역순으로 입력
            for (int j = b, k = a; j >= a ; j--, k++) {
                arr1[k] = temp[j];
            }
        }

        for (int e : arr1) {
            sb.append(e).append(" ");
        }
        System.out.println(sb);
    }
}
