import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken()) - 1;

        int[] dist = new int[N];
        int[] fuel = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fuel[i] = Integer.parseInt(st.nextToken());
        }

        long rangeMax = dist[S] + fuel[S];
        long rangeMin = dist[S] - fuel[S];
        int right = S + 1;
        int left = S - 1;

        while (true) {

            boolean isUpdate = false;

            for (; right < N; right++) {
                if (dist[right] <= rangeMax) {
                    rangeMax = Math.max(rangeMax, dist[right] + fuel[right]);
                    rangeMin = Math.min(rangeMin, dist[right] - fuel[right]);
                }else{
                    break;
                }
            }

            for (; left >= 0; left--) {
                if (dist[left] >= rangeMin) {
                    rangeMax = Math.max(rangeMax, dist[left] + fuel[left]);
                    rangeMin = Math.min(rangeMin, dist[left] - fuel[left]);
                    isUpdate = true;
                }else{
                    break;
                }
            }

            if(!isUpdate) break;
        }


        for (int i = 0; i < N; i++) {
            if (rangeMin <= dist[i] && rangeMax >= dist[i]) {
                sb.append(i + 1).append(" ");
            }
        }
        System.out.println(sb);
    }
}