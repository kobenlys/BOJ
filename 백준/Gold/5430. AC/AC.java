import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> dq = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tcase = 0; tcase < T; tcase++) {

            String input = br.readLine();

            int N = Integer.parseInt(br.readLine());
            String str = br.readLine();
            str = str.replace("[", "");
            str = str.replace("]", "");

            st = new StringTokenizer(str, ",");
            for (int i = 0; i < N; i++) {
                dq.add(Integer.parseInt(st.nextToken()));
            }

            boolean check = false;
            boolean isOk = false;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'R') {
                    check = !check;
                } else {
                    if (dq.isEmpty()) {
                        sb.append("error").append("\n");
                        isOk = true;
                        break;
                    }
                    if (!check) {
                        dq.removeFirst();
                    } else {
                        dq.removeLast();
                    }
                }
            }

            if (!isOk) {
                int len = dq.size();
                if (!check) {
                    sb.append("[");
                    for (int i = 0; i < len; i++) {
                        sb.append(dq.pollFirst());
                        if (i != len - 1) {
                            sb.append(",");
                        }
                    }
                    sb.append("]").append("\n");
                } else {
                    sb.append("[");
                    for (int i = len - 1; i >= 0; i--) {
                        sb.append(dq.pollLast());
                        if (i != 0) {
                            sb.append(",");
                        }
                    }
                    sb.append("]").append("\n");
                }
            }
            dq.clear();
        }
        System.out.print(sb);
    }
}