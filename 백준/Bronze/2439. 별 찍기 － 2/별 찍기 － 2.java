import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        // StringBuilder를 이용한다.
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                sb1.append(" ");
            }
            sb2.append("*");
            System.out.print(sb1);
            System.out.println(sb2);
            sb1.setLength(0);
        }
    }
}
