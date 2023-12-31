import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> dq = new ArrayDeque<>(); // deque 이용해야한다.
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());

        for (int tcase = 0; tcase < T; tcase++) {

            String input = br.readLine();

            int N = Integer.parseInt(br.readLine());
            // 숫자만 입력받기 위해
            String str = br.readLine();
            str = str.replace("[", "");
            str = str.replace("]", "");

            st = new StringTokenizer(str, ",");
            for (int i = 0; i < N; i++) {
                dq.add(Integer.parseInt(st.nextToken()));
            }

            boolean check = false; // 배열을 실제로 뒤집을 필요는 없다, 시간초과남
            boolean isOk = false; // 에러 판단
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'R') {
                    check = !check;
                } else {
                    if (dq.isEmpty()) { // 에러 출력
                        sb.append("error").append("\n");
                        isOk = true;
                        break;
                    }
                    if (!check) { // 배열 순서에 따른 요소 삭제
                        // 정방향 경우
                        dq.removeFirst();
                    } else {
                        // 역방향 경우
                        dq.removeLast();
                    }
                }
            }

            if (!isOk) { // 에러가 안났다면
                int len = dq.size();
                if (!check) {
                    // 정방향 출력
                    sb.append("[");
                    for (int i = 0; i < len; i++) {
                        sb.append(dq.pollFirst());
                        if (i != len - 1) {
                            sb.append(",");
                        }
                    }
                    sb.append("]").append("\n");
                } else {
                    // 역방향 출력
                    sb.append("[");
                    for (int i = 0; i < len; i++) {
                        sb.append(dq.pollLast());
                        if (i != len - 1) {
                            sb.append(",");
                        }
                    }
                    sb.append("]").append("\n");
                }
            }
            dq.clear(); // 초기화
        }
        System.out.print(sb);
    }
}
