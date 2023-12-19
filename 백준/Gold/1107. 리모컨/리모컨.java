import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, min, max;
    public static ArrayList<String> vi = new ArrayList<>();

    public static void algorithm() { // 구현
        min = N;
        max = N;

        while (true) { // N 보다 크면서 숫자버튼으로 만들 수 있는 최솟값
            String tmp = String.valueOf(min);
            boolean check1 = false;
            for (int i = 0; i < vi.size(); i++) {
                if (tmp.contains(vi.get(i))) {
                    check1 = true;
                    break;
                }
            }
            if (!check1) {
                break;
            } else {
                min++;
            }
            if (min > 1000000) {
                break;
            }
        }

        while (true) { // N 보다 작으면서 숫자버튼으로 만들 수 있는 최댓값
            String tmp = String.valueOf(max);
            boolean check2 = false;
            for (int i = 0; i < vi.size(); i++) {
                if (tmp.contains(vi.get(i))) {
                    check2 = true;
                    break;
                }
            }
            if (!check2) {
                break;
            } else {
                max--;
                if (max < 0) {
                    max = min;
                    break;
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        if (M != 0) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < M; i++) {
                String v = st.nextToken();
                vi.add(v); // 고장난 버튼 표시
            }
        }

        if (N == 100) { // 처음 채널이 100번임, 움직일 필요 x
            System.out.println(0);
            System.exit(0);
        } else if (M == 10) { // 모든 숫자 버튼이 고장났을때, "+,-" 버튼만 사용
            System.out.println(Math.abs(N - 100));
            System.exit(0);
        }
        algorithm();

        // 숫자 버튼 누른 수
        int len1 = String.valueOf(max).length();
        int len2 = String.valueOf(min).length();
        // +,- 버튼 누른 수 + 숫자 버튼 누른 수
        int res = Math.min(Math.abs(N - max) + len1, Math.abs(N - min) + len2);
        // +, - 만 누른 수, +,- 버튼 누른 수 + 숫자 버튼 누른 수 비교
        // N = 101 일 경우 예외 처리임.
        int ans = Math.min(Math.abs(N - 100), res);
        System.out.println(ans);
    }
}