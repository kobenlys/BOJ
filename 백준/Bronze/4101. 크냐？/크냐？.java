import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            int T = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (T == 0 && B == 0) {
                break;
            } else if (T > B) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

    }
}
