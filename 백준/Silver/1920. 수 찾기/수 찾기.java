import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        // 이진탐색 풀이

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        st = new StringTokenizer(br.readLine(), " ");

        // 배열에 값 입력
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        // 이진탐색 조건 -> 배열이 sort 되어야 한다.
        Arrays.sort(arr1);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < M; i++) {
            int flag = Integer.parseInt(st.nextToken());
            // binarySearch 메서드 사용하여 검색
            // 있다면 위치 return 없다면 -1 return
            if (Arrays.binarySearch(arr1, flag) >= 0) {
                sb.append(1).append("\n");
            }else{
                sb.append(0).append("\n");
            }
        }

        System.out.print(sb);
    }
}