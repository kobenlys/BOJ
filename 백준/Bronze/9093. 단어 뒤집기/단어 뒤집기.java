import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");

            while (st.hasMoreTokens()) {
                String str = st.nextToken();
                for (int i = str.length()-1; i >=0; i--) {
                    sb.append(str.charAt(i));
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
