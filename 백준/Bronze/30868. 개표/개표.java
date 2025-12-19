import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int num = Integer.parseInt(br.readLine());
            int a = num / 5;
            int b = num % 5;
            for (int i = 0; i < a; i++) {
                sb.append("++++ ");
            }
            for (int i = 0; i < b; i++) {
                sb.append("|");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}