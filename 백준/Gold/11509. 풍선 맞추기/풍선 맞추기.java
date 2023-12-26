import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[1000002]; // 1000001 까지 탐색하기 때문에.
        int cnt = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            // 현재 입력값 +1 이 있는지 확인후 있다면 --.
            if (arr1[num + 1] > 0) {
                arr1[num + 1]--;
            }
            // 현재 입력값을 인덱스로 지정후 그 자리에 ++
            arr1[num]++;
        }
        // 값 출력
        for (int j : arr1) {
            cnt = j > 0 ? cnt + j : cnt;
        }
        System.out.println(cnt);
    }
}