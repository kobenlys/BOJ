import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static boolean[] arr1;

    public static void action(int human, int num) {

        if (human == 1) { // 남자인 경우
            for (int i = num; i < arr1.length; i += num) {
                arr1[i] = !arr1[i];
            }
        } else { // 여자인 경우
            // 투포인트 알고리즘
            int right = num + 1;
            int left = num - 1;
            arr1[num] = !arr1[num];
            
            // left, right 가 범위 안에 있으면서, 두 자리가 서로 같다면 진행
            while (left > 0 && right <= N && arr1[left] == arr1[right]) {

                arr1[left] = !arr1[left];
                arr1[right] = !arr1[right];
                right++;
                left--;
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new boolean[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());

            if (n == 1) {
                arr1[i] = true;
            }
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int human = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            action(human, num);
        }

        for (int i = 1; i <= N; i++) {
            if (arr1[i]) {
                sb.append(1).append(" ");
            } else {
                sb.append(0).append(" ");
            }
            // 20개씩 출력
            if (i % 20 == 0) {
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
