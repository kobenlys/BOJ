import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static HashMap<Long, Integer> map = new HashMap<>();

    public static void bfs(long input) {
        Queue<Long> qu = new LinkedList<>();
        map.put(input, 0);
        qu.offer(input);

        while (!qu.isEmpty()) {

            long now = qu.poll();
            long res;

            if (now % 3 == 0) {
                res = now / 3;
                if(res <= 0) continue;
                if (!map.containsKey(res)) {
                    qu.offer(res);
                    map.put(res, map.get(now) + 1);
                }
            }

            if (now % 2 == 0) {
                res = now / 2;
                if(res <= 0) continue;
                if (!map.containsKey(res)) {
                    qu.offer(res);
                    map.put(res, map.get(now) + 1);
                }
            }

            res = now -1;
            if(res <= 0) continue;

            if (!map.containsKey(res)) {
                qu.offer(res);
                map.put(res, map.get(now) + 1);
            }

            if (map.containsKey((long) 1)) {
                System.out.print(map.get((long) 1));
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long N = Long.parseLong(br.readLine());

        // 52452 -> 17484 -> 5828 -> 2914 -> 1457 -> 1456 -> 728 -> 364 -> 182 -> 91 -> 90
        // -> 30 -> 10 -> 9 -> 3 -> 1
        bfs(N);
        System.out.println(map.get((long) 1));

    }
}