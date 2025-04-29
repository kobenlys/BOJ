import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        int T = Integer.parseInt(br.readLine());
        Map<Long, Integer> map = new HashMap<>();

        while (T-- > 0) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            boolean isOk = false;
            map.clear();

            for (int i = 0; i < N; i++) {
                long soilder = Long.parseLong(st.nextToken());
                map.put(soilder, map.getOrDefault(soilder, 0) + 1);

                if (N / 2 < map.get(soilder)) {
                    isOk = true;
                    sb.append(soilder).append("\n");
                    break;
                }
            }

            if (!isOk) {
                sb.append("SYJKGW").append("\n");
            }
        }

        System.out.println(sb);
    }
}