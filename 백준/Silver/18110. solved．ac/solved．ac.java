import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int sum = 0;
        int[] arr1 = new int[N];

        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr1);
        // 배열의 길이의 비례한 절삭평균 요소갯수
        int per = (int) Math.round(N * 0.15);
        // 배열의 앞,뒤 를 per 만큼 절삭 후 sum 구하기
        for (int i = per; i < N - per; i++) {
            sum += arr1[i];
        }
        System.out.println(Math.round((float) sum / (N - per * 2)));
    }
}