import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException { // 값 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        // 음수까지 표현하기 위해 값을 N*2+1 로 설정한다.
        boolean[] arr1 = new boolean[2000001];
        int max = 0;
        int min = 0;

        // 계수정렬 사용하여 정렬
        for (int i = 0; i < N; i++) {
            int flag = Integer.parseInt(br.readLine());
            // 만약 flag = -1 이라면 999999번 자리에 true 해준다
            arr1[flag + 1000000] = true;
            // 전체 탐색보다 범위 설정하고 탐색하기 위해 최대,최소 값 구하기.
            max = Math.max(max, flag + 1000000);
            min = Math.min(min, flag + 1000000);
        }

        for (int i = min; i <= max; i++) {
            if (arr1[i]) {
                // 999999 - 1000000 = -1 처럼 음수를 저장할 수 있다.
                sb.append(i - 1000000).append("\n");
            }
        }
        System.out.print(sb);
    }
}