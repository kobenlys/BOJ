import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        map.put("Y", 1);
        map.put("F", 2);
        map.put("O", 3);

        int times = map.get(st.nextToken());

        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        System.out.println(set.size() / times);
    }
}