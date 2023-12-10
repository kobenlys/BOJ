import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());


        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            int cnt = 1;
            int res = 0;

            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == 'O') {
                    res += cnt++;
                }else{
                    cnt = 1;
                }
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
}