import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        HashMap<String, String> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            String num = String.valueOf(i);
            // key, value 값을 문자열로 교차해서 저장
            map.put(str, num);
            map.put(num, str);
        }

        for (int i = 0; i < M; i++) {
            String input = br.readLine();

            if (map.containsKey(input)) {
                sb.append(map.get(input)).append("\n");
            }
        }
        System.out.print(sb);
    }
}