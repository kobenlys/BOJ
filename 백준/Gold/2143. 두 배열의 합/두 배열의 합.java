import java.io.*;
import java.util.*;

public class Main {
    public static int T;
    public static int[] arr1, arr2;
    public static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        int N = Integer.parseInt(br.readLine());
        arr1 = new int[N + 1];
        Map<Long, Integer> map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken()) + arr1[i - 1];
        }

        int M = Integer.parseInt(br.readLine());
        arr2 = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            arr2[i] = Integer.parseInt(st.nextToken()) + arr2[i - 1];
        }

        for (int i = 0; i < M; i++) {
            for (int j = i+1; j <= M; j++) {
                long tmp = arr2[j] - arr2[i];
                map.put(tmp, map.getOrDefault(tmp, 0) + 1);
            }
        }
        
        long answer = 0;
        Collections.sort(list);
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j <= N; j++) {
                long targetNumber = arr1[j] - arr1[i];
                if(!map.containsKey(T - targetNumber)) continue;
                answer += map.get(T - targetNumber);
            }
        }

        System.out.println(answer);
    }
}