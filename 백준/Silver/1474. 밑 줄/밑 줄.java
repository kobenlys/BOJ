import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] cache = new String[N];
        Map<Integer, Integer> map = new HashMap<>();
        int sizeCount = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            cache[i] = str;
            sizeCount += str.length();
            map.put(i, 0);
        }

        int temp = (M - sizeCount) / (N - 1);

        for (int i = 1; i < N; i++) {
            map.put(i, temp);
            sizeCount += temp;
        }

        for (int i = 1; i < N; i++) {
            if(sizeCount >= M) break;
            if (Character.isLowerCase(cache[i].charAt(0))) {
                map.put(i, map.get(i) + 1);
                sizeCount++;
            }
        }

        for (int i = N-1; i > 0; i--) {
            if(sizeCount >= M) break;
            if (!Character.isLowerCase(cache[i].charAt(0))) {
                map.put(i, map.get(i) + 1);
                sizeCount++;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < map.get(i); j++) {
                sb.append("_");
            }
            sb.append(cache[i]);
        }

        System.out.println(sb);
    }
}